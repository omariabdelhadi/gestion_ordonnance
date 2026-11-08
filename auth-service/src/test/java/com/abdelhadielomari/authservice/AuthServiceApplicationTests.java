package com.abdelhadielomari.authservice;
import static org.mockito.Mockito.*;

import com.abdelhadielomari.authservice.controller.AuthController;
import com.abdelhadielomari.authservice.entities.AppUser;
import com.abdelhadielomari.authservice.repositories.AppUserRepository;
import com.abdelhadielomari.authservice.security.AppUserDetailsService;

import com.nimbusds.jwt.JWT;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceApplicationTests {

    @Mock
    private AppUserRepository repo;
    @Mock
    private AppUserRepository appUserRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtEncoder jwtEncoder;
    @Mock
    private PasswordEncoder passwordEncoder;


    @InjectMocks
    private AppUserDetailsService appUserDetailsService;
    @InjectMocks
    private AuthController authController;

    @Test
    void cas_user_exist(){
        AppUser u=new AppUser();
        String username="abdo";
        String password="123123";
        Set<String> roles=new HashSet<>();
        roles.add("MEDCIN");
        u.setUsername(username);
        u.setPassword(password);
        u.setRoles(roles);

        when(repo.findByUsername(username)).thenReturn(Optional.of(u));
        UserDetails userDetails=appUserDetailsService.loadUserByUsername(username);
        var authorities=userDetails.getAuthorities();

        assertNotNull(userDetails);
        assertEquals(username,userDetails.getUsername());
        assertEquals(password,userDetails.getPassword());
        assertEquals(1,authorities.size());
        assertTrue(authorities.stream().anyMatch(auth->auth.getAuthority().equals("ROLE_MEDCIN")));

        verify(repo,times(1)).findByUsername(any());

    }
    @Test
    void cas_user_nexist_pas(){
        AppUser u=new AppUser();
        String username="emmm";
        u.setUsername(username);

        when(repo.findByUsername(username)).thenReturn(Optional.empty());

        UsernameNotFoundException exception=assertThrows(UsernameNotFoundException.class,()->{
            appUserDetailsService.loadUserByUsername(username);
        });

        assertTrue(exception.getMessage().contains(username));
        verify(repo,times(1)).findByUsername(any());
    }
    @Test
    void inscription_reussie(){

        String username="abdo";
        String password="123123";

        AppUser user=new AppUser();
        user.setUsername(username);
        user.setUsername(password);

        AuthController.InscriptionRequest inscriptionRequest=new AuthController.InscriptionRequest(username,password);

        when(appUserRepository.findByUsername(username)).thenReturn(Optional.empty());

        ResponseEntity<String> responseEntity=authController.inscription(inscriptionRequest);

        assertEquals("l'inscription fait avec succée",responseEntity.getBody());
        verify(appUserRepository,times(1)).findByUsername(username);
        verify(appUserRepository,times(1)).save(any());
    }
    @Test
    void inscription_pas_reussie(){
        String username="emmmm";
        String password="123123";

        AppUser user=new AppUser();
        user.setUsername(username);
        user.setUsername(password);

        AuthController.InscriptionRequest inscriptionRequest=new AuthController.InscriptionRequest(username,password);

        when(appUserRepository.findByUsername(username)).thenReturn(Optional.of(user));
        ResponseEntity<String> responseEntity=authController.inscription(inscriptionRequest);

        assertEquals("cette user est deja exist",responseEntity.getBody());

        verify(appUserRepository,times(1)).findByUsername(username);
        verify(appUserRepository,never()).save(any());
    }
    @Test
    void login_rieussie(){

        String username="abdo";
        String password="123123";
        AuthController.LoginRequest loginRequest=new AuthController.LoginRequest(username,password);


        Authentication auth=mock(Authentication.class);
        when(auth.getName()).thenReturn(username);

        GrantedAuthority authority=mock(GrantedAuthority.class);
        when(authority.getAuthority()).thenReturn("ROL_MEDCIN");
        doReturn(List.of(authority)).when(auth).getAuthorities();


        when(authenticationManager.authenticate(any())).thenReturn(auth);

        Jwt jwt=mock(Jwt.class);
        when(jwtEncoder.encode(any())).thenReturn(jwt);
        when(jwt.getTokenValue()).thenReturn("faux-tocken");

        Map<String,String> reponse=authController.login(loginRequest);

        assertNotNull(reponse);
        assertEquals("faux-tocken",reponse.get("token"));
    }
    @Test
    void login_pas_rieussie(){
        String username="emmm";
        String password="123123";
        AuthController.LoginRequest loginRequest=new AuthController.LoginRequest(username,password);

        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("mauvais password"));


        ResponseStatusException exception=assertThrows(ResponseStatusException.class,()->{
            authController.login(loginRequest);
        });

        assertEquals(HttpStatus.UNAUTHORIZED,exception.getStatusCode());
    }
}

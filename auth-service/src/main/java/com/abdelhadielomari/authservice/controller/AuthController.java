package com.abdelhadielomari.authservice.controller;

import com.abdelhadielomari.authservice.entities.AppUser;
import com.abdelhadielomari.authservice.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    // Hs
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository appUserRepository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;   // org.springframework.security.crypto.password.PasswordEncoder

    public record LoginRequest(String username, String password) {}
    public record InscriptionRequest(String username, String password) {}
    @PostMapping("/inscription")
    public ResponseEntity<String> inscription(@RequestBody InscriptionRequest request){
        AppUser user=appUserRepository.findByUsername(request.username).orElse(null);

        if (user!=null){
            return ResponseEntity.badRequest().body("cette user est deja exist");
        }

        AppUser newUser=new AppUser();
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setUsername(request.username());
        newUser.getRoles().add("ROLE_MEDECIN");
        appUserRepository.save(newUser);
        return ResponseEntity.ok("l'inscription fait avec succée");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides");
        }

        List<String> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(auth.getName())
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .claim("roles", roles)
                .build();
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
        return Map.of("token", token);
    }
}

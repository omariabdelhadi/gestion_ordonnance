import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {Auth} from '../services/auth';


export const authGuard: CanActivateFn = (route, state) => {
  const router=inject(Router)
  const authoservice=inject(Auth)
  const isLogin=authoservice.isLoggedIn()
  if (isLogin){
    return true;
  }
  else {
    console.log("Accès refusé,redirection vers login");
    router.navigate(['/login']);
    return false;
  }
};

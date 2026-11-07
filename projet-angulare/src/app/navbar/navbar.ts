import { Component } from '@angular/core';
import {Router, RouterLink, RouterOutlet} from "@angular/router";
import {Auth} from '../services/auth';

@Component({
  selector: 'app-navbar',
    imports: [
        RouterLink,
        RouterOutlet
    ],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {
  constructor(private authservice:Auth,private router:Router) {
  }
  out(){
    this.authservice.logout();
    this.router.navigate(['/login']);
  }
}

import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from "@angular/router";
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
  constructor(public authservice:Auth) {
  }
}

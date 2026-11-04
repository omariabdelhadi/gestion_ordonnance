import { Component, signal } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {Navbar} from './navbar/navbar';
import {Auth} from './services/auth';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, Navbar],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {


  protected readonly title = signal('angular-front-end');
  constructor(public authservice:Auth) {
  }

}

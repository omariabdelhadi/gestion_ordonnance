import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgIf } from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import { Auth } from '../services/auth';

@Component({
  selector: 'app-inscription',
  imports: [ReactiveFormsModule, NgIf, RouterLink],
  templateUrl: './inscription.html',
  styleUrl: './inscription.css',
})
export class Inscription implements OnInit {
  inscriptionForm!: FormGroup;
  errorMessage = '';
  successMessage = '';

  constructor(
    private fb: FormBuilder,
    private authservice: Auth,
    private router: Router
  ) {}

  ngOnInit() {
    this.inscriptionForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  onSubmit() {
    if (this.inscriptionForm.invalid) {
      this.inscriptionForm.markAllAsTouched();
      return;
    }

    const { username, password } = this.inscriptionForm.value;

    this.authservice.inscription(username, password).subscribe({
      next: () => {
        this.errorMessage = '';
        this.successMessage = 'Inscription réussie, redirection...';
        setTimeout(() => this.router.navigate(['/login']), 1000);
      },
      error: () => {
        this.successMessage = '';
        this.errorMessage = 'Inscription impossible (nom d\'utilisateur déjà utilisé ?)';
      },
    });
  }
}

import { Injectable,signal  } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private readonly baseUrl = 'http://localhost:8886/AUTH-SERVICE/auth';
  private readonly TOKEN_KEY = 'token';

  public isLoggedInSignal=signal<boolean>(this.isLoggedIn())

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<{ token: string }> {
    return this.http
      .post<{ token: string }>(`${this.baseUrl}/login`, { username, password })
      .pipe(tap(res => {
        localStorage.setItem(this.TOKEN_KEY, res.token);
        this.isLoggedInSignal.set(true); // 3. Ajoute cette ligne
      }));
  }

  inscription(username: string, password: string): Observable<string> {
    // le backend renvoie du texte, pas du JSON
    return this.http.post(`${this.baseUrl}/inscription`, { username, password },
      { responseType: 'text' });
  }

  logout() {
    localStorage.removeItem(this.TOKEN_KEY);
    this.isLoggedInSignal.set(false)
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  private getPayload(): any | null {
    const token = this.getToken();
    if (!token) return null;
    try {
      const base64 = token.split('.')[1].replace(/-/g, '+').replace(/_/g, '/');
      return JSON.parse(atob(base64));
    } catch {
      return null;
    }
  }

  isLoggedIn(): boolean {
    const payload = this.getPayload();
    return !!payload && payload.exp * 1000 > Date.now();
  }

  getRoles(): string[] {
    return this.getPayload()?.roles ?? [];
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from '../model/Patient.model';

@Injectable({
  providedIn: 'root',
})
export class PatientS {
  private baseUrl = "http://localhost:8886/PATIENT-SERVICE";

  constructor(private http: HttpClient) {}

  public getAllPatient(): Observable<any> {
    // On récupère le token directement
    const token = localStorage.getItem('token');

    // On crée les headers
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });

    // On envoie la requête avec les headers
    return this.http.get<any>(`${this.baseUrl}/patients`, { headers: headers });
  }
}

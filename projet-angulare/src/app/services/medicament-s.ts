import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Medicament} from '../model/Medicament.model';

@Injectable({
  providedIn: 'root',
})
export class MedicamentS {
  private baseUrl = "http://localhost:8886/MEDICAMENT-SERVICE";
  constructor(private Http:HttpClient) {}

  public getMedicaments():Observable<any>{
    const token=localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    return this.Http.get<any>("http://localhost:8886/MEDICAMENT-SERVICE/medicaments",{ headers: headers });
  }
  public AddMedicament(nouvelleMedicament:Medicament):Observable<Medicament>{
    const token=localStorage.getItem('token');
    const headers=new HttpHeaders({
      'Authorization': 'Bearer ' + token
    })
    return this.Http.post<Medicament>("http://localhost:8082/AjouterMedicament",nouvelleMedicament,{ headers: headers });
  }
}

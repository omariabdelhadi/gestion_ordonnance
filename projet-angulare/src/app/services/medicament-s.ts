import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Medicament} from '../model/Medicament.model';

@Injectable({
  providedIn: 'root',
})
export class MedicamentS {
  constructor(private Http:HttpClient) {}

  public getMedicaments():Observable<Array<Medicament>>{
    return this.Http.get<Array<Medicament>>("http://localhost:8886/MEDICAMENT-SERVICE/medicaments")
  }
  public AddMedicament(nouvelleMedicament:Medicament):Observable<Medicament>{
    return this.Http.post<Medicament>("http://localhost:8082/AjouterMedicament",nouvelleMedicament)
  }
}

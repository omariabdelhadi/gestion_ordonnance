import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Patient} from '../model/Patient.model';

@Injectable({
  providedIn: 'root',
})
export class PatientS {
  constructor(private Http:HttpClient) {}

  public getAllPatient():Observable<Array<Patient>>{
    return this.Http.get<Array<Patient>>("http://localhost:8081/patients")
  }
  public AddPatient(nouvellePatient:Patient):Observable<Patient>{
    return this.Http.post<Patient>("http://localhost:8081/AddPatient",nouvellePatient)
  }
}

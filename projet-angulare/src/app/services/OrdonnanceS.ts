import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Ordonnance} from '../model/Ordonnance.model';

@Injectable({
  providedIn: 'root',
})
export class OrdonnanceS {

  constructor(private Http:HttpClient) {}

  public AjouterOrdonnance(NouvelleOrdonnance:Ordonnance,IdPatient:number):Observable<any>{
    return this.Http.post<Ordonnance>("http://localhost:8886/ORDONNANCE-SERVICE/addOrdonnance/"+IdPatient,NouvelleOrdonnance)
  }
  public GetDetailsOrdonnances(id:number):Observable<Ordonnance>{
    return this.Http.get<Ordonnance>("http://localhost:8886/ORDONNANCE-SERVICE/ordonnances/"+id);
  }


}

import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Ordonnance} from '../model/Ordonnance.model';
import {Ordonnances} from '../ordonnances/ordonnances';

@Injectable({
  providedIn: 'root',
})
export class OrdonnanceS {

  constructor(private Http:HttpClient) {}

  public AjouterOrdonnance(NouvelleOrdonnance:Ordonnance,IdPatient:number):Observable<any>{
    const token=localStorage.getItem('token')
    const headers=new HttpHeaders({
      'Authorization': 'Bearer ' + token
    })
    return this.Http.post<Ordonnance>("http://localhost:8886/ORDONNANCE-SERVICE/addOrdonnance/"+IdPatient,NouvelleOrdonnance,{ headers: headers })
  }

  public GetDetailsOrdonnances(id:number):Observable<Ordonnance>{
    const token=localStorage.getItem('token')
    const headers=new HttpHeaders({
      'Authorization': 'Bearer ' + token
    })
    return this.Http.get<Ordonnance>("http://localhost:8886/ORDONNANCE-SERVICE/ordonnances/"+id,{ headers: headers });
  }

  public getAllOrdonnances():Observable<any>{

    const token=localStorage.getItem('token')
    const headers=new HttpHeaders({
      'Authorization': 'Bearer ' + token
    })

    return this.Http.get<any>("http://localhost:8886/ORDONNANCE-SERVICE/ordonnances",{ headers: headers })
  }


}

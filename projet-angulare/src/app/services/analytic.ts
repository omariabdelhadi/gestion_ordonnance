import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Analytic {
  constructor(private http:HttpClient) {
  }

  public medicamentStat(id:number):Observable<any>{
    const token=localStorage.getItem('token')
    const headers=new HttpHeaders({
      'Authorization': 'Bearer ' + token
    })
    return this.http.get("http://localhost:8886/ANALYTICS-SERVICE/analytics/medicaments/"+id,{ headers: headers });
  }

  public medicamentsStat():Observable<any>{
    const token=localStorage.getItem('token')
    const headers=new HttpHeaders({
      'Authorization': 'Bearer ' + token
    })
    return this.http.get("http://localhost:8886/ANALYTICS-SERVICE/analytics/medicaments/",{ headers: headers });
  }

  public historiquemedicament(id:number):Observable<any>{
    const token=localStorage.getItem('token')
    const headers=new HttpHeaders({
      'Authorization': 'Bearer ' + token
    })
    return this.http.get("http://localhost:8886/ANALYTICS-SERVICE/analytics/medicament/"+id+"/historique",{ headers: headers });
  }

  public PredectionEpuise(id:number):Observable<any>{
    const token=localStorage.getItem('token')
    const headers=new HttpHeaders({
      'Authorization': 'Bearer ' + token
    })
    return this.http.get("http://localhost:8886/ANALYTICS-SERVICE/analytics/medicament/"+id+"/predection",{ headers: headers });
  }

}

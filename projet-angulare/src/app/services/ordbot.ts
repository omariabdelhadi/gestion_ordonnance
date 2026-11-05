import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Ordbot {
  constructor(private http:HttpClient) {
  }

  public chatbotMessage(message:string):Observable<any>{
    const token=localStorage.getItem('token')
    const headers=new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    const params=new HttpParams().set('query',message).set('conversationId','default')

    return this.http.get("http://localhost:8886/ORDONNANCE-BOT/chat",{ headers: headers , params: params, responseType: 'text' });
  }
}

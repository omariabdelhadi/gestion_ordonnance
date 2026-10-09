import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-medicaments',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './medicaments.html',
  styleUrl: './medicaments.css',
})
export class Medicaments implements OnInit{
  medicamentsA:any

  constructor(private http:HttpClient,private cd:ChangeDetectorRef) {
  }
  ngOnInit() {
    this.http.get<any>("http://localhost:8886/MEDICAMENT-SERVICE/medicaments").subscribe({
      next : data=>{
        this.medicamentsA=data._embedded.medicaments;
        this.cd.detectChanges()
      },
      error: err => {
        console.log(err)
      }
    })
  }

}

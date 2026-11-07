import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Analytic} from '../services/analytic';
import {ActivatedRoute, Router} from '@angular/router';
import {NgForOf,CommonModule} from '@angular/common';

@Component({
  selector: 'app-medicament-stat',
  imports: [
    NgForOf,CommonModule
  ],
  templateUrl: './medicament-stat.html',
  styleUrl: './medicament-stat.css',
})
export class MedicamentStat implements OnInit{

  medicamentstat:any

  constructor(private analyicss:Analytic,private routerA:ActivatedRoute, private cd:ChangeDetectorRef) {
  }

  ngOnInit() {
    const id=this.routerA.snapshot.params['id']
    this.analyicss.medicamentStat(id).subscribe({
      next: (data)=>{
        this.medicamentstat=data
        this.cd.detectChanges()
      },
      error: (err)=>{
        console.log(err)
      }
    })
  }
}

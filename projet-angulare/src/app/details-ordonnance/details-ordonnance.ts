import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Ordonnance} from '../model/Ordonnance.model';
import {OrdonnanceS} from '../services/OrdonnanceS';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-details-ordonnance',
  imports: [
    NgForOf,
    NgIf
  ],
  templateUrl: './details-ordonnance.html',
  styleUrl: './details-ordonnance.css',
})
export class DetailsOrdonnance implements OnInit{

  ordonnance?:Ordonnance;

  constructor(private routerA: ActivatedRoute,private ordonnanceS:OrdonnanceS,private cd:ChangeDetectorRef) {}

  ngOnInit() {
    let idOrd=this.routerA.snapshot.params['id'];
    this.ordonnanceS.GetDetailsOrdonnances(idOrd).subscribe({
      next:data=>{
        this.ordonnance=data;
        this.cd.detectChanges();
      },
      error:err => {
        console.log(err)
      }
    })
  }

}

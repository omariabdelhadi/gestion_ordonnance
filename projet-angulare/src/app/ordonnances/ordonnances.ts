import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {OrdonnanceS} from '../services/OrdonnanceS';
import {Ordonnance} from '../model/Ordonnance.model';


@Component({
  selector: 'app-ordonnances',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './ordonnances.html',
  styleUrl: './ordonnances.css',
})
export class Ordonnances implements OnInit {
  ordonnancesA: any;
  constructor(private ordS:OrdonnanceS,private cd:ChangeDetectorRef,private router:Router) {
  }
  ngOnInit() {
    this.ordS.getAllOrdonnances().subscribe({
      next:data=>{
        this.ordonnancesA=data._embedded.ordonnances;
        this.cd.detectChanges()
      },
      error:err=>{
        console.log(err)
      }
    })
  }

  protected details(id:number) {
    this.router.navigateByUrl("/detailsOrdonnance/"+id);
  }
}

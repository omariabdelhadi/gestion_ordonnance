import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';


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
  ordonnancesA:any;
  constructor(private Http:HttpClient,private cd:ChangeDetectorRef,private router:Router) {
  }
  ngOnInit() {
    this.Http.get<any>("http://localhost:8886/ORDONNANCE-SERVICE/ordonnances").subscribe({
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

import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CommonModule} from '@angular/common';
import {MedicamentS} from '../services/medicament-s';
import {Router} from '@angular/router';

@Component({
  selector: 'app-medicaments',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './medicaments.html',
  styleUrl: './medicaments.css',
})
export class Medicaments implements OnInit{
  medicamentsA:any

  constructor(private meds:MedicamentS,private cd:ChangeDetectorRef,private router:Router) {
  }
  ngOnInit() {
    this.meds.getMedicaments().subscribe({
      next : data=>{
        this.medicamentsA=data._embedded.medicaments;
        this.cd.detectChanges()
      },
      error: err => {
        console.log(err)
      }
    })
  }
  stat(id:number){
    this.router.navigateByUrl("/medicamentStat/"+id)
  }

}

import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CommonModule} from '@angular/common';
import {Router} from '@angular/router';
import {OrdonnanceS} from '../services/OrdonnanceS';
import {PatientS} from '../services/patient-s';


@Component({
  selector: 'app-patients',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './patients.html',
  styleUrl: './patients.css',
})
export class Patients implements OnInit{

  patientsA:any
  constructor(private ps:PatientS,private cd:ChangeDetectorRef,private router:Router) {
  }
  ngOnInit() {
    this.ps.getAllPatient()
      .subscribe({
        next:data=>{
          this.patientsA=data._embedded.patients;
          this.cd.detectChanges()
        },
        error : err => {
          console.log(err);
        }
      })
  }
  CreeOrdonnnace(id:number){
    this.router.navigateByUrl("/nouvelleordonnance/"+id);
  }

}

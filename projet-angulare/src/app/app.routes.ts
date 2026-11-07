import { Routes } from '@angular/router';
import {Patients} from './patients/patients';
import {Medicaments} from './medicaments/medicaments';
import {Ordonnances} from './ordonnances/ordonnances';
import {OrdonnancesBot} from './ordonnances-bot/ordonnances-bot';
import {NouvelleOrdonnance} from './nouvelle-ordonnance/nouvelle-ordonnance';
import {DetailsOrdonnance} from './details-ordonnance/details-ordonnance';
import {Login} from './login/login';
import {Inscription} from './inscription/inscription';
import {authGuard} from './guards/auth-guard';
import {MedicamentStat} from './medicament-stat/medicament-stat';

export const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full" },
  {path:"login",component:Login},
  {path:"inscription",component:Inscription},
  {path :"patients",component:Patients,canActivate:[authGuard]},
  {path:"medicaments",component:Medicaments,canActivate:[authGuard]},
  {path:"medicamentStat/:id",component:MedicamentStat,canActivate:[authGuard]},
  {path:"ordonnances",component:Ordonnances,canActivate:[authGuard]},
  {path:"chatbot",component:OrdonnancesBot,canActivate:[authGuard]},
  {path:"nouvelleordonnance/:id",component:NouvelleOrdonnance,canActivate:[authGuard]},
  {path:"detailsOrdonnance/:id",component:DetailsOrdonnance,canActivate:[authGuard]}
];

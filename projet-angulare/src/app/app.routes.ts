import { Routes } from '@angular/router';
import {Patients} from './patients/patients';
import {Medicaments} from './medicaments/medicaments';
import {Ordonnances} from './ordonnances/ordonnances';
import {OrdonnancesBot} from './ordonnances-bot/ordonnances-bot';
import {NouvelleOrdonnance} from './nouvelle-ordonnance/nouvelle-ordonnance';
import {DetailsOrdonnance} from './details-ordonnance/details-ordonnance';

export const routes: Routes = [
  {path :"patients",component:Patients},
  {path:"medicaments",component:Medicaments},
  {path:"ordonnances",component:Ordonnances},
  {path:"chatbot",component:OrdonnancesBot},
  {path:"nouvelleordonnance/:id",component:NouvelleOrdonnance},
  {path:"detailsOrdonnance/:id",component:DetailsOrdonnance}
];

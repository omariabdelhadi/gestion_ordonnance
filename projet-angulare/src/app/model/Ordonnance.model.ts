import {Patient} from './Patient.model';
import {Medicament} from './Medicament.model';

export interface Ordonnance {
  id?: number;
  ordDate: string;
  patientId?: number;
  medicamentItems: MedicamentItmModel[];
  patient?: Patient;
}

export interface MedicamentItmModel {
  id?: number;
  medicamentId: number;
  quantity: number;
  price: number;
  medicament?: Medicament;
}


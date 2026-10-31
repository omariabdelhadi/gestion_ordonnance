import {Component, OnInit, signal} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators
} from '@angular/forms';
import {DecimalPipe} from '@angular/common';

import {OrdonnanceS} from '../services/OrdonnanceS';
import {MedicamentS} from '../services/medicament-s';
import {Medicament} from '../model/Medicament.model';
import {MedicamentItmModel, Ordonnance} from '../model/Ordonnance.model';

@Component({
  selector: 'app-nouvelle-ordonnance',
  standalone: true,
  imports: [ReactiveFormsModule, DecimalPipe],
  templateUrl: './nouvelle-ordonnance.html',
  styleUrl: './nouvelle-ordonnance.css',
})
export class NouvelleOrdonnance implements OnInit {

  ordonnanceForm!: FormGroup;
  idPatient!: number;

  medicaments = signal<Medicament[]>([]);
  erreur = signal<string | null>(null);
  enCours = signal(false);

  constructor(
    private routerA: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private ordonnanceService: OrdonnanceS,
    private medicamentService: MedicamentS
  ) {}

  ngOnInit() {
    this.idPatient = Number(this.routerA.snapshot.params['id']);

    this.ordonnanceForm = this.fb.group({
      ordDate: [new Date().toLocaleDateString('en-CA'), Validators.required],
      items: this.fb.array([], Validators.minLength(1))
    });

    this.medicamentService.getMedicaments().subscribe({
      next: (data: any) => {
        const liste = Array.isArray(data) ? data : (data._embedded?.medicaments ?? []);
        this.medicaments.set(liste);
        this.ajouterLigne();
      },
      error: err => {
        console.log(err);
        this.erreur.set('Impossible de charger la liste des médicaments.');
      }
    });
  }

  get items(): FormArray {
    return this.ordonnanceForm.get('items') as FormArray;
  }

  ajouterLigne() {
    this.items.push(
      this.fb.group(
        {
          medicamentId: [null, Validators.required],
          quantity: [1, [Validators.required, Validators.min(1)]]
        },
        {validators: this.verifierStock}
      )
    );
  }

  supprimerLigne(i: number) {
    this.items.removeAt(i);
  }

  private verifierStock = (ligne: AbstractControl): ValidationErrors | null => {
    const med = this.medicaments().find(m => m.id === ligne.get('medicamentId')?.value);
    const qte = ligne.get('quantity')?.value ?? 0;
    return med && qte > med.Stock ? {stockInsuffisant: true} : null;
  };

  medicamentChoisi(i: number): Medicament | undefined {
    const id = this.items.at(i).get('medicamentId')?.value;
    return this.medicaments().find(m => m.id === id);
  }

  prixLigne(i: number): number {
    const med = this.medicamentChoisi(i);
    const qte = this.items.at(i).get('quantity')?.value ?? 0;
    return med ? med.price_unit * qte : 0;
  }

  get total(): number {
    return this.items.controls.reduce((somme, _, i) => somme + this.prixLigne(i), 0);
  }

  dejaChoisi(medicamentId: number, ligneCourante: number): boolean {
    return this.items.controls.some(
      (c, i) => i !== ligneCourante && c.get('medicamentId')?.value === medicamentId
    );
  }

  valider() {
    if (this.ordonnanceForm.invalid) {
      this.ordonnanceForm.markAllAsTouched();
      return;
    }

    const medicamentItems: MedicamentItmModel[] = this.items.controls.map((c, i) => ({
      medicamentId: c.get('medicamentId')!.value,
      quantity: c.get('quantity')!.value,
      price: this.prixLigne(i)
    }));

    const ordonnance: Ordonnance = {
      ordDate: this.ordonnanceForm.value.ordDate,
      medicamentItems
    };

    this.enCours.set(true);
    this.erreur.set(null);

    this.ordonnanceService.AjouterOrdonnance(ordonnance, this.idPatient).subscribe({
      next: () => this.router.navigateByUrl('/patients'),
      error: err => {
        console.log(err);
        this.erreur.set(this.messageErreur(err));
        this.enCours.set(false);
      }
    });
  }

  annuler() {
    this.router.navigateByUrl('/patients');
  }

  private messageErreur(err: any): string {
    if (typeof err?.error === 'string' && err.error) return err.error;
    return err?.error?.message ?? "L'ordonnance n'a pas pu être enregistrée. Vérifiez les quantités et réessayez.";
  }
}

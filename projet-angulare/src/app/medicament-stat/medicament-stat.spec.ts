import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicamentStat } from './medicament-stat';

describe('MedicamentStat', () => {
  let component: MedicamentStat;
  let fixture: ComponentFixture<MedicamentStat>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicamentStat],
    }).compileComponents();

    fixture = TestBed.createComponent(MedicamentStat);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

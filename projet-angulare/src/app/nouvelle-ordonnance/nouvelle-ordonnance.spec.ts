import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NouvelleOrdonnance } from './nouvelle-ordonnance';

describe('NouvelleOrdonnance', () => {
  let component: NouvelleOrdonnance;
  let fixture: ComponentFixture<NouvelleOrdonnance>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NouvelleOrdonnance],
    }).compileComponents();

    fixture = TestBed.createComponent(NouvelleOrdonnance);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

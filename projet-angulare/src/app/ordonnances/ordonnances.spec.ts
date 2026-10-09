import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Ordonnances } from './ordonnances';

describe('Ordonnances', () => {
  let component: Ordonnances;
  let fixture: ComponentFixture<Ordonnances>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Ordonnances],
    }).compileComponents();

    fixture = TestBed.createComponent(Ordonnances);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsOrdonnance } from './details-ordonnance';

describe('DetailsOrdonnance', () => {
  let component: DetailsOrdonnance;
  let fixture: ComponentFixture<DetailsOrdonnance>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsOrdonnance],
    }).compileComponents();

    fixture = TestBed.createComponent(DetailsOrdonnance);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

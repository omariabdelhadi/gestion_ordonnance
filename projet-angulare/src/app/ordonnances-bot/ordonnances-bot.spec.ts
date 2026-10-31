import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdonnancesBot } from './ordonnances-bot';

describe('OrdonnancesBot', () => {
  let component: OrdonnancesBot;
  let fixture: ComponentFixture<OrdonnancesBot>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrdonnancesBot],
    }).compileComponents();

    fixture = TestBed.createComponent(OrdonnancesBot);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

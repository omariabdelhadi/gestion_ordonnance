import { TestBed } from '@angular/core/testing';

import { MedicamentS } from './medicament-s';

describe('MedicamentS', () => {
  let service: MedicamentS;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicamentS);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

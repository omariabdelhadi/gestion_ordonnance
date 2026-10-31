import { TestBed } from '@angular/core/testing';

import { PatientS } from './patient-s';

describe('PatientS', () => {
  let service: PatientS;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientS);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

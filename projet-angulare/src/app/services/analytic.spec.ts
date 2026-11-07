import { TestBed } from '@angular/core/testing';

import { Analytic } from './analytic';

describe('Analytic', () => {
  let service: Analytic;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Analytic);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { OrdonnanceS } from './OrdonnanceS';

describe('OrdonnanceS', () => {
  let service: OrdonnanceS;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrdonnanceS);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

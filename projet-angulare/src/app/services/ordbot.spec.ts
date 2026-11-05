import { TestBed } from '@angular/core/testing';

import { Ordbot } from './ordbot';

describe('Ordbot', () => {
  let service: Ordbot;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Ordbot);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

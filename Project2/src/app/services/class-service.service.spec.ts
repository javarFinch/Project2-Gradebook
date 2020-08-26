import { TestBed } from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';

import { ClassService } from './class-service.service';

describe('ClassServiceService', () => {
  let service: ClassService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClient],
      providers: [ClassService]
    });
    service = TestBed.inject(ClassService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

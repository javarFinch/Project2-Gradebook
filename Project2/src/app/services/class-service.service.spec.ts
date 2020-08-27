import { TestBed } from '@angular/core/testing';
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { ClassService } from './class-service.service';

describe('ClassServiceService', () => {
  let service: ClassService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [ClassService, HttpClient]
    });
    service = TestBed.inject(ClassService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

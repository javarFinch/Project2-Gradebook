import { TestBed, async } from '@angular/core/testing';
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { ClassService } from './class-service.service';

import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { User } from '../Models/user';
import { NgbCarouselModule } from '@ng-bootstrap/ng-bootstrap';

describe('ClassServiceService', () => {
  let service: ClassService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ClassService, HttpClient]
    });
    service = TestBed.inject(ClassService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve a user', async(() => {
    const user = new User()
    user.id= 1;
    user.firstName='fName';
    user.lastName='lName';
    user.type='student'
    user.password= 'password'

    service.getUserByName(1,'password').subscribe((check: User) => {
      expect(check.id).toEqual(1);
    });

    const req = httpMock.expectOne(
      'http://localhost:8080/api/user/1/password'
    );
    expect(req.request.method).toBe('GET');
    req.flush(user); // send response
    httpMock.verify();
  }));

  it('should update a user', async(() => {
    const user = new User()
    user.id= 1;
    user.firstName='fName';
    user.lastName='lName';
    user.type='student'
    user.password= 'password'
         

    service.updateUser(new FormData()).subscribe((check: User) => {
      expect(check.id).toEqual(1);
    });

    const req = httpMock.expectOne(
      'http://localhost:8080/api/user/update/'
    );
    expect(req.request.method).toBe('PUT');
    req.flush(user); // send response
    httpMock.verify();
  }));

  it('should update a user', async(() => {
    const user = new User()
    user.id= 1;
    user.firstName='fName';
    user.lastName='lName';
    user.type='student'
    user.password= 'password'
         

    service.newUser(new FormData()).subscribe((check: User) => {
      expect(check.id).toEqual(1);
    });

    const req = httpMock.expectOne(
      'http://localhost:8080/api/admin/newUser'
    );
    expect(req.request.method).toBe('POST');
    req.flush(user); // send response
    httpMock.verify();
  }));

    // it('should retrieve a class list', async(()) => {
    //   const classList = [
    //     {
          
    //     }
    //   ]
    // }

  afterEach(() => {
    TestBed.resetTestingModule();
  });
});

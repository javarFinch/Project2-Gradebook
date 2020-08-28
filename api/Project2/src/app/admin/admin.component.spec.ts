import { async, ComponentFixture, TestBed , fakeAsync,tick} from '@angular/core/testing';

import { AdminComponent } from './admin.component';

import {NgbActiveModal, NgbModal, NgbNav, NgbNavConfig, NgbNavModule} from "@ng-bootstrap/ng-bootstrap";
import { AppComponent } from '../app.component';
import { HttpClient, HttpClientModule, HttpHandler } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { User } from '../Models/user';
import { AdminStudent } from '../Models/admin/admin-student';
import { AdminTeacher } from '../Models/admin/admin-teacher';
import { AdminClass } from '../Models/admin/admin-class';
import { Router } from '@angular/router';
import {Location} from '@angular/common'


describe('AdminComponent', () => {
  let component: AdminComponent;
  let fixture: ComponentFixture<AdminComponent>;
  let location: Location;
  let router: Router;
  let routerSpy;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminComponent ],
      imports: [NgbNavModule, HttpClientModule, RouterTestingModule.withRoutes([])],
      providers: [ HttpHandler,{Router,useValue: routerSpy} ] 
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminComponent);
    component = fixture.componentInstance;
    
    
    let user = new User();
    user.password = 'password';
    user.type = 'student';
    user.firstName = 'fName';
    user.lastName = 'lName';
    user.id = 100;
    let sList = new AdminStudent();
    sList.id = 1;
    sList.fName = 'fName';
    sList.lName = 'lName';
    sList.numberClasses = 2;
    sList.gpa = 3.00;
    let tList = new AdminTeacher();
    tList.id = 2;
    tList.fName = 'fName';
    tList.lName = 'lName';
    tList.numberClasses = 2;
    let cList = new AdminClass();
    cList.name = 'This class sucks';
    cList.numberStudents = 2;
    cList.subject = 'Who cares';
    cList.teacherName = 'Mr.Idgaf';
    component.active = 'Student';
    component.classList = [cList];
    component.teacherList = [tList];
    component.studentList = [sList];
    component.classService.user = user;
    component.username = 'username';
    fixture.detectChanges();

     router = TestBed.get(Router); 
     location = TestBed.get(Location); 
     router.initialNavigation(); 
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // it('navigate to "login" takes you to /login', fakeAsync(() => {
  //   spyOn(component, 'logout');
  //   let button = fixture.debugElement.nativeElement.querySelector('#logout');
  //   //button.click();
  //   router.navigate(['']);
  //   tick();
  //   expect(location.path()).toBe('/login');
  // }));

  // it('navagates to logout', async(()=>{
  //   let routerSpy={navigate: jasmine.createSpy('navigate')}
  //   spyOn(component,'logout');
  //   let button = fixture.debugElement.nativeElement.querySelector('#logout');
  //   button.click();

  //   fixture.whenStable().then(()=>{
  //     expect(component.logout).toHaveBeenCalled();
  //     //expect(routerSpy.navigate).toHaveBeenCalled();
  
    
  //   })
  // }));

  it('should have username "username"', () => {
    component.username = 'username';
    fixture.detectChanges();
    expect(component.username).toBeTruthy();
    expect(component.username).toEqual('username');
  });

  afterEach(() => {
    TestBed.resetTestingModule();
  });
});

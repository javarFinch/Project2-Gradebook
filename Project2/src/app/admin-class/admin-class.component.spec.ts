import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminClassComponent } from './admin-class.component';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { AdminClass } from '../Models/admin/admin-class';
import { AdminTeacher } from '../Models/admin/admin-teacher';
import { AdminStudent } from '../Models/admin/admin-student';

describe('AdminClassComponent', () => {
  let component: AdminClassComponent;
  let fixture: ComponentFixture<AdminClassComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminClassComponent ],
      providers: [ HttpClient, HttpHandler ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminClassComponent);
    component = fixture.componentInstance;

    let classList : AdminClass[];
    classList = [{name: 'name', subject: 'History', teacherName: 'Teacher', numberStudents: 10}];

    let teacherList : AdminTeacher[];
    teacherList =[{id: 1001, fName: 'firstName', lName: 'lastName', numberClasses: 10 }];


    let studentList: AdminStudent[];
    studentList = [{id: 1001, fName: 'firstName', lName: 'lastName', numberClasses: 10, gpa: 3.5}];

    component.classList=classList;
    component.teacherList=teacherList;
    component.studentList=studentList;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
  afterEach(() => {
    TestBed.resetTestingModule();
  });
});
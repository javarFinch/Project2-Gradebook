import { Assignment } from './../Models/assignment';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ClassService } from '../services/class-service.service';

import { StudentComponent } from './student.component';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { NgbNavModule, NgbNav } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from '../app.component';
import { Router, RouterModule } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { Class } from '../Models/class';
import { User } from '../Models/user';

describe('StudentComponent', () => {
  let component: StudentComponent;
  let fixture: ComponentFixture<StudentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentComponent],
      imports: [NgbNavModule, RouterTestingModule.withRoutes([])],
      providers: [ClassService, HttpClient, HttpHandler ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentComponent);
    component = fixture.componentInstance;
    let user=new User();
    user.password = 'testPassword';
    user.type = 'student';
    user.firstName = 'firstName';
    user.lastName = 'lastName';
    user.id = 1002;
    component.classService.user=user;
    let assignment = new Assignment();
    assignment.assignmentName = 'aName';
    assignment.assignmentType = 'aType';
    assignment.actualPoints = 50;
    assignment.totalPoints = 100;
    assignment.dueDate = '28-08-2020';
    let hold = new Class(40, 50, 'Type', 50, 40, [assignment], 20, 
    50, 50, 'Name', 10, 30, 'TeacherName');
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  afterEach(() => {
    TestBed.resetTestingModule();
  });
});

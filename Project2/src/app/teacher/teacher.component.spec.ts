import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherComponent } from './teacher.component';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { NgbNavModule, NgbNav } from '@ng-bootstrap/ng-bootstrap';
import { Grade } from '../Models/teacher/grade';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { User } from '../Models/user';

describe('TeacherComponent', () => {
  let component: TeacherComponent;
  let fixture: ComponentFixture<TeacherComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherComponent],
      imports: [NgbNavModule, RouterTestingModule.withRoutes([])],
      providers: [HttpClient, HttpHandler]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherComponent);
    component = fixture.componentInstance;
    component.activeClass
    component.classService.user=new User();
    component.classService.user.password = 'testPassword';
    component.classService.user.type = 'student';
    component.classService.user.firstName = 'first';
    component.classService.user.lastName = 'last';
    component.classService.user.id = 1002;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  afterEach(() => {
    TestBed.resetTestingModule();
  });
});

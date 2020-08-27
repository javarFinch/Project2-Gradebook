import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherComponent } from './teacher.component';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { NgbNavModule, NgbNav } from '@ng-bootstrap/ng-bootstrap';
import { Grade } from '../Models/teacher/grade';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

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
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

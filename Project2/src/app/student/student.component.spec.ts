import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ClassService } from '../services/class-service.service';

import { StudentComponent } from './student.component';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { NgbNavModule, NgbNav } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from '../app.component';
import { Router, RouterModule } from '@angular/router';

describe('StudentComponent', () => {
  let component: StudentComponent;
  let fixture: ComponentFixture<StudentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentComponent],
      imports: [NgbNavModule, RouterModule],
      providers: [ClassService, HttpClient, HttpHandler, Router ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

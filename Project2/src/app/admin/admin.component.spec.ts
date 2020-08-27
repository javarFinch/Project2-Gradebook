import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminComponent } from './admin.component';

import {NgbActiveModal, NgbModal, NgbNav, NgbNavConfig, NgbNavModule} from "@ng-bootstrap/ng-bootstrap";
import { AppComponent } from '../app.component';
import { HttpClient, HttpClientModule, HttpHandler } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';


describe('AdminComponent', () => {
  let component: AdminComponent;
  let fixture: ComponentFixture<AdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminComponent ],
      imports: [NgbNavModule, HttpClientModule, RouterModule],
      providers: [ HttpHandler, Router ] 
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { User } from './../Models/user';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswordModalComponent } from './password-modal.component';
import { NgbAlertConfig, NgbAlertModule, NgbHighlight, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

describe('PasswordModalComponent', () => {
  let component: PasswordModalComponent;
  let fixture: ComponentFixture<PasswordModalComponent>;
  

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PasswordModalComponent ],
      imports: [NgbAlertModule, HttpClientModule, FormsModule],
      providers: [ NgbActiveModal, HttpClient ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PasswordModalComponent);
    component = fixture.componentInstance;
    
    component.user=new User();
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  afterEach(() => {
    TestBed.resetTestingModule();
  });
});

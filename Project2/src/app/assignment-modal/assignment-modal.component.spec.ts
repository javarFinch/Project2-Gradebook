import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignmentModalComponent } from './assignment-modal.component';
import { NgbActiveModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


describe('AssignmentModalComponent', () => {
  let component: AssignmentModalComponent;
  let fixture: ComponentFixture<AssignmentModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignmentModalComponent ],
      imports: [FormsModule,NgbModule],
      providers: [NgbActiveModal, HttpClient, HttpHandler]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignmentModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
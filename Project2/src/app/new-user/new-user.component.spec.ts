import { async, ComponentFixture, TestBed } from '@angular/core/testing';


import { NgbNavModule, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NewUserComponent } from './new-user.component';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

describe('NewUserComponent', () => {
  let component: NewUserComponent;
  let fixture: ComponentFixture<NewUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewUserComponent ],
      imports: [HttpClientModule, FormsModule],
      providers: [HttpClient, NgbNavModule, NgbActiveModal]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

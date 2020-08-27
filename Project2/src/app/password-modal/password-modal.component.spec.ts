import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswordModalComponent } from './password-modal.component';
import { NgbAlertConfig, NgbAlertModule, NgbHighlight, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

describe('PasswordModalComponent', () => {
  let component: PasswordModalComponent;
  let fixture: ComponentFixture<PasswordModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PasswordModalComponent ],
      imports: [NgbAlertModule],
      providers: [ NgbActiveModal ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PasswordModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

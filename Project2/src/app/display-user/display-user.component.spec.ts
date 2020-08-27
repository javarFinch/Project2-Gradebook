import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayUserComponent } from './display-user.component';
import { NgbActiveModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';

describe('DisplayUserComponent', () => {
  let component: DisplayUserComponent;
  let fixture: ComponentFixture<DisplayUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayUserComponent ],
      imports: [NgbModule],
      providers: [NgbActiveModal]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

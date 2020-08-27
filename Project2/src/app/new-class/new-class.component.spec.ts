import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewClassComponent } from './new-class.component';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

describe('NewClassComponent', () => {
  let component: NewClassComponent;
  let fixture: ComponentFixture<NewClassComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewClassComponent ],
      providers: [ NgbActiveModal]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewClassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

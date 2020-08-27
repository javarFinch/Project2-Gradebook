import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignmentViewComponent } from './assignment-view.component';
import { Assignment } from '../Models/assignment';


describe('AssignmentViewComponent', () => {
  let component: AssignmentViewComponent;
  let fixture: ComponentFixture<AssignmentViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignmentViewComponent ],
      providers: [ Assignment ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignmentViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

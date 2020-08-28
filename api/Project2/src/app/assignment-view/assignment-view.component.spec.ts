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
    component.assignment = new Assignment();
    component.assignment.assignmentName = 'Test';
    component.assignment.assignmentType = 'Type';
    component.assignment.dueDate = '28-08-2020';
    component.assignment.totalPoints = 100;
    component.assignment.actualPoints = 80;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  afterEach(() => {
    TestBed.resetTestingModule();
  });
});

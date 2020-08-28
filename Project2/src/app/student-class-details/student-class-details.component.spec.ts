import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentClassDetailsComponent } from './student-class-details.component';
import { Assignment } from '../Models/assignment';
import { Class } from '../Models/class';

describe('StudentClassDetailsComponent', () => {
  let component: StudentClassDetailsComponent;
  let fixture: ComponentFixture<StudentClassDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentClassDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentClassDetailsComponent);
    component = fixture.componentInstance;
    let assignment = new Assignment();
    assignment.assignmentName = 'aName';
    assignment.assignmentType = 'aType';
    assignment.actualPoints = 50;
    assignment.totalPoints = 100;
    assignment.dueDate = '28-08-2020';
    component.class = new Class(40, 50, 'Type', 50, 40, [assignment], 20, 
    50, 50, 'Name', 10, 30, 'TeacherName');
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  afterEach(() => {
    TestBed.resetTestingModule();
  });
});

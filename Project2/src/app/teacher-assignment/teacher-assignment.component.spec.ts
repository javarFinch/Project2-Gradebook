import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherAssignmentComponent } from './teacher-assignment.component';
import { Grade } from '../Models/teacher/grade';
import { TeacherAssignment } from '../Models/teacher/teacher-assignment';

describe('TeacherAssignmentComponent', () => {
  let component: TeacherAssignmentComponent;
  let fixture: ComponentFixture<TeacherAssignmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherAssignmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherAssignmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    let grade = new Grade(1001, 'testFirst', 'testLast', 100)
    let teacherAssignment = new TeacherAssignment ([grade], '07/25/2020', 100, 'Assignment1', 'Assignment')
    expect(component).toBeTruthy();
  });
});

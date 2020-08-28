import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherClassDetailsComponent } from './teacher-class-details.component';
import { ClassService } from '../services/class-service.service';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { TeacherClass } from '../Models/teacher/teacher-class';
import { TeacherAssignment } from '../Models/teacher/teacher-assignment';
import { Grade } from '../Models/teacher/grade';

describe('TeacherClassDetailsComponent', () => {
  let component: TeacherClassDetailsComponent;
  let fixture: ComponentFixture<TeacherClassDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherClassDetailsComponent ],
      providers: [ HttpClient, ClassService, HttpHandler]

    
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherClassDetailsComponent);
    component = fixture.componentInstance;

    let grade = new Grade(1001, 'testFirst', 'testLast', 100)
    let teacherAssignment = new TeacherAssignment ([grade], '07/25/2020', 100, 'Assignment1', 'Assignment')
    component.activeClass = new TeacherClass(20, 50, 'Test', 40,
    [teacherAssignment], 50, 50, 50, 'Test', 50, 10, 30, 'TeacherName', 1 );
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
  afterEach(() => {
    TestBed.resetTestingModule();
  });
});

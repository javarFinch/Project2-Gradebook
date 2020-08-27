import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GradeModalComponent } from './grade-modal.component';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Grade } from '../Models/teacher/grade';
import { TeacherAssignment } from '../Models/teacher/teacher-assignment';

describe('GradeModalComponent', () => {
  let component: GradeModalComponent;
  let fixture: ComponentFixture<GradeModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GradeModalComponent ],
      imports: [FormsModule],
      providers: [NgbActiveModal, HttpClient, HttpHandler]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GradeModalComponent);
    component = fixture.componentInstance;
    let grade = new Grade(1001, 'testFirst', 'testLast', 100)
    component.assignment = new TeacherAssignment ([grade], '07/25/2020', 100, 'Assignment1', 'Assignment')
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  afterEach(() => {
    TestBed.resetTestingModule();
  });
});
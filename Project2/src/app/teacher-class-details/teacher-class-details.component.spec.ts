import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherClassDetailsComponent } from './teacher-class-details.component';

describe('TeacherClassDetailsComponent', () => {
  let component: TeacherClassDetailsComponent;
  let fixture: ComponentFixture<TeacherClassDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherClassDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherClassDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

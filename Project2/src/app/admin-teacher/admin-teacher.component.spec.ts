import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTeacherComponent } from './admin-teacher.component';
import { HttpClient } from '@angular/common/http';

describe('AdminTeacherComponent', () => {
  let component: AdminTeacherComponent;
  let fixture: ComponentFixture<AdminTeacherComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminTeacherComponent ],
      providers: [ HttpClient]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTeacherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

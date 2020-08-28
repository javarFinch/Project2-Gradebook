import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTeacherComponent } from './admin-teacher.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AdminTeacher } from '../Models/admin/admin-teacher';

describe('AdminTeacherComponent', () => {
  let component: AdminTeacherComponent;
  let fixture: ComponentFixture<AdminTeacherComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminTeacherComponent ],
      imports: [HttpClientModule],
      providers: [ HttpClient]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTeacherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    let tList = new AdminTeacher();
    tList.id = 2;
    tList.fName = 'fName';
    tList.lName = 'lName';
    tList.numberClasses = 2;
    component.input = 'help';
    component.teacherList=[tList];
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should search', () => {
    component.searchTable()
    expect(component.input).toContain('help');
  });

  afterEach(() => {
    TestBed.resetTestingModule();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminStudentComponent } from './admin-student.component';
import { HttpClient, HttpHandler, HttpClientModule } from '@angular/common/http';
import { AdminStudent } from '../Models/admin/admin-student';

describe('AdminStudentComponent', () => {
  let component: AdminStudentComponent;
  let fixture: ComponentFixture<AdminStudentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminStudentComponent ],
      imports: [ HttpClientModule ],
      providers: [ HttpClient, HttpHandler ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminStudentComponent);
    component = fixture.componentInstance;
    let sList = new AdminStudent();
    sList.id = 1;
    sList.fName = 'fName';
    sList.lName = 'lName';
    sList.numberClasses = 2;
    sList.gpa = 3.00;
    component.studentList=[sList];
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should return GPA', () => {
    const check = '90';
    const result = component.gpa(check);
    expect(result).toBe('90.00');
    
  });

  

  afterEach(() => {
    TestBed.resetTestingModule();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminStudentComponent } from './admin-student.component';
import { HttpClient, HttpHandler, HttpClientModule } from '@angular/common/http';

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
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});


import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule, NgbNavModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { StudentComponent } from './student/student.component';
import { ClassService } from './services/class-service.service';
import { HttpClientModule } from '@angular/common/http';

import { StudentClassDetailsComponent } from './student-class-details/student-class-details.component';
import { AdminComponent } from './admin/admin.component';
import { FormsModule } from '@angular/forms';
import { AssignmentViewComponent } from './assignment-view/assignment-view.component';

import { TeacherComponent } from './teacher/teacher.component';
import { TeacherClassDetailsComponent } from './teacher-class-details/teacher-class-details.component';
import { TeacherAssignmentComponent } from './teacher-assignment/teacher-assignment.component';
import { GradeModalComponent } from './grade-modal/grade-modal.component';
import { AssignmentModalComponent } from './assignment-modal/assignment-modal.component';
import { PasswordModalComponent } from './password-modal/password-modal.component';
import { AdminStudentComponent } from './admin-student/admin-student.component';
import { AdminClassComponent } from './admin-class/admin-class.component';
import { AdminTeacherComponent } from './admin-teacher/admin-teacher.component';
import { NewUserComponent } from './new-user/new-user.component';
import { DisplayUserComponent } from './display-user/display-user.component';
import { NewClassComponent } from './new-class/new-class.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    StudentComponent,
    StudentClassDetailsComponent,
    AdminComponent,
    AssignmentViewComponent,
    TeacherComponent,
    TeacherClassDetailsComponent,
    TeacherAssignmentComponent,
    GradeModalComponent,
    AssignmentModalComponent,
    PasswordModalComponent,
    AdminStudentComponent,
    AdminClassComponent,
    AdminTeacherComponent,
    NewUserComponent,
    DisplayUserComponent,
    NewClassComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    NgbNavModule
  ],
  providers: [ClassService, HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }


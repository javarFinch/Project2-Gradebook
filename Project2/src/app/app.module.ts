
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { StudentComponent } from './student/student.component';
import { ClassService } from './services/class-service.service';
import { HttpClientModule } from '@angular/common/http';

import { StudentClassDetailsComponent } from './student-class-details/student-class-details.component';
import { AdminComponent } from './admin/admin.component';
import { DisplayComponent } from './display/display.component';
import { FormsModule } from '@angular/forms';
import { ModalComponent } from './modal/modal.component';
import { AssignmentViewComponent } from './assignment-view/assignment-view.component';
import { TeacherComponent } from './teacher/teacher.component';
import { TeacherClassDetailsComponent } from './teacher-class-details/teacher-class-details.component';
import { TeacherAssignmentComponent } from './teacher-assignment/teacher-assignment.component';
import { GradeModalComponent } from './grade-modal/grade-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    StudentComponent,
    StudentClassDetailsComponent,
    AdminComponent,
    DisplayComponent,
    ModalComponent,
    AssignmentViewComponent,
    TeacherComponent,
    TeacherClassDetailsComponent,
    TeacherAssignmentComponent,
    GradeModalComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ClassService, HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
 

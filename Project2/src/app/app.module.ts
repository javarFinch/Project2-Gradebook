
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

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    StudentComponent,
    StudentClassDetailsComponent,
    AdminComponent,
    DisplayComponent,
    ModalComponent,
    AssignmentViewComponent
    
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
 

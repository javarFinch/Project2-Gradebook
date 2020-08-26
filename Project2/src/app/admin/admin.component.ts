import { AdminTeacher } from '../Models/admin/admin-teacher';
import { AdminStudent } from '../Models/admin/admin-student';
import { ClassService } from '../services/class-service.service';
import { AdminClass } from '../Models/admin/admin-class';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from '../Models/user';
import { NgbModal} from "@ng-bootstrap/ng-bootstrap";
import { PasswordModalComponent } from '../password-modal/password-modal.component';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  
  active = 'Student';
  username:string;
  currentUser:User;
  studentList:AdminStudent[];
  classList: AdminClass[];
  teacherList: AdminTeacher[];


  constructor(public router:Router,private classService: ClassService,private modalService: NgbModal) { }

  ngOnInit(): void {
    console.log(history.state)
    this.currentUser=history.state;
    this.username=this.currentUser.firstName+" "+this.currentUser.lastName;

    this.classService.getAdminClass().subscribe((c: AdminClass[]) => {(this.classList = c);});
    this.classService.getAdminStudent().subscribe((c: AdminStudent[]) => {(this.studentList = c);console.log('students: ',this.studentList);});
    this.classService.getAdminTeacher().subscribe((c: AdminTeacher[]) => {(this.teacherList = c);});
  }

  logout(){
    this.router.navigate(['login']);
  }

  openModal(){
    const modalRef = this.modalService.open(PasswordModalComponent, {size:'lg'});
    modalRef.componentInstance.user = this.currentUser;
    modalRef.result.then((result) => {

      if(result=='Update'){
        //this.classService.updateClass(this.activeClass.Id).subscribe((c: TeacherClass) => {(this.activeClass = c);this.activeClassChange.emit(this.activeClass)});
        
      }
    });
  }

}

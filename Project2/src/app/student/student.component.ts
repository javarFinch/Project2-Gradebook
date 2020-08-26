import { PasswordModalComponent } from './../password-modal/password-modal.component';
import { Component, OnInit } from '@angular/core';
import { Class } from '../Models/class';
import { ClassService } from '../services/class-service.service';
import { User } from '../Models/user';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {
  username:String;
  user:User;
  classList: Class[];
  activeClass:Class;


  newClassList: Class[];

  constructor(public router:Router,private classService: ClassService,private modalService: NgbModal) {
   
    this.activeClass=null;
  }

  setActiveClass(state){
    this.activeClass=state;
  }

  ngOnInit(): void {
    this.user=history.state;
    this.username=this.user.firstName+" "+this.user.lastName;

    //When the Observable is being returned, we can subscribe and listen to the changes.
    // It will continuously change as long as there is data coming in.
    this.classService.getClassList(this.user.id).subscribe((c: Class[]) => {(this.newClassList = c);console.log(this.newClassList);});
    
  }
  logout(){
    this.router.navigate(['login']);
  }

  openModal(){
    const modalRef = this.modalService.open(PasswordModalComponent, {size:'lg'});
    modalRef.componentInstance.user = this.user;
    modalRef.result.then((result) => {

      if(result=='Update'){
        //this.classService.updateClass(this.activeClass.Id).subscribe((c: TeacherClass) => {(this.activeClass = c);this.activeClassChange.emit(this.activeClass)});
        
      }
    });
  }

}

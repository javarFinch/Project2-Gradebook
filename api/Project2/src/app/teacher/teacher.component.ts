import { Component, OnInit } from '@angular/core';
import { TeacherClass } from '../Models/teacher/teacher-class';
import { ClassService } from '../services/class-service.service';
import { User } from '../Models/user';
import { Router } from '@angular/router';
import { PasswordModalComponent } from '../password-modal/password-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})
export class TeacherComponent implements OnInit {

  username:String;
  user:User;
  classList: TeacherClass[];
  activeClass:TeacherClass;
  activeIndex:number;


  newClassList: TeacherClass[];

  constructor(public router:Router,public classService: ClassService,private modalService: NgbModal) {
   
    this.activeClass=null;
    this.activeIndex=null;
  }

  setActiveClass(state){
    this.activeIndex=this.newClassList.findIndex(x=>x.id===state.Id);
    this.activeClass=state;
  }

  ngOnInit(): void {
    this.user=this.classService.user;
    this.username=this.user.firstName+" "+this.user.lastName;

    //When the Observable is being returned, we can subscribe and listen to the changes.
    // It will continuously change as long as there is data coming in.
    this.classService.getTeacherClassList(this.user.id).subscribe((c: TeacherClass[]) => {(this.newClassList = c);},(error)=>console.log(error));
    
  }

  updateActiveClass($event){
    this.newClassList[this.activeIndex]=$event
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

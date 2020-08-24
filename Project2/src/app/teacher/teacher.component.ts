import { Component, OnInit } from '@angular/core';
import { TeacherClass } from '../Models/teacher/teacher-class';
import { ClassService } from '../services/class-service.service';
import { User } from '../Models/user';


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


  newClassList: TeacherClass[];

  constructor(private classService: ClassService) {
   
    this.activeClass=null;
  }

  setActiveClass(state){
    this.activeClass=state;
  }

  ngOnInit(): void {
    console.log(history.state)
    this.user=history.state;
    this.username=this.user.firstName+" "+this.user.lastName;

    //When the Observable is being returned, we can subscribe and listen to the changes.
    // It will continuously change as long as there is data coming in.
    this.classService.getTeacherClassList(this.user.id).subscribe((c: TeacherClass[]) => {(this.newClassList = c);console.log(this.newClassList);console.log('done')});
    
  }

}

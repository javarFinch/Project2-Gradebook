import { Component, OnInit } from '@angular/core';
import { Class } from '../Models/class';
import { ClassService } from '../services/class-service.service';
import { User } from '../Models/user';

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

  constructor(private classService: ClassService) {
   
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

}

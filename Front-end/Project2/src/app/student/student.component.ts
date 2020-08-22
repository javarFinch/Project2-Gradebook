import { Component, OnInit } from '@angular/core';
import { Class } from '../models/class';
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

  //newClassList: Class[] = new Class (' ', true);
  newClassList: Class[];

  constructor(private classService: ClassService) {
    this.classList = [
      {name:'Math 101',
      active:false
      },
      {name:'Bio 101',
      active:false
      },
      {name:'Gym',
      active:false
      },
      {name:'English 101',
      active:false
      }
    ];
  
    this.activeClass={name:'',active:true};
  }

  setActiveClass(state){
    this.activeClass=state;
    state.active=true;
    for(let o of this.classList){
      if(o!==state){
        o.active=false
      }
    }
  }

  ngOnInit(): void {
    console.log('Initiating Class List');
    console.log(history.state)
    this.user=history.state;
    this.username=this.user.firstName+" "+this.user.lastName;

    //When the Observable is being returned, we can subscribe and listen to the changes.
    // It will continuously change as long as there is data coming in.
    //this.classService.getClassList().subscribe((c: Class[]) => (this.newClassList = c));
    
  }

}

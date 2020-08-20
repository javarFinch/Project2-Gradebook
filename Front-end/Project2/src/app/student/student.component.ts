import { Component, OnInit } from '@angular/core';
import { Class } from '../model/class';
import { ClassService } from '../services/class-service.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

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

    //When the Observable is being returned, we can subscribe and listen to the changes.
    // It will continuously change as long as there is data coming in.
    this.classService.getClassList().subscribe((c: Class[]) => (this.newClassList = c));
    
  }

}

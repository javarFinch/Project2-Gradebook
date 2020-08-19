import { Component, OnInit } from '@angular/core';
import { Class } from '../model/class';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  classList: Class[];
  activeClass:string;

  
  constructor() {
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
  
    this.activeClass='Class Details go here';
  }

  setActiveClass(state){
    this.activeClass=state.name;
    state.active=true;
    for(let o of this.classList){
      if(o!==state){
        o.active=false
      }
    }
  }

  ngOnInit(): void {
  }

}

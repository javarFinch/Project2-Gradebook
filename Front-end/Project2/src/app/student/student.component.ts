import { Component, OnInit } from '@angular/core';
import { Class } from '../models/class';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  classList: Class[];
  activeClass:Class;

  
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
  }

}

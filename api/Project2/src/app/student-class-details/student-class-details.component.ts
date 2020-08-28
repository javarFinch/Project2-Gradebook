import { Component, OnInit, Input } from '@angular/core';
import { Class } from '../Models/class';


@Component({
  selector: 'app-student-class-details',
  templateUrl: './student-class-details.component.html',
  styleUrls: ['./student-class-details.component.css']
})
export class StudentClassDetailsComponent implements OnInit {

  @Input() class: Class;

  public testCollapsed:boolean;
  public quizCollapsed:boolean;
  public participationCollapsed:boolean;
  public homeworkCollapsed: boolean;


  constructor() { 
    this.testCollapsed=true;
    this.quizCollapsed=true;
    this.participationCollapsed=true;
    this.homeworkCollapsed=true;
  }

  ngOnInit(): void {
  }

  
}

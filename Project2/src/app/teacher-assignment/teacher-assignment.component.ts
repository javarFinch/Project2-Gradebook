import { TeacherAssignment } from './../Models/teacher/teacher-assignment';
import { Component, OnInit, Input } from '@angular/core';



@Component({
  selector: 'app-teacher-assignment',
  templateUrl: './teacher-assignment.component.html',
  styleUrls: ['./teacher-assignment.component.css']
})
export class TeacherAssignmentComponent implements OnInit {

  @Input() assignment: TeacherAssignment;

  averagePoints:number;

  constructor() { }

  ngOnInit(): void {
    console.log('got here')
    this.averagePoints=0;
    var total=0;
    for(var grade of this.assignment.gradeList){
      if(grade.points>=0){
        this.averagePoints+=grade.points;
        total++;
      }
      
    }
    this.averagePoints=this.averagePoints/total;
    console.log("Average Points: "+ this.averagePoints)
  }

}
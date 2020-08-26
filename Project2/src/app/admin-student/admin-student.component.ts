import { startWith, map } from 'rxjs/operators';
import { AdminStudent } from './../Models/admin/admin-student';
import { Component, OnInit, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';



let studentList: AdminStudent[] = [
  
]




@Component({
  selector: 'app-admin-student',
  templateUrl: './admin-student.component.html',
  styleUrls: ['./admin-student.component.css']
})
export class AdminStudentComponent implements OnInit {

 // @Input()  ;

  studentList: AdminStudent[];
  

  constructor() {
    this.studentList=[{
      id:1,
      firstName:'James',
      lastName:'Veitengruber',
      numClasses: 6,
      gpa:4.0
    },
    {
      id:2,
      firstName:'Rachel',
      lastName:'Veitengruber',
      numClasses: 5,
      gpa:3.0
    },
    {
      id:3,
      firstName:'Kurt',
      lastName:'Veitengruber',
      numClasses: 4,
      gpa:2.0
    }]
   }

  ngOnInit(): void {
  }

}

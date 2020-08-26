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

 @Input()  studentList: AdminStudent[];;

  
  

  constructor() {
   }

  ngOnInit(): void {
  }

}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  classList: string[];

  
  constructor() {
    this.classList = [
      'Math 101',
      'Bio 101',
      'Gym',
      'English 101'
    ];
  
   }

  ngOnInit(): void {
  }

}

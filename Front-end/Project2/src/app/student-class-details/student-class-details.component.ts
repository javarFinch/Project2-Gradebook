import { Component, OnInit, Input } from '@angular/core';
import { Class } from '../models/class';


@Component({
  selector: 'app-student-class-details',
  templateUrl: './student-class-details.component.html',
  styleUrls: ['./student-class-details.component.css']
})
export class StudentClassDetailsComponent implements OnInit {

  @Input() class: Class;

  constructor() { }

  ngOnInit(): void {
  }

}

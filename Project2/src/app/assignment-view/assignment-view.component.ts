import { Assignment } from './../Models/assignment';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-assignment-view',
  templateUrl: './assignment-view.component.html',
  styleUrls: ['./assignment-view.component.css']
})
export class AssignmentViewComponent implements OnInit {

  @Input() assignment: Assignment;

  constructor() { }

  ngOnInit(): void {
  }

}

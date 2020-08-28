import { User } from './../Models/user';
import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-display-user',
  templateUrl: './display-user.component.html',
  styleUrls: ['./display-user.component.css']
})
export class DisplayUserComponent implements OnInit {

  @Input() user:User;
  
  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

}

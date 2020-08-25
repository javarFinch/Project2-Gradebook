import { Component, OnInit } from '@angular/core';
import { User } from '../Models/user';
import {NgbActiveModal, NgbModal, NgbNav, NgbNavConfig} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  user: string;
  username:string;
  currentUser:User;

  userArray: string[] = ['student', 'teacher', 'class']

  constructor() { }

  ngOnInit(): void {
    console.log(history.state)
    this.currentUser=history.state;
    this.username=this.currentUser.firstName+" "+this.currentUser.lastName;
  }

  selectDisplay(user: string){
    this.user = user;
  }

}

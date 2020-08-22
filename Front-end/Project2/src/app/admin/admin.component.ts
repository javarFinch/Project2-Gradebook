import { Component, OnInit } from '@angular/core';
import { User } from '../Models/user';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  user: string;

  userArray: string[] = ['student', 'teacher', 'class']

  constructor() { }

  ngOnInit(): void {
  }

  selectDisplay(user: string){
    this.user = user;
  }

}

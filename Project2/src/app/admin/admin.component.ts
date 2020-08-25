import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from '../Models/user';

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

  constructor(public router:Router) { }

  ngOnInit(): void {
    console.log(history.state)
    this.currentUser=history.state;
    this.username=this.currentUser.firstName+" "+this.currentUser.lastName;
  }

  selectDisplay(user: string){
    this.user = user;
  }

  logout(){
    this.router.navigate(['login']);
  }

}

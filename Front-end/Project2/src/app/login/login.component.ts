import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  nextLink:string;
  constructor(public router:Router) { }

  ngOnInit(): void {
  }

  onSubmit():void{
    this.router.navigate(['student']);
  }

}

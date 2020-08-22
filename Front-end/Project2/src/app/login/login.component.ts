import { ClassService } from './../services/class-service.service';
import { User } from './../Models/user';
import { Component, OnInit, NgModule } from '@angular/core';
import { Router } from '@angular/router';




@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})



export class LoginComponent implements OnInit {

  model=new User();
  user: User;

  nextLink:string;
  showError:boolean;
  errorMessage:string;

  constructor(public router:Router, private classService: ClassService) { }


  ngOnInit(): void {
    
    this.errorMessage=' ';
  }

  onSubmit():void{
    this.classService.getUserByName(this.model.username,this.model.password).subscribe((c: User) => {(this.user = c); console.log(this.user);this.changePages()});
    
  }

  changePages():void{
    if(this.user){
      this.errorMessage=' '
      if(this.user.type=='student'){
        this.router.navigate(['student'],{state:this.user});
      }
    }else{
      this.errorMessage='Invalid Username or Password'
    
    }
    
  }

}

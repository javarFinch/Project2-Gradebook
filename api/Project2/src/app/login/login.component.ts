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

  model:User;
  user: User;

  nextLink:string;
  showError:boolean;
  errorMessage:string;

  constructor(public router:Router, private classService: ClassService) { }


  ngOnInit(): void {
    this.model=new User();
    this.errorMessage=' ';
  }

  onSubmit():void{
    this.classService.getUserByName(this.model.id,this.model.password).subscribe((c: User) => {(this.user = c); this.changePages()},(error)=>console.log(error));
    
  }

  changePages():void{
    console.log('user: ',this.user)
    if(this.user){
      this.errorMessage=' '
      if(this.user.type=='student'){

        this.classService.user=this.user;
        this.router.navigate(['student'],{state:this.user});
      }else if(this.user.type=='admin'){
 
        this.classService.user=this.user;
        this.router.navigate(['admin'],{state:this.user});
      }
      else if(this.user.type=='teacher'){

        this.classService.user=this.user;
        this.router.navigate(['teacher'],{state:this.user});
      }
    }else{
      this.errorMessage='Invalid Username or Password'
    
    }
    
  }

}

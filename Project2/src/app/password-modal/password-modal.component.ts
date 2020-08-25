import { User } from './../Models/user';
import { ClassService } from './../services/class-service.service';
import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-password-modal',
  templateUrl: './password-modal.component.html',
  styleUrls: ['./password-modal.component.css']
})
export class PasswordModalComponent implements OnInit {
  @Input() user:User;

  public current:string;
  public currentError:string;

  public new1:string;
  public new2:string;
  public newError1:string;
  public newError2:string;

  constructor(public activeModal: NgbActiveModal,private classService: ClassService) { 
    this.currentError="";
    this.newError1="";
    this.newError2="";
  }

  ngOnInit(): void {
  }

  updatePassword(formData){
    console.log("updating: ",this.user)
    if(this.formValidation()){
      this.classService.updatePassword(formData).subscribe();
      this.activeModal.close('Update')
    }
    
  }

  formValidation():boolean{
    let confirm=true;
    if(this.current!=this.user.password){
      this.currentError="Incorrect Password"
      confirm=false;
    }else{
      this.currentError="";
    }
    if(this.new1!=this.new2){
      this.newError1="Passwords must match";
      this.newError2="Passwords must match";
      confirm=false;
    }else{
      this.newError1="";
      this.newError2="";
      if(!this.new1){
        this.newError1="Invalid Input"
        this.newError2="Invalid Input"
        confirm=false
      }
    }
    
    return confirm;
  }
  

  
}
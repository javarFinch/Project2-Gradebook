import { DisplayUserComponent } from './../display-user/display-user.component';
import { User } from './../Models/user';
import { ClassService } from './../services/class-service.service';
import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  @Input() type:string;

  public firstName:string;
  public firstError:string;

  public lastName:string;
  public lastError:string;

  constructor(public activeModal: NgbActiveModal,private classService: ClassService, private modalService: NgbModal) { 
    this.firstError="";
    this.lastError="";
    
  }

  newUser(formData){
    console.log("new user")
    if(this.formValidation()){
      this.classService.newUser(formData.value).subscribe((c:User)=>{this.showUser(c)},(error)=>console.log(error));
    }
    
  }

  formValidation():boolean{
    let confirm=true;
    if(!this.firstName){
      this.firstError="Invalid Input"
      confirm=false;
    }else{
      this.firstError="";
    }
    if(!this.lastName){
      this.lastError="Invalid Input"
      confirm=false;
    }else{
      this.lastError="";
    }
    return confirm;
  }

  showUser(user:User){
    if(user){
      this.activeModal.close('Update')
    }
      const modalRef = this.modalService.open(DisplayUserComponent, {size:'sm'});
      modalRef.componentInstance.user = user;
  }

  ngOnInit(): void {
  }

}

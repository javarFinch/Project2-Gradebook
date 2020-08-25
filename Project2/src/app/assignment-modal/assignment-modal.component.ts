import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { NgbActiveModal, NgbTypeahead, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
import { ClassService } from '../services/class-service.service';
import { Subject, Observable, merge } from 'rxjs';
import {debounceTime, distinctUntilChanged, filter, map} from 'rxjs/operators';


const types=['Test','Quiz','Homework','Participation'];

@Component({
  selector: 'app-assignment-modal',
  templateUrl: './assignment-modal.component.html',
  styleUrls: ['./assignment-modal.component.css']
})
export class AssignmentModalComponent implements OnInit {

  

  @Input() classId: number;

  public output:any;
  public type:any;
  public name:string;
  public points:number;
  public date:NgbDateStruct;
  public nameError:string;
  public typeError:string;
  public pointsError:string;
  public dateError:string;

  constructor(public activeModal: NgbActiveModal,private classService: ClassService) {
    this.nameError="";
    this.typeError="";
    this.pointsError="";
    this.dateError="";
   }

  ngOnInit(): void {
  }

  checkAssignment():boolean{
    let confirm=true;
    //check and show error for no input
    if(!this.type){
      this.typeError="Invalid Input"
      confirm=false;
    }else{
      if(types.includes(this.type)){
        this.typeError=""
      }else{
        this.typeError="Invalid Input"
        confirm=false;
      }
      
    }
    if(!this.name){
      this.nameError="Invalid Input"
      confirm=false;
    }else{
      this.nameError="";
    }
    if(!this.points){
      this.pointsError="Invalid Input"
      confirm=false;
    }
    else{
      this.pointsError="";
    }
    if(!this.date){
      this.dateError="Invalid Input"
      confirm=false;
    }
    else{
      this.dateError="";
    }
    console.log(confirm)
    return confirm;
  }

  sendAssignment(formData){
    console.log("Form Validation: ",formData.value)
    if(this.checkAssignment()){
      console.log("Form Sent:",formData.value);
      this.classService.newAssignment(formData.value).subscribe();
      this.activeModal.close('Update')
    }
    
  }

  @ViewChild('instance', {static: true}) instance: NgbTypeahead;
  focus$ = new Subject<string>();
  click$ = new Subject<string>();

  search = (text$: Observable<string>) => {
    const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged());
    const clicksWithClosedPopup$ = this.click$.pipe(filter(() => !this.instance.isPopupOpen()));
    const inputFocus$ = this.focus$;

    return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
      map(term => (term === '' ? types
        : types.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
    );
  }

}

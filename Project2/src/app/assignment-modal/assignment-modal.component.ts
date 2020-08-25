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
  public date2:string;
  public nameError:string;
  public typeError:string;
  public pointsError:string;
  public dateError:string;

  constructor(public activeModal: NgbActiveModal,private classService: ClassService) { }

  ngOnInit(): void {
  }

  sendAssignment(formData){
    this.output={
      classId:this.classId,
      
      data:formData.value
    }
    console.log("Form Value",formData.value);
    //this.classService.updateGrades(this.output).subscribe();
    this.activeModal.close('Update')
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

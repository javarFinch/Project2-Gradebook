import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { NgbTypeahead, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject, Observable, merge } from 'rxjs';
import { debounceTime, distinctUntilChanged, filter, map } from 'rxjs/operators';
import { ClassService } from '../services/class-service.service';

const subjects=['Math','English','History','Science','Other'];
const teachers=['1','2','3','4','5',]

@Component({
  selector: 'app-new-class',
  templateUrl: './new-class.component.html',
  styleUrls: ['./new-class.component.css']
})
export class NewClassComponent implements OnInit {

  @Input() type:string;
  @Input() teachers:string[];

  name:string;
  subject:string;
  teacherId:string;
  studentList:number[];

  nameError:string;
  subjectError:string;
  teacherError:string;

  constructor(public activeModal: NgbActiveModal,private classService: ClassService) { }

  ngOnInit(): void {
  }

  newClass(formData){
    console.log("Form Validation: ",formData.value)
    // if(this.checkAssignment()){
    //   console.log("Form Sent:",formData.value);
    //   this.classService.newAssignment(formData.value).subscribe();
    //   this.activeModal.close('Update')
    // }
    
  }

  @ViewChild('instance', {static: true}) instance: NgbTypeahead;
  focus$ = new Subject<string>();
  click$ = new Subject<string>();

  search = (text$: Observable<string>) => {
    const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged());
    const clicksWithClosedPopup$ = this.click$.pipe(filter(() => !this.instance.isPopupOpen()));
    const inputFocus$ = this.focus$;

    return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
      map(term => (term === '' ? subjects
        : subjects.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
    );
  }

  @ViewChild('instance2', {static: true}) instance2: NgbTypeahead;
  focus2$ = new Subject<string>();
  click2$ = new Subject<string>();
  search2 = (text$: Observable<string>) => {
    const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged());
    const clicksWithClosedPopup2$ = this.click2$.pipe(filter(() => !this.instance2.isPopupOpen()));
    const inputFocus2$ = this.focus2$;

    return merge(debouncedText$, inputFocus2$, clicksWithClosedPopup2$).pipe(
      map(term => (term === '' ? this.teachers
        : this.teachers.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
    );
  }
}

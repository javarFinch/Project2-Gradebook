import { AssignmentModalComponent } from './../assignment-modal/assignment-modal.component';
import { GradeModalComponent } from './../grade-modal/grade-modal.component';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TeacherClass } from '../Models/teacher/teacher-class';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { ClassService } from '../services/class-service.service';

@Component({
  selector: 'app-teacher-class-details',
  templateUrl: './teacher-class-details.component.html',
  styleUrls: ['./teacher-class-details.component.css']
})
export class TeacherClassDetailsComponent implements OnInit {

  @Input() activeClass: TeacherClass;
  @Output() activeClassChange= new EventEmitter<TeacherClass>();

  public testCollapsed:boolean;
  public quizCollapsed:boolean;
  public participationCollapsed:boolean;
  public homeworkCollapsed: boolean;
  public size:string;




  constructor(private modalService: NgbModal,private classService: ClassService) {
    this.testCollapsed=true;
    this.quizCollapsed=true;
    this.participationCollapsed=true;
    this.homeworkCollapsed=true;

  }

  ngOnInit(): void {

  }

  openModal(state){
    const modalRef = this.modalService.open(GradeModalComponent, {size:'lg'});
    modalRef.componentInstance.assignment = state;
    modalRef.componentInstance.classId = this.activeClass.id;
    modalRef.result.then((result) => {

      if(result=='Update'){
        this.classService.updateClass(this.activeClass.id).subscribe((c: TeacherClass) => {(this.activeClass = c);this.activeClassChange.emit(this.activeClass)});

      }
    });
  }

  newAssignment(){
    const modalRef = this.modalService.open(AssignmentModalComponent, {size:'lg'});
    modalRef.componentInstance.classId = this.activeClass.id;
    modalRef.result.then((result) => {

      if(result=='Update'){
        this.classService.updateClass(this.activeClass.id).subscribe((c: TeacherClass) => {(this.activeClass = c);this.activeClassChange.emit(this.activeClass)});
      }
    });
  }

}

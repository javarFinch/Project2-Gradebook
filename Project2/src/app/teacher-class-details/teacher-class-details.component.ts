import { GradeModalComponent } from './../grade-modal/grade-modal.component';
import { Component, OnInit, Input } from '@angular/core';
import { TeacherClass } from '../Models/teacher/teacher-class';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-teacher-class-details',
  templateUrl: './teacher-class-details.component.html',
  styleUrls: ['./teacher-class-details.component.css']
})
export class TeacherClassDetailsComponent implements OnInit {

  @Input() class: TeacherClass;

  public testCollapsed:boolean;
  public quizCollapsed:boolean;
  public participationCollapsed:boolean;
  public homeworkCollapsed: boolean;
  public size:string;



  constructor(private modalService: NgbModal) { 
    this.testCollapsed=true;
    this.quizCollapsed=true;
    this.participationCollapsed=true;
    this.homeworkCollapsed=true;

  }

  ngOnInit(): void {
    console.log('assignments:')
    console.log(this.class.AssignmentList)
  }

  openModal(state){
    const modalRef = this.modalService.open(GradeModalComponent, {size:'lg'});
    modalRef.componentInstance.assignment = state;
    
  }
}

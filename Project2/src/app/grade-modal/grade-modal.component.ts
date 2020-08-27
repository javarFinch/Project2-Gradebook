import { ClassService } from './../services/class-service.service';
import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import { TeacherAssignment } from '../Models/teacher/teacher-assignment';

@Component({
  selector: 'app-grade-modal',
  templateUrl: './grade-modal.component.html',
  styleUrls: ['./grade-modal.component.css']
})
export class GradeModalComponent implements OnInit {

  @Input() assignment:TeacherAssignment;

  public output:any;

  constructor(public activeModal: NgbActiveModal,private classService: ClassService) { 
    
  }

  ngOnInit(): void {
  }

  updateGrades(formData){
    this.output={
      name:this.assignment.assignmentName,
      type:this.assignment.assignmentType,
      data:formData.value
    }
    this.classService.updateGrades(this.output).subscribe(c=>{this.activeModal.close('Update')},(error)=>console.log(error));
    
  }
}

import { AdminStudent } from './../Models/admin/admin-student';
import { AdminTeacher } from './../Models/admin/admin-teacher';
import { NewClassComponent } from './../new-class/new-class.component';
import { AdminClass } from './../Models/admin/admin-class';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ClassService } from '../services/class-service.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-admin-class',
  templateUrl: './admin-class.component.html',
  styleUrls: ['./admin-class.component.css']
})
export class AdminClassComponent implements OnInit {

  @Input()  classList: AdminClass[];
  @Input()  teacherList: AdminTeacher[];
  @Input()  studentList:AdminStudent[];
  @Output() classListChange= new EventEmitter<AdminClass[]>();
  @Output() studentListChange= new EventEmitter<AdminStudent[]>();
  @Output() teacherListChange= new EventEmitter<AdminTeacher[]>();

 public input:string;
 public teachers:string[];
 public students:string[];

  constructor(private classService: ClassService,private modalService: NgbModal) { }

  ngOnInit(): void {
      this.teachers=this.generateTeachers();
  }

  generateTeachers():string[]{
      let output=[];
      for(let teacher of this.teacherList){
          let hold=teacher.id+'-('+teacher.fName+' '+teacher.lName+')';
        output.push(hold)
      }
      return output;
  }

  openModal(){
    const modalRef = this.modalService.open(NewClassComponent, {size:'lg'});
    modalRef.componentInstance.type = 'class';
    modalRef.componentInstance.teachers=this.teachers;
    modalRef.componentInstance.studentList=this.studentList;
    modalRef.result.then((result) => {

      if(result=='Update'){
        this.classService.getAdminClass().subscribe((c: AdminClass[]) => {(this.classList = c);console.log(this.classList);this.classListChange.emit(this.classList)});
        this.classService.getAdminStudent().subscribe((c: AdminStudent[]) => {(this.studentList = c);this.studentListChange.emit(this.studentList)});
        this.classService.getAdminTeacher().subscribe((c: AdminTeacher[]) => {(this.teacherList = c);this.teacherListChange.emit(this.teacherList)});
        
      }
    });
  }

  sortClass(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("class-body");
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.rows;
        /* Loop through all table rows (except the
        first, which contains table headers): */
        for (i = 0; i < (rows.length-1); i++) {
            // Start by saying there should be no switching:
            shouldSwitch = false;
            /* Get the two elements you want to compare,
            one from current row and one from the next: */
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /* Check if the two rows should switch place,
            based on the direction, asc or desc: */
            if (dir == "asc") {
                if(n==3){
                    //check numbers
                    console.log(x.lastChild.innerHTML,">",y.lastChild.innerHTML)
                    if (parseFloat(x.lastChild.innerHTML) > parseFloat(y.lastChild.innerHTML)) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }

                }else{
                    //checking all other columns
                    if (x.lastChild.innerHTML.toLowerCase() > y.lastChild.innerHTML.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                }

            } else if (dir == "desc") {
                if(n==3){
                    if (parseFloat(x.lastChild.innerHTML) < parseFloat(y.lastChild.innerHTML)) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                }else{
                    //checking all other columns
                    if (x.lastChild.innerHTML.toLowerCase() < y.lastChild.innerHTML.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                }
            }
        }
        if (shouldSwitch) {
            /* If a switch has been marked, make the switch
            and mark that a switch has been done: */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            // Each time a switch is done, increase this count by 1:
            switchcount++;
        } else {
            /* If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again. */
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

searchTable() {
    // Declare variables
    console.log('Start Search')
    
      console.log('Search table for: ',this.input)
      var filter, table, tr, td, i, txtValue;
      filter = this.input.toUpperCase();
      table = document.getElementById("class-body");
      tr = table.getElementsByTagName("tr");

      // Loop through all table rows, and hide those who don't match the search query
      for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td");
        let count = 0;
        for( var j=0;j<td.length;j++){
            if (td) {
                txtValue = td[j].lastChild.textContent || td[j].lastChild.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    count+=1;
                }
            }
        }
        if (count>0) {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        }

      }
    
  }
}

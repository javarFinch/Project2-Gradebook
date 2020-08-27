import { NewClassComponent } from './../new-class/new-class.component';
import { AdminTeacher } from './../Models/admin/admin-teacher';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ClassService } from '../services/class-service.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-admin-teacher',
  templateUrl: './admin-teacher.component.html',
  styleUrls: ['./admin-teacher.component.css']
})
export class AdminTeacherComponent implements OnInit {

  @Input()  teacherList: AdminTeacher[];
  @Output() teacherListChange= new EventEmitter<AdminTeacher[]>();

  public input:string;

  constructor(private classService: ClassService,private modalService: NgbModal) { }

  ngOnInit(): void {
  }
  openModal(){
    const modalRef = this.modalService.open(NewClassComponent, {size:'md'});
    modalRef.componentInstance.type = 'class';
    modalRef.result.then((result) => {

      if(result=='Update'){
        this.classService.getAdminTeacher().subscribe((c: AdminTeacher[]) => {(this.teacherList = c);this.teacherListChange.emit(this.teacherList)});
        
      }
    });
}

  sortTeachers(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("teacher-body");
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
                if(n==0 || n==3){
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
                if(n==0 || n==3){
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
      table = document.getElementById("teacher-body");
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

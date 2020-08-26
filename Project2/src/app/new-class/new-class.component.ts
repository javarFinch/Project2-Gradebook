import { AdminStudent } from './../Models/admin/admin-student';
import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { NgbTypeahead, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject, Observable, merge } from 'rxjs';
import { debounceTime, distinctUntilChanged, filter, map } from 'rxjs/operators';
import { ClassService } from '../services/class-service.service';

const subjects=['Math','English','History','Science','Other'];


@Component({
  selector: 'app-new-class',
  templateUrl: './new-class.component.html',
  styleUrls: ['./new-class.component.css']
})
export class NewClassComponent implements OnInit {

  @Input() type:string;
  @Input() teachers:string[];
  @Input() studentList:AdminStudent[];

  name:string;
  subject:string;
  teacherId:string;
  studentIds:number[];
  input:string;

  nameError:string;
  subjectError:string;
  teacherError:string;
  studentError:string;

  count:number;

  constructor(public activeModal: NgbActiveModal,private classService: ClassService) {
    console.log('students: ',this.studentList)
    this.count=0;
   }

  ngOnInit(): void {
    
  }

  newClass(formData){
    console.log("Form Validation: ",formData.value)
    if(this.formValidation()){
      console.log("Form Sent:",formData.value);
      //this.classService.newClass(formData.value).subscribe();
      this.activeModal.close('Update')
    }
    
  }

  formValidation():boolean{
    let confirm=true;
    this.subjectError='';
    this.teacherError='';
    this.studentError='';

    if(this.name){
      this.nameError='';
    }else{
      this.nameError="Invalid input";
      confirm=false
    }
    if(!this.teachers.includes(this.teacherId)){
      this.teacherError="invalid input";
      confirm=false;
    }
    if(!subjects.includes(this.subject)){
      this.subjectError='Invalid input'
      confirm=false;
    }
    if(this.count===0){
      this.studentError='Pick a student'
      confirm=false;
    }

    return confirm;
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

  changed(){
    this.count=0;
    this.studentIds=[];
    var rows;
    var table = document.querySelector("#student-body");
    rows = table.querySelectorAll('tr');
    for(var i=0;i<rows.length;i++){
      if(rows[i].getElementsByTagName("TD")[0].lastChild.checked){
        this.count++;
        this.studentIds.push(rows[i].getElementsByTagName("TD")[1].lastChild.innerHTML)
      }
    }
  }

  sortStudents(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("student-body");
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
                if(n==1 || n==4){
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
                if(n==1 || n==4){
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
      table = document.getElementById("student-body");
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

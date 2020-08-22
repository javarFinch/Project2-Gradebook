import { Component, OnInit, Input } from '@angular/core';
import { AdminComponent } from '../admin/admin.component';


@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit {

  @Input() user: String;
  
  constructor() { }

  ngOnInit(): void {
  }

}

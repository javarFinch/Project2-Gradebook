import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GradeModalComponent } from './grade-modal.component';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient, HttpHandler } from '@angular/common/http';

describe('GradeModalComponent', () => {
  let component: GradeModalComponent;
  let fixture: ComponentFixture<GradeModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GradeModalComponent ],
      providers: [NgbActiveModal, HttpClient, HttpHandler]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GradeModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

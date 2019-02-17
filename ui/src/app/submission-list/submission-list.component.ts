import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import {Router} from '@angular/router';
import {Submission} from '../model/submission.model';
import {BackendApiService} from '../services/backend-api.service';
import { MatTableDataSource} from '@angular/material/table';
import { MatPaginator} from '@angular/material';
import { FormGroup, FormBuilder } from '@angular/forms';
import {first} from 'rxjs/operators';

@Component({
  selector: 'ltk-submission-list',
  templateUrl: './submission-list.component.html',
  styleUrls: ['./submission-list.component.scss']
})

export class SubmissionListComponent implements OnInit {

  displayedColumns: string[] = ['topic', 'description', 'targetLightningTalkDate', 'approved', 'approveBtn'];
  dataSource: MatTableDataSource<Submission> = new MatTableDataSource(new Array(0));

  selectedTalkDateGrp: FormGroup;

  talkDates: { 'epoch': number, 'dt': string}[] = [];
  selectedTalkDate ;


  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: BackendApiService) { }

  ngOnInit() {

//    if(!window.localStorage.getItem('token')) {
//      this.router.navigate(['login']);
//      return;
//    }

    this.selectedTalkDateGrp = this.formBuilder.group({ });
    this.refresh();

  } // ngOnInit

  private refresh() {
    this.apiService.getSessionDates()
    .pipe(first()).subscribe(dates => {
      this.talkDates = dates.result;
      this.selectedTalkDate = this.talkDates[0].epoch;
    });

    this.apiService.getSubmissions()
      .subscribe(data => {
        this.dataSource = new MatTableDataSource(data.result);
        this.dataSource.paginator = this.paginator;
      });
  }

  approveSubmission(submission: Submission): void {
    submission.approved = true;
    this.apiService.approveSubmission(submission)
    .subscribe( data => {
      this.ngOnInit();
    });
  }

  onDateChanged(item: { 'epoch': number, 'dt': string}) {
  this.selectedTalkDate = item.epoch;
  }
}

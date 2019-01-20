import { Component, OnInit, Inject } from '@angular/core';
import {Router} from '@angular/router';
import {Submission} from '../model/submission.model';
import {BackendApiService} from '../services/backend-api.service';

@Component({
  selector: 'ltk-submission-list',
  templateUrl: './submission-list.component.html',
  styleUrls: ['./submission-list.component.scss']
})
export class SubmissionListComponent implements OnInit {

  submissions: Submission[];

  constructor(private router: Router, private apiService: BackendApiService) { }

  ngOnInit() {
//    if(!window.localStorage.getItem('token')) {
//      this.router.navigate(['login']);
//      return;
//    }
    this.apiService.getSubmissions()
      .subscribe( data => {
          this.submissions = data.result;
      });
  }

  approveSubmission(submission: Submission): void {
    submission.approved = true;
    this.apiService.approveSubmission(submission)
    .subscribe( data => {
      this.ngOnInit();
    });
  }
}

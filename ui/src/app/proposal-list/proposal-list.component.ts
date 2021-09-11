import {Component, OnInit, ViewChild, Inject } from '@angular/core';
import {Router} from '@angular/router';
import {Proposal} from '../model/proposal.model';
import {BackendApiService} from '../services/backend-api.service';
import {MatTableDataSource} from '@angular/material/table';
import {first} from 'rxjs/operators';
import {MatPaginator} from '@angular/material/paginator';
import { AlertService } from '../services/alert.service';

@Component({
  selector: 'ltk-proposal-list',
  templateUrl: './proposal-list.component.html',
  styleUrls: ['./proposal-list.component.scss']
})
export class ProposalListComponent implements OnInit {

  displayedColumns: string[] = ['topic', 'description', 'email', 'editBtn', 'deleteBtn', 'submitBtn'];
  dataSource: MatTableDataSource<Proposal> = new MatTableDataSource(new Array(0));
  noData: boolean;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(public router: Router, private alertService: AlertService, private apiService: BackendApiService) { }

  ngOnInit() {
//    if(!window.localStorage.getItem('token')) {
//      this.router.navigate(['login']);
//      return;
//    }
    this.refresh();
  }

  private refresh() {
    this.apiService.getProposals()
      .subscribe(data => {
        this.dataSource = new MatTableDataSource(data.result);
        this.dataSource.paginator = this.paginator;
        this.noData = data.result.length === 0;
      });
  }

  deleteProposal(proposal: Proposal): void {
    this.apiService.deleteProposal(proposal.id)
    .pipe(first())
    .subscribe(
        data => {
          if (data.status === 200) {
            this.alertService.success('Proposal deleted successfully.', true);
            // this.chgdr.markForCheck();
            this.refresh();
            this.router.navigate(['proposal-list']);
          } else {
            this.alertService.error(data.message, true);
          }
        },
        error => {
          this.alertService.error(error.message, true);
        });
  }

  editProposal(proposal: Proposal): void { // fwd to edit
    window.localStorage.removeItem('editProposalId');
    window.localStorage.setItem('editProposalId', proposal.id.toString());
    this.router.navigate(['proposal-edit']);
  }

  addProposal(): void {
    this.router.navigate(['proposal-add']);
  }

  submitProposal(proposal: Proposal): void {
    this.apiService.submitProposal(proposal)
      .pipe(first())
      .subscribe(
        data => {
          if (data.status === 201) {
            this.alertService.success('Proposal submitted successfully.', true);
            this.router.navigate(['proposal-list']);
          } else {
            this.alertService.error(data.message);
          }
        },
        error => {
          this.alertService.error(error.message);
        });
  }

}

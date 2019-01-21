import {Component, OnInit, ViewChild, ChangeDetectionStrategy, ChangeDetectorRef, Inject } from '@angular/core';
import {Router} from '@angular/router';
import {Proposal} from '../model/proposal.model';
import {BackendApiService} from '../services/backend-api.service';
import {MatTableDataSource} from '@angular/material/table';
import {first} from 'rxjs/operators';
import {MatPaginator} from '@angular/material';

@Component({
  selector: 'ltk-proposal-list',
  templateUrl: './proposal-list.component.html',
  styleUrls: ['./proposal-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class ProposalListComponent implements OnInit {

  displayedColumns: string[] = ['topic', 'description', 'email', 'editBtn', 'deleteBtn', 'submitBtn'];
  dataSource: MatTableDataSource<Proposal> = new MatTableDataSource(new Array(0));

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private router: Router, private apiService: BackendApiService,
    private chgdr: ChangeDetectorRef) { }

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
      });
  }

  deleteProposal(proposal: Proposal): void {
    this.apiService.deleteProposal(proposal.id)
    .pipe(first())
    .subscribe(
        data => {
          if (data.status === 200) {
            alert('Proposal deleted successfully.');
            // this.chgdr.markForCheck();
            this.refresh();
            this.router.navigate(['proposal-list']);
          } else {
            if (data.status === undefined) {
              alert(data);
            } else {
              alert(data.message);
            }
          }
        },
        error => {
          alert(error);
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
            alert('Proposal submitted successfully.');
            this.router.navigate(['proposal-list']);
          } else {
            alert(data.message);
          }
        },
        error => {
          alert(error);
        });
  }

}

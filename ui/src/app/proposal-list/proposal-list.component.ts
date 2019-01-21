import {Component, OnInit, ViewChild, Inject } from '@angular/core';
import {Router} from '@angular/router';
import {Proposal} from '../model/proposal.model';
import {BackendApiService} from '../services/backend-api.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material';

@Component({
  selector: 'ltk-proposal-list',
  templateUrl: './proposal-list.component.html',
  styleUrls: ['./proposal-list.component.scss']
})
export class ProposalListComponent implements OnInit {

  displayedColumns: string[] = ['topic', 'description', 'email', 'editBtn', 'submitBtn', 'deleteBtn'];
  dataSource: MatTableDataSource<Proposal> = new MatTableDataSource(new Array(0));

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private router: Router, private apiService: BackendApiService) { }

  ngOnInit() {
//    if(!window.localStorage.getItem('token')) {
//      this.router.navigate(['login']);
//      return;
//    }
    this.apiService.getProposals()
      .subscribe( data => {
        this.dataSource = new MatTableDataSource(data.result);
        this.dataSource.paginator = this.paginator;
      });
  }

  deleteProposal(proposal: Proposal): void {
    this.apiService.deleteProposal(proposal.id)
      .subscribe( data => {
        this.dataSource.data.filter(p => p !== proposal);
      });
  }

  editProposal(proposal: Proposal): void {
    window.localStorage.removeItem('editProposalId');
    window.localStorage.setItem('editProposalId', proposal.id.toString());
    this.router.navigate(['proposal-edit']);
  }

  addProposal(): void {
    this.router.navigate(['proposal-add']);
  }

  submitProposal(proposal: Proposal): void {
    this.apiService.submitProposal(proposal)
    .subscribe( data => {
      console.log(data);
    });
  }

}

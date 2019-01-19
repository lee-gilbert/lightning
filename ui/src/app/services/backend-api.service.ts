import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Proposal} from "../model/proposal.model";
import {Submission} from "../model/submission.model";
import {ApiResponse} from "../model/api.response";
import {Observable} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class BackendApiService {

  constructor(private http: HttpClient) { }
  proposalURL: string = 'http://localhost:8080/api/proposal/'; //TODO make config constants
  submissionlURL: string = 'http://localhost:8080/api/submission/';

  getProposals() : Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.proposalURL);
  }

  getProposalById(id: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.proposalURL + id);
  }

  createProposal(proposal: Proposal): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.proposalURL, proposal);
  }

  updateProposal(proposal: Proposal): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.proposalURL + proposal.id, proposal);
  }

  submitProposal(proposal: Proposal): Observable<ApiResponse> {
    proposal.submitted = true; 
    const submission = new Submission
    submission.id = proposal.id
    submission.topic = proposal.topic
    submission.description = proposal.description
    submission.email = proposal.email
    submission.approved = false
    return this.http.post<ApiResponse>(this.submissionlURL, submission); //create Submission
  }

  deleteProposal(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.proposalURL + id);
  }
}

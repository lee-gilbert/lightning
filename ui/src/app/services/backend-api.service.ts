import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Proposal} from "../model/proposal.model";
import {ApiResponse} from "../model/api.response";
import {Observable} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class BackendApiService {

  constructor(private http: HttpClient) { }
   baseUrl: string = 'http://localhost:8080/api/proposal/';


  getProposals() : Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getProposalById(id: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

  createProposal(proposal: Proposal): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.baseUrl, proposal);
  }

  updateProposal(proposal: Proposal): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.baseUrl + proposal.id, proposal);
  }

  submitProposal(proposal: Proposal): Observable<ApiResponse> {
    proposal.submitted = true;
    return this.http.put<ApiResponse>(this.baseUrl + proposal.id, proposal);
  }

  deleteProposal(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.baseUrl + id);
  }
}

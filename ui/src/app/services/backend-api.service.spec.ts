// import {Injector} from '@angular/core';
// import { TestBed, getTestBed } from '@angular/core/testing';
// import { of } from 'rxjs';
// import { HttpClientModule, HttpClient } from '@angular/common/http';
// import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
// import {BackendApiService} from '../services/backend-api.service';
// import { ApiResponse } from '../model/api.response';
// import { Proposal } from '../model/proposal.model';

// const res1: Proposal[] = [{'id': 1,
// 'topic': 'Microservices', 'description': 'Microservices, their definition, and pros and cons.', 'email': 'a@b.com', 'submitted': false}];
// const dummyProposals: ApiResponse = new ApiResponse(200, 'success', res1);

// const mockApiService = {
//   getProposals() {
//     return of(dummyProposals);
//   }
// };

// describe('HttpTestingController test', () => {
//   let httpMock: HttpTestingController;
//   let http: HttpClient;
//   let apiService: BackendApiService;
//   beforeEach(() => {
//       TestBed.configureTestingModule({
//           imports: [HttpClientModule, HttpClientTestingModule],
//           providers: [{BackendApiService , useValue: mockApiService} ]
//       });
//       httpMock = TestBed.inject(HttpTestingController);
//       http = TestBed.inject(HttpClient);
//       apiService = TestBed.inject(BackendApiService);
//   });

//   it('should be created', () => {
//     expect(apiService).toBeTruthy();
//   });

//   it('should get all Proposals from http', () => {
//     const req = httpMock.expectOne(apiService.proposalURL);
//     expect(req.request.method).toBe('GET');
//     req.flush(dummyProposals);
//     httpMock.verify();
//   });

// });

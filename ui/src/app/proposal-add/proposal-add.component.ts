import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {BackendApiService} from '../services/backend-api.service';

@Component({
  selector: 'ltk-proposal-add',
  templateUrl: './proposal-add.component.html',
  styleUrls: ['./proposal-add.component.scss']
})

export class ProposalAddComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: BackendApiService) { }

  addForm: FormGroup;

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      id: [],
      topic: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
      description: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(120)]],
      email: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(255), Validators.email]]
     });
  }

  onSubmit() {
    this.apiService.createProposal(this.addForm.value)
      .subscribe( data => {
        this.router.navigate(['proposal-list']);
      });
  }
}

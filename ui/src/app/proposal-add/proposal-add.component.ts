import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {BackendApiService} from "../services/backend-api.service";

@Component({
  selector: 'proposal-add',
  templateUrl: './proposal-add.component.html',
  styleUrls: ['./proposal-add.component.scss']
})

export class ProposalAddComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private router: Router, private apiService: BackendApiService) { }

  addForm: FormGroup;

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      id: [],
      topic: ['', Validators.required],
      description: ['', Validators.required],
      email: ['', Validators.required],
    });
  }

  onSubmit() {
    this.apiService.createProposal(this.addForm.value)
      .subscribe( data => {
        this.router.navigate(['proposal-list']);
      });
  }
}

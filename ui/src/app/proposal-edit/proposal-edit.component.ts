import { Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import {first} from 'rxjs/operators';
import {Router} from '@angular/router';
import {BackendApiService} from '../services/backend-api.service';
import { AlertService } from '../services/alert.service';

@Component({
  selector: 'ltk-proposal-edit',
  templateUrl: './proposal-edit.component.html',
  styleUrls: ['./proposal-edit.component.scss']
})

export class ProposalEditComponent implements OnInit {

  editForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, 
    private alertService: AlertService, private apiService: BackendApiService) { }

  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    });
  }

  ngOnInit() {
    const proposalId = window.localStorage.getItem('editProposalId');

    this.editForm = this.formBuilder.group({
      id: [''],
      topic: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
      description: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(120)]],
      email: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(255), Validators.email]],
      submitted: ['']
    });

    if (!proposalId) {
      this.alertService.error('Invalid edit action.', true);
      return;
    }

    this.apiService.getProposalById(+proposalId)
      .subscribe( data => {
        this.editForm.setValue(data.result);
      });
  }

  onCancel() {
    this.router.navigate(['proposal-list']);
  }

  onSubmit() {
    if (this.editForm.valid) {
    this.apiService.updateProposal(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          if (data.status === 200) {
            this.alertService.success('Proposal updated successfully.', true);
            this.router.navigate(['proposal-list']);
          } else {
            this.alertService.error(data.message);
          }
        }, err => {
          this.alertService.error(err);
      });
      } else {
        this.validateAllFormFields(this.editForm);
      }
  }
}

import { Component, OnInit , ChangeDetectionStrategy, Inject} from '@angular/core';
import {Router} from '@angular/router';
import {Proposal} from '../model/proposal.model';
import {FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import {first} from 'rxjs/operators';
import {BackendApiService} from '../services/backend-api.service';


@Component({
  selector: 'ltk--proposal-edit',
  templateUrl: './proposal-edit.component.html',
  styleUrls: ['./proposal-edit.component.scss'],
  changeDetection: ChangeDetectionStrategy.Default
})

export class ProposalEditComponent implements OnInit {

  proposal: Proposal;
  editForm: FormGroup;
  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: BackendApiService) { }

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
    if (!proposalId) {
      alert('Invalid edit action.');
      this.router.navigate(['proposal-list']);
      return;
    }
    this.editForm = this.formBuilder.group({
      id: [''],
      topic: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
      description: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(120)]],
      email: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(255), Validators.email]],
      submitted: ['']
    });
    this.apiService.getProposalById(+proposalId)
      .subscribe( data => {
        this.editForm.setValue(data.result);
      });
  }

  onSubmit() {
    if (this.editForm.valid) {
    this.apiService.updateProposal(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          if (data.status === 200) {
            alert('Proposal updated successfully.');
            this.router.navigate(['proposal-list']);
          } else {
            alert(data.message);
          }
        },
        error => {
          alert(error);
        });
      } else {
        this.validateAllFormFields(this.editForm);
      }
  }
}

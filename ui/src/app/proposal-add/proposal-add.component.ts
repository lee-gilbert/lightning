import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
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

//In case you need to handle validation messages for the FormControls as well, you can modify the code to also mark the
// FormGroup as touched by simply removing the { onlySelf: true } parameter. When we pass onlSelf: true to the markAsDirty
//or markAsTouch (or other markAs* methods) Angular only marks the control itself. Without this option, Angular will mark the control and its parent.

ngOnInit() {
    this.addForm = this.formBuilder.group({
      id: [],
      topic: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
      description: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(120)]],
      email: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(255), Validators.email]]
     });
  }

  onSubmit() {
    if (this.addForm.valid) {
    this.apiService.createProposal(this.addForm.value)
      .subscribe( data => {
        this.router.navigate(['proposal-list']);
      });
  } else {
    this.validateAllFormFields(this.addForm);
  }
}

}

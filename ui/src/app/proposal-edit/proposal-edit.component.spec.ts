import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProposalEditComponent } from './proposal-edit.component';

describe('ProposalEditComponent', () => {
  let component: ProposalEditComponent;
  let fixture: ComponentFixture<ProposalEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProposalEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProposalEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProposalAddComponent } from './proposal-add.component';

describe('ProposalAddComponent', () => {
  let component: ProposalAddComponent;
  let fixture: ComponentFixture<ProposalAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProposalAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProposalAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

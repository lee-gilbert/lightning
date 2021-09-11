import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { TestsModule} from '../modules/tests.module';
import { ProposalListComponent } from './proposal-list.component';
import { CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import { ActivatedRoute, convertToParamMap} from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { MatTableModule } from '@angular/material';
import {APP_BASE_HREF} from '@angular/common';
import { AlertService } from '../services/alert.service';

describe('ProposalListComponent', () => {
  let component: ProposalListComponent;
  let fixture: ComponentFixture<ProposalListComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ProposalListComponent ],
      imports: [TestsModule, MatTableModule, RouterTestingModule],
      providers: [
        {provide: ActivatedRoute, useValue: { snapshot: { paramMap: convertToParamMap({ id: 'BzTvl77YsRTtdihH0jehl' }) } }},
        {provide: APP_BASE_HREF, useValue: ''},
        AlertService
     ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProposalListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // spyOn(component.router, 'navigate').and.returnValue(true);
  // expect(component.router.navigate).toHaveBeenCalledWith('/home/advisor');

});

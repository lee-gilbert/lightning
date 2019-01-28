import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {TestsModule} from '../modules/tests.module';
import { AlertService } from '../services/alert.service';
import { ProposalEditComponent } from './proposal-edit.component';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {ActivatedRoute, convertToParamMap} from '@angular/router';

describe('ProposalEditComponent', () => {
  let component: ProposalEditComponent;
  let fixture: ComponentFixture<ProposalEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProposalEditComponent ],
      imports: [TestsModule],
      providers: [
        {provide: ActivatedRoute,
        useValue: {
          snapshot: {
            paramMap: convertToParamMap({
              id: 'BzTvl77YsRTtdihH0jei'
            })
          }
        }
      }, AlertService
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
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

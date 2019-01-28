import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { TestsModule} from '../modules/tests.module';
import { AlertService } from '../services/alert.service';
import { ProposalAddComponent } from './proposal-add.component';
import { CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import { ActivatedRoute, convertToParamMap} from '@angular/router';

describe('ProposalAddComponent', () => {
  let component: ProposalAddComponent;
  let fixture: ComponentFixture<ProposalAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProposalAddComponent ],
      imports: [TestsModule],
      providers: [
        {provide: ActivatedRoute,
        useValue: {
          snapshot: {
            paramMap: convertToParamMap({
              id: 'BzTvl77YsRTtdihH0jeh'
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
    fixture = TestBed.createComponent(ProposalAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

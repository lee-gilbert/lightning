import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {TestsModule} from '../modules/tests.module';
import { SubmissionListComponent } from './submission-list.component';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {ActivatedRoute, convertToParamMap} from '@angular/router';
import {APP_BASE_HREF} from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';

describe('SubmissionListComponent', () => {
  let component: SubmissionListComponent;
  let fixture: ComponentFixture<SubmissionListComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ SubmissionListComponent ],
      imports: [TestsModule, MatTableModule,  MatInputModule, MatSelectModule],
      providers: [
        {provide: ActivatedRoute,
        useValue: {
          snapshot: {
            paramMap: convertToParamMap({
              id: 'BzTvl77YsRTtdihH0jeo'
            })
          }
        }
      },
      {provide: APP_BASE_HREF, useValue: '/'},
],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubmissionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

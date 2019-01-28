import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { AlertComponent} from './directives/alert.component';
import { AlertService} from './services/alert.service';
import { ProposalListComponent } from './proposal-list/proposal-list.component';
import { ProposalEditComponent } from './proposal-edit/proposal-edit.component';
import { ProposalAddComponent } from './proposal-add/proposal-add.component';
import { SubmissionListComponent } from './submission-list/submission-list.component';
import { MNavComponent } from './m-nav/m-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule, MatPaginatorModule,
   MatCardModule, MatFormFieldModule, MatInputModule,  MatRadioModule,
   MatSelectModule, MatOptionModule, MatSlideToggleModule   } from '@angular/material';
import { MatTableModule } from '@angular/material/table';
import { BrowserXhr } from '@angular/http';
import {CustExtBrowserXhr} from './cust-ext-browser-xhr';
import { ErrorInterceptor } from './util/error.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    AlertComponent,
    ProposalListComponent,
    ProposalEditComponent,
    ProposalAddComponent,
    SubmissionListComponent,
    MNavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    MatSelectModule,
    MatOptionModule,
    MatSlideToggleModule
  ],
  providers: [ AlertService,
    { provide: BrowserXhr, useClass: CustExtBrowserXhr},
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

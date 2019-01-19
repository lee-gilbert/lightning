import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import {ReactiveFormsModule} from "@angular/forms";
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";

import { ProposalListComponent } from './proposal-list/proposal-list.component';
import { ProposalEditComponent } from './proposal-edit/proposal-edit.component';
import { ProposalAddComponent } from './proposal-add/proposal-add.component';


@NgModule({
  declarations: [
    AppComponent,
    ProposalListComponent,
    ProposalEditComponent,
    ProposalAddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

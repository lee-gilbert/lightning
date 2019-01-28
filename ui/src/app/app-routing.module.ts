import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProposalListComponent } from './proposal-list/proposal-list.component';
import { ProposalEditComponent } from './proposal-edit/proposal-edit.component';
import { ProposalAddComponent } from './proposal-add/proposal-add.component';
import { SubmissionListComponent } from './submission-list/submission-list.component';

const routes: Routes = [
  { path: 'proposal-edit', component: ProposalEditComponent },
  { path: 'proposal-edit/:id', component: ProposalEditComponent },
   { path: 'proposal-add', component: ProposalAddComponent },
  { path: 'proposal-list', component: ProposalListComponent },
  { path: 'submission-list', component: SubmissionListComponent },
  { path: '**', redirectTo: 'submission-list' , pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: true }) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routing = RouterModule.forRoot(routes);

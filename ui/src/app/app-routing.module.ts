import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProposalListComponent } from "./proposal-list/proposal-list.component";
import { ProposalEditComponent } from "./proposal-edit/proposal-edit.component";
import { ProposalAddComponent } from "./proposal-add/proposal-add.component";

const routes: Routes = [
  { path: 'proposal-edit', component: ProposalEditComponent },
  { path: 'proposal-add', component: ProposalAddComponent },
  { path: 'proposal-list', component: ProposalListComponent },
  { path: '', component: ProposalListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routing = RouterModule.forRoot(routes);

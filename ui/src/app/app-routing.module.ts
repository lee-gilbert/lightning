import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProposalListComponent} from "./proposal-list/proposal-list.component";

const routes: Routes = [
  { path: 'proposal-list', component: ProposalListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routing = RouterModule.forRoot(routes);

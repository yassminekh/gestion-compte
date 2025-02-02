import { RouterModule, Routes } from '@angular/router';
import { CompteListComponent } from './components/compte-list/compte-list.component';
import { ClientListComponent } from './components/client-list/client-list.component';
import { NgModule } from '@angular/core';

export const routes: Routes = [
  { path: 'comptes', component: CompteListComponent }, 
  { path: 'clients', component: ClientListComponent }, 
  { path: '', redirectTo: '/comptes', pathMatch: 'full' },
  { path: '**', redirectTo: '/comptes' }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
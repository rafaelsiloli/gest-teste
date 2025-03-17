import { Routes } from '@angular/router';
import { ConsultaCreditosComponent } from './components/consulta-creditos/consulta-creditos.component';

export const appRoutes: Routes = [
  { path: '', redirectTo: 'consulta', pathMatch: 'full' },
  { path: 'consulta', component: ConsultaCreditosComponent },
  { path: '**', redirectTo: 'consulta' }
];

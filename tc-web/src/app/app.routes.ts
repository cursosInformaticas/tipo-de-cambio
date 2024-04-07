import { Routes } from '@angular/router';
import { AddComponent } from './pages/add/add.component';
import { ListComponent } from './pages/list/list.component';

export const routes: Routes = [
  { path: 'agregar/:monedaBase', component: AddComponent },
  // { path: '', component: ListComponent },
   { path: 'listar', component: ListComponent },
   { path: '', redirectTo: '/listar', pathMatch: 'full' },
];

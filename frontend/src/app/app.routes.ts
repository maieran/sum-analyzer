import { Routes } from '@angular/router';
import { InputComponent } from './components/input/input.component';


export const routes: Routes = [
    { path: '', redirectTo: 'analysis', pathMatch: 'full'},
    { path: 'input', component: InputComponent },
];

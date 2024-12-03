import { Routes } from '@angular/router';
import { InputComponent } from './components/input/input.component';
import { ResultComponent  } from './components/result/result.component';


export const routes: Routes = [
    { path: '', redirectTo: 'analysis', pathMatch: 'full'},
    { path: 'input', component: InputComponent },
    { path: 'result', component: ResultComponent }
];

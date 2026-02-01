import { Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { ConversationsListComponent } from './conversations-list/conversations-list.component';
import {RegisterComponent} from './user/register/register.component';

export const routes: Routes = [
	{ path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
	{ path: 'conversations', component: ConversationsListComponent },
	{ path: '', redirectTo: 'login', pathMatch: 'full' }
];

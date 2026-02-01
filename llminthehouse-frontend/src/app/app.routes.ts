import { Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import {RegisterComponent} from './user/register/register.component';
import {ConversationsListComponent} from './conversation/conversations-list/conversations-list.component';
import {ConversationComponent} from './conversation/conversation-component/conversation.component';

export const routes: Routes = [
	{ path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
	{ path: 'conversations', component: ConversationsListComponent },
  {path: 'conversation/:uuid', component: ConversationComponent},
	{ path: '', redirectTo: 'login', pathMatch: 'full' }
];

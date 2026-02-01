import { Injectable } from '@angular/core';
import {UserWebDTO} from '../api/backend-api';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user: UserWebDTO | undefined;

  constructor() { }

  setUser(user: UserWebDTO): void {
    this.user = user;
  }
}

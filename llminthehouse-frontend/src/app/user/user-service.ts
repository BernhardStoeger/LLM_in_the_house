import { Injectable } from '@angular/core';
import {UserWebDTO} from '../api/backend-api';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user: UserWebDTO | undefined;
  localStorageKey = 'user';

  constructor() { }

  loadUserFromLocalStorage(): Promise<boolean> {
    return new Promise<boolean>((resolve) => {
      let user = localStorage.getItem(this.localStorageKey);
      if (user && user != '') {
        this. user = JSON.parse(user) as UserWebDTO;
        return resolve(true);
      }
      resolve(false)
    });
  }

  setUser(user: UserWebDTO): void {
    this.user = user;
    localStorage.setItem(this.localStorageKey, JSON.stringify(user));
  }

  logout(): void {
    this.user = undefined;
    localStorage.setItem(this.localStorageKey, '');
  }
}

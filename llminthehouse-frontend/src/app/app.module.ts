import {UserService} from './user/user-service';

export function userInitServiceFactory(userService: UserService) {
  return () => userService.loadUserFromLocalStorage();
}

import {AfterViewInit, Component, inject} from '@angular/core';
import {FormControl, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService, LoginRequestWebDTO} from '../api/backend-api';
import {MatError, MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {Router} from '@angular/router';
import {UserService} from '../user/user-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [
    FormsModule,
    MatLabel,
    MatFormField,
    MatLabel,
    MatError,
    ReactiveFormsModule,
    MatInput,
    MatButton
  ]
})
export class LoginComponent implements AfterViewInit{
  router: Router = inject(Router);
  userService: UserService = inject(UserService);
  username = new FormControl('', [Validators.required]);
  password= new FormControl('', [Validators.required]);
  error: string = '';

  constructor(private authService: AuthService) {}

  ngAfterViewInit() {
    if (this.userService.user) {
      this.router.navigate(['/conversations']);
    }
  }

  login() {
    const loginRequest: LoginRequestWebDTO = {
      username: this.username.value ?? '',
      password: this.password.value ?? ''
    };
    this.authService.login(loginRequest).subscribe({
      next: response => {
        this.error = '';
        this.userService.setUser(response);
        this.router.navigate(['/conversations'])
      },
      error: () => {
        this.error = 'Invalid credentials';
      }
    });
  }

  register(): void {
    this.router.navigate(['/register']);
  }
}

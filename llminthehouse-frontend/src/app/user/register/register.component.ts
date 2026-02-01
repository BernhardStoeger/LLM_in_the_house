import {Component, inject} from '@angular/core';
import {MatError, MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {FormControl, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService, RegisterRequestWebDTO} from '../../api/backend-api';
import {MatButton} from '@angular/material/button';
import {MatDialog} from '@angular/material/dialog';
import {RegisterSuccessDialog} from '../register-success/register-success-dialog';
import {Router} from '@angular/router';

@Component({
  selector: 'llminthehouse-register',
  imports: [
    MatFormField,
    MatLabel,
    MatError,
    ReactiveFormsModule,
    FormsModule,
    MatInput,
    MatButton,
  ],
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  readonly dialog = inject(MatDialog);
  router: Router = inject(Router);

  username = new FormControl('', [Validators.required]);
  password= new FormControl('', [Validators.required]);
  passwordConfirm= new FormControl('', [Validators.required]);
  error: string = '';

  constructor(private authService: AuthService) {}

  register() {
    if (this.password.value != this.passwordConfirm.value) {
      this.error = 'Passwords are not the same';
      return;
    }
    const loginRequest: RegisterRequestWebDTO = {
      username: this.username.value ?? '',
      password: this.password.value ?? ''
    };
    this.authService.register(loginRequest).subscribe({
      next: () => {
        this.error = '';
        this.openSuccessDialog();
      },
      error: () => {
        this.error = 'Could not register';
      }
    });
  }

  openSuccessDialog(): void {
    const dialogRef = this.dialog.open(RegisterSuccessDialog, {});

    dialogRef.afterClosed().subscribe(result => {
      this.router.navigate(['/login']);
    });
  }
}

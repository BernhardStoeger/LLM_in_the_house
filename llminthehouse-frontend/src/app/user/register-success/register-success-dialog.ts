import {Component, inject} from '@angular/core';
import {MatButton} from '@angular/material/button';
import {MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';

@Component({
  selector: 'llminthehouse-register-success',
  imports: [
    MatButton,
    MatDialogTitle,
    MatDialogContent
  ],
  templateUrl: './register-success-dialog.html'
})
export class RegisterSuccessDialog {
  readonly dialogRef = inject(MatDialogRef<RegisterSuccessDialog>);

  ok(): void {
    this.dialogRef.close();
  }

}

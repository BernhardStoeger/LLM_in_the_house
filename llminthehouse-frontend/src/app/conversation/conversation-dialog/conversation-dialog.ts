import {Component, inject} from '@angular/core';
import {MatButton} from '@angular/material/button';
import {MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {FormControl, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';

@Component({
  selector: 'llminthehouse-conversation-dialog',
  imports: [
    MatButton,
    MatDialogContent,
    MatDialogTitle,
    MatFormField,
    MatInput,
    MatLabel,
    ReactiveFormsModule
  ],
  templateUrl: './conversation-dialog.html'
})
export class ConversationDialog {

  readonly dialogRef = inject(MatDialogRef<ConversationDialog>);

  name = new FormControl('', [Validators.required]);

  ok(): void {
    this.dialogRef.close();
  }
}

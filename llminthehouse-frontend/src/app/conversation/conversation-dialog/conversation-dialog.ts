import {Component, inject} from '@angular/core';
import {MatButton} from '@angular/material/button';
import {MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {FormControl, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {ConversationService, CreateConversationWebDTO} from '../../api/backend-api';
import {UserService} from '../../user/user-service';

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

  conversationService: ConversationService = inject(ConversationService);
  userService: UserService = inject(UserService);
  readonly dialogRef = inject(MatDialogRef<ConversationDialog>);

  name = new FormControl('', [Validators.required]);

  ok(): void {
    const conversation: CreateConversationWebDTO = {
      useruuid: this.userService.getUserUUID(),
      title: this.name.value ?? ''
    }
    this.conversationService.createConversation(conversation).subscribe(res => {
      this.dialogRef.close(res);
    });
  }
}

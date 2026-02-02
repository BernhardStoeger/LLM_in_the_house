import {Component, inject, OnInit, signal} from '@angular/core';
import {
  MatCard,
  MatCardContent, MatCardFooter,
  MatCardHeader,
  MatCardTitle
} from '@angular/material/card';
import {MatIconButton} from '@angular/material/button';
import {ConversationService, ConversationWebDTO, MessageWebDTO} from '../../api/backend-api';
import {ActivatedRoute} from '@angular/router';
import {MatProgressSpinner} from '@angular/material/progress-spinner';
import {DatePipe} from '@angular/common';
import {MatFormField, MatInput, MatLabel, MatSuffix} from '@angular/material/input';
import {MatIcon} from '@angular/material/icon';
import {FormControl, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'llminthehouse-conversation.component',
  imports: [
    MatCard,
    MatCardHeader,
    MatCardContent,
    MatCardTitle,
    MatProgressSpinner,
    DatePipe,
    MatFormField,
    MatLabel,
    MatInput,
    MatCardFooter,
    MatIcon,
    ReactiveFormsModule,
    MatIconButton,
    MatSuffix
  ],
  templateUrl: './conversation.component.html'
})
export class ConversationComponent implements OnInit {
  conversationService: ConversationService = inject(ConversationService);
  activatedRoute: ActivatedRoute = inject(ActivatedRoute);
  conversation = signal<ConversationWebDTO>({});
  message = new FormControl('', []);
  uuid: string = '';

  isLoading = signal(false);

  ngOnInit() {
    this.isLoading.set(true);
    this.activatedRoute.paramMap.subscribe(params => {
      this.uuid = params.get('uuid') ?? '';
      if (this.uuid) {
        this.conversationService.getConversation(this.uuid).subscribe(conversation => {
          this.conversation.set(conversation);
          this.isLoading.set(false);
        });
      }
    });
  }

  sendMessage(): void {
    this.isLoading.set(true);
    const message: MessageWebDTO = {
      content: this.message.value ?? '',
    }
    this.conversationService.addMessage(this.uuid, message).subscribe(res => {
      const conversation = this.conversation();
      for (const msg of res) {
        conversation.messages?.push(msg);
      }
      this.conversation.set(conversation);
      this.isLoading.set(false);
      this.message.setValue('');
    });
  }
}

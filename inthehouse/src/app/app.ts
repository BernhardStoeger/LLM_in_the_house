
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LLMChatService, LLMModel, ChatMessage } from './llm-chat.service';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatListModule,
    MatSelectModule,
    MatToolbarModule,
    HttpClientModule
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App implements OnInit {
  llms: LLMModel[] = [];
  selectedLLM: LLMModel | null = null;
  messages: ChatMessage[] = [];
  userInput = '';
  loading = false;

  constructor(private chatService: LLMChatService) {}

  ngOnInit() {
    this.llms = this.chatService.getAvailableLLMs();
    if (this.llms.length > 0) {
      this.selectedLLM = this.llms[0];
    }
  }

  sendMessage() {
    if (!this.userInput.trim() || !this.selectedLLM) return;
    const userMsg: ChatMessage = {
      role: 'user',
      content: this.userInput,
      timestamp: new Date()
    };
    this.messages.push(userMsg);
    const input = this.userInput;
    this.userInput = '';
    this.loading = true;
    this.chatService.sendMessage(this.selectedLLM, input).subscribe({
      next: (response) => {
        this.messages.push({
          role: 'llm',
          content: response,
          timestamp: new Date()
        });
        this.loading = false;
      },
      error: (err) => {
        this.messages.push({
          role: 'llm',
          content: 'Error: Unable to get response from LLM.',
          timestamp: new Date()
        });
        this.loading = false;
      }
    });
  }
}

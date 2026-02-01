import { Component, Output, EventEmitter, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConversationWebDTO } from '../api/backend-api';
import {
  MatCell, MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef,
  MatRow, MatRowDef,
  MatTable
} from '@angular/material/table';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-conversations-list',
  templateUrl: './conversations-list.component.html',
  imports: [
    MatTable,
    MatHeaderCell,
    MatCell,
    MatHeaderRow,
    MatRow,
    MatColumnDef,
    MatHeaderCellDef,
    MatCellDef,
    MatHeaderRowDef,
    MatRowDef,
    DatePipe
  ],
  styleUrls: ['./conversations-list.component.scss']
})
export class ConversationsListComponent {
  @Input() userId: number | null = null;
  @Output() selectConversation = new EventEmitter<ConversationWebDTO>();
  conversations: ConversationWebDTO[] = [];

  constructor(private http: HttpClient) {}

  ngOnChanges() {
    if (this.userId != null) {
      this.loadConversations();
    }
  }

  loadConversations() {
    this.http.get<ConversationWebDTO[]>(`/api/conversations/user/${this.userId}`).subscribe(convs => {
      this.conversations = convs;
    });
  }

  onSelect(conversation: ConversationWebDTO) {
    this.selectConversation.emit(conversation);
  }
}

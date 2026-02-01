import {Component, AfterViewInit, inject} from '@angular/core';
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
import {ConversationListWebDTO, ConversationService, ConversationWebDTO} from '../../api/backend-api';
import {MatPaginator} from '@angular/material/paginator';
import {Router} from '@angular/router';
import {UserService} from '../../user/user-service';

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
    DatePipe,
    MatPaginator
  ]
})
export class ConversationsListComponent implements AfterViewInit {

  router: Router = inject(Router);
  userService: UserService = inject(UserService);
  conversationService: ConversationService = inject(ConversationService);

  conversations: ConversationListWebDTO[] = [];

  ngAfterViewInit() {
    this.loadConversations();
  }

  loadConversations() {
    const userUUID = this.userService.user?.uuid;
    if (!userUUID) {
      return;
    }
    this.conversationService.getConversationsForUser(userUUID).subscribe(res =>
      this.conversations = res)
  }

  onSelect(conversation: ConversationWebDTO) {
    this.router.navigate(['/conversation', conversation.uuid])
  }
}

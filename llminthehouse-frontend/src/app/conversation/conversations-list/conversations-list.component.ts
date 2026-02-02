import {Component, inject, OnInit} from '@angular/core';
import {
  MatCell, MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef,
  MatRow, MatRowDef,
  MatTable, MatTableDataSource
} from '@angular/material/table';
import {DatePipe} from '@angular/common';
import {ConversationListWebDTO, ConversationService, ConversationWebDTO} from '../../api/backend-api';
import {MatPaginator} from '@angular/material/paginator';
import {Router} from '@angular/router';
import {UserService} from '../../user/user-service';
import {MatDialog} from '@angular/material/dialog';
import {MatButton} from '@angular/material/button';
import {ConversationDialog} from '../conversation-dialog/conversation-dialog';

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
    MatPaginator,
    MatButton
  ]
})
export class ConversationsListComponent implements OnInit {

  dialog = inject(MatDialog);
  router: Router = inject(Router);
  userService: UserService = inject(UserService);
  conversationService: ConversationService = inject(ConversationService);

  dataSource: MatTableDataSource<ConversationListWebDTO> = new MatTableDataSource();

  ngOnInit() {
    this.loadConversations();
  }

  loadConversations() {
    const userUUID = this.userService.user?.uuid;
    if (!userUUID) {
      return;
    }
    this.conversationService.getConversationsForUser(userUUID).subscribe(res =>
      this.dataSource.data = res
    );
  }

  onSelect(conversation: ConversationWebDTO) {
    this.router.navigate(['/conversation', conversation.uuid])
  }

  openCreateDialog(): void {
    const dialogRef = this.dialog.open(ConversationDialog, {});

    dialogRef.afterClosed().subscribe(res => {
      if (res) {
        this.onSelect(res);
      }
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { UserDto } from 'src/app/domain/dto.namespace';
import { BaseDeleteRecordComponent } from 'src/app/table/base-table/base-delete-record/base-delete-record.component';
import { AdminUsersModuleService } from '../admin-users-module.service';

@Component({
  selector: 'app-delete-user-modal',
  templateUrl: './delete-user-modal.component.html',
  styleUrls: ['./delete-user-modal.component.css']
})
export class DeleteUserModalComponent extends BaseDeleteRecordComponent<UserDto> implements OnInit {

  constructor(
    protected override readonly service: AdminUsersModuleService,
    protected override readonly modal: NgbActiveModal
  ) {
    super(service, modal);
  }
}

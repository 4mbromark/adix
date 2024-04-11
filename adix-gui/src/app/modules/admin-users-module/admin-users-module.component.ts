import { AddEditUserModalComponent } from './add-edit-user-modal/add-edit-user-modal.component';
import { UserDto } from './../../domain/dto.namespace';
import { BaseTableComponent } from 'src/app/table/base-table/base-table.component';
import { Component, OnInit } from '@angular/core';
import { Module, MODULE_ADMIN_USERS } from '../modules.namespace';
import { ColDef } from 'ag-grid-community';
import { AdminUsersModuleService } from './admin-users-module.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TABLE_ADMIN_USERS_MODULE } from '../modules-table.namespace';
import { DeleteUserModalComponent } from './delete-user-modal/delete-user-modal.component';

@Component({
  selector: 'app-admin-users-module',
  templateUrl: './../../table/base-table/base-table.component.html',
  styleUrls: ['./../../table/base-table/base-table.component.css']
})
export class AdminUsersModuleComponent extends BaseTableComponent<UserDto> implements OnInit {
  protected override module: Module = MODULE_ADMIN_USERS;

  protected override columnDefs: ColDef<UserDto>[] = TABLE_ADMIN_USERS_MODULE;

  constructor(
    private readonly adminUsersModuleService: AdminUsersModuleService,
    private readonly modal: NgbModal
  ) {
    super(adminUsersModuleService);
  }

  protected override add(): void {
    this.modal.open(AddEditUserModalComponent, { size: 'lg', backdrop: 'static' });
  }
}

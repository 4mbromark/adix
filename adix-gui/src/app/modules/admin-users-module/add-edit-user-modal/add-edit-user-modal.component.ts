import { UserDto } from './../../../domain/dto.namespace';
import { BaseAddEditRecordComponent } from './../../../table/base-table/base-add-edit-record/base-add-edit-record.component';
import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormControl, Validators } from '@angular/forms';
import { AdminUsersModuleService } from '../admin-users-module.service';

@Component({
  selector: 'app-add-edit-user-modal',
  templateUrl: './add-edit-user-modal.component.html',
  styleUrls: ['./add-edit-user-modal.component.css']
})
export class AddEditUserModalComponent extends BaseAddEditRecordComponent<UserDto> implements OnInit {
  protected code: FormControl<string> = new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(2)]);
  protected emailAddress: FormControl<string> = new FormControl('', [Validators.required, Validators.email]);
  protected firstName: FormControl<string> = new FormControl('', [Validators.required]);
  protected lastName: FormControl<string> = new FormControl('', [Validators.required]);
  protected note: FormControl<string> = new FormControl('');

  constructor(
    protected override readonly service: AdminUsersModuleService,
    protected override readonly modal: NgbActiveModal
  ) {
    super(service, modal);
  }

  protected override create(): UserDto {
    return new UserDto(this.code.value, this.emailAddress.value, this.firstName.value, this.lastName.value, this.note.value);
  }

  protected override valid(): boolean {
    return this.firstName.valid && this.lastName.valid && this.code.valid && this.emailAddress.valid && this.note.valid;
  }
}

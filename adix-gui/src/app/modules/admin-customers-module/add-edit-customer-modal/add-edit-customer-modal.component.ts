import { CUSTOMER_ACCOUNTING_TYPE_LIST, CustomerAccountingType, CustomerAccountingTypeOption, CustomerDto, UserDto } from './../../../domain/dto.namespace';
import { BaseAddEditRecordComponent } from './../../../table/base-table/base-add-edit-record/base-add-edit-record.component';
import { FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AdminCustomersModuleService } from '../admin-customers-module.service';

@Component({
  selector: 'app-add-edit-customer-modal',
  templateUrl: './add-edit-customer-modal.component.html',
  styleUrls: ['./add-edit-customer-modal.component.css']
})
export class AddEditCustomerModalComponent extends BaseAddEditRecordComponent<CustomerDto> implements OnInit {
  protected code: FormControl<string> = new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(2)]);
  protected businessName: FormControl<string> = new FormControl('', [Validators.required]);
  protected vatNumber: FormControl<string> = new FormControl('', [Validators.required, Validators.minLength(11), Validators.maxLength(11)]);
  protected accountingType: FormControl<string> = new FormControl(CustomerAccountingType.INTERNAL, [Validators.required]);
  protected note: FormControl<string> = new FormControl('');

  protected customerAccountingTypes: CustomerAccountingTypeOption[] = CUSTOMER_ACCOUNTING_TYPE_LIST;

  constructor(
    protected override readonly service: AdminCustomersModuleService,
    protected override readonly modal: NgbActiveModal
  ) {
    super(service, modal);
  }

  protected override create(): CustomerDto {
    return new CustomerDto(this.code.value, this.businessName.value, this.vatNumber.value, this.accountingType.value, this.note.value);
  }

  protected override valid(): boolean {
    return this.code.valid && this.businessName.valid && this.vatNumber.valid && this.accountingType.valid && this.note.valid;
  }
}

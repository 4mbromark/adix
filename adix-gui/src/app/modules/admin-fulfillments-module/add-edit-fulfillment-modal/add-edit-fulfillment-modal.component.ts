import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { FulfillmentDto } from 'src/app/domain/dto.namespace';
import { BaseAddEditRecordComponent } from 'src/app/table/base-table/base-add-edit-record/base-add-edit-record.component';
import { AdminFulfillmentsModuleService } from '../admin-fulfillments-module.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-add-edit-fulfillment-modal',
  templateUrl: './add-edit-fulfillment-modal.component.html',
  styleUrls: ['./add-edit-fulfillment-modal.component.css']
})
export class AddEditFulfillmentModalComponent extends BaseAddEditRecordComponent<FulfillmentDto> implements OnInit {
  protected name: FormControl<string> = new FormControl('', [Validators.required]);
  protected description: FormControl<string> = new FormControl('', [Validators.required]);
  protected monthRecurrence: FormControl<number> = new FormControl(1, [Validators.required, Validators.min(1), Validators.max(12)]);
  protected note: FormControl<string> = new FormControl('');

  constructor(
    protected override readonly service: AdminFulfillmentsModuleService,
    protected override readonly modal: NgbActiveModal
  ) {
    super(service, modal);
  }

  protected override create(): FulfillmentDto {
    return new FulfillmentDto(this.name.value, this.description.value, this.monthRecurrence.value, this.note.value);
  }

  protected override valid(): boolean {
    return this.name.valid && this.description.valid && this.monthRecurrence.valid && this.note.valid;
  }
}

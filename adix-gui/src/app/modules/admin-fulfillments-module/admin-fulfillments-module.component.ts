import { Component, OnInit } from '@angular/core';
import { FulfillmentDto } from 'src/app/domain/dto.namespace';
import { MODULE_ADMIN_FULFILLMENTS, Module } from '../modules.namespace';
import { AdminFulfillmentsModuleService } from './admin-fulfillments-module.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BaseTableComponent } from 'src/app/table/base-table/base-table.component';
import { TABLE_ADMIN_FULFILLMENTS_MODULE } from '../modules-table.namespace';
import { ColDef } from 'ag-grid-community';
import { AddEditFulfillmentModalComponent } from './add-edit-fulfillment-modal/add-edit-fulfillment-modal.component';

@Component({
  selector: 'app-admin-fulfillments-module',
  templateUrl: './../../table/base-table/base-table.component.html',
  styleUrls: ['./../../table/base-table/base-table.component.css']
})
export class AdminFulfillmentsModuleComponent extends BaseTableComponent<FulfillmentDto> implements OnInit {
  protected override module: Module = MODULE_ADMIN_FULFILLMENTS;

  protected override columnDefs: ColDef<FulfillmentDto>[] = TABLE_ADMIN_FULFILLMENTS_MODULE;

  constructor(
    private readonly adminFulfillmentsModuleService: AdminFulfillmentsModuleService,
    private readonly modal: NgbModal
  ) {
    super(adminFulfillmentsModuleService);
  }

  protected add(): void {
    this.modal.open(AddEditFulfillmentModalComponent, { size: 'lg', backdrop: 'static' })
  }
}

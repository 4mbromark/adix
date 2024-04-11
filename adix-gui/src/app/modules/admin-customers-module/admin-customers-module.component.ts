import { AddEditCustomerModalComponent } from './add-edit-customer-modal/add-edit-customer-modal.component';
import { NgbModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AdminCustomersModuleService } from './admin-customers-module.service';
import { Module, MODULE_ADMIN_CUSTOMERS } from './../modules.namespace';
import { CustomerDto } from './../../domain/dto.namespace';
import { BaseTableComponent } from 'src/app/table/base-table/base-table.component';
import { Component, OnInit } from '@angular/core';
import { TABLE_ADMIN_CUSTOMERS_MODULE } from '../modules-table.namespace';
import { ColDef } from 'ag-grid-community';

@Component({
  selector: 'app-admin-customers-module',
  templateUrl: './../../table/base-table/base-table.component.html',
  styleUrls: ['./../../table/base-table/base-table.component.css']
})
export class AdminCustomersModuleComponent extends BaseTableComponent<CustomerDto> implements OnInit {
  protected override module: Module = MODULE_ADMIN_CUSTOMERS;

  protected override columnDefs: ColDef<CustomerDto>[] = TABLE_ADMIN_CUSTOMERS_MODULE;

  constructor(
    private readonly adminCustomersModuleService: AdminCustomersModuleService,
    private readonly modal: NgbModal
  ) {
    super(adminCustomersModuleService);
  }

  protected override add(): void {
    this.modal.open(AddEditCustomerModalComponent, { size: 'lg', backdrop: 'static' });
  }
}

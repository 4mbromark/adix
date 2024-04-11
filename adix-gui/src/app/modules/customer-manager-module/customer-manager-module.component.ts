import { TABLE_CUSTOMER_RESPONSIBLE_MODULE } from './../modules-table.namespace';
import { MODULE_CUSTOMER_RESPONSIBLE, Module } from './../modules.namespace';
import { CustomerDto } from './../../domain/dto.namespace';
import { BaseTableComponent } from 'src/app/table/base-table/base-table.component';
import { Component, OnInit } from '@angular/core';
import { ColDef } from 'ag-grid-community';
import { CustomerManagerModuleService } from './customer-manager-module.service';

@Component({
  selector: 'app-customer-manager-module',
  templateUrl: './../../table/base-table/base-table.component.html',
  styleUrls: ['./../../table/base-table/base-table.component.css']
})
export class CustomerResponsibleModuleComponent extends BaseTableComponent<CustomerDto> implements OnInit {
  protected override module: Module = MODULE_CUSTOMER_RESPONSIBLE;

  protected override columnDefs: ColDef<CustomerDto>[] = TABLE_CUSTOMER_RESPONSIBLE_MODULE;

  constructor(
    private readonly customerManagerModuleService: CustomerManagerModuleService
  ) {
    super(customerManagerModuleService);
  }

  protected override add(): void {

  }
}

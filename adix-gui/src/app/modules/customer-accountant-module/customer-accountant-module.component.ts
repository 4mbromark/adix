import { MODULE_CUSTOMER_ACCOUNTANT } from './../modules.namespace';
import { BaseTableComponent } from 'src/app/table/base-table/base-table.component';
import { Component, OnInit } from '@angular/core';
import { CustomerDto } from 'src/app/domain/dto.namespace';
import { Module } from '../modules.namespace';
import { CustomerAccountantModuleService } from './customer-accountant-module.service';
import { ColDef } from 'ag-grid-community';
import { TABLE_CUSTOMER_ACCOUNTANT_MODULE } from '../modules-table.namespace';

@Component({
  selector: 'app-customer-accountant-module',
  templateUrl: './../../table/base-table/base-table.component.html',
  styleUrls: ['./../../table/base-table/base-table.component.css']
})
export class CustomerAccountantModuleComponent extends BaseTableComponent<CustomerDto> implements OnInit {
  protected override module: Module = MODULE_CUSTOMER_ACCOUNTANT;

  protected override columnDefs: ColDef<CustomerDto>[] = TABLE_CUSTOMER_ACCOUNTANT_MODULE;

  constructor(
    private readonly customerAccountantModuleService: CustomerAccountantModuleService
  ) {
    super(customerAccountantModuleService);
  }

  protected override add(): void {

  }
}

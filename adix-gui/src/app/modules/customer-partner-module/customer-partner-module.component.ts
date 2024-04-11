import { TABLE_CUSTOMER_PARTNER_MODULE } from './../modules-table.namespace';
import { BaseTableComponent } from 'src/app/table/base-table/base-table.component';
import { Module, MODULE_CUSTOMER_PARTNER } from './../modules.namespace';
import { CustomerPartnerModuleService } from './customer-partner-module.service';
import { Component, OnInit } from '@angular/core';
import { ColDef } from 'ag-grid-community';
import { CustomerDto } from 'src/app/domain/dto.namespace';

@Component({
  selector: 'app-customer-partner-module',
  templateUrl: './../../table/base-table/base-table.component.html',
  styleUrls: ['./../../table/base-table/base-table.component.css']
})
export class CustomerPartnerModuleComponent extends BaseTableComponent<CustomerDto> implements OnInit {
  protected override module: Module = MODULE_CUSTOMER_PARTNER;

  protected override columnDefs: ColDef<CustomerDto>[] = TABLE_CUSTOMER_PARTNER_MODULE;

  constructor(
    private readonly customerPartnerModuleService: CustomerPartnerModuleService
  ) {
    super(customerPartnerModuleService);
  }

  protected override add(): void {

  }
}

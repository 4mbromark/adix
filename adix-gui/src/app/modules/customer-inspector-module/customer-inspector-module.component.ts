import { TABLE_CUSTOMER_INSPECTOR_MODULE } from './../modules-table.namespace';
import { BaseTableComponent } from 'src/app/table/base-table/base-table.component';
import { Module, MODULE_CUSTOMER_INSPECTOR } from './../modules.namespace';
import { Component, OnInit } from '@angular/core';
import { CustomerInspectorModuleService } from './customer-inspector-module.service';
import { ColDef } from 'ag-grid-community';
import { CustomerDto } from 'src/app/domain/dto.namespace';

@Component({
  selector: 'app-customer-inspector-module',
  templateUrl: './../../table/base-table/base-table.component.html',
  styleUrls: ['./../../table/base-table/base-table.component.css']
})
export class CustomerInspectorModuleComponent extends BaseTableComponent<CustomerDto> implements OnInit {
  protected override module: Module = MODULE_CUSTOMER_INSPECTOR;

  protected override columnDefs: ColDef<CustomerDto>[] = TABLE_CUSTOMER_INSPECTOR_MODULE;

  constructor(
    private readonly customerInspectorModuleService: CustomerInspectorModuleService
  ) {
    super(customerInspectorModuleService);
  }

  protected override add(): void {

  }
}

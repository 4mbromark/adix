import { HttpClient } from '@angular/common/http';
import { CustomerDto } from '../../domain/dto.namespace';
import { Injectable } from '@angular/core';
import { BaseTableService } from 'src/app/table/base-table/base-table.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerPartnerModuleService extends BaseTableService<CustomerDto> {
  protected override url: string = '/customers/partner';

  constructor(
    protected override readonly http: HttpClient
  ) {
    super(http);
  }
}

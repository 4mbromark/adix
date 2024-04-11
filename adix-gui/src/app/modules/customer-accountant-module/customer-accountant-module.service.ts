import { HttpClient } from '@angular/common/http';
import { CustomerDto } from '../../domain/dto.namespace';
import { Injectable } from '@angular/core';
import { BaseTableService } from 'src/app/table/base-table/base-table.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerAccountantModuleService extends BaseTableService<CustomerDto> {

  protected override url: string = '/customers/accountant';

  constructor(
    protected override readonly http: HttpClient
  ) {
    super(http);
  }
}

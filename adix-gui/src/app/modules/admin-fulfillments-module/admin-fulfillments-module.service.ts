import { FulfillmentDto, UserDto } from '../../domain/dto.namespace';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseTableService } from 'src/app/table/base-table/base-table.service';

@Injectable({
  providedIn: 'root'
})
export class AdminFulfillmentsModuleService extends BaseTableService<FulfillmentDto> {
  protected override url: string = '/fulfillments';

  constructor(
    protected override readonly http: HttpClient
  ) {
    super(http);
  }
}

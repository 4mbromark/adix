import { UserDto } from './../../domain/dto.namespace';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseTableService } from 'src/app/table/base-table/base-table.service';

@Injectable({
  providedIn: 'root'
})
export class AdminUsersModuleService extends BaseTableService<UserDto> {
  protected override url: string = '/users';

  constructor(
    protected override readonly http: HttpClient
  ) {
    super(http);
  }
}

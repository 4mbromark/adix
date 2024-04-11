import { Component, OnInit } from '@angular/core';
import { CellRenderingAbstractBaseComponent } from '../cell-rendering-base/cell-rendering-base.component';
import { CustomerDto } from 'src/app/domain/dto.namespace';
import { Clipboard } from '@angular/cdk/clipboard';

@Component({
  selector: 'app-cell-rendering-customer',
  templateUrl: './cell-rendering-customer.component.html',
  styleUrls: ['./cell-rendering-customer.component.css']
})
export class CellRenderingCustomerComponent extends CellRenderingAbstractBaseComponent<CustomerDto> implements OnInit {

  constructor(
    protected override readonly clipboard: Clipboard
  ) {
    super(clipboard);
  }
}

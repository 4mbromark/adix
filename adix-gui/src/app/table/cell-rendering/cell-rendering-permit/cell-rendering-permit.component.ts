import { Component, OnInit } from '@angular/core';
import { UserPermits } from 'src/app/authentication/authentication.namespace';
import { CellRenderingAbstractBaseComponent } from '../cell-rendering-base/cell-rendering-base.component';
import { Clipboard } from '@angular/cdk/clipboard';

@Component({
  selector: 'app-cell-rendering-permit',
  templateUrl: './cell-rendering-permit.component.html',
  styleUrls: ['./cell-rendering-permit.component.css']
})
export class CellRenderingPermitComponent extends CellRenderingAbstractBaseComponent<UserPermits[]> implements OnInit {

  constructor(
    protected override readonly clipboard: Clipboard
  ) {
    super(clipboard);
  }
}

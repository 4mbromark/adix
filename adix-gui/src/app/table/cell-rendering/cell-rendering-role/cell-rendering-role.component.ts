import { Component, OnInit } from '@angular/core';
import { CellRenderingAbstractBaseComponent } from '../cell-rendering-base/cell-rendering-base.component';
import { UserRoles } from 'src/app/authentication/authentication.namespace';
import { Clipboard } from '@angular/cdk/clipboard';

@Component({
  selector: 'app-cell-rendering-role',
  templateUrl: './cell-rendering-role.component.html',
  styleUrls: ['./cell-rendering-role.component.css']
})
export class CellRenderingRoleComponent extends CellRenderingAbstractBaseComponent<UserRoles[]> implements OnInit {

  constructor(
    protected override readonly clipboard: Clipboard
  ) {
    super(clipboard);
  }
}

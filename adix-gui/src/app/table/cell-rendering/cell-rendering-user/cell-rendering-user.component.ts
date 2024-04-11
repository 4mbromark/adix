import { UserDto } from './../../../domain/dto.namespace';
import { Component, OnInit } from '@angular/core';
import { CellRenderingAbstractBaseComponent } from '../cell-rendering-base/cell-rendering-base.component';
import { Clipboard } from '@angular/cdk/clipboard';

@Component({
  selector: 'app-cell-rendering-user',
  templateUrl: './cell-rendering-user.component.html',
  styleUrls: ['./cell-rendering-user.component.css']
})
export class CellRenderingUserComponent extends CellRenderingAbstractBaseComponent<UserDto> implements OnInit {

  constructor(
    protected override readonly clipboard: Clipboard
  ) {
    super(clipboard);
  }
}

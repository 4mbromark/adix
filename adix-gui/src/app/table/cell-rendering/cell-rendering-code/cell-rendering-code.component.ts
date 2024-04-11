import { Component, OnInit } from '@angular/core';
import { CellRenderingAbstractBaseComponent } from '../cell-rendering-base/cell-rendering-base.component';
import { Clipboard } from '@angular/cdk/clipboard';

@Component({
  selector: 'app-cell-rendering-code',
  templateUrl: './cell-rendering-code.component.html',
  styleUrls: ['./cell-rendering-code.component.css']
})
export class CellRenderingCodeComponent extends CellRenderingAbstractBaseComponent<string> implements OnInit {

  constructor(
    protected override readonly clipboard: Clipboard
  ) {
    super(clipboard);
  }
}

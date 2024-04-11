import { Component, OnInit } from '@angular/core';
import { CellRenderingAbstractBaseComponent } from '../cell-rendering-base/cell-rendering-base.component';
import { Clipboard } from '@angular/cdk/clipboard';

@Component({
  selector: 'app-cell-rendering-action',
  templateUrl: './cell-rendering-action.component.html',
  styleUrls: ['./cell-rendering-action.component.css']
})
export class CellRenderingActionComponent extends CellRenderingAbstractBaseComponent<any> implements OnInit {

  constructor(
    protected override readonly clipboard: Clipboard
  ) {
    super(clipboard);
  }

  protected editRecord(): void {

  }

  protected deleteRecord(): void {

  }
}

import { Component, OnInit } from '@angular/core';
import { CellRenderingAbstractBaseComponent } from '../cell-rendering-base/cell-rendering-base.component';
import { Clipboard } from '@angular/cdk/clipboard';

@Component({
  selector: 'app-cell-rendering-enabled',
  templateUrl: './cell-rendering-enabled.component.html',
  styleUrls: ['./cell-rendering-enabled.component.css']
})
export class CellRenderingEnabledComponent extends CellRenderingAbstractBaseComponent<boolean> implements OnInit {

  constructor(
    protected override readonly clipboard: Clipboard
  ) {
    super(clipboard);
  }

  protected toggleEnabled(): void {

  }
}

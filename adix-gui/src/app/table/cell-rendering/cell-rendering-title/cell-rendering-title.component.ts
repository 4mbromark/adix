import { Component, OnInit } from '@angular/core';
import { CellRenderingAbstractBaseComponent } from '../cell-rendering-base/cell-rendering-base.component';
import { Clipboard } from '@angular/cdk/clipboard';

@Component({
  selector: 'app-cell-rendering-title',
  templateUrl: './cell-rendering-title.component.html',
  styleUrls: ['./cell-rendering-title.component.css']
})
export class CellRenderingTitleComponent extends CellRenderingAbstractBaseComponent<string> implements OnInit {

  constructor(
    protected override readonly clipboard: Clipboard
  ) {
    super(clipboard);
  }
}

import { Component, OnInit } from '@angular/core';
import { CellRenderingAbstractBaseComponent } from '../cell-rendering-base/cell-rendering-base.component';
import { Clipboard } from '@angular/cdk/clipboard';

@Component({
  selector: 'app-cell-rendering-credential',
  templateUrl: './cell-rendering-credential.component.html',
  styleUrls: ['./cell-rendering-credential.component.css']
})
export class CellRenderingCredentialComponent extends CellRenderingAbstractBaseComponent<any> implements OnInit {

  constructor(
    protected override readonly clipboard: Clipboard
  ) {
    super(clipboard);
  }

  protected manageCredential(): void {

  }
}

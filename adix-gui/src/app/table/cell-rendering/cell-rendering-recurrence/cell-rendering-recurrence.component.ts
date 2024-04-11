import { Component, OnInit } from '@angular/core';
import { CellRenderingAbstractBaseComponent } from '../cell-rendering-base/cell-rendering-base.component';
import { Clipboard } from '@angular/cdk/clipboard';

@Component({
  selector: 'app-cell-rendering-recurrence',
  templateUrl: './cell-rendering-recurrence.component.html',
  styleUrls: ['./cell-rendering-recurrence.component.css']
})
export class CellRenderingRecurrenceComponent extends CellRenderingAbstractBaseComponent<number> implements OnInit {
  protected months: number[] = [];

  constructor(
    protected override readonly clipboard: Clipboard
  ) {
    super(clipboard);
  }

  override ngOnInit(): void {
    this.value = this.params.value;
    this.months = Array(this.value).fill(0).map((x, i) => i);
  }
}

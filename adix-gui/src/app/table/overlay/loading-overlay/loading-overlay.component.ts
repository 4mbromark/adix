import { Component, OnInit } from '@angular/core';
import { ILoadingOverlayAngularComp } from 'ag-grid-angular';
import { ILoadingOverlayParams } from 'ag-grid-community';

@Component({
  selector: 'app-loading-overlay',
  templateUrl: './loading-overlay.component.html',
  styleUrls: ['./loading-overlay.component.css']
})
export class LoadingOverlayComponent implements ILoadingOverlayAngularComp {
  params: ILoadingOverlayParams;

  constructor() { }

  agInit(params: ILoadingOverlayParams): void {
    this.params = params;
  }
}

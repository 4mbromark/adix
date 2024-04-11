import { Component, Directive, HostListener, OnInit, ViewChild } from '@angular/core';
import { IAfterGuiAttachedParams, ICellRendererParams } from 'ag-grid-community';
import { Clipboard } from '@angular/cdk/clipboard';


@Directive()
export abstract class CellRenderingAbstractBaseComponent<T> implements OnInit {
  protected params: ICellRendererParams;
  protected value: T;

  // service: BaseTableService;

  protected saving = false;

  @HostListener('contextmenu', ['$event'])
  onRightClick(event) {
    event.preventDefault();
    this.clipboard.copy(this.value as unknown as string);
    return false;
  }
  constructor(
    // protected lightning: LightningService,
    protected readonly clipboard: Clipboard
  ) {
    // this.service = this.lightning.getTableService();
  }

  ngOnInit(): void {
    this.value = this.params.value;
  }

  refresh(params: any): boolean {
    throw new Error('Method not implemented.');
  }
  agInit(params: ICellRendererParams): void {
    this.params = params;
  }
  afterGuiAttached?(params?: IAfterGuiAttachedParams): void {
    throw new Error('Method not implemented.');
  }

  triggerMenu(event: any): void {
    // this.trigger.openMenu();
    event.stopPropagation();
  }
}

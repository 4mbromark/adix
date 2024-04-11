import { Component, Directive, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { BaseTableService } from '../base-table.service';
import { BaseRecord } from 'src/app/domain/dto.namespace';

@Directive()
export abstract class BaseAddEditRecordComponent<T extends BaseRecord> implements OnInit {
  protected record: T;

  constructor(
    protected readonly service: BaseTableService<T>,
    protected readonly modal: NgbActiveModal
  ) { }

  ngOnInit(): void {
  }

  protected confirm(): void {
    if (!this.valid()) {
      return;
    }

    if (this.record && this.record.id) {
      this.service.edit(this.record, this.record.id).then(() => {
        this.close();
      });
    } else {
      this.record = this.create();
      this.service.add(this.record).then(() => {
        this.close();
      });
    }
  }

  protected abstract create(): T;

  protected abstract valid(): boolean;

  protected undo(): void {
    this.close();
  }

  private close(): void {
    this.modal.close();
  }
}

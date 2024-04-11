import { Component, Directive, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { BaseTableService } from '../base-table.service';
import { BaseRecord } from 'src/app/domain/dto.namespace';

@Directive()
export abstract class BaseDeleteRecordComponent<T extends BaseRecord> implements OnInit {
  protected record: T;

  constructor(
    protected readonly service: BaseTableService<T>,
    protected readonly modal: NgbActiveModal
  ) { }

  ngOnInit(): void {
  }

  protected confirm(): void {
    this.service.delete(this.record.id).then(() => {
      this.close();
    });
  }

  protected undo(): void {
    this.close();
  }

  private close(): void {
    this.modal.close();
  }
}

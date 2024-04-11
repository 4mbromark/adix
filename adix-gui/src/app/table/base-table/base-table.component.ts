import { Module } from './../../modules/modules.namespace';
import { Component, OnInit, Directive } from '@angular/core';
import { AgGridEvent, ColDef, ColGroupDef, ColumnApi, ColumnState, GridApi, GridOptions, INoRowsOverlayParams, RowClassParams, RowClickedEvent, RowStyle } from 'ag-grid-community';
import { CellRenderingUserComponent } from '../cell-rendering/cell-rendering-user/cell-rendering-user.component';
import { BaseTableService } from './base-table.service';
import { CellRenderingCustomerComponent } from '../cell-rendering/cell-rendering-customer/cell-rendering-customer.component';
import { CellRenderingCodeComponent } from '../cell-rendering/cell-rendering-code/cell-rendering-code.component';
import { LoadingOverlayComponent } from '../overlay/loading-overlay/loading-overlay.component';
import { CellRenderingActionComponent } from '../cell-rendering/cell-rendering-action/cell-rendering-action.component';
import { CellRenderingEnabledComponent } from '../cell-rendering/cell-rendering-enabled/cell-rendering-enabled.component';
import { CellRenderingTitleComponent } from '../cell-rendering/cell-rendering-title/cell-rendering-title.component';
import { CellRenderingCredentialComponent } from '../cell-rendering/cell-rendering-credential/cell-rendering-credential.component';
import { CellRenderingRecurrenceComponent } from '../cell-rendering/cell-rendering-recurrence/cell-rendering-recurrence.component';
import { BaseRecord } from 'src/app/domain/dto.namespace';
import { CellRenderingRoleComponent } from '../cell-rendering/cell-rendering-role/cell-rendering-role.component';
import { CellRenderingPermitComponent } from '../cell-rendering/cell-rendering-permit/cell-rendering-permit.component';

@Directive()
export abstract class BaseTableComponent<T extends BaseRecord> implements OnInit {
  protected abstract module: Module;

  protected rowData: T[] = [];
  protected columnDefs: ColDef[] = [];

  protected gridApi: GridApi;
  protected columnApi: ColumnApi;

  protected gridOptions: GridOptions = {
    rowData: this.rowData,
    rowHeight: 40,
    getRowStyle(params: RowClassParams<any>) {
      if (params.data.enabled === false) {
        return {
          'background-color': 'lightyellow',
          'color': 'silver',
        };
      } else {
        return {};
      }
    },
    columnDefs: this.columnDefs,
    defaultColDef: {
      headerName: 'Azioni',
      // headerComponent: 'headerRenderingComponent',
      headerComponentParams: {
        enableMenu: true,
        enableSorting: true
      },
      field: '',
      flex: 1,
      minWidth: 100,
      sortable: true,
      resizable: true,
      filter: true
    },
    suppressDragLeaveHidesColumns: true,
    suppressRowClickSelection: true,
    suppressCellSelection: true,
    suppressNoRowsOverlay: true,
    animateRows: true
  };

  protected frameworkComponents = {
    // emptyOverlayComponent: EmptyOverlayComponent,
    loadingOverlayComponent: LoadingOverlayComponent,

    // headerControllerRenderingComponent: HeaderControllerRenderingComponent,
    // headerRenderingComponent: HeaderRenderingComponent,

    codeRenderingComponent: CellRenderingCodeComponent,
    titleRenderingComponent: CellRenderingTitleComponent,
    userRenderingComponent: CellRenderingUserComponent,
    customerRenderingComponent: CellRenderingCustomerComponent,
    recurrenceRenderingComponent: CellRenderingRecurrenceComponent,
    credentialRenderingComponent: CellRenderingCredentialComponent,
    enabledRenderingComponent: CellRenderingEnabledComponent,
    actionRenderingComponent: CellRenderingActionComponent,
    roleRenderingComponent: CellRenderingRoleComponent,
    permitRenderingComponent: CellRenderingPermitComponent

    // projectRenderingComponent: ProjectRenderingComponent,
    // taskRenderingComponent: TaskRenderingComponent
  };
  protected emptyOverlayComponent = 'emptyOverlayComponent';
  protected loadingOverlayComponent = 'loadingOverlayComponent';

  protected emptyOverlayParams: INoRowsOverlayParams;

  protected loading = false;

  constructor(
    // protected lightning: LightningService,
    protected readonly service: BaseTableService<T>
  ) {
    // this.lightning.setTableService(this.service);
  }

  ngOnInit(): void {
    // this.service.buildService();
  }
  ngOnDestroy(): void {
    this.rowData = [];
    // this.service.closeInformations();
    // this.service.destroyService();
    // this.lightning.setTableService(null);
  }

  private loadObservables(): void {
    this.service.fetch();
    this.service.isLoading().subscribe((loading: boolean) => {
      this.loading = loading;

      if (loading) {
        this.gridApi.showLoadingOverlay();
      } else {
        this.gridApi.hideOverlay();

        if (this.rowData.length === 0) {
          this.gridApi.showNoRowsOverlay();
        }
      }
    });
    this.service.getRecords().subscribe((records: T[]) => {
      this.rowData = records;
    });
  }

  protected refresh(): void {
    this.service.fetch();
  }

  protected gridReady(params: AgGridEvent): void {
    this.gridApi = params.api as unknown as GridApi;
    this.columnApi = params.columnApi as unknown as ColumnApi;
    this.loadObservables();
  }

  protected abstract add(): void;
}

import { AuthenticationInterceptor } from './authentication/authentication.interceptor';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AgGridModule } from 'ag-grid-angular';
import { CellRenderingUserComponent } from './table/cell-rendering/cell-rendering-user/cell-rendering-user.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { CustomerPartnerModuleComponent } from './modules/customer-partner-module/customer-partner-module.component';
import { CustomerInspectorModuleComponent } from './modules/customer-inspector-module/customer-inspector-module.component';
import { CustomerResponsibleModuleComponent } from './modules/customer-manager-module/customer-manager-module.component';
import { CustomerAccountantModuleComponent } from './modules/customer-accountant-module/customer-accountant-module.component';
import { AdminCustomersModuleComponent } from './modules/admin-customers-module/admin-customers-module.component';
import { NgbDropdownModule, NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AddEditCustomerModalComponent } from './modules/admin-customers-module/add-edit-customer-modal/add-edit-customer-modal.component';
import { AdminUsersModuleComponent } from './modules/admin-users-module/admin-users-module.component';
import { BaseAddEditRecordComponent } from './table/base-table/base-add-edit-record/base-add-edit-record.component';
import { AddEditUserModalComponent } from './modules/admin-users-module/add-edit-user-modal/add-edit-user-modal.component';
import { ToastComponent } from './toast/toast.component';
import { ManagementModuleComponent } from './modules/management-module/management-module.component';
import { AdminFulfillmentsModuleComponent } from './modules/admin-fulfillments-module/admin-fulfillments-module.component';
import { AddEditFulfillmentModalComponent } from './modules/admin-fulfillments-module/add-edit-fulfillment-modal/add-edit-fulfillment-modal.component';
import { CellRenderingCustomerComponent } from './table/cell-rendering/cell-rendering-customer/cell-rendering-customer.component';
import { CellRenderingCodeComponent } from './table/cell-rendering/cell-rendering-code/cell-rendering-code.component';
import { StandardCodeComponent } from './standard/standard-code/standard-code.component';
import { StandardSpinnerComponent } from './standard/standard-spinner/standard-spinner.component';
import { LoadingOverlayComponent } from './table/overlay/loading-overlay/loading-overlay.component';
import { CellRenderingActionComponent } from './table/cell-rendering/cell-rendering-action/cell-rendering-action.component';
import { CellRenderingEnabledComponent } from './table/cell-rendering/cell-rendering-enabled/cell-rendering-enabled.component';
import { CellRenderingTitleComponent } from './table/cell-rendering/cell-rendering-title/cell-rendering-title.component';
import { CellRenderingCredentialComponent } from './table/cell-rendering/cell-rendering-credential/cell-rendering-credential.component';
import { CellRenderingRecurrenceComponent } from './table/cell-rendering/cell-rendering-recurrence/cell-rendering-recurrence.component';
import { DeleteUserModalComponent } from './modules/admin-users-module/delete-user-modal/delete-user-modal.component';
import { CellRenderingRoleComponent } from './table/cell-rendering/cell-rendering-role/cell-rendering-role.component';
import { CellRenderingPermitComponent } from './table/cell-rendering/cell-rendering-permit/cell-rendering-permit.component';
import { ManagePermitModalComponent } from './modules/admin-users-module/manage-permit-modal/manage-permit-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    CellRenderingUserComponent,
    LoginComponent,
    HomeComponent,
    SidebarComponent,
    CustomerPartnerModuleComponent,
    CustomerInspectorModuleComponent,
    CustomerResponsibleModuleComponent,
    CustomerAccountantModuleComponent,
    AdminCustomersModuleComponent,
    AddEditCustomerModalComponent,
    AdminUsersModuleComponent,
    AddEditUserModalComponent,
    ToastComponent,
    ManagementModuleComponent,
    AdminFulfillmentsModuleComponent,
    AddEditFulfillmentModalComponent,
    CellRenderingCustomerComponent,
    CellRenderingCodeComponent,
    StandardCodeComponent,
    StandardSpinnerComponent,
    LoadingOverlayComponent,
    CellRenderingActionComponent,
    CellRenderingEnabledComponent,
    CellRenderingTitleComponent,
    CellRenderingCredentialComponent,
    CellRenderingRecurrenceComponent,
    DeleteUserModalComponent,
    CellRenderingRoleComponent,
    CellRenderingPermitComponent,
    ManagePermitModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AgGridModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    NgbModalModule,
    NgbDropdownModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

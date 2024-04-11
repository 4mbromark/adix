import { AdminUsersModuleComponent } from './modules/admin-users-module/admin-users-module.component';
import { AdminCustomersModuleComponent } from './modules/admin-customers-module/admin-customers-module.component';
import { CustomerResponsibleModuleComponent } from './modules/customer-manager-module/customer-manager-module.component';
import { CustomerPartnerModuleComponent } from './modules/customer-partner-module/customer-partner-module.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationUnloggedGuard } from './authentication/authentication-unlogged.guard';
import { AuthenticationLoggedGuard } from './authentication/authentication-logged.guard';
import { HomeComponent } from './components/home/home.component';
import { CustomerInspectorModuleComponent } from './modules/customer-inspector-module/customer-inspector-module.component';
import { CustomerAccountantModuleComponent } from './modules/customer-accountant-module/customer-accountant-module.component';
import { RouteLink } from './app-routing.namespace';
import { AdminFulfillmentsModuleComponent } from './modules/admin-fulfillments-module/admin-fulfillments-module.component';

const routes: Routes = [
  {
    path: RouteLink.LOGIN, component: LoginComponent,
    canActivate: [AuthenticationUnloggedGuard]
  },
  {
    path: RouteLink.HOME, component: HomeComponent,
    canActivate: [AuthenticationLoggedGuard]
  },
  {
    path: RouteLink.ADMIN_USERS, component: AdminUsersModuleComponent,
    canActivate: [AuthenticationLoggedGuard]
  },
  {
    path: RouteLink.ADMIN_CUSTOMERS, component: AdminCustomersModuleComponent,
    canActivate: [AuthenticationLoggedGuard]
  },
  {
    path: RouteLink.ADMIN_FULFILLMENTS, component: AdminFulfillmentsModuleComponent,
    canActivate: [AuthenticationLoggedGuard]
  },
  {
    path: RouteLink.CUSTOMER_PARTNER, component: CustomerPartnerModuleComponent,
    canActivate: [AuthenticationLoggedGuard]
  },
  {
    path: RouteLink.CUSTOMER_INSPECTOR, component: CustomerInspectorModuleComponent,
    canActivate: [AuthenticationLoggedGuard]
  },
  {
    path: RouteLink.CUSTOMER_RESPONSIBLE, component: CustomerResponsibleModuleComponent,
    canActivate: [AuthenticationLoggedGuard]
  },
  {
    path: RouteLink.CUSTOMER_ACCOUNTANT, component: CustomerAccountantModuleComponent,
    canActivate: [AuthenticationLoggedGuard]
  },
  {
    path: RouteLink.FULFILLMETS, component: HomeComponent,
    canActivate: [AuthenticationLoggedGuard]
  },
  {
    path: '**', redirectTo: RouteLink.HOME
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

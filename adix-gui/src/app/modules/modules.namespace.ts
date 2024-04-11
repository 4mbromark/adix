import { UserPermits } from './../authentication/authentication.namespace';
import { RouteLink } from "../app-routing.namespace";

export class Module {
  title: string;
  icon: string;
  link: RouteLink;

  requiredPermit?: UserPermits;

  // add button
  addButtonEnabled?: boolean = false;
  addButtonText?: string;
}

// Amministrazione
export const MODULE_ADMIN_USERS: Module = {
  title: 'Gestione utenti',
  icon: 'group',
  link: RouteLink.ADMIN_USERS,
  requiredPermit: UserPermits.ADMIN_VIEW_ALL_USERS,
  addButtonEnabled: true,
  addButtonText: 'Nuovo utente'
}
export const MODULE_ADMIN_CUSTOMERS: Module = {
  title: 'Gestione clienti',
  icon: 'format_list_numbered',
  link: RouteLink.ADMIN_CUSTOMERS,
  requiredPermit: UserPermits.ANY_ROLE_VIEW_ALL_CUSTOMERS,
  addButtonEnabled: true,
  addButtonText: 'Nuovo cliente'
}
export const MODULE_ADMIN_FULFILLMENTS: Module = {
  title: 'Gestione adempimenti',
  icon: 'playlist_add_check',
  link: RouteLink.ADMIN_FULFILLMENTS,
  requiredPermit: UserPermits.ANY_ROLE_VIEW_ALL_FULFILLMENTS,
  addButtonEnabled: true,
  addButtonText: 'Nuovo adempimento'
}

// Pannelli aziende
export const MODULE_CUSTOMER_PARTNER: Module = {
  title: 'Pannello socio',
  icon: 'apartment',
  link: RouteLink.CUSTOMER_PARTNER,
  requiredPermit: UserPermits.ROLE_PARTNER_VIEW_OWN_CUSTOMERS
}
export const MODULE_CUSTOMER_INSPECTOR: Module = {
  title: 'Pannello controllore',
  icon: 'download_done',
  link: RouteLink.CUSTOMER_INSPECTOR,
  requiredPermit: UserPermits.ROLE_INSPECTOR_VIEW_OWN_CUSTOMERS
}
export const MODULE_CUSTOMER_RESPONSIBLE: Module = {
  title: 'Pannello responsabile',
  icon: 'local_police',
  link: RouteLink.CUSTOMER_RESPONSIBLE,
  requiredPermit: UserPermits.ROLE_RESPONSIBLE_VIEW_OWN_CUSTOMERS
}
export const MODULE_CUSTOMER_ACCOUNTANT: Module = {
  title: 'Pannello contabile',
  icon: 'query_stats',
  link: RouteLink.CUSTOMER_ACCOUNTANT,
  requiredPermit: UserPermits.ROLE_ACCOUNTANT_VIEW_OWN_CUSTOMERS
}

//
export const MODULE_FULFILLMENTS: Module = {
  title: 'Adempimenti periodici',
  icon: 'checklist',
  link: RouteLink.FULFILLMETS
}

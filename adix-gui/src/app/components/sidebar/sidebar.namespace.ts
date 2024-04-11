import { Module, MODULE_ADMIN_USERS, MODULE_ADMIN_CUSTOMERS, MODULE_ADMIN_FULFILLMENTS, MODULE_CUSTOMER_PARTNER, MODULE_CUSTOMER_INSPECTOR, MODULE_CUSTOMER_RESPONSIBLE, MODULE_CUSTOMER_ACCOUNTANT, MODULE_FULFILLMENTS } from './../../modules/modules.namespace';

export class SidebarButton {
  module?: Module
}

export const SIDEBAR_BUTTON_LIST: SidebarButton[] = [
  {
    module: MODULE_ADMIN_USERS
  },
  {
    module: MODULE_ADMIN_CUSTOMERS
  },
  {
    module: MODULE_ADMIN_FULFILLMENTS
  },
  {},
  {
    module: MODULE_CUSTOMER_PARTNER
  },
  {
    module: MODULE_CUSTOMER_INSPECTOR
  },
  {
    module: MODULE_CUSTOMER_RESPONSIBLE
  },
  {
    module: MODULE_CUSTOMER_ACCOUNTANT
  },
  {},
  {
    module: MODULE_FULFILLMENTS
  }
];

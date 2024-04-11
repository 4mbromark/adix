import { ColDef } from 'ag-grid-community';

const COLUMN_CODE: ColDef = {
  headerName: 'Codice',
  field: 'code',
  flex: 0.8,
  cellRenderer: 'codeRenderingComponent'
};

const COLUMN_CUSTOMER_PARTNER: ColDef = {
  headerName: 'Socio',
  field: 'userPartner',
  minWidth: 200,
  cellRenderer: 'userRenderingComponent'
};

const COLUMN_CUSTOMER_INSPECTOR: ColDef = {
  headerName: 'Controllore',
  field: 'userInspector',
  minWidth: 200,
  cellRenderer: 'userRenderingComponent'
};

const COLUMN_CUSTOMER_RESPONSIBLE: ColDef = {
  headerName: 'Responsabile',
  field: 'userResponsible',
  minWidth: 200,
  cellRenderer: 'userRenderingComponent'
};

const COLUMN_CUSTOMER_ACCOUNTANT: ColDef = {
  headerName: 'Contabile',
  field: 'userAccountant',
  minWidth: 200,
  cellRenderer: 'userRenderingComponent'
};

const COLUMN_CUSTOMER_BUSINESS_NAME: ColDef = {
  headerName: 'Società',
  field: 'businessName',
  flex: 3,
  minWidth: 200,
  cellRenderer: 'customerRenderingComponent'
};

const COLUMN_CUSTOMER_ACCOUNTING_TYPE: ColDef = {
  headerName: 'Contabilità',
  field: 'accountingType'
  // cellRenderer: 'userRenderingComponent'
};

const COLUMN_CUSTOMER_DELEGATION: ColDef = {
  headerName: 'Delega CF/FE',
  field: 'delegation'
  // cellRenderer: 'userRenderingComponent'
};

const COLUMN_ENABLED: ColDef = {
  headerName: 'Stato',
  field: 'enabled',
  flex: 1.5,
  minWidth: 200,
  cellRenderer: 'enabledRenderingComponent'
};

const COLUMN_ACTION: ColDef = {
  headerName: 'Azioni',
  headerComponentParams: {
    enableMenu: false,
    enableSorting: false
  },
  width: 150,
  pinned: 'right',
  cellRenderer: 'actionRenderingComponent',
  suppressMovable: true,
  sortable: false,
  resizable: false,
  filter: false
};

export const TABLE_ADMIN_USERS_MODULE: ColDef[] = [
  COLUMN_CODE,
  {
    headerName: 'Nome',
    field: 'firstName',
    minWidth: 150,
    flex: 2,
    cellRenderer: 'titleRenderingComponent'
  },
  {
    headerName: 'Cognome',
    field: 'lastName',
    minWidth: 150,
    flex: 2,
    cellRenderer: 'titleRenderingComponent'
  },
  {
    headerName: 'Indirizzo Email',
    field: 'emailAddress',
    minWidth: 200,
    flex: 4
  },
  {
    headerName: 'Ruoli',
    field: 'roles',
    minWidth: 150,
    flex: 2,
    cellRenderer: 'roleRenderingComponent'
  },
  {
    headerName: 'Permessi',
    minWidth: 150,
    flex: 2,
    cellRenderer: 'permitRenderingComponent'
  },
  {
    headerName: 'Credenziali',
    minWidth: 150,
    cellRenderer: 'credentialRenderingComponent'
  },
  COLUMN_ENABLED,
  COLUMN_ACTION
];

export const TABLE_ADMIN_CUSTOMERS_MODULE: ColDef[] = [
  COLUMN_CODE,
  COLUMN_CUSTOMER_PARTNER,
  COLUMN_CUSTOMER_INSPECTOR,
  COLUMN_CUSTOMER_RESPONSIBLE,
  COLUMN_CUSTOMER_ACCOUNTANT,
  COLUMN_CUSTOMER_BUSINESS_NAME,
  COLUMN_CUSTOMER_ACCOUNTING_TYPE,
  COLUMN_CUSTOMER_DELEGATION,
  COLUMN_ENABLED,
  COLUMN_ACTION
];

export const TABLE_ADMIN_FULFILLMENTS_MODULE: ColDef[] = [
  {
    headerName: 'Nome',
    field: 'name',
    minWidth: 150,
    flex: 3,
    cellRenderer: 'titleRenderingComponent'
  },
  {
    headerName: 'Descrizione',
    headerComponentParams: {
      enableSorting: false
    },
    field: 'description',
    minWidth: 200,
    flex: 5,
    sortable: false
  },
  {
    headerName: 'Ricorrenza (mesi)',
    field: 'monthRecurrence',
    minWidth: 200,
    flex: 2,
    cellRenderer: 'recurrenceRenderingComponent'
  },
  COLUMN_ENABLED,
  COLUMN_ACTION
];

export const TABLE_CUSTOMER_PARTNER_MODULE: ColDef[] = [
  COLUMN_CUSTOMER_INSPECTOR,
  COLUMN_CUSTOMER_RESPONSIBLE,
  COLUMN_CUSTOMER_ACCOUNTANT,
  COLUMN_CUSTOMER_BUSINESS_NAME,
  COLUMN_CUSTOMER_ACCOUNTING_TYPE,
  COLUMN_CUSTOMER_DELEGATION
];

export const TABLE_CUSTOMER_INSPECTOR_MODULE: ColDef[] = [
  COLUMN_CUSTOMER_PARTNER,
  COLUMN_CUSTOMER_RESPONSIBLE,
  COLUMN_CUSTOMER_ACCOUNTANT,
  COLUMN_CUSTOMER_BUSINESS_NAME,
  COLUMN_CUSTOMER_ACCOUNTING_TYPE,
  COLUMN_CUSTOMER_DELEGATION
];

export const TABLE_CUSTOMER_RESPONSIBLE_MODULE: ColDef[] = [
  COLUMN_CUSTOMER_PARTNER,
  COLUMN_CUSTOMER_INSPECTOR,
  COLUMN_CUSTOMER_ACCOUNTANT,
  COLUMN_CUSTOMER_BUSINESS_NAME,
  COLUMN_CUSTOMER_ACCOUNTING_TYPE,
  COLUMN_CUSTOMER_DELEGATION
];

export const TABLE_CUSTOMER_ACCOUNTANT_MODULE: ColDef[] = [
  COLUMN_CUSTOMER_PARTNER,
  COLUMN_CUSTOMER_INSPECTOR,
  COLUMN_CUSTOMER_RESPONSIBLE,
  COLUMN_CUSTOMER_BUSINESS_NAME,
  COLUMN_CUSTOMER_ACCOUNTING_TYPE,
  COLUMN_CUSTOMER_DELEGATION
];

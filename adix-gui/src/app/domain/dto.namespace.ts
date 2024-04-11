import { UserRoles } from './../authentication/authentication.namespace';

export class BaseRecord {
  id: number;
  insertDate: Date;
  updateDate: Date;
}

export class UserDto extends BaseRecord {
  code: string;
  emailAddress: string;
  firstName: string;
  lastName: string;
  enabled: boolean;
  note: string;
  roles: UserRoles [];

  constructor(
    code: string,
    emailAddress: string,
    firstName: string,
    lastName: string,
    note: string
  ) {
    super();
    this.code = code;
    this.emailAddress = emailAddress;
    this.firstName = firstName;
    this.lastName = lastName;
    this.note = note;
  }
}

export class PermitDto extends BaseRecord {
  name: string;
  description: string;
  enabled: boolean;
}

export enum CustomerAccountingType {
  INTERNAL = 'INTERNAL',
  EXTERNAL = 'EXTERNAL'
}

export class CustomerAccountingTypeOption {
  code: CustomerAccountingType;
  title: string;
}

export const CUSTOMER_ACCOUNTING_TYPE_LIST: CustomerAccountingTypeOption[] = [
  {
    code: CustomerAccountingType.INTERNAL,
    title: 'Interna'
  },
  {
    code: CustomerAccountingType.EXTERNAL,
    title: 'Esterna'
  }
];

export class CustomerDto extends BaseRecord {
  code: string;
  businessName: string;
  vatNumber: string;
  user: UserDto;
  inspector: string;
  manager: string;
  accountingType: string;
  accountant: string;
  delegation: boolean;
  enabled: boolean;
  note: string;

  constructor(
    code: string,
    businessName: string,
    vatNumber: string,
    accountingType: string,
    note: string
  ) {
    super();
    this.code = code;
    this.businessName = businessName;
    this.vatNumber = vatNumber;
    this.accountingType = accountingType;
    this.note = note;
  }
}

export class FulfillmentDto extends BaseRecord {
  name: string;
  description: string;
  monthRecurrence: number;
  enabled: boolean;
  note: string;

  constructor(
    name: string,
    description: string,
    monthRecurrence: number,
    note: string
  ) {
    super();
    this.name = name;
    this.description = description;
    this.monthRecurrence = monthRecurrence;
    this.note = note;
  }
}


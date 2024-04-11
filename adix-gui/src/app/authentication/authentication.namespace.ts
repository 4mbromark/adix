import { UserDto } from './../domain/dto.namespace';

export class TokenProvider {
  private static readonly TOKEN_STRING: string = 'adix-authentication-token';

  public static getTokenFromLocalStorage(): string {
    return localStorage.getItem(TokenProvider.TOKEN_STRING);
  }

  public static saveTokenInLocalStorage(token: string): void {
    localStorage.setItem(TokenProvider.TOKEN_STRING, token);
  }

  public static removeTokenFromLocalStorage(): void {
    localStorage.removeItem(TokenProvider.TOKEN_STRING);
  }

  public static isTokenInLocalStorage(): boolean {
    const token: string = this.getTokenFromLocalStorage();
    return token !== null && token !== undefined;
  }
}

export enum UserRoles {
  PARTNER,
  INSPECTOR,
  RESPONSIBLE,
  ACCOUNTANT
}

export enum UserPermits {
  ADMIN_VIEW_ALL_USERS = 'ADMIN_VIEW_ALL_USERS',
  ADMIN_ADD_USER = 'ADMIN_ADD_USER',
  ADMIN_EDIT_USER = 'ADMIN_EDIT_USER',
  ADMIN_DELETE_USER = 'ADMIN_DELETE_USER',
  ANY_ROLE_VIEW_ALL_CUSTOMERS = 'ANY_ROLE_VIEW_ALL_CUSTOMERS',
  ANY_ROLE_ADD_CUSTOMER = 'ANY_ROLE_ADD_CUSTOMER',
  ANY_ROLE_EDIT_CUSTOMER = 'ANY_ROLE_EDIT_CUSTOMER',
  ANY_ROLE_DELETE_CUSTOMER = 'ANY_ROLE_DELETE_CUSTOMER',
  ANY_ROLE_VIEW_ALL_FULFILLMENTS = 'ANY_ROLE_VIEW_ALL_FULFILLMENTS',
  ANY_ROLE_ADD_FULFILLMENT = 'ANY_ROLE_ADD_FULFILLMENT',
  ANY_ROLE_EDIT_FULFILLMENT = 'ANY_ROLE_EDIT_FULFILLMENT',
  ANY_ROLE_DELETE_FULFILLMENT = 'ANY_ROLE_DELETE_FULFILLMENT',
  ROLE_PARTNER_VIEW_OWN_CUSTOMERS = 'ROLE_PARTNER_VIEW_OWN_CUSTOMERS',
  ROLE_INSPECTOR_VIEW_OWN_CUSTOMERS = 'ROLE_INSPECTOR_VIEW_OWN_CUSTOMERS',
  ROLE_RESPONSIBLE_VIEW_OWN_CUSTOMERS = 'ROLE_RESPONSIBLE_VIEW_OWN_CUSTOMERS',
  ROLE_ACCOUNTANT_VIEW_OWN_CUSTOMERS = 'ROLE_ACCOUNTANT_VIEW_OWN_CUSTOMERS',
  ANY_ROLE_VIEW_OWN_FULFILLMENTS_RECORDS = 'ANY_ROLE_VIEW_OWN_FULFILLMENTS_RECORDS',
  ANY_ROLE_EDIT_OWN_FULFILLMENTS_RECORDS = "ANY_ROLE_EDIT_OWN_FULFILLMENTS_RECORDS",
  ANY_ROLE_VIEW_ALL_FULFILLMENTS_RECORDS = 'ANY_ROLE_VIEW_ALL_FULFILLMENTS_RECORDS',
  ANY_ROLE_EDIT_ALL_FULFILLMENTS_RECORDS = "ANY_ROLE_EDIT_ALL_FULFILLMENTS_RECORDS"
}

export class LoginRequest {
  username: string;
  password: string;

  constructor(
    code: string,
    mail: string,
    password: string
  ) {
    this.username = `${code}:${mail}`;
    this.password = password;
  }

  public isValid(): boolean {
    return this.username !== null && this.username !== undefined && this.username !== ''
      && this.password !== null && this.password !== undefined && this.password !== '';
  }
}

export class AuthenticationContainer extends UserDto {
  permits: UserPermits[];
}

export class AuthenticationContainerProvider {
  container: AuthenticationContainer;
}

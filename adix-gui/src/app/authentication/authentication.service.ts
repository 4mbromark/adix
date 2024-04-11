import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest, AuthenticationContainerProvider, AuthenticationContainer, TokenProvider } from './authentication.namespace';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private container: Promise<AuthenticationContainer>;

  constructor(
    private readonly http: HttpClient
  ) {
    this.container = this.loadContainer();
  }

  public login(code: string, mail: string, password: string): Promise<void> {
    const request: LoginRequest = new LoginRequest(code, mail, password);

    return new Promise<void>((resolve, reject) => {
      if (!request.isValid()) {
        reject();
        return;
      }

      this.http.post('/auth/login', request, { observe: 'response' }).subscribe((response: HttpResponse<Response>) => {
        const token: string = response.headers.get('Authorization');
        TokenProvider.saveTokenInLocalStorage(token)
        location.reload();
        resolve();
      }, (error: HttpErrorResponse) => {
        reject(error);
      });
    });
  }

  public logout(): void {
    if (!TokenProvider.isTokenInLocalStorage()) {
      TokenProvider.removeTokenFromLocalStorage();
      location.reload();
    }
  }

  public verify(): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      if (!TokenProvider.isTokenInLocalStorage()) {
        reject();
        return;
      }

      this.http.post('/auth/verify', {}).subscribe((response: Response) => {
        resolve();
      }, (error: HttpErrorResponse) => {
        reject(error);
      });
    });
  }

  public getContainer(): Promise<AuthenticationContainer> {
    return this.container;
  }

  private loadContainer(): Promise<AuthenticationContainer> {
    return new Promise<AuthenticationContainer>((resolve, reject) => {
      if (!TokenProvider.isTokenInLocalStorage()) {
        reject();
        return;
      }

      this.http.get('/auth/user', {}).subscribe((response: AuthenticationContainerProvider) => {
        resolve(response.container);
      }, (error: HttpErrorResponse) => {
        reject(error);
      });
    });
  }
}

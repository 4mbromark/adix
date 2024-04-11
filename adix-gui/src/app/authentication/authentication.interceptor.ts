import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { ToastService } from '../toast/toast.service';
import { TokenProvider } from './authentication.namespace';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor(
    // private readonly authentication: AuthenticationService,
    private readonly toast: ToastService
  ) {}

  intercept(req: HttpRequest<string>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token: string = TokenProvider.getTokenFromLocalStorage();
    req = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + token) });
    req = req.clone({ headers: req.headers.set('Content-Type', 'application/json') });
    req = req.clone({ headers: req.headers.set('Accept', 'application/json') });
    req = req.clone({ url: `/api${req.url}` });

    return next.handle(req)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (TokenProvider.isTokenInLocalStorage()) {
            const message = error.error?.message || 'Errore imprevisto nell\'applicazione, segnalare immediatamente';
            this.toast.show('ATTENZIONE', message);
          }

          // TODO
          if (error.status === 401) {
            if (TokenProvider.isTokenInLocalStorage()) {
              TokenProvider.removeTokenFromLocalStorage();
              location.reload();
            }
          }
          /* if (error.status === 401) {
            this.authentication.logout();
          } */

          return throwError(() => error);
        }
      )
    );
  }
}

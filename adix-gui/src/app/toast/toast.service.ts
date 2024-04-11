import { ToastContent } from './toast.namespace';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ToastService {
  private toasts: ToastContent[] = [];

  constructor() { }

  public getToasts(): ToastContent[] {
    return this.toasts;
  }

  public show(header: string, body: string): void {
    const toast = new ToastContent(header, body);
    this.toasts.push(toast);
  }

  public remove(toast: ToastContent): void {
    this.toasts = this.toasts.filter(t => t != toast);
  }
}

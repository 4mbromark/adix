import { ToastContent } from './toast.namespace';
import { BehaviorSubject, Observable } from 'rxjs';
import { ToastService } from './toast.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.css']
})
export class ToastComponent implements OnInit {

  constructor(
    private readonly toastService: ToastService
  ) { }

  ngOnInit(): void {
  }

  protected getToasts(): ToastContent[] {
    return this.toastService.getToasts();
  }

  protected remove(toast: ToastContent): void {
    this.toastService.remove(toast);
  }
}

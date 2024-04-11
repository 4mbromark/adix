import { AuthenticationService } from './../../authentication/authentication.service';
import { AuthenticationContainer } from './../../authentication/authentication.namespace';
import { SidebarButton, SIDEBAR_BUTTON_LIST } from './sidebar.namespace';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  protected buttonList: SidebarButton[] = [];

  constructor(
    private readonly authentication: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.authentication.getContainer().then((container: AuthenticationContainer) => {
      this.buttonList = SIDEBAR_BUTTON_LIST.filter(button => {
        return container.permits.find(permit => permit === button.module?.requiredPermit);
      });
    });
  }
}

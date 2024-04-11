import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-standard-code',
  templateUrl: './standard-code.component.html',
  styleUrls: ['./standard-code.component.css']
})
export class StandardCodeComponent implements OnInit {
  @Input() protected value: string;
  @Input() protected width?: number = 30;
  @Input() protected height?: number = 30;

  constructor() { }

  ngOnInit(): void {
  }
}

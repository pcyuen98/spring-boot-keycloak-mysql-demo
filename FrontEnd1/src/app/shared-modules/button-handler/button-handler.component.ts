import { Component, Input, OnInit } from '@angular/core';
import { Action } from 'src/app/models/action';
import { PageBaseComponent } from 'src/app/util/page-base.component';

@Component({
  selector: 'app-button-handler',
  templateUrl: 'button-handler.component.html',
  styleUrls: ['button-handler.component.css'],
})
export class ButtonHandlerComponent implements OnInit {
  @Input() actions: Action[] = [];
  @Input() title = '';
  @Input() pageBaseComponent!: PageBaseComponent;

  ngOnInit(): void {}

  getButtonColor(action: Action): string {
    return action.color ?? 'primary';
  }

  isButtonErrorType(action: Action): boolean {
    return !!action.isButtonErrorType;
  }

  isClearButtonErrorType(action: Action): boolean {
    return !!action.isClearButtonErrorType;
  }

  actionHandler(action: Action): void {
    action.handler();
  }
}

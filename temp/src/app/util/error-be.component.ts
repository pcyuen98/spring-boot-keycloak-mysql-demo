import { Component, Input, NgModule } from '@angular/core';
import { CommonService } from '../service/CommonService';
import { CommonModule } from '@angular/common';
import { IonicModule } from '@ionic/angular';
import { PageBaseComponent } from './page-base.component';
import { ErrorApp } from '../models/error-app';


@Component({
  selector: 'error-be',
  template: `

  <ion-header>
  <ion-toolbar>
    <!-- Left aligned title -->
    <ion-title>
      <b>Error Occurred</b>
    </ion-title>

    <!-- Right aligned buttons -->
    <ion-buttons slot="end">
      <ion-icon name="close-circle" size="large" (click)="close()" class="ion-margin-start"></ion-icon>
    </ion-buttons>
  </ion-toolbar>
</ion-header>

  <div   class="error-box" style="color: red; border: 1px solid red; padding: 10px; border-radius: 5px;">
  <ul *ngIf="errorApp">
    <li>Error Message: {{errorApp.appMessage}}. {{errorApp.error.message}}. </li>
    <li>Caused: {{errorApp.caused}}</li>
    <li>Please try again later</li>
  </ul>
</div>
  <b (click)="viewError()" class="font-error-link">&nbsp;View Details&nbsp;</b> 

  `,
  styles: [`h1 { font-family: Lato; }`]
})

export class ErrorBEComponent {

  @Input() errorApp: ErrorApp;
  @Input() title: string;
  @Input() message: string;
  @Input() pageBaseComponent!: PageBaseComponent;
  constructor(
    private commonService: CommonService,
  ) { }

  public viewError() {
    this.commonService.openPopModal(this.errorApp.error.exception, this.errorApp.error.message, this.errorApp.error)
  }

  close() {
    this.pageBaseComponent.clearErrorMessage()
  }

  getGlobalErrorTest() {
    console.log(this)
  }

}

@NgModule({
  declarations: [ErrorBEComponent],
  imports: [CommonModule, IonicModule],
  exports: [ErrorBEComponent]
})
export class ErrorBEModule { }
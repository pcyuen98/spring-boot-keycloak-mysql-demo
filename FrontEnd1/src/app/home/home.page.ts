import { Component, Injector, ViewChild } from '@angular/core';
import { PageBaseComponent } from '../util/page-base.component';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss']
})
export class HomePage extends PageBaseComponent{

  constructor(injector: Injector,
     ) {
    super(injector);
  }
  ngOnInit() {}

  getGlobalErrorTest() {
    let test = this.getGlobalError()
    console.log(this)
}


}

import {
  Component,
  Injector,
} from '@angular/core';
import { ModuleName } from '../models/module.name';
import { PageBaseComponent } from '../util/page-base.component';

@Component({
  selector: 'app-redis',
  templateUrl: 'redis.page.html',
  styleUrls: ['redis.page.css']
})
export class RedisPage  extends PageBaseComponent {

  constructor(injector: Injector) {
    super(injector);
  }
}

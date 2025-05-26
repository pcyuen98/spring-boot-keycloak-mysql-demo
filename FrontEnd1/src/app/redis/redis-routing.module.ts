import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RedisPage } from './redis.page';

const routes: Routes = [
  {
    path: '',
    component: RedisPage,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RedisPageRoutingModule {}

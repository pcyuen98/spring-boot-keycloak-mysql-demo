import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule } from '@ionic/angular';
import { ReactiveFormsModule } from '@angular/forms';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { HeaderModule } from '../shared-modules/header/header.module';
import { ModelPopModule } from '../shared-modules/modal-pop/modal-pop.module';
import { ModelPopArrayModule } from '../shared-modules/modal-pop-array/modal-pop-array.module';
import { RedisPage } from './redis.page';
import { RedisPageRoutingModule } from './redis-routing.module';
import { FooterModule } from '../shared-modules/footer/footer.module';

@NgModule({
  imports: [ CommonModule, IonicModule, HeaderModule, RedisPageRoutingModule, ReactiveFormsModule, 
    ModelPopModule, ModelPopArrayModule
    ,FooterModule],
  declarations: [ RedisPage],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class RedisPageModule {}

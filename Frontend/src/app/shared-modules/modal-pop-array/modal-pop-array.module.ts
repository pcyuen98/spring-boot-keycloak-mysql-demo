import {NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ModalPopArrayComponent } from './modal-pop-array.component';
import { AskAIModule } from 'src/app/util/ask-ai.component';

@NgModule({
    declarations: [ModalPopArrayComponent],
    exports: [ModalPopArrayComponent],
    imports: [
      CommonModule
      ,FormsModule
      ,AskAIModule
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
  })
export class ModelPopArrayModule { }

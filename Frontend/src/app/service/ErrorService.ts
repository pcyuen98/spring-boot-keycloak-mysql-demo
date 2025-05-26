import { Injectable } from "@angular/core";
import { GlobalConstants } from "src/environments/GlobalConstants";
import { ErrorApp } from "../models/error-app";

enum HttpErrorCodes {
  BadRequest = 400,
  Unauthorized = 401,
  Forbidden = 403,
  ServerError = 500,
}

@Injectable({
    providedIn: 'root'
})
export class ErrorService {
  handleError(message: string, caused: string, error: any): void {
    if (!error) {
      alert('Error object is required.');
      console.warn('handleError was called with an undefined or null error.');
      return;
    }

    const errorApp = new ErrorApp();
    errorApp.appMessage = message;
    errorApp.caused = caused;
    errorApp.error = error;

    GlobalConstants.globalBEError = errorApp;

    console.error(`[ErrorService] ${message} | Type: ${caused} | Error:`, error);
  }

}
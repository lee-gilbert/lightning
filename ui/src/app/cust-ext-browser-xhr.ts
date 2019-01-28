import {Injectable} from '@angular/core';
import {BrowserXhr} from '@angular/http';
@Injectable()
/**
 * Extention of BrowserXhr to support CORS
 */
export class CustExtBrowserXhr extends BrowserXhr {
  constructor() {
      super();
  }
  build(): any {
    const xhr = super.build();
    xhr.withCredentials = true;  // essential bit
    return <any>(xhr);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Conversion } from '../model/conversion';
import { ConversionRequest } from '../model/conversion.request';
import { ConversionRate } from '../model/conversion.rate';


@Injectable({
  providedIn: 'root'
})
export class TipoCambioService {

  private URL_API: string = "http://localhost:8034";

  constructor(private http: HttpClient) { }

  listarTiposCambio(): Observable<Conversion[]> {
    return this.http.get<Conversion[]>(`${this.URL_API}/listado`);
  }
  convertirMoneda_(request: ConversionRequest): Observable<any> {
    return this.http.post(`${this.URL_API}/convert`, request);
  }

  convertirMoneda(monedaBase: string, request: ConversionRequest): Observable<any> {
    return this.http.post(`${this.URL_API}/convert/${monedaBase}`, request);
}

getAvailableCurrencies(): Observable<ConversionRate[]> {
  return this.http.get<ConversionRate[]>(`${this.URL_API}/money`);
}
}

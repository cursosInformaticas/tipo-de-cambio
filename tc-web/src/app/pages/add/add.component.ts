import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { ActivatedRoute, Router, RouterModule, RouterOutlet } from '@angular/router';
import { TipoCambioService } from '../../service/tipoCambio.service';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { ConversionRequest } from '../../model/conversion.request';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { ConversionRate } from '../../model/conversion.rate';

@Component({
  selector: 'app-add',
  standalone: true,
  imports: [RouterOutlet,
     RouterModule,
    FormsModule,MatSnackBarModule,CommonModule
  ],
  templateUrl: './add.component.html',
  styleUrl: './add.component.css'
})
export class AddComponent implements OnInit {
  request: ConversionRequest = {
    monto: 0,
    monedaOrigen: '',
    monedaDestino: ''
  };
  availableCurrencies: ConversionRate[] = [];
  monedaBase: string;
  constructor(
    private tipoCambioService: TipoCambioService,
    private router: Router,private route: ActivatedRoute,
     private snackBar: MatSnackBar
    ) { }

  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {
      this.monedaBase = params.get('monedaBase');
      this.loadAvailableCurrencies();
    });
  }
  convertirMoneda(): void {
    this.tipoCambioService.convertirMoneda('USD',this.request)
      .subscribe(response => {
        this.snackBar.open('Se registró la conversión', 'Aviso', { duration: 2000 });

        setTimeout(() => {
          this.router.navigate(['/listar']);
        }, 2000);

        console.log(response);
      }, error => {
        console.error(error);
      });
  }

  loadAvailableCurrencies() {
    this.tipoCambioService.getAvailableCurrencies().subscribe(currencies => {
      this.availableCurrencies = currencies;
    }, error => {
      console.error('Error al obtener las monedas disponibles', error);
    });
  }
}

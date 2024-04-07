import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { RouterModule, RouterOutlet } from '@angular/router';
import { MatSort } from '@angular/material/sort';
import { DISPLAY_COLUMN_TABLE } from '../../helpers/actions';
import { TipoCambioService } from '../../service/tipoCambio.service';
import { Conversion } from '../../model/conversion';

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [RouterOutlet,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,MatIconModule,MatInputModule,CommonModule, RouterModule
  ],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent implements OnInit{
  tiposCambio: Conversion[] = [];

  dataSource: MatTableDataSource<Conversion>;
  displayedColumns: string[] = DISPLAY_COLUMN_TABLE;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(public tipoCambioService: TipoCambioService) { }

  ngOnInit(): void {
    this.obtenerTiposCambio()
  }

  obtenerTiposCambio(){
    this.tipoCambioService.listarTiposCambio().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
     // console.log(data)
     })
  }
}

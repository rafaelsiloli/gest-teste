import { Component } from '@angular/core';
import { CreditosService } from './../../services/creditos.service';
import { Credito } from './../../models/credito.model';
import { ListaCreditosComponent } from '../lista-creditos/lista-creditos.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-consulta-creditos',
  templateUrl: './consulta-creditos.component.html',
  styleUrls: ['./consulta-creditos.component.scss'],
  standalone: true,
  imports: [ListaCreditosComponent, FormsModule]
})
export class ConsultaCreditosComponent {
  numero: string = '';
  creditos: Credito[] = [];

  constructor(private creditosService: CreditosService) {}

  buscarPorNfse() {
    if (!this.numero) return;

    this.creditosService.getByNfse(this.numero).subscribe(data => {
      this.creditos = data;
    });
  }

  buscarPorCredito() {
    if (!this.numero) return;

    this.creditosService.getByNumeroCredito(this.numero).subscribe(data => {
      this.creditos = [data];
    });
  }
}

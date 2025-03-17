import { Component, Input } from '@angular/core';
import { Credito } from './../../models/credito.model';
import { DecimalPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-lista-creditos',
  templateUrl: './lista-creditos.component.html',
  styleUrls: ['./lista-creditos.component.scss'],
  standalone: true,
  imports: [NgFor, DecimalPipe]
  
})
export class ListaCreditosComponent {
  @Input() creditos: Credito[] = [];
}

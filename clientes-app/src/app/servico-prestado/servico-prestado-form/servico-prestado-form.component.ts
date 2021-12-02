import { Component, OnInit } from '@angular/core';
import { ClientesFormComponent } from 'src/app/clientes/clientes-form/clientes-form.component';
import { Cliente } from '../../clientes/cliente';

@Component({
  selector: 'app-servico-prestado-form',
  templateUrl: './servico-prestado-form.component.html',
  styleUrls: ['./servico-prestado-form.component.css']
})
export class ServicoPrestadoFormComponent implements OnInit {

  clientes: Cliente[] = []

  constructor() { }

  ngOnInit(): void {
  }

}

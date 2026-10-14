import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';

import { ClientService } from '../../services/client.service';
import { Client } from '../../interfaces/client';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  firstName = '';
  lastName = '';
  surname = '';
  phone = '';
  email = '';
  formError = '';

  displayedColumns: string[] = [
    'id',
    'firstName',
    'lastName',
    'surname',
    'phone',
    'email',
    'actions'
  ];

  dataSource = new MatTableDataSource<Client>([]);

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.getClients();
  }

  onSave(form: NgForm): void {
    this.formError = '';

    if (form.invalid) {
      form.control.markAllAsTouched();

      const missingFields: string[] = [];
      if (!this.firstName.trim()) missingFields.push('First Name');
      if (!this.lastName.trim()) missingFields.push('Last Name');
      if (!this.surname.trim()) missingFields.push('Surname');
      if (!this.phone.trim()) missingFields.push('Phone');
      if (!this.email.trim()) missingFields.push('Email');

      this.formError = missingFields.length
        ? `Completa los campos obligatorios: ${missingFields.join(', ')}.`
        : 'Revisa los formatos indicados en los campos marcados.';

      return;
    }

    const client: Client = {
      id: 0,
      firstName: this.firstName.trim(),
      lastName: this.lastName.trim(),
      surname: this.surname.trim(),
      phone: this.phone.trim(),
      email: this.email.trim()
    };

    this.clientService.saveClient(client).subscribe({
      next: () => {
        alert('Cliente guardado correctamente.');
        form.resetForm();
        this.clearFields();
        this.getClients();
      },
      error: (error) => {
        this.formError = `No se pudo guardar el cliente: ${error.message}`;
      }
    });
  }

  deleteClient(id: number): void {
    if (!confirm(`¿Eliminar el cliente ${id}?`)) {
      return;
    }

    this.clientService.deleteClient(id).subscribe({
      next: () => {
        alert('Cliente eliminado correctamente.');
        this.getClients();
      },
      error: (error) => {
        alert(`No se pudo eliminar el cliente: ${error.message}`);
      }
    });
  }

  getClients(): void {
    this.clientService.getClients().subscribe({
      next: (clients: Client[]) => {
        this.dataSource.data = clients;
      },
      error: (error) => {
        alert(`No se pudieron cargar los clientes: ${error.message}`);
      }
    });
  }

  private clearFields(): void {
    this.firstName = '';
    this.lastName = '';
    this.surname = '';
    this.phone = '';
    this.email = '';
  }
}
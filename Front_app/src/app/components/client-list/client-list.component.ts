import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { Client } from '../../models/client.model';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule
  ],
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  clients: Client[] = [];
  displayedColumns: string[] = ['cin', 'nom', 'prenom', 'actions'];
  dataSource = new MatTableDataSource<Client>();

  newClient: Client = { cin: '', nom: '', prenom: '' };
  selectedClient: Client | null = null;

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.loadClients();
  }

  // Charger les clients
  loadClients(): void {
    this.clientService.getClients().subscribe(data => {
      this.clients = data;
      this.dataSource.data = this.clients;
    });
  }


  addClient(): void {
    if (this.newClient.cin && this.newClient.nom && this.newClient.prenom) {
      this.clientService.createClient(this.newClient).subscribe(client => {
        this.clients.push(client);
        this.dataSource.data = this.clients;
        this.newClient = { cin: '', nom: '', prenom: '' };
      });
    }
  }


  editClient(client: Client): void {
    this.selectedClient = { ...client };
  }


  updateClient(): void {
    if (this.selectedClient) {
      this.clientService.updateClient(this.selectedClient.cin, this.selectedClient).subscribe(
        updatedClient => {
          const index = this.clients.findIndex(c => c.cin === updatedClient.cin);
          if (index !== -1) {
            this.clients[index] = updatedClient;
            this.dataSource.data = this.clients;
          }
          this.selectedClient = null;
        },
        error => {
          console.error('Erreur lors de la mise à jour du client', error);
        }
      );
    }
  }


  deleteClient(cin: string): void {
    Swal.fire({
      title: 'Êtes-vous sûr ?',
      text: 'Vous ne pourrez pas revenir en arrière !',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Oui, supprimer !',
    }).then((result) => {
      if (result.isConfirmed) {
        this.clientService.deleteClient(cin).subscribe(() => {
          this.clients = this.clients.filter(client => client.cin !== cin);
          this.dataSource.data = this.clients;
          Swal.fire('Supprimé !', 'Le client a été supprimé.', 'success');
        });
      }
    });
  }
}
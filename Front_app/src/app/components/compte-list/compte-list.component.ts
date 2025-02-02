import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';

import { CompteService } from '../../services/compte.service';
import { ClientService } from '../../services/client.service';
import { Compte } from '../../models/compte.model';
import { Client } from '../../models/client.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-compte-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    FormsModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
  ],
  templateUrl: './compte-list.component.html',
  styleUrls: ['./compte-list.component.css']
})
export class CompteListComponent implements OnInit {
  comptes: Compte[] = [];
  clients: Client[] = [];
  displayedColumns: string[] = ['rib', 'client', 'solde', 'actions'];
  dataSource = new MatTableDataSource<Compte>();

  newCompte: Compte = {
    rib: 0,
    solde: 0,
    client: { cin: '', nom: '', prenom: '' }
  };

  clientControl = new FormControl();
  filteredClients: Observable<Client[]>;

  constructor(
    private compteService: CompteService,
    private clientService: ClientService
  ) {

    this.filteredClients = new Observable<Client[]>();
  }

  ngOnInit(): void {
    this.loadComptes();
    this.loadClients();


    this.filteredClients = this.clientControl.valueChanges.pipe(
      startWith(''),
      map((value) => this._filterClients(value))
    );
  }


  private _filterClients(value: string): Client[] {
    const filterValue = value.toLowerCase();
    return this.clients.filter(
      (client) =>
        client.nom.toLowerCase().includes(filterValue) ||
        client.prenom.toLowerCase().includes(filterValue)
    );
  }


  resetForm(): void {
    this.newCompte = { rib: 0, solde: 0, client: { cin: '', nom: '', prenom: '' } };
    this.clientControl.reset();
  }


  loadComptes(): void {
    this.compteService.getAllComptes().subscribe(data => {
      this.comptes = data;
      this.dataSource.data = this.comptes;


      this.comptes.forEach(compte => {
        if (!compte.client) {
          console.warn('Compte sans client :', compte);
          compte.client = { cin: '', nom: 'Inconnu', prenom: '' };
        }
      });
    });
  }


  loadClients(): void {
    this.clientService.getClients().subscribe(data => {
      this.clients = data;
    });
  }


  addCompte(): void {
    const selectedClient = this.clients.find(client => client.nom + ' ' + client.prenom === this.clientControl.value);
    if (selectedClient) {
      this.newCompte.client = selectedClient;
    }

    this.compteService.addCompte(this.newCompte).subscribe(compte => {
      this.comptes.push(compte);
      this.dataSource.data = this.comptes;
      this.newCompte = { rib: 0, solde: 0, client: { cin: '', nom: '', prenom: '' } };
      this.clientControl.reset();
    });
  }


  deleteCompte(rib: number): void {
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
        this.compteService.deleteCompte(rib).subscribe(() => {
          this.comptes = this.comptes.filter((compte) => compte.rib !== rib);
          Swal.fire('Supprimé !', 'Le compte a été supprimé.', 'success');
        });
      }
    });
  }


  selectCompte(compte: Compte): void {
    this.newCompte = { ...compte };
    if (!this.newCompte.client) {
      this.newCompte.client = { cin: '', nom: '', prenom: '' };
    }
    this.clientControl.setValue(this.newCompte.client.nom + ' ' + this.newCompte.client.prenom);
  }


  updateCompte(): void {
    if (this.newCompte.rib) {
      const selectedClient = this.clients.find(client => client.nom + ' ' + client.prenom === this.clientControl.value);
      if (selectedClient) {
        this.newCompte.client = selectedClient;
      }

      this.compteService.updateCompte(this.newCompte.rib, this.newCompte).subscribe(updatedCompte => {
        const index = this.comptes.findIndex(c => c.rib === updatedCompte.rib);
        if (index !== -1) {
          this.comptes[index] = updatedCompte;
          this.dataSource.data = this.comptes;
        }
        this.resetForm();
      });
    }
  }
}
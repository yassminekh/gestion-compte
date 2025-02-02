import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { HttpClient } from '@angular/common/http';
import { AppComponent } from './app.component';
// app-routing.module.ts
import { CompteListComponent } from './components/compte-list/compte-list.component';
import { ClientListComponent } from './components/client-list/client-list.component';
import { RouterModule } from '@angular/router';
import { AppRoutingModule, routes } from './app.routes';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
@NgModule({
  declarations: [
    AppComponent,

  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatTableModule,
    MatToolbarModule,
    AppRoutingModule,
    HttpClient,
    MatAutocompleteModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
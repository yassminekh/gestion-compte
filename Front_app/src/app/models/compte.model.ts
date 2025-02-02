import { Client } from "./client.model";

export interface Compte {
    rib: number;
    solde: number;
    client: Client;
}
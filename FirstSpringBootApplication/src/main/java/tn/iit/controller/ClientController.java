package tn.iit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.iit.entity.Client;
import tn.iit.service.ClientService;

import java.util.List;

@RestController 
@RequestMapping("/api/clients") 
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.save(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id) {
        clientService.deleteById(id);
    }

    @GetMapping("/search")
    public List<Client> searchClients(@RequestParam String term) {
        return clientService.findByNomContaining(term);
    }
    @PutMapping("/{cin}")
    public Client updateClient(@PathVariable String cin, @RequestBody Client client) {
        
    	return clientService.saveOrUpdate(client);
    }
}
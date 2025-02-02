package tn.iit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.entity.Compte;
import tn.iit.service.CompteService;

import java.util.List;

@RestController 
@RequestMapping("/api/comptes") 
@RequiredArgsConstructor
public class CompteController {

    private final CompteService compteService;

    @GetMapping
    public List<Compte> getAllComptes() {
        return compteService.findAll();
    }

    @PostMapping
    public Compte createCompte(@RequestBody Compte compte) {
        return compteService.saveOrUpdate(compte);
    }

    @DeleteMapping("/{rib}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Integer rib) {
        compteService.deleteById(rib);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{rib}")
    public Compte getCompteById(@PathVariable Integer rib) {
        return compteService.findById(rib);
    }

    @PutMapping("/{rib}")
    public Compte updateCompte(@PathVariable Integer rib, @RequestBody Compte compte) {
        compte.setRib(rib); 
        return compteService.saveOrUpdate(compte);
    }
}
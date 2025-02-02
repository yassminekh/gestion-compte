package tn.iit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.iit.entity.Compte;
import tn.iit.exception.CompteNotFoundException;
import tn.iit.repository.CompteRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompteService {

	private final CompteRepository compteRepository;

	public Compte saveOrUpdate(Compte compte) {
	    Compte savedCompte = compteRepository.save(compte); 
	    log.info("save or update compte with rib = {}", savedCompte.getRib());
	    return savedCompte; 
	}

	public List<Compte> findAll() {
		return compteRepository.findAll();
	}

	public void deleteById(Integer rib) {
		compteRepository.deleteById(rib);
	}

	public Compte findById(Integer rib) {
		return compteRepository.findById(rib)
				.orElseThrow(() -> new CompteNotFoundException("Compte With rib = " + rib + " is not found"));

	}

}

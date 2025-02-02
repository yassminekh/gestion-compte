package tn.iit.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import tn.iit.entity.Client;
import tn.iit.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;

	public Client saveOrUpdate(Client client) {
		if (client.getCin() != null && clientRepository.existsById(client.getCin())) {
			
			return clientRepository.save(client);
		} else {
		
			return clientRepository.save(client);
		}
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public Client save(Client client) {
		return clientRepository.save(client);
	}

	public void deleteById(String id) {
		clientRepository.deleteById(id);
	}

	public Client findById(String id) {
		return clientRepository.findById(id).orElse(null);
	}

	public List<Client> findByNomContaining(String nom) {
		return clientRepository.findByNomContaining(nom);
	}

}
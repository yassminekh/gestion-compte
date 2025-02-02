package tn.iit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.entity.Client;

public interface ClientRepository extends JpaRepository<Client, String> {
	 List<Client> findByNomContaining(String nom);
}
package tn.iit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iit.entity.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

}

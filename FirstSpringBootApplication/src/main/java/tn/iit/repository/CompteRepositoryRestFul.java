package tn.iit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import tn.iit.entity.Compte;

@RepositoryRestResource
public interface CompteRepositoryRestFul extends JpaRepository<Compte, Integer> {
}

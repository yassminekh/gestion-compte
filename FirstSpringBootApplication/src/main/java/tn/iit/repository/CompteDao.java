package tn.iit.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import tn.iit.entity.Compte;

@Repository
public class CompteDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	private void save(Compte compte) {
		entityManager.persist(compte);
	}

	@Transactional
	private void update(Compte compte) {
		entityManager.merge(compte);
	}

	@Transactional
	private Compte findById(Integer rib) {
		return entityManager.find(Compte.class, rib);
	}

	@Transactional
	public List<Compte> findAll() {
		// JPQL!=SQL,JPQL+=SQL orienté objet
		return entityManager.createQuery("select c from Compte c", Compte.class).getResultList();
	}
}

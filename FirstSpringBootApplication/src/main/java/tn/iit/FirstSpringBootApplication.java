package tn.iit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import tn.iit.repository.CompteRepository;

@SpringBootApplication
public class FirstSpringBootApplication implements CommandLineRunner {
	//@PersistenceContext
	@Autowired
	private EntityManager entityManager;
	private CompteRepository compteRepository;

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringBootApplication.class, args);
	}

	@Transactional //commit
	@Override
	public void run(String... args) throws Exception {
		//sql=>jp-ql ou h-ql==>sql orinenté objet (nom de l'objet,nom de l'attribut)
		/*TypedQuery<Compte> query1= entityManager.createQuery("select object(c) from Compte c where c.nomClient=:nc ",Compte.class);//nc :parametre
		query1.setParameter("nc", "yass");//filtrage d'injection sql sur "yass" +remplacer ':nc' par le resultat
		System.out.println("aa="+query1.getResultList());
		TypedQuery<Compte> query11= entityManager.createQuery("select object(c) from Compte c where c.nomClient=:nc ",Compte.class);
		System.out.println("sana="+query11.setParameter("nc", "sana"));*/
		//sql=native
		//Query query2=entityManager.createNativeQuery("select object(c) from t_compte c where c.nomClient='aa' ");
		//query1.setParameter("nc", "sana");
		//System.out.println(query2.getResultList());
		//delete
		/*Compte c6=entityManager.find(Compte.class, 3);
		entityManager.remove(c6);*/
		
		//update
		/*Compte c5=entityManager.find(Compte.class, 3);
		c5.setSolde(c5.getSolde()+200);
		entityManager.merge(c5);*/
		
		//recherche:
		/*Compte c4=entityManager.find(Compte.class, 2);
		System.out.println(c4);*/
		
		//ajout
		/*ompte c1 = new Compte("aa", 1000);
		entityManager.persist(c1);
		Compte c2 = new Compte("bb", 2000);
		entityManager.persist(c2);
		Compte c3 = new Compte("cc", 3000);
		entityManager.persist(c3);*/
		


	}
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Autorise les requêtes CORS pour /api
                        .allowedOrigins("http://localhost:4200") // Autorise Angular (frontend)
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes autorisées
                        .allowedHeaders("*"); // Headers autorisés
            }
        };
    }

}

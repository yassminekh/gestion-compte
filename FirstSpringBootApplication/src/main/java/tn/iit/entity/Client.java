package tn.iit.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "t_client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Include
	@Id
	private String cin;
	private String nom;
	private String prenom;
	// bidirectionnelle
	@Exclude // casser la boucle toString
	@JsonIgnore 
	@ManyToMany(mappedBy = "client")
	private List<Compte> comptes;

	public Client(String cin, String nom, String prenom) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
	}

}

package tn.iit.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "t_compte")
public class Compte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rib;

	private float solde;

	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "cin", nullable = false)
	private Client client;

	public Compte(float solde) {
		this.solde = solde;
	}
}
package paymentSystem.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="parametres") 
public class Parametres   {
	@Id
	@Column(name="code")
	private String code;
	/**
	 * Le nombre de tentatives de connexion echouee à l'application
	 */
	@Column(name="nbre_connexions_echouees_permises", nullable=false)
	private Integer nbreConnexionsEchoueesPermises;
	/**
	 * La durée de désactivation du compte après que le nombre de connexions echouees permises ait été dépassé
	 */
	@Column(name="duree_desactivation_compte", nullable=false)
	private Integer dureeDesactivationCompte;
	/*
	 * La durée en jours de la période de validité du mot de passe.
	 */
	@Column(name="duree_validite_mdp", nullable=false)
	private Integer dureeValiditeMdp;

}

package paymentSystem.entity.etat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import paymentSystem.entity.rh.Affectation;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class VueAffectation {
	private String ancienneAgence;
	private String nouvelleAgence;
	private String ancienneFonction;
	private String nouvelleFonction;
	private String ancienneStructure;
	private String novelleStructure;

	public VueAffectation(Affectation affectation) {
		this.ancienneAgence = (affectation.getAncienneAgence() != null) ? affectation.getAncienneAgence().getDesignation() : "";
		this.nouvelleAgence = (affectation.getNouvelleAgence() != null) ? affectation.getNouvelleAgence().getDesignation() : "";
		this.ancienneFonction = (affectation.getAncienneFonction() != null) ? affectation.getAncienneFonction().getDesignation() : "";
		this.nouvelleFonction = (affectation.getNouvelleFonction() != null) ? affectation.getNouvelleFonction().getDesignation() : "";
		this.ancienneStructure = (affectation.getAncienneStructure() != null) ? affectation.getAncienneStructure().getDesignation() : "";
		this.novelleStructure = (affectation.getNouvelleStructure() != null) ? affectation.getNouvelleStructure().getDesignation() : "";
	}

}

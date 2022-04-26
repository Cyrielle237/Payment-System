package paymentSystem.entity.etat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import paymentSystem.entity.rh.Avancement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VueAvancement {
	private String ancienneCategorie;
	private String nouvelleCategorie;
	private String ancienEchelon;
	private String nouvelEchelon;

	public VueAvancement(Avancement avancement) {
		super();
		this.ancienneCategorie = (avancement.getAncienneCategorie() != null) ? avancement.getAncienneCategorie().getDesignation() : "";
		this.nouvelleCategorie = (avancement.getNouvelleCategorie() != null) ? avancement.getNouvelleCategorie().getDesignation() : "";
		this.ancienEchelon = (avancement.getAncienEchelon() != null) ? avancement.getAncienEchelon().getDesignation() : "";
		this.nouvelEchelon = (avancement.getNouvelEchelon() != null) ? avancement.getNouvelEchelon().getDesignation() : "";
	}

}

package paymentSystem.entity.etat;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import paymentSystem.entity.rh.Promotion;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VuePromotion {
	private String ancienneFonction;
	private String novelleFonction;
	private LocalDate datePromotion;

	public VuePromotion(Promotion promotion) {
		this.ancienneFonction = (promotion.getAncienneFonction() != null) ?promotion.getAncienneFonction().getDesignation():"";
		this.datePromotion = promotion.getDatePromotion();
		this.novelleFonction = (promotion.getNouvelleFonction() != null)?promotion.getNouvelleFonction().getDesignation():"";
	}


}

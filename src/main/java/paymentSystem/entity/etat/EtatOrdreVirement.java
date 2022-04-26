package paymentSystem.entity.etat;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EtatOrdreVirement implements Serializable{
	private String numero;
	private String nom;
	private String compteBancaire;
	private String montant;


}

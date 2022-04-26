package paymentSystem.entity.etat;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class EtatCfc implements Serializable {
	private String numero;
	private String nom;
	private String base;
	private String partPatronale;
	private String partSalariale;
	private String total;
	private String somme;

}

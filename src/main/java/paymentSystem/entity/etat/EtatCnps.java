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
public class EtatCnps implements Serializable {
	private String nom;
	private String numero;
	private String numeroCNPS;
	private String base;
	private String basePlafonee;
	private String accidentTravail;
	private String pvidSalariale;
	private String pvidPatronale;
	private String allocationsFamiliales;
	private String total;
	private String sommeBasePlafonee;
	private String sommeAccidentTravail;
	private String sommePVIDSalariale;
	private String sommePVIDPatronale;
	private String sommeAllocationsFamiliales;
	private String sommeTotale;
	private String sommeTotaleLettre;


}

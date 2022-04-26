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
public class EtatBulletinPaie implements Serializable {
	private String nom;
	private int annee;
	private  String libelleMois;
	private String categorie;
	private String echelon;
	private String fonction;
	private String matricule;
	private String structure;
	private String anciennete;
	private String rubrique;
	private String base;
	private String tauxSal;
	private String gainSal;
	private String retenueSal;
	private String tauxPat;
	private String retenuePat;
	private String salaireBrut;
	private String retenuesSal;
	private String retenuesPat;
	private String salaireNet;
	private String heuresSupp;
	private String absences;
	private String modePaiement;
	private String banque;
	private String numeroCompte;
	private String numeroCNPS;
	private String netEnLettre;

}

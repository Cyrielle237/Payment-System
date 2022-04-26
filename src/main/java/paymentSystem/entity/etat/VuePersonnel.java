package paymentSystem.entity.etat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import paymentSystem.entity.rh.Personnel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VuePersonnel {
	private static String DATE_FORMAT = "dd/MM/yyyy";
	private String matricule;
	private String nom;
	private String dateNaissance;
	private String regionDorigine;
	private String sexe;
	private String etatCivil;
	private String telephone;
	private String email;
	private String residence;
	private String position;
	private String rang;
	private String dateEmbauche;
	private String structure;
	private String fonction;
	private String agence;
	private String banque;
	private String compteBanquaire;
	private String numeroCNPS;
	private List<VueConge> conges;
	private List<VuePromotion> promotions;
	private List<VueAffectation> affectations;
	private List<VueAvancement> avancements;
	private List<VueNotation> notations;


	private String date2String(LocalDate date) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(DATE_FORMAT);
		return simpleFormat.format(date);
	} 

	public VuePersonnel(Personnel personnel) {
		this.agence = (personnel.getAgence() != null) ? personnel.getAgence().getDesignation() : "";
		this.banque = (personnel.getBanque() != null) ? personnel.getBanque().getDesignation() : "";
		this.compteBanquaire = (personnel.getCompteBancaire() != null) ? personnel.getCompteBancaire() : "";
		this.dateEmbauche = (personnel.getDatePriseService() != null) ? date2String(personnel.getDatePriseService()) : "";
		this.dateNaissance = (personnel.getDateNaissance() != null) ? date2String(personnel.getDateNaissance()) : "";
		this.email = ((personnel.getAdresseEmail() != null) ? personnel.getAdresseEmail() : "");
		this.etatCivil = ((personnel.getSituationMatrimoniale() != null) ? personnel.getSituationMatrimoniale() : "");
		this.fonction = (personnel.getFonction() != null) ? personnel.getFonction().getDesignation() : "";
		this.matricule = ((personnel.getMatricule() != null) ? personnel.getMatricule() : "");
		this.nom = ((personnel.getCivilite() != null) ? personnel.getCivilite() : "") + " " + ((personnel.getNom() != null) ? personnel.getNom() : "") + " " + ((personnel.getPrenom() != null) ? personnel.getPrenom() : "");
		this.numeroCNPS = (personnel.getMatriculeCNPS() != null) ? personnel.getMatriculeCNPS() : "";
		this.position = ((personnel.getCategorie() != null) ? personnel.getCategorie().getDesignation() : "") + ((personnel.getEchelon() != null) ? personnel.getEchelon().getDesignation() : "");
		this.regionDorigine = (personnel.getRegionOrigine() != null) ? personnel.getRegionOrigine().getDesignation() : "";
		this.residence = (personnel.getQuartier() != null) ? personnel.getQuartier().getDesignation() : "";
		this.sexe = (personnel.getSexe() != null) ? personnel.getSexe() : "";
		this.structure = (personnel.getStructure() != null) ? personnel.getStructure().getSigle() : "";
		this.telephone = personnel.getNumeroTelephone();
		this.rang = (personnel.getRang() != null) ? personnel.getRang().getDesignation() : "";
	}



}

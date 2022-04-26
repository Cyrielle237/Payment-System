package paymentSystem.entity.etat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import paymentSystem.entity.ref.Banque;
import paymentSystem.entity.ref.Region;
import paymentSystem.entity.rh.Categorie;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.entity.rh.Structure;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class StatPersonnel {

	private List<Personnel> personnels;
	private String nomDuPlusage= "";
	private String nomDuPlusJeune;
	private String nomPlusAncien;
	private String nomDuNouveau;
	private Double masseSalariale;
	private int nombreHomme = 0;
	private int nombreFemme = 0;
	private int total;
	private int ageMoyen = 0;
	private List<StatBanque> banques;
	private List<StatCategorie> categories;
	private List<StatEtatCivil> etatsCivils;
	private List<StatRegion> regions;
	private List<StatStructure> structures;


	public StatPersonnel(List<Personnel> personnels) {
		this.personnels = personnels;
		this.total = personnels.size();
		this.regions = new ArrayList<StatRegion>();
		this.structures = new ArrayList<StatStructure>();
		this.banques = new ArrayList<StatBanque>();
		this.categories = new ArrayList<StatCategorie>();
		this.etatsCivils = new ArrayList<StatEtatCivil>();
		Personnel personnelLePlusAge = personnels.get(0);
		Personnel personnelLePlusJeune = personnels.get(0);
		Personnel personnelLePlusAncien = personnels.get(0);
		Personnel personnelNouveau =  personnels.get(0);
		Long ages = 0L;
		int nbreDateNaissanceIsSet = 0;
		int nbreDatePriseServiceIsSet = 0;
		int nbreCategorieIsSet = 0;
		int nbreEtatCivilIsSet = 0;
		int nbreRegionIsSet = 0;
		int nbreBanqueIsSet = 0;
		int nbreStructureIsSet = 0;


		nombreHomme = 
				(int) personnels.stream()
				.filter(Personnel -> Personnel.getSexe().equals("Masculin"))
				.count();

		nombreFemme = 
				(int) personnels.stream()
				.filter(Personnel -> Personnel.getSexe().equals("Feminin"))
				.count();

		nbreDateNaissanceIsSet =
				(int) personnels.stream()
				.filter(personnel -> Objects.nonNull(personnel.getDateNaissance()) )
				.count();

		//		ages = 
		//				personnels.stream()
		//				.filter(personnel -> Objects.nonNull(personnel))
		//				.collect(summingLong(personnel -> personnel.getDateNaissance().toEpochDay()));


		final Personnel persAge = personnelLePlusAge;
		personnelLePlusAge = 
				personnels.stream()
				.filter(p -> p.getDateNaissance().isBefore(persAge.getDateNaissance()))
				.findAny()
				.get();

		final Personnel persJeune = personnelLePlusJeune;
		personnelLePlusJeune = 
				personnels.stream()
				.filter(p -> p.getDateNaissance().isAfter(persJeune.getDateNaissance()))
				.findAny()
				.get();

		nbreDatePriseServiceIsSet =
				(int) personnels.stream()
				.filter(personnel -> Objects.nonNull(personnel.getDatePriseService()) )
				.count();

		final Personnel persAncien = personnelLePlusAncien;
		personnelLePlusAncien =
				personnels.stream()
				.filter(p -> p.getDatePriseService().isBefore(persAncien.getDatePriseService()))
				.findAny()
				.get();

		final Personnel persNouveau = personnelNouveau;
		personnelNouveau = 
				personnels.stream()
				.filter(p -> p.getDatePriseService().isAfter(persNouveau.getDatePriseService()))
				.findAny()
				.get();

		nbreCategorieIsSet =
				(int) personnels.stream()
				.filter(personnel -> Objects.nonNull(personnel.getCategorie()) )
				.count();

	}

	//methode non terminée
	private boolean isInCategories(Categorie categorie) {
		boolean result= 
				categories.stream()
				.anyMatch(statcategorie ->  statcategorie.getDesignation().equals(statcategorie.getDesignation()));
		return true;

	}

	private boolean isInBanque(Banque banque) {
		return false;

	}

	private boolean isInRegion(Region region) {
		return false;
	}

	private boolean isInStructure(Structure structure) {
		return false;
	}

	private boolean isInEtatCivil(String etatCivil) {
		return false;
	}

	private int indexOfCategorie(Categorie categorie) {
		return 0;

	}

	private int indexOfStructure(Structure structure) {
		return 0;

	}

	private int indexOfBanque(Banque banque) {
		return 0;

	}

	private int indexOfEtatCivil(String etatCivil) {
		return 0;

	}

	private int indexOfRegion(Region region) {
		return 0;

	}




}

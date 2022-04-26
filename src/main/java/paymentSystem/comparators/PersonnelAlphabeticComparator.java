package paymentSystem.comparators;

import java.util.Comparator;

import paymentSystem.entity.rh.Personnel;

public class PersonnelAlphabeticComparator implements Comparator<Personnel> {

	public int compare(Personnel personnel1, Personnel personnel2) {
		if(!personnel1.getNom().equals(personnel2.getNom()))
			return personnel1.getNom().compareTo(personnel2.getNom());
		else
			return personnel1.getPrenom().compareTo(personnel2.getPrenom());

	}


}

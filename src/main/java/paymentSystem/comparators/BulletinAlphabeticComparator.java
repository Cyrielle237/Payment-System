package paymentSystem.comparators;

import java.util.Comparator;

import paymentSystem.entity.paie.BulletinPaie;
import paymentSystem.entity.rh.Personnel;

public class BulletinAlphabeticComparator implements Comparator<BulletinPaie> {

	@Override
	public int compare(BulletinPaie bulletin1,BulletinPaie bulletin2) {
		Personnel personnel1=bulletin1.getPersonnel();
		Personnel personnel2=bulletin2.getPersonnel();
		if(!personnel1.getNom().equals(personnel2.getNom()))
			return personnel1.getNom().compareTo(personnel2.getNom());
		else
			return personnel1.getPrenom().compareTo(personnel2.getPrenom());
	}

}

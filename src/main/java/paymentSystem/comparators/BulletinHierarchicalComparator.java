package paymentSystem.comparators;

import java.time.LocalDate;
import java.util.Comparator;

import paymentSystem.entity.paie.BulletinPaie;
import paymentSystem.entity.rh.Personnel;

public class BulletinHierarchicalComparator implements Comparator<BulletinPaie> {
	@Override
	public int compare(BulletinPaie bulletin1,BulletinPaie bulletin2) {
		Personnel personnel1=bulletin1.getPersonnel();
		Personnel personnel2=bulletin2.getPersonnel();
		if(personnel1.getRang()==null && personnel2.getRang()==null){
			return 0;
		}else if(personnel1.getRang()==null){
			return -1;
		}else if(personnel2.getRang()==null){
			return 1;
		}else{
			if(!personnel2.getRang().getCode().equals(personnel1.getRang().getCode()))
				return -1* personnel2.getRang().getCode().compareTo(personnel1.getRang().getCode());
			else
				return -1*Long.valueOf(personnel1.getAnciennete(LocalDate.now())).compareTo(personnel2.getAnciennete(LocalDate.now()));
		}
	}


}

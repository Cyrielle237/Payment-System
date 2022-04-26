package paymentSystem.comparators;

import java.time.LocalDate;
import java.util.Comparator;

import paymentSystem.entity.rh.Personnel;

public class PersonnelHierarchicalComparator implements Comparator<Personnel> {
	@Override
	public int compare(Personnel personnel1, Personnel personnel2) {
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

package paymentSystem.comparators;

import java.util.Comparator;

import paymentSystem.entity.paie.RegleCalculPaie;
import paymentSystem.util.TypeRubriquePaie;

public class PrimeComparator  implements Comparator<RegleCalculPaie> {
	public int compare(RegleCalculPaie regle1, RegleCalculPaie regle2) {
		if(TypeRubriquePaie.SALAIREBASE.getCode().equals(regle1.getRubriquePaie().getType())) return -1;
		else if(TypeRubriquePaie.SALAIREBASE.getCode().equals(regle2.getRubriquePaie().getType())) return 1;

		if((TypeRubriquePaie.PRIME.getCode().equals(regle1.getRubriquePaie().getType())
				|| TypeRubriquePaie.INDEMNITE.getCode().equals(regle1.getRubriquePaie().getType())) 
				&& (TypeRubriquePaie.PRIME.getCode().equals(regle2.getRubriquePaie().getType())
						|| TypeRubriquePaie.INDEMNITE.getCode().equals(regle2.getRubriquePaie().getType())) ){
			if((regle1.getTauxApplique()!=null && regle1.getTauxApplique() <=0 ) && 
					(regle2.getTauxApplique()!=null && regle2.getTauxApplique() > 0)) return -1;
			else if(regle2.getTauxApplique()!=null && regle2.getTauxApplique() <=0 &&
					regle1.getTauxApplique()!=null && regle1.getTauxApplique()>0 ) return 1;
		}


		if(TypeRubriquePaie.PRIME.getCode().equals(regle1.getRubriquePaie().getType())&&
				! TypeRubriquePaie.PRIME.getCode().equals(regle2.getRubriquePaie().getType())) return -1;
		else if(TypeRubriquePaie.PRIME.getCode().equals(regle2.getRubriquePaie().getType())&&
				! TypeRubriquePaie.PRIME.getCode().equals(regle1.getRubriquePaie().getType())) return 1;

		if(TypeRubriquePaie.INDEMNITE.getCode().equals(regle1.getRubriquePaie().getType())&&
				! TypeRubriquePaie.INDEMNITE.getCode().equals(regle2.getRubriquePaie().getType())) return -1;
		else if(TypeRubriquePaie.INDEMNITE.getCode().equals(regle2.getRubriquePaie().getType())&&
				! TypeRubriquePaie.INDEMNITE.getCode().equals(regle1.getRubriquePaie().getType())) return 1;

		if(TypeRubriquePaie.AVANTAGEENNATURE.getCode().equals(regle1.getRubriquePaie().getType())&&
				! TypeRubriquePaie.AVANTAGEENNATURE.getCode().equals(regle2.getRubriquePaie().getType())) return -1;
		else if(TypeRubriquePaie.AVANTAGEENNATURE.getCode().equals(regle2.getRubriquePaie().getType())&&
				! TypeRubriquePaie.AVANTAGEENNATURE.getCode().equals(regle1.getRubriquePaie().getType())) return 1;
		if(Integer.parseInt(regle1.getRubriquePaie().getType()) > Integer.parseInt(regle2.getRubriquePaie().getType())) return 1;
		else if(Integer.parseInt(regle1.getRubriquePaie().getType()) < Integer.parseInt(regle2.getRubriquePaie().getType())) return -1;
		else return 0;
	}


}

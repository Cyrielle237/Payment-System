package paymentSystem.comparators;

import java.util.Comparator;

import paymentSystem.entity.paie.RegleCalculPaie;

public class RetenueComparator implements Comparator<RegleCalculPaie> {
	@Override
	public int compare(RegleCalculPaie regle1, RegleCalculPaie regle2) {
		//if(regle1.getRubriquePaie().getCode().equals(CoreConstants.CODE_IRPP)) return 1 ;
		//if(regle2.getRubriquePaie().getCode().equals(CoreConstants.CODE_IRPP)) return -1 ;
		if(regle1.getRubriquePaie().getLibellePartSalariale()&&regle2.getRubriquePaie().getLibellePartPatronale()) return -1;
		if(regle2.getRubriquePaie().getLibellePartSalariale()&&regle1.getRubriquePaie().getLibellePartPatronale()) return 1;
		else return 0;
	}

}

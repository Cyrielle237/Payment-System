package paymentSystem.util;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor

public enum MotifResiliationContrat {
	DEMISSION("1","DEMISSION"),LICENCIEMENT("2","LICENCIEMENT"),FINCONTRAT("3","FIN CONTRAT"),
	DECES("4","DECES"),MALADIE("5","MALADIE"), FINDETACHEMENT("6","FIN PERIODE DETACHEMENT"),
	FINMISEADISPOSITION("7","FIN MISE A DISPOSITION"),RETRAITE("8","RETRAITE");

	@Getter
	@Setter
	private String code, designation;

	public static Map<String,String> getListeMotifs(){
		Map<String,String> map=new HashMap<String,String>();
		map.put(DEMISSION.getCode(), DEMISSION.getDesignation());
		map.put(DECES.getCode(), DECES.getDesignation());
		map.put(LICENCIEMENT.getCode(), LICENCIEMENT.getDesignation());
		map.put(FINCONTRAT.getCode(), FINCONTRAT.getDesignation());
		map.put(FINMISEADISPOSITION.getCode(), FINMISEADISPOSITION.getDesignation());
		map.put(MALADIE.getCode(), MALADIE.getDesignation());
		map.put(FINDETACHEMENT.getCode(), FINDETACHEMENT.getDesignation());
		map.put(RETRAITE.getCode(), RETRAITE.getDesignation());
		return map;
	}

}

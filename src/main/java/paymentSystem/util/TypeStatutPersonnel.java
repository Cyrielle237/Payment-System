package paymentSystem.util;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public enum TypeStatutPersonnel {

	CONTRACTUEL ("1", "CONTRACTUEL"),FONCTIONNAIREDETACHE("2","FONCTIONNAIRE DETACHE"),
	FONCTIONNAIREMISADISPOSITION("3","FONCTIONNAIRE MIS A DISPOSITION"),
	CASSPECIAL("4","CAS SPECIAL");


	@Setter
	private String code, designation;

	public static String getDesignation(String code){
		if(FONCTIONNAIREMISADISPOSITION.getCode().equals(code)) return FONCTIONNAIREMISADISPOSITION.getDesignation();
		else if(FONCTIONNAIREDETACHE.getCode().equals(code)) return FONCTIONNAIREDETACHE.getDesignation();
		else if(CONTRACTUEL.getCode().equals(code)) return CONTRACTUEL.getDesignation();
		else return null;
	}

	public static Map<String, String> getListeValeurs() {

		Map<String, String> map = new HashMap<String, String>();
		map.put(FONCTIONNAIREMISADISPOSITION.getCode(), FONCTIONNAIREMISADISPOSITION.getDesignation());
		map.put(FONCTIONNAIREDETACHE.getCode(), FONCTIONNAIREDETACHE.getDesignation());
		map.put(CONTRACTUEL.getCode(), CONTRACTUEL.getDesignation());
		return map;
	}

}

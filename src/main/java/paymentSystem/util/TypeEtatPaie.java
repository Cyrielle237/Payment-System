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

public enum TypeEtatPaie {
	ENCOURS("1","En Cours"),CLOTURE("2","CLOTURE"),ANNULE("3","ANNULE");

	@Setter
	private String code, designation;

	public static String getDesignation(String code) {
		if(ENCOURS.getCode().equals(code)) return ENCOURS.getDesignation();
		else if(CLOTURE.getCode().equals(code)) return CLOTURE.getDesignation();
		else if(ANNULE.getCode().equals(code)) return ANNULE.getDesignation();
		else return null;
	}


	public static Map<String, String> getListeValeurs() {
		Map<String, String> map = new HashMap<String, String>();

		return map;
	}


}

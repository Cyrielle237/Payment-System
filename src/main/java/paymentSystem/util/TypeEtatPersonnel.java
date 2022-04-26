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

public enum TypeEtatPersonnel {
	ENSERVICE("1","En Service"),ENRETRAITE("2","En Retraite"),DECEDE("3","Décédé"),AUTRE("4","Autre");

	@Setter
	private String code, designation;

	public static String getDesignation(String code) {
		if(ENSERVICE.getCode().equals(code)) return ENSERVICE.getDesignation();
		else if(ENRETRAITE.getCode().equals(code)) return ENRETRAITE.getDesignation();
		else if(DECEDE.getCode().equals(code)) return DECEDE.getDesignation();
		else if(AUTRE.getCode().equals(code)) return AUTRE.getDesignation();
		else return null;
	}

	public static Map<String, String> getListeValeurs() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(ENSERVICE.getCode(), ENSERVICE.getDesignation());

		return map;
	}


}

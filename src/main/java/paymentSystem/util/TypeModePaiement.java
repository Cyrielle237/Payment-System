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
public enum TypeModePaiement {
	VIREMENT ("1", "VIREMENT"),CHEQUE("2","CHEQUE"),ESPECES("3","ESPECES");


	@Setter
	private String code, designation;

	public static String getDesignation(String code){
		if(VIREMENT.getCode().equals(code)) return VIREMENT.getDesignation();
		else if(CHEQUE.getCode().equals(code)) return CHEQUE.getDesignation();
		else if(ESPECES.getCode().equals(code)) return ESPECES.getDesignation();
		else return null;
	}

	public static Map<String, String> getListeValeurs() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(VIREMENT.getCode(), VIREMENT.getDesignation());
		map.put(CHEQUE.getCode(), CHEQUE.getDesignation());
		map.put(ESPECES.getCode(), ESPECES.getDesignation());
		return map;
	}

}

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
public enum TypeRubriquePaie {

	SALAIREBASE("1","SALAIRE DE BASE"), PRIME("2","PRIME"), INDEMNITE("3","INDEMNITE"),
	RETENUE("4", "RETENUE"),  REVENUEXCEPTIONNEL("5","REVENU EXCEPTIONNEL"),
	AVANTAGEENNATURE("6","AVANTAGE EN NATURE"), ALLOCATIONFAMILLIALE("7","ALLOCATION FAMILLIALE");


	@Setter
	private String code;
	private String designation;

	public static String getLibelle(String code){
		if(SALAIREBASE.getCode().equals(code)) return SALAIREBASE.getDesignation();
		else if(PRIME.getCode().equals(code)) return PRIME.getDesignation();
		else if(INDEMNITE.getCode().equals(code)) return INDEMNITE.getDesignation();
		else if(RETENUE.getCode().equals(code)) return RETENUE.getDesignation();
		else if(ALLOCATIONFAMILLIALE.getCode().equals(code)) return ALLOCATIONFAMILLIALE.getDesignation();
		else if(AVANTAGEENNATURE.getCode().equals(code)) return AVANTAGEENNATURE.getDesignation();
		else if(REVENUEXCEPTIONNEL.getCode().equals(code)) return REVENUEXCEPTIONNEL.getDesignation();
		else return null;
	}

	public static Map<String,String> getListeValeurs(){
		Map<String,String> map=new HashMap<String,String>();
		map.put(SALAIREBASE.getCode(), SALAIREBASE.getDesignation());
		map.put(PRIME.getCode(), PRIME.getDesignation());
		map.put(INDEMNITE.getCode(), INDEMNITE.getDesignation());
		map.put(RETENUE.getCode(), RETENUE.getDesignation());
		map.put(REVENUEXCEPTIONNEL.getCode(), REVENUEXCEPTIONNEL.getDesignation());
		return map;
	}


}

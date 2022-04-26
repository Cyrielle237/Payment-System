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
public enum TypeSituationMatrimonial {

	CELIBATAIRE("1", "CELIBATAIRE"), MARIE("2", "MARIE"), DIVORCE("3",
			"DIVORCE"), VEUF("4", "VEUF");


	@Setter
	private String code, designation;

	public static Map<String, String> getListeValeurs() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(CELIBATAIRE.getCode(), CELIBATAIRE.getDesignation());
		map.put(MARIE.getCode(), MARIE.getDesignation());
		map.put(DIVORCE.getCode(), DIVORCE.getDesignation());
		map.put(VEUF.getCode(), VEUF.getDesignation());
		return map;
	}

}

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
public enum TypeSexe {

	MASCULIN ("1", "MASCULIN"),FEMININ("2","FEMININ");

	@Setter
	private String code, designation;

	public static Map<String, String> getListeValeurs() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(MASCULIN.getCode(), MASCULIN.getDesignation());
		map.put(FEMININ.getCode(), FEMININ.getDesignation());
		return map;
	}

}

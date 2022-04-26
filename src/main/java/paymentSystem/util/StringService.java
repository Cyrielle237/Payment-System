package paymentSystem.util;

import java.util.HashMap;
import java.util.Map;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringService {

	public static final String YEAR="YEAR";
	public static final String DAY="DAY";
	public static final String DATE_FORMAT="dd-MM-yyyy";
	public static final String DATETIME_FORMAT="dd-MM-yyyy HH:mm:ss";
	public static final String TIME_FORMAT="HH:mm:ss";
	private static StringService stringService;


	public static StringService getInstance(){
		if(stringService==null)
			stringService = new StringService();
		return stringService;
	}


	public String removeSpecialChars(String word){
		Map<String, String> map = new HashMap<String, String> ();
		String newWord = word ;
		newWord = newWord.replaceAll("é", "e");
		newWord = newWord.replaceAll("é".toUpperCase(), "e".toUpperCase());
		newWord = newWord.replaceAll("à", "a");
		newWord = newWord.replaceAll("à".toUpperCase(), "a".toUpperCase());
		newWord = newWord.replaceAll("è", "e");
		newWord = newWord.replaceAll("è".toUpperCase(), "e".toUpperCase());
		newWord = newWord.replaceAll("'", " ");
		newWord = newWord.replaceAll("’", " ");
		newWord = newWord.replaceAll("-", " ");
		newWord = newWord.replaceAll("\\.", "");
		return newWord ;
	}

	public static void main(String[] args)throws Exception{

		System.out.println(StringService.getInstance().removeSpecialChars("S'’ilvèreé."));
	}

}

package paymentSystem.util;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import lombok.extern.log4j.Log4j2;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
@Log4j2
public class NumberService {  ///ecrire la classe logger pour decommenter les logs
	private static NumberService instance = null;
	private static  ScriptEngine engine = null;
	public static final String PERCENTAGE_FORMAT="#0.00";
	public static final String PERCENTAGE_FORMAT2="##,###.00";
	public static final String MONTANT_FORMAT="##,###";

	private NumberService(){
		ScriptEngineManager mgr = new ScriptEngineManager() ;
		engine = mgr.getEngineByName("JavaScript") ;
	}


	public static NumberService getInstance() {
		if(instance == null)
			instance = new NumberService() ;
		return instance ;
	}

	public Double evaluate(String formule, Map<String, Object> params) throws Exception{
		try{
			Set<String> keys=params.keySet();
			for(String key:keys){
				System.out.println("Key="+key+", Value="+params.get(key));
				formule=formule.replaceAll(key, params.get(key).toString());
			}
			System.out.println("Formule après remplacement="+formule);
			log.error("Formule après remplacement="+formule);
			Expression expression = new ExpressionBuilder(formule).build();
			return expression.evaluate();
		}catch(Exception e){
			//getLogger().error("Formule après remplacement="+formule);
			throw e;
		}
	}

	public String format(Double number, String format){
		if(number==null) return "";
		DecimalFormat formatter = new DecimalFormat(format);
		return formatter.format(number);
	}

	public String format( Long number, String format){
		if(number==null) return "";
		DecimalFormat formatter = new DecimalFormat(format);
		return formatter.format(number);
	}


	public static String Unite(String x){
		String ch="";
		switch (Integer.parseInt(x)){
		case 0: ch="zéro";break;
		case 1: ch="un";break;
		case 2: ch="deux";break;
		case 3: ch="trois";break;
		case 4: ch="quatre";break;
		case 5: ch="cinq";break;
		case 6: ch="six";break;
		case 7: ch="sept";break;
		case 8: ch="huit";break;
		case 9: ch="neuf";break;
		}
		return ch;
	}
	public static String Dizaine(String x){
		String ch=""; String x1=x.substring(0,1) ; String x2=x.substring(1,2);
		switch(Integer.parseInt(x1)){
		case 0: ch=Unite(x2);break;
		case 1:
			switch(Integer.parseInt(x2)){
			case 0: ch="dix";break;
			case 1: ch="onze";break;
			case 2: ch="douze";break;
			case 3: ch="treize";break;
			case 4: ch="quatorze";break;
			case 5: ch="quinze";break;
			case 6: ch="seize";break;
			case 7: ch="dix-sept";break;
			case 8: ch="dix-huit";break;
			case 9: ch="dix-neuf";break;
			}
			break;
		case 2:
			ch="vingt";
			switch(Integer.parseInt(x2)){
			case 0: ch="vingt";break;
			default: ch=ch + " " + Unite(x2);break;
			}
			break;
		case 3:
			ch="trente";
			switch(Integer.parseInt(x2)){
			case 0: ch="trente";break;
			default: ch=ch + " " + Unite(x2);break;
			}
			break;
		case 4:
			ch="quarante";
			switch(Integer.parseInt(x2)){
			case 0: ch="quarante";break;
			default: ch=ch + " " + Unite(x2);break;
			}
			break;
		case 5:
			ch="cinquante";
			switch(Integer.parseInt(x2)){
			case 0: ch="cinquante";break;
			default: ch=ch + " " + Unite(x2);break;
			}
			break;
		case 6:
			ch="soixante";
			switch(Integer.parseInt(x2)){
			case 0: ch="soixante";break;
			default: ch=ch + " " + Unite(x2);break;
			}
			break;
		case 7:
			switch(Integer.parseInt(x2)){
			case 0: ch="soixante dix";break;
			case 1: ch="soixante onze";break;
			case 2: ch="soixante douze";break;
			case 3: ch="soixante treize";break;
			case 4: ch="soixante quatorze";break;
			case 5: ch="soixante quinze";break;
			case 6: ch="soixante seize";break;
			case 7: ch="soixante dix-sept";break;
			case 8: ch="soixante dix-huit";break;
			case 9: ch="soixante dix-neuf";break;
			}
			break;
		case 8:
			ch="quatre-vingt";
			switch(Integer.parseInt(x2)){
			case 0: ch="quatre-vingts";break;
			default: ch=ch + " " + Unite(x2);break;
			}
			break;
		case 9:
			ch="quatre-vingt dix";
			switch(Integer.parseInt(x2)){
			case 0: ch="quatre-vingt dix";break;
			case 1: ch="quatre-vingt onze";break;
			case 2: ch="quatre-vingt douze";break;
			case 3: ch="quatre-vingt treize";break;
			case 4: ch="quatre-vingt quatorze";break;
			case 5: ch="quatre-vingt quinze";break;
			case 6: ch="quatre-vingt seize";break;
			case 7: ch="quatre-vingt dix-sept";break;
			case 8: ch="quatre-vingt dix-huit";break;
			case 9: ch="quatre-vingt dix-neuf";break;
			}
			break;
		}
		return ch;

	}

	public static String Centaine(String x){
		String ch="";
		if(x.length() ==1){
			ch = Unite(x);
			return ch;
		}
		if(x.length() ==2){
			ch = Dizaine(x);
			return ch;
		}
		String x1=x.substring(0,1);
		String x2=x.substring(1,2);
		String x3=x.substring(2,3);
		switch (Integer.parseInt(x1)){
		case 0: ch=Dizaine(x2+x3);break;
		case 1:
			ch="cent";
			switch(Integer.parseInt(x2)){
			case 0:
				switch(Integer.parseInt(x3)){
				case 0:
				default: ch=ch + " " + Unite(x3);break;
				}
			default:ch=ch + " " + Dizaine(x2+x3);break;
			}
			break;
		default:
			ch= Unite(x1);
			switch(Integer.parseInt(x2)){
			case 0:
				switch(Integer.parseInt(x3)){
				case 0: ch=ch + " cents"; break;
				default: ch=ch + " cent " + Unite(x3);break;
				}
				break;
			default: ch = ch + " cent " + Dizaine(x2+x3);break;
			}
			break;
		}
		return ch;
	}

	public static String Mille(String x){
		String ch="";int i=x.length()-3;
		String x1=x.substring(0,i); String x2=x.substring(i,x.length());
		if(Integer.parseInt(x1) ==0){
			ch = Centaine(x2);
		}else{
			if(Integer.parseInt(x2) ==0){
				ch = Centaine(x1) + " mille ";
			}else{
				ch = Centaine(x1) + " mille " + Centaine(x2);
			}
		}
		return ch;
	}

	public static String Million(String x){
		String ch=""; int i=x.length()-6;
		String x1=x.substring(0,i); String x2=x.substring(i,x.length());
		if(Integer.parseInt(x1) ==0){
			ch = Mille(x2);
		}else{
			if(Integer.parseInt(x2) ==0){
				ch = Centaine(x1) + " million(s) ";
			}else{
				ch = Centaine(x1) + " million(s) " + Mille(x2);
			}
		}

		return ch;
	}
	public static String Milliard(String x){
		String ch=""; int i=x.length()-9;
		String x1=x.substring(0,i); String x2=x.substring(i,x.length());
		if(Integer.parseInt(x1) ==0){
			ch = Million(x2);
		}else{
			if(Integer.parseInt(x2) ==0){
				ch = Centaine(x1) + " milliard(s) ";
			}else{
				ch = Centaine(x1) + " milliard(s) " + Million(x2);
			}
		}

		return ch;
	}

	public static String convertToLetters(Long montant){
		String montantChiffre=Long.toString(montant),montantLettre="";

		if(montantChiffre.length()>=10) return Milliard(montantChiffre);
		else if(montantChiffre.length()>=7) montantLettre= Million(montantChiffre);
		else if(montantChiffre.length()>=4) montantLettre= Mille(montantChiffre);
		else if(montantChiffre.length()>=3) montantLettre= Centaine(montantChiffre);
		else if(montantChiffre.length()>=2) montantLettre= Dizaine(montantChiffre);
		else if(montantChiffre.length()>=1) montantLettre= Unite(montantChiffre);
		else montantLettre="";
		char firstChar=montantLettre.charAt(0);
		montantLettre=montantLettre.replaceFirst(""+firstChar, (""+firstChar).toUpperCase());
		if(montantLettre.endsWith("un un"))
			montantLettre=montantLettre.substring(0, montantLettre.length()-3);
		return montantLettre;
	}



	public static void main(String[] args){
		try{
			String chaine="administrateur";

			System.out.println(NumberService.getInstance().format(4L,
					NumberService.PERCENTAGE_FORMAT));

			//System.out.println(NumberService.getInstance().format(4L, NumberService.PERCENTAGE_FORMAT));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

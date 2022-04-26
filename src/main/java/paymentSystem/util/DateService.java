package paymentSystem.util;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import paymentSystem.entity.ref.Mois;

@NoArgsConstructor
@Getter
@Setter
public class DateService {
	public static final String YEAR="YEAR";
	public static final String DAY="DAY";
	public static final String DATE_FORMAT="dd-MM-yyyy";
	public static final String DATETIME_FORMAT="dd-MM-yyyy HH:mm:ss";
	public static final String TIME_FORMAT="HH:mm:ss";
	private static DateService dateService;

	public static DateService getInstance(){
		if(dateService==null)
			dateService=new DateService();
		return dateService;
	}

	public LocalDate addDays(LocalDate date, int days){
		return date.plusDays(days);
	}


	public LocalDateTime addHours(LocalDateTime date, int hours){
		return date.plusHours(hours);
	}

	public String format(LocalDate date, String format) {
		return date.format(DateTimeFormatter.ofPattern(format)) ;
	}


	public String format(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) ;
	}


	public LocalDate getDateValue(String format, String date) throws ParseException{
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
		LocalDate localDate = LocalDate.parse(date, dateFormatter);
		return localDate;
	}

	public LocalDate getDateValue(String date) throws ParseException{
		//SimpleDateFormat dateFormatter=new SimpleDateFormat(DATE_FORMAT);
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		LocalDate localDate = LocalDate.parse(date, dateFormatter);
		return localDate;
	}

	public int compare( LocalDate date1,LocalDate date2){
		if( date1.compareTo(date2) >0) 
			return 1;
		else if(date1.compareTo(date2) <0) 
			return -1;
		else 
			return 0;
	}

	public Long dateDiff(LocalDate date1, LocalDate date2){
		Period period = Period.between(date1, date2) ;
		return period.get(ChronoUnit.YEARS) ;
	}

	public long monthsDiff(LocalDate date1, LocalDate date2){
		int month1 = Integer.parseInt(DateService.getInstance().format(date1, "MM")) ;
		int month2 = Integer.parseInt(DateService.getInstance().format(date2, "MM")) ;

		int year1 = Integer.parseInt(DateService.getInstance().format(date1, "yyyy")) ;
		int year2 = Integer.parseInt(DateService.getInstance().format(date2, "yyyy")) ;

		long monthsDiff = (year1 - year2)*12L + (month1 - month2) ;
		return monthsDiff ;
	}

	public long yearssDiff(LocalDate date1, LocalDate date2){
		long yearsInBetween = ChronoUnit.YEARS.between(date1, date2); 
		//int monthsDiff = ChronoUnit.MONTHS.between(date1, date2); 
		return yearsInBetween  ;
	}


	public Mois getMois(String code) {  //switch case
		String designation=null;
		if("01".equals(code)) designation="JANVIER";
		else if("02".equals(code)) designation="FEVRIER";
		else if("03".equals(code)) designation="MARS";
		else if("04".equals(code)) designation="AVRIL";
		else if("05".equals(code)) designation="MAI";
		else if("06".equals(code)) designation="JUIN";
		else if("07".equals(code)) designation="JUILLET";
		else if("08".equals(code)) designation="AOUT";
		else if("09".equals(code)) designation="SEPTEMBRE";
		else if("10".equals(code)) designation="OCTOBRE";
		else if("11".equals(code)) designation="NOVEMBRE";
		else if("12".equals(code)) designation="DECEMBRE";
		else if("13".equals(code)) designation="13EME MOIS";
		else return null;
		Mois mois=new Mois();
		mois.setCode(code);
		mois.setLabel(designation);
		return mois;
	}




	public long dateDiff(LocalDate date1, LocalDate date2, String mode){
		Instant instant1 = date1.atStartOfDay(ZoneId.systemDefault()).toInstant();	
		long timeInMillis1 = instant1.toEpochMilli();

		Instant instant2 = date2.atStartOfDay(ZoneId.systemDefault()).toInstant();	
		long timeInMillis2 = instant2.toEpochMilli();

		if(mode!=null && mode.equals(YEAR)){
			return (timeInMillis1 -timeInMillis2)/(1000*60*60*24*365L);
		}else if(mode!=null && mode.equals(DAY)){
			return (timeInMillis1 -timeInMillis2)/(1000*60*60*24L);
		}else 
			return timeInMillis1 -timeInMillis2;
	}




	public static void main(String[] args)throws Exception{
		System.out.println(DateService.getInstance().dateDiff(LocalDate.now(),
				DateService.getInstance().getDateValue("dd/MM/yyyy","21/06/2009"),DateService.YEAR));

	}

}

package paymentSystem.security;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import paymentSystem.util.DateService;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="logs") 
public class Log  {
	@Id
	@Column(name="code")
	private String code;
	@Column(name="username")
	private String username;
	@Column(name="action")
	private String action;
	@Column(name="entite")
	private String entite;
	@Column(name="date")
	private LocalDate date;
	@Column(name="objet")
	private String objet;
	@javax.persistence.Transient
	private String libelleDate;

	public String getLibelleDate()throws Exception {
		if(this.date!=null){
			this.libelleDate=DateService.getInstance().format(getDate(), DateService.DATETIME_FORMAT);
		}
		return libelleDate;
	}

}

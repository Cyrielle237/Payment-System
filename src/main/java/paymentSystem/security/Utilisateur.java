package paymentSystem.security;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.util.DateService;


@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="utilisateur") 
public class Utilisateur  {

	@Id
	@Column(name="code")
	private String code;

	@Column(name="login",unique=true,updatable=false)
	private String login;

	@Column(name="password")
	private String password;

	@OneToOne
	@JoinColumn(name="code_personnel")
	private Personnel personnel;

	@ManyToOne
	@JoinColumn(name="code_role")
	private Role role;

	@Column(name="etat")
	private String etat;

	@Column(name="date_dern_connexion")
	private LocalDate dateDernConnexion;

	@Column(name="date_exp_mdp")
	private LocalDate dateExpMdp;

	@Column(name="nbre_tentatives_connexions_erronnees",nullable=false)
	private Integer nbreTentativesConnexionsErronnes=0; 

	@Column(name="connecte")
	private Boolean connecte;

	public String getLibelleDateDernConn() {
		if(dateDernConnexion !=null)
			return DateService.getInstance().format(dateDernConnexion, DateService.DATE_FORMAT);
		else return null;
	}

	public String getLibelleDateExpMdp() {
		if(dateExpMdp !=null)
			return DateService.getInstance().format(dateExpMdp, DateService.DATE_FORMAT);
		else return null;
	}

	public String getDateExpMdpAsString(){
		return DateService.getInstance().format(dateExpMdp);
	}

	public String getDateDernConnAsString(){
		return DateService.getInstance().format(dateDernConnexion);
	}

}

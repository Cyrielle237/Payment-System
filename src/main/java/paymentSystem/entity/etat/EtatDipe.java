package paymentSystem.entity.etat;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EtatDipe implements Serializable{
	private String matricule;
	private String nom;
	private String numeroDIPE;
	private String numeroCNPS;
	private String salaireBrut;
	private String salaireTaxable;
	private String salaireCotisable;
	private String plafond;
	private String irpp;
	private String cac;
	private String tdl;

}

package paymentSystem.entity.paie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import paymentSystem.util.TypeRubriquePaie;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="rubriquepaie") 
public class RubriquePaie {
	@Id
	@Column(name="code")
	private String code;
	@Column(name="designation",unique=true)
	private String designation;
	@Column(name="description")
	private String description;
	@Column(name="type")
	private String type;
	@Column(name="cotisable")
	private String cotisable;
	@Column(name="taxable")
	private String taxable;
	@Column(name="taux")
	private Double taux;
	@Column(name="part_patronale")
	private String partPatronale;
	@Column(name="part_salariale")
	private String partSalariale;

	@Transient
	private String libelleType;
	@OneToOne
	@JoinColumn(name="code_variable_paie")
	private VariablePaie variablePaie;
	@Transient
	private boolean libelleCotisable;
	@Transient
	private boolean libelleTaxable;
	@Transient
	private boolean libellePartSalariale;
	@Transient
	private boolean libellePartPatronale;

	public boolean getLibelleCotisable() {
		return cotisable!=null && "1".equals(cotisable);
	}


	public boolean getLibelleTaxable() {
		return taxable !=null && "1".equals(taxable);
	}


	public boolean getLibellePartSalariale() {
		return partSalariale !=null && "1".equals(partSalariale);
	}


	public boolean getLibellePartPatronale() {
		return partPatronale !=null && "1".equals(partPatronale);
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	} 

	public void setTaux(Integer taux) {
		if(taux!=null)
			this.taux = taux*1.0;
	}


	public String getLibelleType() {
		return TypeRubriquePaie.getLibelle(type);
	}

}

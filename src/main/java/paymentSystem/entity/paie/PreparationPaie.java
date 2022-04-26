package paymentSystem.entity.paie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import paymentSystem.entity.rh.Personnel;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="preparationpaie") 
public class PreparationPaie{
	@Id
	@Column(name="code")
	private String code;
	@ManyToOne
	@JoinColumn(name="code_paie",nullable=false)
	private Paie paie;
	@ManyToOne
	@JoinColumn(name="code_personnel",nullable=false)
	private Personnel personnel;
	@Column(name="heures_supp")
	private Long heuresSupp;
	@Column(name="conges_payes")
	private Double congesPayes;
	@Column(name="absences")
	private Long absences;
	@Column(name="avance")
	private Double avance;
	@Column(name="remboursement")
	private Double remboursement;


}

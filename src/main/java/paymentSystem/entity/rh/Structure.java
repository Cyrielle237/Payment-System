package paymentSystem.entity.rh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Table(name="structure")
public class Structure {
	@Id
	@Column(name="code")
	private String code;

	@Column(name="designation",unique=true)
	private String designation;

	@ManyToOne
	@JoinColumn(name="code_structure_parente")
	private Structure structureParente;

	@Column(name="sigle",unique=true)
	private String sigle;

	@Column(name="numero_sp")
	private Integer numeroSp;

	@Column(name="libelle_sp")
	private Integer libelleSp;

}

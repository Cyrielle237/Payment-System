package paymentSystem.entity.paie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="variablepaie") 
public class VariablePaie {
	@Id
	@Column(name="code")
	private String code;
	@Column(name="designation",nullable=false,unique=true)
	private String designation;
	@Column(name="description",nullable=false)
	private String description;

}

package paymentSystem.entity.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="pays")
public class Pays {
	@Id
	@Column(name="code")
	private String code;
	@Column(name="designation")
	private String designation;

}

package paymentSystem.entity.rh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
@Entity
@Table(name="rang")
public class Rang {
	@Id
	@Column(name="code")
	private String code;
	@Column(name="designation",unique=true)
	private String designation;
}

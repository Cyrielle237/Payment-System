package paymentSystem.entity.ref;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Table(name="ville")
public class Ville  {
	@Id
	@Column(name="code")
	private String code;
	@Column(name="designation",unique=true)
	private String designation;
	@OneToMany(mappedBy="ville")
	private List<Quartier> quartiers=null;

}

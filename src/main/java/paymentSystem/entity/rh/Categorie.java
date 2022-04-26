package paymentSystem.entity.rh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table(name="categorie")
public class Categorie{
	@Id
	@Column(name="code")
	private String code;

	@Column(name="designation" ,unique=true)
	private String designation;

	@Column(name="description")
	private String description;

}

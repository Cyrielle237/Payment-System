package paymentSystem.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="action") 
public class Action {
	@Id
	@Column(name="code")
	private String code;

	@Column(name="designation",unique=true)
	private String designation;

	@Column(name="entite")
	private String entite;

	@Column(name="methode")
	private String methode;


}

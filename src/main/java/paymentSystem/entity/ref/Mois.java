package paymentSystem.entity.ref;

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
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="mois")
public class Mois{
	@Id
	@Column(name="code")
	private String code;
	public static Mois TREISIEME_MOIS = new Mois("13", "13ème MOIS") ;

	@Column(name="label",nullable=false, unique=true)
	private String label;


}

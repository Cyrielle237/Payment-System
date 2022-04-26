package paymentSystem.entity.rh;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name="conge")
public class Conge  {
	@Id
	@Column(name="code")
	private String code;


}

package paymentSystem.entity.etat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class StatCategorie {
	private String designation;
	private Double occurences;
	private Double pourcentage;

}

package paymentSystem.entity.etat;

import java.time.LocalDate;

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
public class VueConge {
	private String typeConge;
	private LocalDate dateDepart;
	private LocalDate dateRetour;
	private int nbreJour;

}

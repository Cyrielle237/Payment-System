package paymentSystem.entity.etat;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import paymentSystem.entity.rh.Notation;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VueNotation {
	private Double note;
	private LocalDate dateNotation;

	public VueNotation(Notation notation) {
		this.note =notation.getNote();
		this.dateNotation = LocalDate.now();
	}

}

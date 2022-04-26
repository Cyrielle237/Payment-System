package paymentSystem.entity.etat;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import paymentSystem.entity.rh.Structure;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StructureVue {
	private String designation;
	private String sigle;
	private String structureParente;
	private List<VuePersonnel> employes;
	private int nbrEmploye;

	public StructureVue(Structure structure, List<VuePersonnel> employes) {
		this.employes = employes;
		this.nbrEmploye = employes.size();
		this.designation = structure.getDesignation()+" ("+structure.getSigle()+")";
		this.sigle = structure.getSigle();
		this.structureParente = (structure.getStructureParente() != null)? structure.getStructureParente().getDesignation()+" ("+structure.getStructureParente().getSigle()+")":"";
	}

}

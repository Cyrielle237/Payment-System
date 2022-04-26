package paymentSystem.test.repository.paie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import paymentSystem.entity.paie.RegleCalculPaie;
import paymentSystem.entity.paie.RubriquePaie;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Categorie;
import paymentSystem.entity.rh.Echelon;
import paymentSystem.entity.rh.Fonction;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.entity.rh.Rang;
import paymentSystem.repository.paie.RegleCalculPaieRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RegleCalculPaieTest {
	//tous ces tests sont des cas positifs, penser écrire ceux qui generent des erreurs

	@Autowired
	private RegleCalculPaieRepository regleCalcPaieRepository ;

	@Autowired
	private TestEntityManager entityManager ;


	@Test
	public void tester_getAvantagesEnNature() throws Exception {
		Categorie categorie = entityManager.persist(Categorie.builder().code("1369640790466").designation("11").build()) ;
		Echelon echelon = entityManager.persist(Echelon.builder().code("1369641072669").description("A").build()) ;
		Rang rang = entityManager.persist(Rang.builder().code("1371134012200").designation("Cadre").build());
		Fonction fonction = entityManager.persist(Fonction.builder().code("1369640790466").designation("Cadre").build());
		Personnel personnel = entityManager.persist(Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO").prenom("Gilbert")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2022, 1, 1)).categorie(categorie).echelon(echelon)
				.dureeContrat(2).fonction(fonction).rang(rang).build()) ;
		RubriquePaie rubriquePaie = entityManager.persist(RubriquePaie.builder().code("1371288006660").description("AVANTAGES EN NATURE").type("6").build()) ;
		RegleCalculPaie regleCalc = entityManager.persist(RegleCalculPaie.builder().code("1369640790466").rubriquePaie(rubriquePaie)
				.categorie(categorie).ancienneteInf(null).ancienneteSup(null).echelon(echelon).rang(rang).fonction(fonction).personnel(personnel).build());

		List<RegleCalculPaie> regles = regleCalcPaieRepository.getAvantagesEnNature(personnel, Mois.builder().code("03").label("MARS").build(), 2022);

		assertNotNull(regles);
		assertThat(regles).isNotEmpty();
	}

	//AVANTAGES EN NATURE THAT FAILS

	@Test
	public void tester_getPrimesIdemnites() throws Exception {
		Categorie categorie = entityManager.persist(Categorie.builder().code("1369640790466").designation("11").build()) ;
		Echelon echelon = entityManager.persist(Echelon.builder().code("1369641072669").description("A").build()) ;
		Rang rang = entityManager.persist(Rang.builder().code("1371134012200").designation("Cadre").build());
		Fonction fonction = entityManager.persist(Fonction.builder().code("1369640790466").designation("Cadre").build());
		Personnel personnel = entityManager.persist(Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO").prenom("Gilbert")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2022, 1, 1)).categorie(categorie).echelon(echelon)
				.dureeContrat(2).fonction(fonction).rang(rang).build()) ;
		RubriquePaie rubriquePaie = entityManager.persist(RubriquePaie.builder().code("1371288006660").description("AVANTAGES EN NATURE").type("6").build()) ;
		RegleCalculPaie regleCalc = entityManager.persist(RegleCalculPaie.builder().code("1369640790466").rubriquePaie(rubriquePaie)
				.categorie(categorie).ancienneteInf(null).ancienneteSup(null).echelon(echelon).rang(rang).fonction(fonction).personnel(personnel).build());

		List<RegleCalculPaie> regles = regleCalcPaieRepository.getPrimesIndemnites(personnel, Mois.builder().code("03").label("MARS").build(), 2022);
		assertNotNull(regles);
		assertThat(regles).isNotEmpty();

	}

	//PRIMES INDEMNITES THAT FAILS


	@Test
	public void tester_getReglesRubrique() throws Exception {
		Categorie categorie = entityManager.persist(Categorie.builder().code("1369640790466").designation("11").build()) ;
		Echelon echelon = entityManager.persist(Echelon.builder().code("1369641072669").description("A").build()) ;
		Rang rang = entityManager.persist(Rang.builder().code("1371134012200").designation("Cadre").build());
		Mois mois = entityManager.persist(Mois.builder().code("03").label("MARS").build());
		Fonction fonction = entityManager.persist(Fonction.builder().code("1369640790466").designation("Cadre").build());
		Personnel personnel = entityManager.persist(Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO").prenom("Gilbert")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2022, 1, 1)).categorie(categorie).echelon(echelon)
				.dureeContrat(2).fonction(fonction).rang(rang).build()) ;
		RubriquePaie rubriquePaie = entityManager.persist(RubriquePaie.builder().code("1371288006660").description("AVANTAGES EN NATURE").type("6").build()) ;
		RegleCalculPaie regleCalc = entityManager.persist(RegleCalculPaie.builder().code("1369640790466").rubriquePaie(rubriquePaie)
				.categorie(categorie).echelon(echelon).mois(mois)
				.fonction(fonction).personnel(personnel).annee(2022).build());

		List<RegleCalculPaie> regles = regleCalcPaieRepository.getReglesRubrique(mois, 2022, "6");
		assertNotNull(regles);
		assertThat(regles).isNotEmpty();		
	}

	@Test
	public void tester_getReglesRubrique_AndLoss() throws Exception {
		Categorie categorie = entityManager.persist(Categorie.builder().code("1369640790466").designation("11").build()) ;
		Echelon echelon = entityManager.persist(Echelon.builder().code("1369641072669").description("A").build()) ;
		Rang rang = entityManager.persist(Rang.builder().code("1371134012200").designation("Cadre").build());
		Mois mois = entityManager.persist(Mois.builder().code("03").label("MARS").build());
		Fonction fonction = entityManager.persist(Fonction.builder().code("1369640790466").designation("Cadre").build());
		Personnel personnel = entityManager.persist(Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO").prenom("Gilbert")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2022, 1, 1)).categorie(categorie).echelon(echelon)
				.dureeContrat(2).fonction(fonction).rang(rang).build()) ;
		RubriquePaie rubriquePaie = entityManager.persist(RubriquePaie.builder().code("1371288006660").description("AVANTAGES EN NATURE").type("6").build()) ;
		RegleCalculPaie regleCalc = entityManager.persist(RegleCalculPaie.builder().code("1369640790466").rubriquePaie(rubriquePaie)
				.categorie(categorie).echelon(echelon).mois(mois)
				.fonction(fonction).personnel(personnel).annee(2022).build());

		List<RegleCalculPaie> regles = regleCalcPaieRepository.getReglesRubrique(Mois.builder().code("03").label("MARS").build(), 2022, "2");
		assertThat(regles).isEmpty();		
	}


	@Test
	public void tester_getRetenues() throws Exception {

	}

	//	public List<RegleCalculPaie> getRetenues(Personnel personnel, Mois mois, Integer annee) throws Exception {
	//		String query="select r from RegleCalculPaie r where   r.rubriquePaie.type=:typeRubrique  " +
	//				" and ( r.categorie is null or r.categorie.code=:codeCategorie) "+
	//				" and (r.echelon is null or r.echelon.code=:codeEchelon) and (r.rang is null or r.rang.code=:codeRang) "+
	//				" and (r.statutPersonnel is null or r.statutPersonnel=:statutPersonnel) "+
	//				" and (r.fonction is null or r.fonction.code=:codeFonction) and (r.fonction is null or r.fonction.code=:codeFonction) "+
	//				" and ( r.ancienneteInf is null or r.ancienneteSup is null or ( r.ancienneteInf <=:anciennete and r.ancienneteSup>=:anciennete)) "+
	//				" and ( r.personnel is null or r.personnel.code=:codePersonnel)"+
	//				" and ( r.mois.code is null or r.mois.code=:codeMois)"+
	//				" and ( r.annee is null or r.annee=:annee) ";
	//
	//		Query jpaQuery=getEntityManager().createQuery(query);
	//		jpaQuery.setParameter("typeRubrique", TypeRubriquePaie.RETENUE.getCode());
	//		jpaQuery.setParameter("statutPersonnel", personnel.getStatut());
	//		jpaQuery.setParameter("codeCategorie", personnel.getCategorie().getCode());
	//		jpaQuery.setParameter("codeEchelon", personnel.getEchelon().getCode());
	//		jpaQuery.setParameter("codeRang", personnel.getRang().getCode());
	//		jpaQuery.setParameter("codeFonction", personnel.getFonction().getCode());
	//		jpaQuery.setParameter("anciennete", personnel.getAnciennete(DateService.getInstance().getDateValue("dd/MM/yyyy","25/"+mois.getCode()+"/"+annee)));
	//		jpaQuery.setParameter("codePersonnel", personnel.getCode());
	//		jpaQuery.setParameter("codeMois", mois.getCode());
	//		jpaQuery.setParameter("annee", annee);
	//		List<RegleCalculPaie> regles= jpaQuery.getResultList();
	//		return regles;
	//	}


	@Test
	public void tester_getRetenues_EmptyList() throws Exception {

	}


}

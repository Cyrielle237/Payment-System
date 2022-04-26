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

import paymentSystem.entity.paie.BulletinPaie;
import paymentSystem.entity.paie.RegleCalculPaie;
import paymentSystem.entity.paie.RubriqueBulletinPaie;
import paymentSystem.entity.paie.RubriquePaie;
import paymentSystem.entity.ref.Banque;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Categorie;
import paymentSystem.entity.rh.Echelon;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.entity.rh.Rang;
import paymentSystem.repository.paie.BulletinPaieRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BulletinPaieRepositoryTest {

	//tous ces tests sont des cas positifs, penser écrire ceux qui generent des erreurs

	@Autowired
	private BulletinPaieRepository bulletinPaieRepository ;

	@Autowired
	private TestEntityManager entityManager ;


	@Test
	public void tester_getSalaireDeBase_avecCategorieEtEchelon() throws Exception {
		//Categorie, Echelon, Personnel, RubriquePaie, RegleCalculPaie
		Categorie categorie = entityManager.persist(Categorie.builder().code("1369640790466").designation("11").build()) ;
		Echelon echelon = entityManager.persist(Echelon.builder().code("1369641072669").description("A").build()) ;

		Personnel personnel = entityManager.persist(Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO").prenom("Gilbert")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2022, 1, 1)).categorie(categorie).echelon(echelon)
				.dureeContrat(2).build()) ;

		RubriquePaie rubriquePaie = entityManager.persist(RubriquePaie.builder().code("1371288006660").description("Salaire de base").build()) ;

		RegleCalculPaie regleCalculPaie = entityManager.persist(RegleCalculPaie.builder().code("1371288006660").rubriquePaie(rubriquePaie)
				.categorie(categorie).echelon(echelon).formule("500000").build()) ;

		RubriqueBulletinPaie rubriqueBulletinPaie = bulletinPaieRepository.getSalaireDeBase(personnel,  
				Mois.builder().code("03").label("MARS").build(), 2022) ;

		assertNotNull(rubriqueBulletinPaie) ;
		assertThat(rubriqueBulletinPaie.getMontant()).isEqualTo(Long.valueOf(500000)) ;
	}



	@Test
	public void tester_getSalaireDeBase_avecPersonnelUniquement() throws Exception {
		//Categorie, Echelon, Personnel, RubriquePaie, RegleCalculPaie
		Categorie categorie = entityManager.persist(Categorie.builder().code("1369640790466").designation("11").build()) ;
		Echelon echelon = entityManager.persist(Echelon.builder().code("1369641072669").description("A").build()) ;

		Personnel personnel = entityManager.persist(Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO").prenom("Gilbert")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2022, 1, 1)).categorie(categorie).echelon(echelon)
				.dureeContrat(2).build()) ;

		RubriquePaie rubriquePaie = entityManager.persist(RubriquePaie.builder().code("1371288006660").description("Salaire de base").build()) ;

		RegleCalculPaie regleCalculPaie = entityManager.persist(RegleCalculPaie.builder().code("1371288006660").rubriquePaie(rubriquePaie)
				.personnel(personnel).formule("400000").build()) ;

		RubriqueBulletinPaie rubriqueBulletinPaie = bulletinPaieRepository.getSalaireDeBase(personnel,  
				Mois.builder().code("03").label("MARS").build(), 2022) ;

		assertNotNull(rubriqueBulletinPaie) ;
		assertThat(rubriqueBulletinPaie.getMontant()).isEqualTo(Long.valueOf(400000)) ;
	}


	@Test 
	public void tester_getMajorationInterne() throws Exception{
		Categorie categorie = entityManager.persist(Categorie.builder().code("1369640790466").designation("11").build()) ;
		Echelon echelon = entityManager.persist(Echelon.builder().code("1369641072669").description("A").build()) ;
		Personnel personnel = entityManager.persist(Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO").prenom("Gilbert")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2022, 1, 1)).categorie(categorie).echelon(echelon)
				.dureeContrat(2).build()) ;
		RubriquePaie rubriquePaie = entityManager.persist(RubriquePaie.builder().code("1371288778768").description("Code majoration interne").build()) ;
		RegleCalculPaie regleCalculPaie = entityManager.persist(RegleCalculPaie.builder().code("1371288006660").rubriquePaie(rubriquePaie)
				.personnel(personnel).formule("900000").build()) ;

		RubriqueBulletinPaie rubriqueBulletinPaie = bulletinPaieRepository.getMajorationInterne(personnel, 
				Mois.builder().code("03").label("MARS").build(), 2022);

		assertNotNull(rubriqueBulletinPaie) ;
		assertThat(rubriqueBulletinPaie.getMontant()).isEqualTo(Long.valueOf(900000)) ;
		assertThat(rubriqueBulletinPaie.getRubriquePaie().getCode()).isEqualTo("1371288778768");
		assertThat(rubriqueBulletinPaie.getMontant()).isNotNull();
	}


	@Test
	public void tester_getRubrique13Mois() throws Exception {
		Categorie categorie = entityManager.persist(Categorie.builder().code("1369640790466").designation("11").build()) ;
		Echelon echelon = entityManager.persist(Echelon.builder().code("1369641072669").description("A").build()) ;
		Rang rang = entityManager.persist(Rang.builder().code("1371134012200").designation("Cadre").build());
		Personnel personnel = entityManager.persist(Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO").prenom("Gilbert")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2020, 1, 1)).categorie(categorie).echelon(echelon)
				.rang(rang).dureeContrat(4).build()) ;
		RubriquePaie rubriquePaie = entityManager.persist(RubriquePaie.builder().code("1382946998698").description("Code treisieme mois").build()) ;

		RubriqueBulletinPaie rubriqueBulletinPaie = bulletinPaieRepository.getRubrique13Mois(personnel, rubriquePaie, Long.valueOf(90000), 
				Mois.builder().code("03").label("MARS").build(), 2021);

		assertNotNull(rubriqueBulletinPaie) ;
		assertThat(rubriqueBulletinPaie.getRubriquePaie().getCode()).isEqualTo("1382946998698");
		assertThat(rubriqueBulletinPaie.getMontant()).isEqualTo(Long.valueOf(90000));

	}


	@Test
	public void tester_rechercherBulletins() throws Exception{
		Categorie categorie = entityManager.persist(Categorie.builder().code("1369640790466").designation("11").build()) ;
		Echelon echelon = entityManager.persist(Echelon.builder().code("1369641072669").description("A").build()) ;
		Rang rang = entityManager.persist(Rang.builder().code("1371134012200").designation("Cadre").build());
		Mois mois = entityManager.persist(Mois.builder().code("03").label("MARS").build());
		Banque banque = entityManager.persist(Banque.builder().code("1369641980375").designation("Afriland First Bank").codeRib("10005").build());
		Personnel personnel = entityManager.persist(Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO").prenom("Gilbert")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2020, 1, 1)).categorie(categorie).echelon(echelon)
				.rang(rang).banque(banque).dureeContrat(4).build()) ;
		BulletinPaie bulletinPaie = entityManager.persist(BulletinPaie.builder().code("1310105281623").personnel(personnel)
				.mois(mois).annee(2022).build());
		List<BulletinPaie> bulletin = bulletinPaieRepository.rechercherBulletins(rang,"03", 2022, banque);

		assertNotNull(bulletin) ;
		assertThat(bulletin.size()).isGreaterThan(0);

	}


	@Test
	public void tester_rechercherBulletinsContractuels() throws Exception {
		Categorie categorie = entityManager.persist(Categorie.builder().code("1369640790466").designation("11").build()) ;
		Echelon echelon = entityManager.persist(Echelon.builder().code("1369641072669").description("A").build()) ;
		Rang rang = entityManager.persist(Rang.builder().code("1371134011934").designation("DG").build());
		Rang rd = entityManager.persist(Rang.builder().code("1371134011995").designation("DGA").build());
		Mois mois = entityManager.persist(Mois.builder().code("02").label("MARS").build());
		//Structure structure =  entityManager.persist(Structure.builder().code(null).numeroSp(null)
		Banque banque = entityManager.persist(Banque.builder().code("1369641980375").designation("Afriland First Bank").codeRib("10005").build());
		Personnel personnel = entityManager.persist(Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO").prenom("Gilbert")
				.dateNaissance(LocalDate.of(1990,  10, 10)).categorie(categorie).echelon(echelon).rang(rang)
				.banque(banque).dureeContrat(4).datePriseService(LocalDate.of(2021, 03, 10)).statut("1").build()) ;

		BulletinPaie bulletinPaie = entityManager.persist(BulletinPaie.builder().code("1310105281623").personnel(personnel)
				.mois(mois).annee(2022).build());

		List<BulletinPaie> bulletin = bulletinPaieRepository.rechercherBulletinsContractuels("02", 2022, banque, new Rang[]{rd});
		assertNotNull(bulletin) ;
		assertThat(bulletin).isNotEmpty();
	}



}

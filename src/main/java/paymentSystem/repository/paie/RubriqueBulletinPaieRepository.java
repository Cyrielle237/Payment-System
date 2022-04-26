package paymentSystem.repository.paie;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.paie.BulletinPaie;
import paymentSystem.entity.paie.RubriqueBulletinPaie;
import paymentSystem.entity.rh.Personnel;

public interface RubriqueBulletinPaieRepository extends CrudRepository<RubriqueBulletinPaie, String> {

	List<RubriqueBulletinPaie> findByCodeOrPersonnelOrBulletinPaie(String code, Personnel personnel, BulletinPaie b);

}

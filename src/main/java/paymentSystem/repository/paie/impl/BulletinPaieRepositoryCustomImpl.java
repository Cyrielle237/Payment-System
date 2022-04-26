package paymentSystem.repository.paie.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import paymentSystem.entity.exceptions.BackendException;
import paymentSystem.entity.paie.BulletinPaie;
import paymentSystem.entity.paie.RegleCalculPaie;
import paymentSystem.entity.paie.RubriqueBulletinPaie;
import paymentSystem.entity.paie.RubriquePaie;
import paymentSystem.entity.ref.Banque;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.entity.rh.Rang;
import paymentSystem.repository.paie.BulletinPaieRepositoryCustom;
import paymentSystem.util.CoreConstants;
import paymentSystem.util.DateService;
import paymentSystem.util.NumberService;

public class BulletinPaieRepositoryCustomImpl implements BulletinPaieRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}


	@Override
	public RubriqueBulletinPaie getSalaireDeBase(Personnel personnel, Mois mois, Integer annee) throws Exception{
		String query="select r from RegleCalculPaie r where "+
				"  (r.rubriquePaie.code=:codeRubrique1  and r.categorie.code=:codeCategorie  and  r.echelon.code=:codeEchelon )"+
				" or (  r.rubriquePaie.code=:codeRubrique2  and r.personnel.code=:codePersonnel )";
		Query jpaQuery=getEntityManager().createQuery(query);
		jpaQuery.setParameter("codeRubrique1",CoreConstants.CODE_SALAIRE_DE_BASE);
		jpaQuery.setParameter("codeRubrique2",CoreConstants.CODE_SALAIRE_DE_BASE);
		jpaQuery.setParameter("codePersonnel", personnel.getCode());
		jpaQuery.setParameter("codeCategorie", personnel.getCategorie().getCode());
		jpaQuery.setParameter("codeEchelon", personnel.getEchelon().getCode());	

		List<RegleCalculPaie> regles=jpaQuery.getResultList();
		RegleCalculPaie regleCalculPaie=null;
		//logger.info("Nombre de règles de calcul de paie sur le salaire de base : "+(regles!=null?regles.size():0));
		if(regles.size()==0){
			throw new BackendException ("Calcul impossible : Salaire de base non configuré");
		}else if(regles.size()==1){
			regleCalculPaie=regles.get(0);
		}else if(regles.size()>1){
			for(RegleCalculPaie regle:regles){
				if(regle.getPersonnel()!=null){
					regleCalculPaie=regle;
					break;
				}
			}
		}

		if( regleCalculPaie==null){
			throw new BackendException ("Calcul impossible : Salaire de base mal configuré");
		}
		getEntityManager().clear();
		RubriqueBulletinPaie rubriqueBulletinPaie=new RubriqueBulletinPaie();
		rubriqueBulletinPaie.setMois(mois);
		rubriqueBulletinPaie.setAnnee(annee);
		rubriqueBulletinPaie.setPersonnel(personnel);
		rubriqueBulletinPaie.setRubriquePaie(regleCalculPaie.getRubriquePaie());
		rubriqueBulletinPaie.setMontant(Math.round(1.0*NumberService.getInstance().evaluate(regleCalculPaie.getFormule(), new HashMap<String, Object>())));

		return rubriqueBulletinPaie;
	}


	@Override
	public RubriqueBulletinPaie getMajorationInterne(Personnel personnel, Mois mois, Integer annee) throws Exception {
		String query="select r from RegleCalculPaie r where  r.rubriquePaie.code=:codeRubrique  and r.personnel.code=:codePersonnel";
		Query jpaQuery=getEntityManager().createQuery(query);
		jpaQuery.setParameter("codeRubrique",CoreConstants.CODE_MAJORATION_INTERNE);
		jpaQuery.setParameter("codePersonnel", personnel.getCode());
		List<RegleCalculPaie> regles=jpaQuery.getResultList();

		if( regles==null || regles.size()==0){
			return null;
		}

		RegleCalculPaie regleCalculPaie=regles.get(0);
		RubriqueBulletinPaie rubriqueBulletinPaie=new RubriqueBulletinPaie();
		rubriqueBulletinPaie.setMois(mois);
		rubriqueBulletinPaie.setAnnee(annee);
		rubriqueBulletinPaie.setPersonnel(personnel);
		rubriqueBulletinPaie.setRubriquePaie(regleCalculPaie.getRubriquePaie());
		//rubriqueBulletinPaie.setMontant(Math.round(NumberService.getInstance().evaluate(regleCalculPaie.getFormule(), null)));  //added (NumberService)
		rubriqueBulletinPaie.setMontant(Math.round(NumberService.getInstance().evaluate(regleCalculPaie.getFormule(), new HashMap<String, Object>())));  
		return rubriqueBulletinPaie;
	}



	@Override
	public Double getIrpp(Double salaireBrutTaxable, Double revenusExceptionnels) throws Exception {
		Double irppNormal=12*salaireBrutTaxable*(1-0.3-0.028)-500000.0;
		System.out.println("Irpp Normal 0="+irppNormal);
		irppNormal= (irppNormal<=2000000.0? irppNormal*0.1:200000.0)+ (irppNormal<=3000000.0? Math.max(0, 0.15*(irppNormal-2000000.0)):150000.0)+
				(irppNormal<=5000000.0? Math.max(0, 0.25*(irppNormal-3000000.0)):500000.0)+ (irppNormal>=5000000.0? 0.35*(irppNormal-5000000.0):0.0);
		System.out.println("Irpp normal="+irppNormal);
		Double irppREX=(12*salaireBrutTaxable+revenusExceptionnels/4)*(1-0.3-0.028)-500000.0;
		System.out.println("Irpp excep. 0 ="+irppREX);
		irppREX= (irppREX<=2000000.0? irppREX*0.1:200000.0)+ (irppREX<=3000000.0? Math.max(0, 0.15*(irppREX-2000000.0)):150000.0)+
				(irppREX<=5000000.0? Math.max(0, 0.25*(irppREX-3000000.0)):500000.0)+ (irppREX>=5000000.0? 0.35*(irppREX-5000000.0):0.0);
		System.out.println("Irpp excep. 1 ="+irppREX);
		irppREX=4*(irppREX-irppNormal);
		System.out.println("Irpp excep. 2 ="+irppREX);
		return irppNormal/12.0+irppREX;
	}



	@Override
	public Double getIrpp(Double salaireBrutTaxable) throws Exception{
		Double irppNormal=12*salaireBrutTaxable*(1-0.3-0.028)-500000.0;
		System.out.println("Irpp Normal 0="+irppNormal);
		irppNormal= (irppNormal<=2000000.0? irppNormal*0.1:200000.0)+ (irppNormal<=3000000.0? Math.max(0, 0.15*(irppNormal-2000000.0)):150000.0)+
				(irppNormal<=5000000.0? Math.max(0, 0.25*(irppNormal-3000000.0)):500000.0)+ (irppNormal>=5000000.0? 0.35*(irppNormal-5000000.0):0.0);
		System.out.println("Irpp normal="+irppNormal);		
		return irppNormal/12.0;
	}


	@Override
	public List<BulletinPaie> rechercherBulletins(Rang rang, String mois, int annee, Banque banque) throws Exception {
		String queryString = "select b from BulletinPaie b where b.personnel.rang=:rang and b.mois.code=:codeMois and b.annee=:annee " ;
		if(banque != null && banque.getCode() != null)
			queryString += " and b.personnel.banque=:banque" ;
		Query query = getEntityManager().createQuery(queryString) ;
		query.setParameter("rang", rang) ;
		query.setParameter("codeMois", mois) ;
		query.setParameter("annee", annee) ;
		if(banque != null && banque.getCode() != null)
			query.setParameter("banque", banque) ;
		return query.getResultList() ;
	}



	@Override
	public List<BulletinPaie> rechercherBulletinsNouveaux(String mois, int annee, Banque banque, Rang... rangs)
			throws Exception {
		LocalDate debutAnnee = DateService.getInstance().getDateValue("01-01-"+annee) ;
		LocalDate finAnnee = DateService.getInstance().getDateValue("31-12-"+annee) ;

		String queryString = "select b from BulletinPaie b where  b.mois.code=:codeMois and b.annee=:annee  " ;
		queryString += " and b.personnel.datePriseService <= :dateFinAnneeCalcul and b.personnel.datePriseService >= :dateDebutAnneeCalcul " ;
		if(banque != null && banque.getCode() != null)
			queryString += " and b.personnel.banque=:banque " ;
		for (int i = 0 ; i < rangs.length; i++) {
			queryString+=" and b.personnel.rang !=:rang"+i ;
		}
		Query query = getEntityManager().createQuery(queryString) ;
		for (int i = 0 ; i < rangs.length; i++) {
			query.setParameter("rang"+i, rangs[i]) ;
		}
		query.setParameter("codeMois", mois) ; 
		query.setParameter("annee", annee) ;
		query.setParameter("dateDebutAnneeCalcul", debutAnnee) ;
		query.setParameter("dateFinAnneeCalcul", finAnnee) ;
		if(banque != null && banque.getCode() != null)
			query.setParameter("banque", banque) ;
		return query.getResultList() ;
	}




	@Override
	public List<BulletinPaie> rechercherBulletinsContractuels(String mois, int annee, Banque banque, Rang... rangs)
			throws Exception {
		LocalDate debutAnnee = DateService.getInstance().getDateValue("01-01-"+annee) ;
		String queryString = "select b from BulletinPaie b where  b.mois.code=:codeMois and b.annee=:annee and b.personnel.statut=:statut " ;
		queryString += " and b.personnel.datePriseService < :dateDebutAnneeCalcul " ;
		if(banque != null && banque.getCode() != null)
			queryString += " and b.personnel.banque=:banque " ;
		for (int i = 0 ; i < rangs.length; i++) {
			queryString+=" and b.personnel.rang !=:rang"+i ;
		}
		Query query = getEntityManager().createQuery(queryString) ;
		for (int i = 0 ; i < rangs.length; i++) {
			query.setParameter("rang"+i, rangs[i]) ; 
		}
		query.setParameter("statut", "1") ;
		query.setParameter("codeMois", mois) ;
		query.setParameter("annee", annee) ;
		query.setParameter("dateDebutAnneeCalcul", debutAnnee) ;
		if(banque != null && banque.getCode() != null)
			query.setParameter("banque", banque) ;
		return query.getResultList() ;
	}



	@Override
	public List<BulletinPaie> rechercherBulletinsFonctionnaires(String mois, int annee, Banque banque, Rang... rangs)
			throws Exception {
		LocalDate debutAnnee = DateService.getInstance().getDateValue("01-01-"+annee) ;
		String queryString = "select b from BulletinPaie b where  b.mois.code=:codeMois and b.annee=:annee and (b.personnel.statut=:statut1  or b.personnel.statut=:statut2)" ;
		queryString += " and b.personnel.datePriseService < :dateDebutAnneeCalcul " ;
		if(banque != null && banque.getCode() != null)
			queryString += " and b.personnel.banque=:banque " ;
		for (int i = 0 ; i < rangs.length; i++) {
			queryString+=" and b.personnel.rang !=:rang"+i ;
		}
		Query query = getEntityManager().createQuery(queryString) ;
		for (int i = 0 ; i < rangs.length; i++) {
			query.setParameter("rang"+i, rangs[i]) ;
		}
		query.setParameter("statut1", "2") ;
		query.setParameter("statut2", "3") ;
		query.setParameter("codeMois", mois) ;
		query.setParameter("dateDebutAnneeCalcul", debutAnnee) ;
		query.setParameter("annee", annee) ;
		if(banque != null && banque.getCode() != null)
			query.setParameter("banque", banque) ;
		return query.getResultList() ;
	}



	@Override
	public List<BulletinPaie> rechercherBulletinsParSousProg(String mois, Integer annee, Integer sousProg)
			throws Exception {
		String queryString = "select b from BulletinPaie b where  b.mois.code=:codeMois and b.annee=:annee and b.personnel.structure.numeroSp=:numeroSp " ;
		Query query = getEntityManager().createQuery(queryString) ;
		query.setParameter("numeroSp", sousProg) ;
		query.setParameter("codeMois", mois) ;
		query.setParameter("annee", annee) ;
		return query.getResultList() ;
	}



	@Override
	public List<BulletinPaie> rechercherBulletinsNouveauxProg(String mois, int annee, int numeroSp, Rang... rangs)
			throws Exception {
		LocalDate debutAnnee = DateService.getInstance().getDateValue("01-01-"+annee) ;
		LocalDate finAnnee = DateService.getInstance().getDateValue("31-12-"+annee) ;

		String queryString = "select b from BulletinPaie b where  b.mois.code=:codeMois and b.annee=:annee  " ;
		queryString += " and b.personnel.datePriseService <= :dateFinAnneeCalcul and b.personnel.datePriseService >= :dateDebutAnneeCalcul and b.personnel.structure.numeroSp=:numeroSp" ;
		for (int i = 0 ; i < rangs.length; i++) {
			queryString+=" and b.personnel.rang !=:rang"+i ;
		}
		Query query = getEntityManager().createQuery(queryString) ;

		query.setParameter("codeMois", mois) ;
		query.setParameter("annee", annee) ;
		query.setParameter("dateDebutAnneeCalcul", debutAnnee) ;
		query.setParameter("dateFinAnneeCalcul", finAnnee) ;
		query.setParameter("numeroSp", numeroSp) ;
		for (int i = 0 ; i < rangs.length; i++) {
			query.setParameter("rang"+i, rangs[i]) ;
		}
		return query.getResultList() ;
	}



	@Override
	public List<BulletinPaie> rechercherBulletinsContractuelsProg(String mois, int annee, int numeroSp, Rang... rangs)
			throws Exception {
		LocalDate debutAnnee = DateService.getInstance().getDateValue("01-01-"+annee) ;
		String queryString = "select b from BulletinPaie b where  b.mois.code=:codeMois and b.annee=:annee and b.personnel.statut=:statut " ;
		queryString += " and b.personnel.datePriseService < :dateDebutAnneeCalcul and b.personnel.structure.numeroSp=:numeroSp " ;
		for (int i = 0 ; i < rangs.length; i++) {
			queryString+=" and b.personnel.rang !=:rang"+i ;
		}
		Query query = getEntityManager().createQuery(queryString) ;

		query.setParameter("statut", "1") ;
		query.setParameter("codeMois", mois) ;
		query.setParameter("annee", annee) ;
		query.setParameter("dateDebutAnneeCalcul", debutAnnee) ;
		query.setParameter("numeroSp", numeroSp) ;
		for (int i = 0 ; i < rangs.length; i++) {
			query.setParameter("rang"+i, rangs[i]) ;
		}
		return query.getResultList() ;
	}



	@Override
	public List<BulletinPaie> rechercherBulletinsFonctionnairesProg(String mois, int annee, int numeroSp, Rang... rangs)
			throws Exception {
		LocalDate debutAnnee = DateService.getInstance().getDateValue("01-01-"+annee) ;
		String queryString = "select b from BulletinPaie b where  b.mois.code=:codeMois and b.annee=:annee and (b.personnel.statut=:statut1  or b.personnel.statut=:statut2)" ;
		for (int i = 0 ; i < rangs.length; i++) {
			queryString+=" and b.personnel.rang !=:rang"+i ;
		}
		queryString += " and b.personnel.datePriseService < :dateDebutAnneeCalcul and b.personnel.structure.numeroSp=:numeroSp" ;
		Query query = getEntityManager().createQuery(queryString) ;
		query.setParameter("statut1", "2") ;
		query.setParameter("statut2", "3") ;
		query.setParameter("codeMois", mois) ;
		query.setParameter("dateDebutAnneeCalcul", debutAnnee) ;
		query.setParameter("annee", annee) ;
		query.setParameter("numeroSp", numeroSp) ;
		for (int i = 0 ; i < rangs.length; i++) {
			query.setParameter("rang"+i, rangs[i]) ;
		}
		return query.getResultList() ;
	}




	@Override
	public List<BulletinPaie> rechercherBulletinsProg(Rang rang, String mois, int annee, int numeroSp)
			throws Exception {
		String queryString = "select b from BulletinPaie b where b.personnel.rang=:rang and b.mois.code=:codeMois and b.annee=:annee and b.personnel.structure.numeroSp=:numeroSp" ;

		Query query = getEntityManager().createQuery(queryString) ;
		query.setParameter("rang", rang) ;
		query.setParameter("codeMois", mois) ;
		query.setParameter("annee", annee) ;
		query.setParameter("numeroSp", numeroSp) ;
		return query.getResultList() ;

	}



	@Override
	public RubriqueBulletinPaie getRubrique13Mois(Personnel personnel, RubriquePaie rubriquePaie, Long montantBrut,
			Mois mois, Integer annee) throws Exception {
		RubriqueBulletinPaie rubriqueBulletinPaie = new RubriqueBulletinPaie() ;
		rubriqueBulletinPaie.setCode(System.currentTimeMillis()+UUID.randomUUID().toString());
		rubriqueBulletinPaie.setRubriquePaie(rubriquePaie) ;
		rubriqueBulletinPaie.setPersonnel(personnel) ;

		LocalDate date1 = LocalDate.of(annee, 12, 31);
		LocalDate date2 = personnel.getDatePriseService();

		Boolean isDGOrDGA = CoreConstants.CODE_RANG_DG.equals(personnel.getRang().getCode()) 
				|| CoreConstants.CODE_RANG_DGA.equals(personnel.getRang().getCode())  ;

		Long anciennete = DateService.getInstance().monthsDiff(date1, date2) ;
		if( anciennete < 12 || isDGOrDGA)
			return null ;
		rubriqueBulletinPaie.setMontant(montantBrut) ;
		rubriqueBulletinPaie.setAnnee(annee) ;
		rubriqueBulletinPaie.setMois(mois) ;
		return rubriqueBulletinPaie ;
	}


	@Override
	public RubriqueBulletinPaie getRubriqueIRNC(Personnel personnel, RubriquePaie rubriquePaie, 
			Long montantBrut, Mois mois, Integer annee) throws Exception {
		RubriqueBulletinPaie rubriqueBulletinPaie = new RubriqueBulletinPaie() ;
		rubriqueBulletinPaie.setCode(System.currentTimeMillis()+UUID.randomUUID().toString());
		rubriqueBulletinPaie.setRubriquePaie(rubriquePaie) ;
		rubriqueBulletinPaie.setPersonnel(personnel) ;
		rubriqueBulletinPaie.setMontant(Math.round(montantBrut*0.11)) ;
		rubriqueBulletinPaie.setAnnee(annee) ;
		rubriqueBulletinPaie.setMois(mois) ;
		return rubriqueBulletinPaie ;
	}

}

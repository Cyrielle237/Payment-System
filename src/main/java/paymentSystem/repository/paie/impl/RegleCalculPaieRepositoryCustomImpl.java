package paymentSystem.repository.paie.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import paymentSystem.entity.paie.RegleCalculPaie;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.repository.paie.RegleCalculPaieRepositoryCustom;
import paymentSystem.util.DateService;
import paymentSystem.util.TypeRubriquePaie;


public class RegleCalculPaieRepositoryCustomImpl implements RegleCalculPaieRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}


	@Override
	public List<RegleCalculPaie> getReglesRubrique( Mois mois, Integer annee, String typeRubrique) throws Exception{
		String query="select r from RegleCalculPaie r where r.annee=:annee and r.mois.code=:codeMois and "+
				" r.rubriquePaie.type=:typeRubrique";
		Query jpaQuery=getEntityManager().createQuery(query);
		jpaQuery.setParameter("annee", annee);
		jpaQuery.setParameter("codeMois", mois.getCode());
		jpaQuery.setParameter("typeRubrique", typeRubrique);
		return jpaQuery.getResultList();
	}

	@Override
	public List<RegleCalculPaie> getRetenues(Personnel personnel, Mois mois, Integer annee) throws Exception {
		String query="select r from RegleCalculPaie r where   r.rubriquePaie.type=:typeRubrique  " +
				" and ( r.categorie is null or r.categorie.code=:codeCategorie) "+
				" and (r.echelon is null or r.echelon.code=:codeEchelon) and (r.rang is null or r.rang.code=:codeRang) "+
				" and (r.statutPersonnel is null or r.statutPersonnel=:statutPersonnel) "+
				" and (r.fonction is null or r.fonction.code=:codeFonction) and (r.fonction is null or r.fonction.code=:codeFonction) "+
				" and ( r.ancienneteInf is null or r.ancienneteSup is null or ( r.ancienneteInf <=:anciennete and r.ancienneteSup>=:anciennete)) "+
				" and ( r.personnel is null or r.personnel.code=:codePersonnel)"+
				" and ( r.mois.code is null or r.mois.code=:codeMois)"+
				" and ( r.annee is null or r.annee=:annee) ";

		Query jpaQuery=getEntityManager().createQuery(query);
		jpaQuery.setParameter("typeRubrique", TypeRubriquePaie.RETENUE.getCode());
		jpaQuery.setParameter("statutPersonnel", personnel.getStatut());
		jpaQuery.setParameter("codeCategorie", personnel.getCategorie().getCode());
		jpaQuery.setParameter("codeEchelon", personnel.getEchelon().getCode());
		jpaQuery.setParameter("codeRang", personnel.getRang().getCode());
		jpaQuery.setParameter("codeFonction", personnel.getFonction().getCode());
		jpaQuery.setParameter("anciennete", personnel.getAnciennete(DateService.getInstance().getDateValue("dd/MM/yyyy","25/"+mois.getCode()+"/"+annee)));
		jpaQuery.setParameter("codePersonnel", personnel.getCode());
		jpaQuery.setParameter("codeMois", mois.getCode());
		jpaQuery.setParameter("annee", annee);
		List<RegleCalculPaie> regles= jpaQuery.getResultList();
		return regles;
	}

	@Override
	public List<RegleCalculPaie> getRevenusExceptionnels(Personnel personnel, Mois mois, Integer annee)
			throws Exception {
		String query="select r from RegleCalculPaie r where   r.rubriquePaie.type=:typeRubrique  " +
				" and ( r.categorie is null or r.categorie.code=:codeCategorie) "+
				" and (r.echelon is null or r.echelon.code=:codeEchelon) and (r.rang is null or r.rang.code=:codeRang) "+
				" and (r.fonction is null or r.fonction.code=:codeFonction) and (r.fonction is null or r.fonction.code=:codeFonction) "+
				" and ( r.ancienneteInf is null or r.ancienneteSup is null or ( r.ancienneteInf <=:anciennete and r.ancienneteSup>=:anciennete)) "+
				" and ( r.personnel is null or r.personnel.code=:codePersonnel) ";
		Query jpaQuery=getEntityManager().createQuery(query);
		jpaQuery.setParameter("typeRubrique", TypeRubriquePaie.REVENUEXCEPTIONNEL.getCode());
		jpaQuery.setParameter("codeFonction", personnel.getFonction().getCode());
		jpaQuery.setParameter("codeCategorie", personnel.getCategorie().getCode());
		jpaQuery.setParameter("codeEchelon", personnel.getEchelon().getCode());
		jpaQuery.setParameter("codeRang", personnel.getRang().getCode());
		jpaQuery.setParameter("anciennete", personnel.getAnciennete(DateService.getInstance().getDateValue("dd/MM/yyyy","25/"+mois.getCode()+"/"+annee)));
		jpaQuery.setParameter("codePersonnel", personnel.getCode());

		List<RegleCalculPaie> regles =jpaQuery.getResultList();
		return regles;
	}

	@Override
	public List<RegleCalculPaie> getAvantagesEnNature(Personnel personnel, Mois mois, Integer annee) throws Exception {
		String query="select r from RegleCalculPaie r where (r.rubriquePaie.type=:typeRubrique)" +

		" and ( r.categorie is null or r.categorie.code=:codeCategorie)"+
		" and (r.echelon is null or r.echelon.code=:codeEchelon) and (r.rang is null or r.rang.code=:codeRang) "+
		" and (r.fonction is null or r.fonction.code=:codeFonction) and (r.fonction is null or r.fonction.code=:codeFonction) "+
		" and ( r.ancienneteInf is null or r.ancienneteSup is null or ( r.ancienneteInf <=:anciennete and r.ancienneteSup>=:anciennete)) "+
		" and ( r.personnel is null or r.personnel.code=:codePersonnel) and (r.rubriquePaie.type  is not null) order by r.rubriquePaie.type desc";
		Query jpaQuery=getEntityManager().createQuery(query);
		jpaQuery.setParameter("typeRubrique", TypeRubriquePaie.AVANTAGEENNATURE.getCode());
		jpaQuery.setParameter("codeCategorie", personnel.getCategorie().getCode());
		jpaQuery.setParameter("codeEchelon", personnel.getEchelon().getCode());
		jpaQuery.setParameter("codeRang", personnel.getRang().getCode());
		jpaQuery.setParameter("anciennete", personnel.getAnciennete(DateService.getInstance().getDateValue("dd/MM/yyyy","25/"+mois.getCode()+"/"+annee)));
		jpaQuery.setParameter("codePersonnel", personnel.getCode());
		jpaQuery.setParameter("codeFonction", personnel.getFonction().getCode());
		return jpaQuery.getResultList();
	}

	@Override
	public List<RegleCalculPaie> getPrimesIndemnites(Personnel personnel, Mois mois, Integer annee) throws Exception {
		String query="select r from RegleCalculPaie r where (r.rubriquePaie.type=:typeRubrique1 or r.rubriquePaie.type=:typeRubrique2 " +
				" or r.rubriquePaie.type=:typeRubrique3 or r.rubriquePaie.type=:typeRubrique4  or r.rubriquePaie.type=:typeRubrique5 )"+
				" and ((r.mois.code is null or r.mois.code=:codeMois) and (r.annee=:annee  or r.annee is null)) and ( r.categorie is null or r.categorie.code=:codeCategorie)"+
				" and (r.echelon is null or r.echelon.code=:codeEchelon) and (r.rang is null or r.rang.code=:codeRang) "+
				" and (r.fonction is null or r.fonction.code=:codeFonction) and (r.fonction is null or r.fonction.code=:codeFonction) "+
				" and ( r.ancienneteInf is null or r.ancienneteSup is null or ( r.ancienneteInf <=:anciennete and r.ancienneteSup>=:anciennete)) "+
				" and ( r.personnel is null or r.personnel.code=:codePersonnel) ";
		query += " order by r.rubriquePaie.taux  " ;

		System.out.println("-----------------"+query);
		Query jpaQuery=getEntityManager().createQuery(query);
		jpaQuery.setParameter("typeRubrique1", TypeRubriquePaie.INDEMNITE.getCode());
		jpaQuery.setParameter("typeRubrique2", TypeRubriquePaie.PRIME.getCode());
		jpaQuery.setParameter("typeRubrique3", TypeRubriquePaie.ALLOCATIONFAMILLIALE.getCode());
		jpaQuery.setParameter("typeRubrique4", TypeRubriquePaie.AVANTAGEENNATURE.getCode());
		jpaQuery.setParameter("typeRubrique5", TypeRubriquePaie.REVENUEXCEPTIONNEL.getCode());
		jpaQuery.setParameter("codeMois", mois.getCode());
		jpaQuery.setParameter("annee", annee);
		jpaQuery.setParameter("codeCategorie", personnel.getCategorie().getCode());
		jpaQuery.setParameter("codeEchelon", personnel.getEchelon().getCode());
		jpaQuery.setParameter("codeRang", personnel.getRang().getCode());
		jpaQuery.setParameter("codeFonction", personnel.getFonction().getCode());
		jpaQuery.setParameter("anciennete", 
				DateService.getInstance().dateDiff(DateService.getInstance().getDateValue("dd/MM/yyyy","25/"+(mois.getCode().equals("13")?"12":mois.getCode())+"/"+annee),
						personnel.getDatePriseService(), DateService.YEAR));
		jpaQuery.setParameter("codePersonnel", personnel.getCode());
		List<RegleCalculPaie> regles=jpaQuery.getResultList(),regles2=new ArrayList<RegleCalculPaie>();
		for(RegleCalculPaie regle: regles){
			regles2.add(regle);
			if(regle.getPersonnel()!=null){
				for(RegleCalculPaie r: regles){
					if(r.getPersonnel()==null && r.getRubriquePaie().equals(regle.getRubriquePaie()))
						regles2.remove(r);
				}
			}else{
				for(RegleCalculPaie r: regles){
					if(r.getPersonnel()!=null && r.getRubriquePaie().equals(regle.getRubriquePaie()))
						regles2.remove(regle);
				}
			}
		}
		return regles2;
	}

}

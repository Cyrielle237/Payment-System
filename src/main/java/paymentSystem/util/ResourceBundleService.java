package paymentSystem.util;

import java.util.ResourceBundle;

public class ResourceBundleService {
	public static final String CHEMIN_LOGO_ANTIC="chemin.logo.antic";
	public static final String CHEMIN_DOSSIER_ETATS_GENERES="chemin_dossier_etats_generes";
	public static final String CHEMIN_FICHIER_ETAT_BULLETIN_PAIE_PAYSAGE="chemin_fichier_etat_bulletin_paie_mensuel_paysage";
	public static final String CHEMIN_FICHIER_ETAT_BULLETIN_PAIE_PORTRAIT="chemin_fichier_etat_bulletin_paie_mensuel_portrait";
	public static final String CHEMIN_FICHIER_ETAT_DIPE="chemin_fichier_etat_dipe_mensuel";
	public static final String CHEMIN_FICHIER_ETAT_CFC="chemin_fichier_etat_cfc";
	public static final String CHEMIN_FICHIER_ETAT_FNE="chemin_fichier_etat_fne";
	public static final String CHEMIN_FICHIER_ETAT_RAV="chemin_fichier_etat_rav";
	public static final String CHEMIN_FICHIER_ETAT_CNPS="chemin_fichier_etat_cotisations_cnps_mensuelles";
	public static final String CHEMIN_FICHIER_ETAT_TRESOR_PUBLIC="chemin_fichier_etat_cotisations_tresor_public_mensuelles";
	public static final String CHEMIN_FICHIER_ETAT_LIVRE_PAIE="chemin_fichier_etat_livre_de_paie";
	public static final String CHEMIN_FICHIER_ETAT_ORDRE_VIREMENT="chemin_fichier_etat_ordre_virement_mensuel";
	public static final String CHEMIN_FICHIER_ETAT_ORDRE_VIREMENT_BANQUE_PAR_BANQUE="chemin_fichier_etat_ordre_virement_banque_par_banque_mensuel";
	public static final String CHEMIN_FICHIER_ETAT_LISTE_PERSONNEL="chemin_fichier_etat_liste_personnel";
	public static final String CHEMIN_DOSSIER_SAUVEGARDES="chemin_dossier_sauvegardes";
	public static final String PERIODICITE_DES_SAUVEGARDES="periodicite_des_sauvegardes";
	public static final String BD_NOM_UTILISATEUR="bd.username";
	public static final String BD_MOT_DE_PASSE="bd.password";
	public static final String BD_NAME="bd.name";
	public static final String VIREMENT_EMETTEUR = "virement_emetteur" ;
	public static final String VIREMENT_EMETTEUR_CODE_A = "virement_emetteur_code_A" ;
	public static final String VIREMENT_DESTINATAIRE_CODE_A = "virement_destinataire_code_A" ;
	public static final String VIREMENT_TOTAL_CODE_A = "virement_total_code_A" ;
	public static final String VIREMENT_EMETTEUR_CODE_B1 = "virement_emetteur_code_B1" ;
	public static final String VIREMENT_DESTINATAIRE_CODE_B1 = "virement_destinataire_code_B1" ;
	public static final String VIREMENT_EMETTEUR_CODE_BANQUE = "virement_emetteur_code_banque" ;
	public static final String VIREMENT_EMETTEUR_CODE_AGENCE = "virement_emetteur_code_agence" ;
	public static final String VIREMENT_EMETTEUR_COMPTE = "virement_emetteur_compte" ;
	public static final String VIREMENT_EMETTEUR_CLE = "virement_emetteur_cle" ;
	public static final String VIREMENT_EMETTEUR_LIBELLE = "virement_emetteur_libelle" ;
	public static final String RUBRIQUE_TREIZIEME_MOIS = "rubrique_treizieme_mois_code" ;

	private static ResourceBundleService instance=null;
	public static final String BUNDLE_PATH="cm.antic.rh_paie.config";
	private ResourceBundle bundle=null;

	private ResourceBundleService ()throws Exception{
		bundle=ResourceBundle.getBundle(BUNDLE_PATH);
	}

	public static ResourceBundleService getInstance()throws Exception{
		if(instance==null){
			instance=new ResourceBundleService();
		}		
		return instance;
	}

	public String getPath(String key) throws Exception{
		return bundle.getString(key);
	}

	public String getValue(String key) throws Exception{
		return bundle.getString(key);
	}


}

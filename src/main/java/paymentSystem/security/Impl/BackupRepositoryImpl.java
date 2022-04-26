package paymentSystem.security.Impl;

import java.time.LocalDate;

import lombok.extern.log4j.Log4j2;
import paymentSystem.repository.security.BackupRepository;
import paymentSystem.util.DateService;
import paymentSystem.util.ResourceBundleService;

@Log4j2
public class BackupRepositoryImpl implements BackupRepository{

	private String fs=System.getProperty("file.separator");


	//	@Scheduled
	//	public void startTimer()throws Exception{
	//		try{
	//			if(timer==null){
	//				Long periodicite=3600000L*Integer.parseInt(ResourceBundleService.getInstance().getValue(ResourceBundleService.PERIODICITE_DES_SAUVEGARDES));
	//				timer = timerService.createTimer(0L,periodicite ,"Backup Timer Created ");
	//				log.info("Démarrage du service des sauvegardes à "+DateService.getInstance().format(LocalDate.now(),
	//						DateService.DATETIME_FORMAT));
	//			}
	//		}catch(Exception e){
	//			log.error("Erreur survenue lors du démarrage du service de sauvegardes",e);
	//		}
	//	}


	@Override
	public void sauvegarder(){
		try{
			log.info("Lancement d'une sauvegarde le "+DateService.getInstance().format(LocalDate.now(),
					DateService.DATETIME_FORMAT));
			String cheminDossierSauvegardes=ResourceBundleService.getInstance().getPath(ResourceBundleService.CHEMIN_DOSSIER_SAUVEGARDES);
			String bdname=ResourceBundleService.getInstance().getValue(ResourceBundleService.BD_NAME);
			String username=ResourceBundleService.getInstance().getValue(ResourceBundleService.BD_NOM_UTILISATEUR);
			String password=ResourceBundleService.getInstance().getValue(ResourceBundleService.BD_MOT_DE_PASSE);
			String filename=cheminDossierSauvegardes+fs+"bdpaie_"+DateService.getInstance().format(LocalDate.now(), "ddMMyyyy_hhmmss")+".sql";
			String query="mysqldump -u"+username+" -p"+password+" -r\""+filename+"\"  "+bdname;
			log.info(query);
			Runtime runtime=Runtime.getRuntime();
			Process process=runtime.exec(query);
			log.info(("Sauvegarde effectuée le "+DateService.getInstance().format(LocalDate.now(),
					DateService.DATETIME_FORMAT)));
		}catch(Exception e){
			log.error("Erreur survenue lors de la sauvegarde  du "+DateService.getInstance().format(LocalDate.now(),
					DateService.DATETIME_FORMAT),e);
		}
	}


}

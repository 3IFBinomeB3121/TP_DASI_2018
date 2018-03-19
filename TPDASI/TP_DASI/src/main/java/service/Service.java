
package service;

import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import dao.InterventionDAO;
import dao.JpaUTIL;
import dao.PersonneDAO;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.RollbackException;
import modele.Client;
import modele.Employe;
import modele.Intervention;
import modele.Personne;
import util.GeoTest;

/**
 * Service est la classe gérant tous les services transactionnels et 
 * non transactionnels nécessaires à notre application métier
 * 
 * @author Christophe Etienne
 * @author William Occelli
 * @version 1.0
 */
public class Service {
    
    /**
     * Méthode permettant d'ajouter des {@link Employe} dans la base de données
     * pour effectuer des tests de l'application.
     * 
     * @throws ParseException Exception liée à la tentative de parser
     * une chaîne de caractère en date
     */
    public static void ajouterEmploye() throws ParseException {
        //On crée l'objet Personne correspondant
        //Employe emp = new Employe(true,heuredebut,heurefin,civilite,nom,prenom,dateNaissance,age,adresse,motdepasse);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        
        // Les dates de naissances des employés
        String date = "12-05-1982";
        String date2 = "31-08-1999";
        String date3 = "20-01-1956";
        String date4 = "15-11-1987";
        
        Date d1 = sdf.parse(date);
        Date d2 = sdf.parse(date2);
        Date d3 = sdf.parse(date3);
        Date d4 = sdf.parse(date4);
       
        // Les heures de travail des employés
        int t2 = 10;
        int t3 = 15;
        int t4 = 23;
        int t5 = 20;
        
        Employe emp = new Employe(true,0,24,"mme","karda","helene",d1,"20 Rue Decomberousse, Villeurbanne", "0630276677", "helene.karda@outlook.fr", "dasi_18");
        Employe emp2 = new Employe(true,0,24,"mme","eti","julie",d1,"61 Avenue Roger Salengro, Villeurbanne", "0630276677", "julie.eti@hotmail.fr", "pdp");
        Employe emp3 = new Employe(true,0,24,"mme","occ","marine",d2,"37 Avenue Jean Capelle Est, Villeurbanne","ocmarine999@hotmail.fr","0630276677","01239485");
        Employe emp4 = new Employe(true,0,24,"m","barg","pauline",d3,"7 Avenue Jean Capelle Ouest, Villeurbanne","pauline.barg@gmail.com","0630276677","0aedi55");
        Employe emp5 = new Employe(true,0,24,"m","bell","gerard",d4,"20 Avenue Albert Eisntein, Villeurbanne", "gegedu90@gmail.com","0630276677","coucouhesa");
        Employe emp6 = new Employe(true,0,24,"m","tucker","chris",d4,"7 Rue de la Cloche, Villeurbanne", "marty.rvf@sfr.fr","0630276677","cPROBA_cool");
        
        
        //On vérifie si l'entity manager est ouvert
        JpaUTIL.creerEntityManager();
        //On commence une transaction
        JpaUTIL.ouvrirTransaction();
        
        emp.setCoords(calculerCoords(emp.getAdresse()));
        emp2.setCoords(calculerCoords(emp2.getAdresse()));
        emp3.setCoords(calculerCoords(emp3.getAdresse()));
        emp4.setCoords(calculerCoords(emp4.getAdresse()));
        emp5.setCoords(calculerCoords(emp5.getAdresse()));
        emp6.setCoords(calculerCoords(emp6.getAdresse()));
        //On rend l'objet persistant
        PersonneDAO.persistEmploye(emp);
        PersonneDAO.persistEmploye(emp2);
        PersonneDAO.persistEmploye(emp3);
        PersonneDAO.persistEmploye(emp4);
        PersonneDAO.persistEmploye(emp5);
        PersonneDAO.persistEmploye(emp6);
        //On commit la transaction
        try{
            JpaUTIL.validerTransaction();
        }catch(RollbackException e){
            JpaUTIL.annulerTransaction();
            JpaUTIL.fermerEntityManager();
            throw e;
        }
        JpaUTIL.fermerEntityManager();
    }
    
    /**
     * Méthode permettant d'inscrire un {@link Client} dans la base de données
     * 
     * @param cli {@link Client} représentant le client à inscrire dans la base
     * de données
     * @return {@Client} le client inscrit ou bien null si l'inscription
     * ne s'est pas passé correctement (e-mail déja présent dans la base)
     */
    public static Client inscrireClient(Client cli) {
        JpaUTIL.creerEntityManager();
        JpaUTIL.ouvrirTransaction();
        
        LatLng coord = calculerCoords(cli.getAdresse());
        cli.setCoords(coord);
        
        boolean mailValide = verifierValiditeMail(cli.getMail());
        
        PersonneDAO.persistClient(cli);
        
        String prenom = cli.getPrenom();
        String lePrenom = prenom.substring(0,1).toUpperCase() 
                + prenom.substring(1, prenom.length()).toLowerCase();
        
        if (mailValide && coord != null){
            System.out.println("Bonjour " + lePrenom + ",\r\n"
                    + "Nous vous confirmons votre inscription"
                    + " au service PROACT'IF. Votre numéro de client"
                    + " est : " + cli.getId());
            try{
                JpaUTIL.validerTransaction();
                JpaUTIL.fermerEntityManager();
                return cli;
            }catch (RollbackException e){
                JpaUTIL.annulerTransaction();
                JpaUTIL.fermerEntityManager();
                throw e;
            }
        }
        else {
            System.out.println("Bonjour " + lePrenom + ",\r\n"
                    + "Votre inscription au service PROACT'IF a "
                    + "malencontreusement échoué... Merci de recommencer"
                    + " ultérieurement.");
            JpaUTIL.annulerTransaction();
            JpaUTIL.fermerEntityManager();
            return null;
        }
        
    }
    
    /**
     * Méthode permettant de calculer les coordonnées GPS d'une adresse passée 
     * en paramètre
     * 
     * @param adresse {@link String} représentant l'adresse dont on
     * souhaite calculée les coordonnées GPS
     * 
     * @return {@link LatLng} les coordonnées GPS de l'adresse passée en 
     * paramètre
     */
    private static LatLng calculerCoords(String adresse){
        return GeoTest.getLatLng(adresse);
    }
    
    /**
     * Méthode permettant de vérifier la validité d'une adresse e-mail passée
     * en paramètre.
     * Une adresse e-mail est valide si elle n'est pas déja utilisée par un 
     * {@link Client} inscrit dans la base de données
     * 
     * @param mail {@link String} représentant l'adresse e-mail de la personne
     * 
     * @return {@link boolean} true si le mail est valide, false sinon
     */
    private static boolean verifierValiditeMail(String mail){
        List<Personne> listePers = PersonneDAO.verifierDoublonEmail(mail);
        return listePers.isEmpty();
    }
    
    /**
     * Méthode permettant de connecter une {@link Personne} : un {@link Client}
     * ou un {@link Employe} sur le site PROACT'IF grâce à ses identifiants 
     * (e-mail et mot de passe).
     * Pour un succés de connexion, l'adresse e-mail et le mot de passe doivent 
     * correspondre à une {@link Personne} déja inscrite dans la base de données
     * 
     * @param mail {@link String} représentant l'adresse e-mail de la personne
     * @param mdp {@link String} représentant le mot de passe de la personne
     * 
     * @return {@link Personne} la personne si le mail et le mot de passe sont
     * corrects, sinon renvoie null
     */
    public static Personne seConnecter (String mail, String mdp) {
        JpaUTIL.creerEntityManager();
        
        List<Personne> pers = PersonneDAO.verifierExistencePersonne(mail, mdp);
        JpaUTIL.fermerEntityManager();
        if (pers.isEmpty()){
            return null;
        }
        else {
            return pers.get(0);
        }
    }
    
    /**
     * Méthode permettant de charger l'historique des demandes 
     * d'{@link Intervention} d'un client depuis son inscription sur le site
     * 
     * @param cli {@link Client} représentant le client dont on souhaite
     * récupére l'historique
     * 
     * @return {@link List} d'{@link Intervention} contenant toutes les 
     * interventions que le client a demandées depuis son inscription au site
     */
    public static List<Intervention> chargerHistorique(Client cli) {
        JpaUTIL.creerEntityManager();
        
        List<Intervention> listIntervDuClient;
        listIntervDuClient = InterventionDAO.rechercherInterventionDuClient(cli);
        
        // On trie la liste en comparant les dates de demandes d'interventions
        Collections.sort(listIntervDuClient, new Comparator<Intervention>() {
 
            @Override
            public int compare(Intervention i1, Intervention i2) {
                return i2.getHorodate().compareTo(i1.getHorodate());
            }
            
        });
        
        JpaUTIL.fermerEntityManager();
        return listIntervDuClient;
    }
    
    /**
     * Méthode permettant d'effectuer une demande d'intervention. Cette méthode
     * recherche les employés disponibles c'est-à-dire qui ne sont pas déja en 
     * intervention et dont l'heure de demande d'intervention est comprise
     * dans leur horaires de travail. En fonction des {@link Employe} disponibles, on 
     * cherche celui dont la durée pour aller jusqu'à l'adresse du {@link Client}
     * en vélo est la plus faible. En cas de conflit lors du commit, un rollback 
     * est effectué et on répéte l'opération jusqu'à ce qu'un employé soit affecté
     * ou qu'aucun employé ne soit disponible.
     * 
     * @param cli {@link Client} représentant le client effectuant la demande
     * d'{@link Intervention}
     * @param interv {@link Intervention} représentant l'intervention que le
     * {@link Client} demande
     * 
     * @return {@link Intervention} l'intervention si un {@link Employe} a été 
     * affectée, sinon null
     */
    public static Intervention demanderIntervention (Client cli, Intervention interv) {
        JpaUTIL.creerEntityManager();
        JpaUTIL.ouvrirTransaction();
        
        Intervention intervention = InterventionDAO.merge(interv);
        
        Date heureIntervention = new Date();
        
        List<Employe> listEmployeDispo = PersonneDAO.rechercherEmployeDisponible(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        
        Double duration = 100000000000.0;
        int indiceEmpLePlusProche=0;
        boolean success = false;
        
        if (listEmployeDispo.isEmpty()){
            JpaUTIL.fermerEntityManager();
            return null;
        }
        else {
            while (!success && !listEmployeDispo.isEmpty()){
                for (int i = 0; i<listEmployeDispo.size(); i++){
                    Double tmp = GeoTest.getTripDurationByBicycleInMinute(cli.getCoords(), listEmployeDispo.get(i).getCoords());
                    if (tmp<duration){
                        duration = tmp;
                        indiceEmpLePlusProche = i;
                    }
                }
                Employe emp;
                emp = PersonneDAO.mergeEmploye(listEmployeDispo.get(indiceEmpLePlusProche));
                Double laDistance = GeoTest.getTripDurationOrDistance(TravelMode.BICYCLING, false, cli.getCoords(), emp.getCoords());
                emp.setDisponibilite(false);
                intervention.setClient(cli);
                intervention.setEmploye(emp);
                intervention.setDistance(laDistance);
                intervention.setHorodate(heureIntervention);
                
                try {
                    InterventionDAO.persist(intervention);
                    PersonneDAO.persistEmploye(emp);
                    JpaUTIL.validerTransaction();
                    success = true;
                } catch (RollbackException e){
                    JpaUTIL.annulerTransaction();
                    /* On recherche de nouveau les employés disponibles au cas 
                        où un employé serait devenue disponible depuis la 
                        dernière recherche*/
                    listEmployeDispo = PersonneDAO.rechercherEmployeDisponible(heureIntervention.getHours());
                }
            }
        }
        // Cas où on est sorti de la boucle car il n'y avait plus d'employés 
        // disponibles
        if (listEmployeDispo.isEmpty()){
            JpaUTIL.fermerEntityManager();
            return null;
        }
        else{ 
            avertirEmployeNouvelleIntervention(intervention);
            JpaUTIL.fermerEntityManager();
            return intervention;
        }
    }
    
    /**
     * Méthode permettant d'avertir l'employé qu'il a été affecté à une nouvelle
     * mission. On lui fournit les données de l'{@link Intervention} nécessaires
     * à sa réalisation.
     * 
     * @param inter {@link Intervention} représentant l'intervention à laquelle
     * l'employé a été affecté
     */
    public static void avertirEmployeNouvelleIntervention (Intervention inter) {
        Personne cli = PersonneDAO.findPersonneByIndex(inter.getClient().getId());
        
        String typeIntervention = inter.getClass().toString().split(" ")[1];
        String type = typeIntervention.replace('.',' ').split(" ")[1];
        
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd HH:mm");
        String laDate = sf.format(inter.getHorodate());
        
        String prenom = cli.getPrenom();
        String lePrenom = prenom.substring(0,1).toUpperCase() + prenom.substring(1, prenom.length()).toLowerCase();
        
        String nom = cli.getNom();
        String leNom = nom.substring(0,1).toUpperCase() + nom.substring(1, nom.length()).toLowerCase();
        
        double laDistance = inter.getDistance();
        DecimalFormat df = new DecimalFormat("0.00");
        
        System.out.println("Intervention " + type + " demandée le "
                + laDate + " pour " + lePrenom + " " 
                + leNom + " " + "(#" + cli.getId() + "), " 
                + cli.getAdresse() + " (" + df.format(laDistance) + " km)" + " : " + inter.getDescription());
    }
    
    /**
     * Méthode permettant de confirmer la fin d'une {@link Intervention}. 
     * 
     * @param interv {@link Intervention} représentant l'intervention finie
     * @param etat {@link String} représentant l'état de l'intervention
     * c'est-à-dire si elle s'est terminée sans problèmes : 'Terminée' ou 
     * non : 'Problème'.
     * @param commentaireEmp {@link String} représentant le commentaire de 
     * l'employé sur la réalisation de l'intervention
     * 
     * @return {@link Intervention} l'intervention avec les attributs modifiées
     */
    public static Intervention confirmerFinIntervention (Intervention interv, String etat, String commentaireEmp){
        JpaUTIL.creerEntityManager();
        JpaUTIL.ouvrirTransaction();
        
        Intervention intervention = InterventionDAO.merge(interv);
        Employe employe = PersonneDAO.mergeEmploye(intervention.getEmploye());
        
        Date finInter = new Date();
        
        intervention.setHeureFin(finInter);
        intervention.setCommentaireEmp(commentaireEmp);
        intervention.setEtat(etat);
        intervention.setEstFini(true);
        
        employe.setDisponibilite(true);
        
        InterventionDAO.persist(intervention);
        PersonneDAO.persistEmploye(employe);
        
        try {
            JpaUTIL.validerTransaction();
        }catch (RollbackException e){
            JpaUTIL.annulerTransaction();
            JpaUTIL.fermerEntityManager();
            throw e;
        }
        JpaUTIL.fermerEntityManager();
        
        avertirClientFinIntervention(intervention);
        return intervention;
    }
    
    /**
     * Méthode permettant d'avertir le client que l'{@link Intervention} qu'il
     * a demandée a été réalisée
     * 
     * @param intervention {@link Intervention} représentant l'intervention
     * demandée par le client dont l'{@link Employe} a confirmé la fin
     */
    private static void avertirClientFinIntervention (Intervention intervention) {
        
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
        SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm");
        
        String laDate = sf.format(intervention.getHeureFin());
        String heureDeFin = sf2.format(intervention.getHeureFin());
        
        System.out.println("L'intervention a été effectuée le " + laDate
            + " à " + heureDeFin + ".\r\n Etat de l'intervention : " + intervention.getEtat());
        if (!intervention.getCommentaireEmp().equals("")){
            System.out.println("Commentaire de l'employé : " + intervention.getCommentaireEmp() + "\r\n");
        }
    }
    
    /**
     * Méthode permettant de consulter l'ensemble des {@link Intervention} du 
     * jour réalisées par un {@link Employe}
     * 
     * @param emp {@link Employe} représentant l'employé dont on souhaite
     * consulté les {@link Intervention} du jour
     * 
     * @return {@link List} d'{@link Intervention} qui contient l'ensemble des
     * interventions du jour réalisées par l'employé. Si il n'en a réalisé 
     * aucune, retourne une liste vide.
     */
    public static List<Intervention> consulterOpeDuJour (Employe emp) {
        
        JpaUTIL.creerEntityManager();
        
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        
        Calendar tomorrow = (Calendar) today.clone();
        tomorrow.add(Calendar.DAY_OF_YEAR,1);
        
        List<Intervention> listInterv; 
        listInterv = InterventionDAO.rechercherInterventionParHorodateEmploye(emp, today, tomorrow);
        
        JpaUTIL.fermerEntityManager();
        return listInterv;
    }

    /**
     * Méthode permettant de récupérer tous les clients inscrits dans la base
     * de données. 
     * 
     * @return {@link List} de {@link Client] contenant tous les clients 
     * inscrits dans la base de données
     */
    public static List<Client> recupererInfosClients() {
        
        JpaUTIL.creerEntityManager();
        
        List<Client> lesClients = PersonneDAO.recupererTousLesClients();
        
        JpaUTIL.fermerEntityManager();
        return lesClients;
    }

    /**
     * Méthode permettant de récupérer tous les employés disponibles au moment
     * du test. Cette méthode est utilisée seulement dans le cas d'un test 
     * pour le scénario de présentation 
     * 
     * @return {@link int} le nombre d'employé disponible au moment de la
     * recherche
     */
    public static int recupererNbEmpDispo() {
        JpaUTIL.creerEntityManager();
        
        List<Employe> lesEmployesDispo = PersonneDAO.rechercherEmployeDisponible(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        
        JpaUTIL.fermerEntityManager();
        return lesEmployesDispo.size();
    }
}

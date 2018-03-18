
package service;

/**
 * Service est la classe représentant 
 * 
 * @author Christophe Etienne
 * @author William Occelli
 * @version 1.0
 */

import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import dao.InterventionDAO;
import dao.JpaUTIL;
import dao.PersonneDAO;
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
 *
 * @author cetienne
 */
public class Service {
    
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
        JpaUTIL.validerTransaction();
        JpaUTIL.fermerEntityManager();
        
        System.out.println(emp);
        System.out.println(emp2);
        System.out.println(emp3);
        System.out.println(emp4);
        System.out.println(emp5);
        System.out.println(emp6);
    }
    
    public static void inscrireClient(Client cli) {
        //On crée l'objet Personne correspondant directement dans l'IHM (le main)
        //On vérifie si l'entity manager est ouvert
        JpaUTIL.creerEntityManager();
        //On commence une transaction
        JpaUTIL.ouvrirTransaction();
        
        LatLng coord = calculerCoords(cli.getAdresse());
        cli.setCoords(coord);
        
        boolean mailValide = verifierValiditeMail(cli.getMail());
        
        //On rend l'objet persistant
        PersonneDAO.persistClient(cli);
        //On commit la transaction
        
        
        if (mailValide){
            System.out.println("Bonjour " + cli.getPrenom() + ",\r\n"
                    + "Nous vous confirmons votre inscription"
                    + " au service PROACT'IF. Votre numéro de client"
                    + " est : " + cli.getId());
            JpaUTIL.validerTransaction();
        }
        else {
            System.out.println("Bonjour " + cli.getPrenom() + ",\r\n"
                    + "Votre inscription au service PROACT'IF a "
                    + "malencontreusement échoué... Merci de recommencer"
                    + " ultérieurement.");
            JpaUTIL.annulerTransaction();
        }
        JpaUTIL.fermerEntityManager();
    }
    
    public static LatLng calculerCoords(String adresse){
        return GeoTest.getLatLng(adresse);
    }
    
    private static boolean verifierValiditeMail(String mail){
       //TODO
       // Verifier qu'elle n'existe pas deja dans la bdd 
       // 2 sol : comaparer avec tous les e-mails ou faire une requete
       // avec une condition sur le mail et si aucun resultat alors
       // l'e-mail est valide
       // Coder la fonction JPQL dans Personne DAO
        
        List<Personne> listePers = PersonneDAO.verifierDoublonEmail(mail);
        return listePers.isEmpty();
    }
    
    /*public static void afficherPersonnesPersistantes(){
        Query query = JpaUTIL.obtenirEntityManager().createQuery("SELECT p FROM Personne p");
        List<Personne> res = (List<Personne>)query.getResultList();
        for (Iterator<Personne> it = res.iterator(); it.hasNext();){
            Personne tmp = it.next();
            System.out.println(tmp);
        }
    }*/
    
    public static Personne seConnecter (String mail, String mdp) {
        //TODO
        // Idée : Comparer avec tous les pseudo de la base (e-mail ici) et voir 
        // s'il existe déja. Si oui --> verifier que le mot de passe est le même
        // Retourne true si le mail et le mot de passe correspond sinon retourne false
        
        //On vérifie si l'entity manager est ouvert
        JpaUTIL.creerEntityManager();
        
        List<Personne> pers = PersonneDAO.verifierExistencePersonne(mail, mdp);
        
        if (pers.isEmpty()){
            JpaUTIL.fermerEntityManager();
            return null;
        }
        else {
            JpaUTIL.fermerEntityManager();
            return pers.get(0);
        }
    }
    
    public static List<Intervention> chargerHistorique(Client cli) {
        //TODO
        //On vérifie si l'entity manager est ouvert
        JpaUTIL.creerEntityManager();
        
        List<Intervention> listIntervDuClient;
        listIntervDuClient = InterventionDAO.rechercherInterventionDuClient(cli);
        Collections.sort(listIntervDuClient, new Comparator<Intervention>() {
 
            @Override
            public int compare(Intervention i1, Intervention i2) {
                return i2.getHorodate().compareTo(i1.getHorodate());
            }
            
        });
        
        JpaUTIL.fermerEntityManager();
        return listIntervDuClient;
    }
    
    public static Intervention demanderIntervention (Client cli, Intervention interv) {
        // On met pas le client dans le constructeur de Intervention, on l'affecte
        // Permet d'ajouter l'intervention dans la base 
        JpaUTIL.creerEntityManager();
        JpaUTIL.ouvrirTransaction();
        
        Intervention intervention = InterventionDAO.merge(interv);
        
        // On recherche les employes dispo (voir condition dans DAO)
        Date heureIntervention = new Date();
        
        // Gérer la distance et aussi trier par employe les plus proches !
        // attention ... stepvpar par défaut que je m'en fou mais pour google maps teste la possibilité de gérer les escales
        System.out.println(heureIntervention.getHours());
        List<Employe> listEmployeDispo = new ArrayList<>();
        try {
            listEmployeDispo = PersonneDAO.rechercherEmployeDisponible(heureIntervention.getHours());
        }
        catch (Exception e){
            System.out.println("Aucun employe dispo");
        }
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
                /*Client client = PersonneDAO.mergeClient(cli);
                client.addInterventions(intervention);*/
                try {
                    InterventionDAO.persist(intervention);
                    PersonneDAO.persistEmploye(emp);
                    //PersonneDAO.persistClient(client);
                    
                    JpaUTIL.validerTransaction();
                    success = true;
                } catch (RollbackException e){
                    // rollback et verifier qu'il reste des employés dispo
                    // Rafrachir la liste des employes au cas ou d'autres se serait rendu dispo
                    success = false;
                    JpaUTIL.annulerTransaction();
                    listEmployeDispo = PersonneDAO.rechercherEmployeDisponible(heureIntervention.getHours());
                }
            }
        }
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
    
    public static void avertirEmployeNouvelleIntervention (Intervention inter) {
        //TODO
        Personne cli = PersonneDAO.findPersonneByIndex(inter.getClient().getId());
        
        String typeIntervention = inter.getClass().toString().split(" ")[1];
        String type = typeIntervention.replace('.',' ').split(" ")[1];
        
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd HH:mm");
        String laDate = sf.format(inter.getHorodate());
        
        System.out.println("Intervention " + type + " demandée le "
                + laDate + " pour " + cli.getPrenom() + " " 
                + cli.getNom() + " " + "(#" + cli.getId() + "), " 
                + cli.getAdresse() + " (" + inter.getDistance() + " km)" + " : " + inter.getDescription());
    }
    
    public static Intervention confirmerFinIntervention (Intervention interv, String etat, String commentaireEmp){
        //TODO
        
        JpaUTIL.creerEntityManager();
        JpaUTIL.ouvrirTransaction();
        // On récupére les infos
        Intervention intervention = InterventionDAO.merge(interv);
        Employe employe = PersonneDAO.mergeEmploye(intervention.getEmploye());
        
        Date finInter = new Date();
        
        intervention.setHeureFin(finInter);
        intervention.setCommentaireEmp(commentaireEmp);
        intervention.setEtat(etat);
        intervention.setEstFini(true);
        
        employe.setDisponibilite(true);
        
        // On persist et valide (pour l'instant)
        InterventionDAO.persist(intervention);
        PersonneDAO.persistEmploye(employe);
        
        JpaUTIL.validerTransaction();
        JpaUTIL.fermerEntityManager();
        
        avertirClientFinIntervention(intervention);
        return intervention;
    }
    
    private static void avertirClientFinIntervention (Intervention intervention) {
        
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
        SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm");
        String laDate = sf.format(intervention.getHeureFin());
        String heureDeFin = sf2.format(intervention.getHeureFin());
        
        System.out.println("L'intervention a été effectuée à " + laDate
            + ".\r\n Etat de l'intervention : " + intervention.getEtat());
        if (!intervention.getCommentaireEmp().equals("")){
            System.out.println("Commentaire de l'employé : " + intervention.getCommentaireEmp() + "\r\n");
        }
    }
    
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

    public static List<Client> recupererInfosClients() {
        JpaUTIL.creerEntityManager();
        List<Client> lesClients = PersonneDAO.recupererTousLesClients();
        JpaUTIL.fermerEntityManager();
        return lesClients;
    }
}

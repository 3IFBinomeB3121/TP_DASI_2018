/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.maps.model.LatLng;
import dao.InterventionDAO;
import static dao.InterventionDAO.update;
import dao.JpaUTIL;
import dao.PersonneDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import modele.Client;
import modele.Employe;
import modele.Intervention;
import modele.Personne;
import util.GeoTest;

/**
 *
 * @author cetienne
 */
public class ServicePersonne {
    
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
        int t4 = 17;
        int t5 = 20;
        
        Employe emp2 = new Employe(true,t2,t3,"mme","eti","julie",d1,"4, rue du général", "julie.eti@hotmail.fr", "pdp");
        Employe emp3 = new Employe(true,t3,t4,"mme","occ","marine",d2,"4, rue du president","ocmarine999@hotmail.fr","01239485");
        Employe emp4 = new Employe(true,t2,t4,"m","barg","pauline",d3,"4, rue du coul","pauline.barg@gmail.com","0aedi55");
        Employe emp5 = new Employe(true,t3,t5,"m","bell","gerard",d4,"4, rue du carabine", "gegedu90@gmail.com","coucouhesa");
        
        System.out.println(emp2);
        System.out.println(emp3);
        System.out.println(emp4);
        System.out.println(emp5);
        
        //On vérifie si l'entity manager est ouvert
        JpaUTIL.creerEntityManager();
        //On commence une transaction
        JpaUTIL.ouvrirTransaction();
        //On rend l'objet persistant
        PersonneDAO.persistEmploye(emp2);
        PersonneDAO.persistEmploye(emp3);
        PersonneDAO.persistEmploye(emp4);
        PersonneDAO.persistEmploye(emp5);
        //On commit la transaction
        JpaUTIL.validerTransaction();
        JpaUTIL.fermerEntityManager();
    }
    
    public static void inscrireClient(Client cli) {
        //On crée l'objet Personne correspondant directement dans l'IHM (le main)
        //On vérifie si l'entity manager est ouvert
        JpaUTIL.creerEntityManager();
        //On commence une transaction
        JpaUTIL.ouvrirTransaction();
        
        LatLng coord = calculerCoord(cli.getAdresse());
        cli.setCoords(coord);
        
        boolean mailValide = verifierValiditeMail(cli.getMail());
        
        //On rend l'objet persistant
        PersonneDAO.persistClient(cli);
        
        if (mailValide){
            System.out.println("Bonjour " + cli.getPrenom() + ",\r\n"
                    + "Nous vous confirmons votre inscription"
                    + " au service PROACT'IF. Votre numéro de client"
                    + " est : " + cli.getId());
        }
        else {
            System.out.println("Bonjour " + cli.getPrenom() + ",\r\n"
                    + "Votre inscription au service PROACT'IF a "
                    + "malencontreusement échoué... Merci de recommencer"
                    + " ultérieurement.");
        }
        
        //On commit la transaction
        JpaUTIL.validerTransaction();
        JpaUTIL.fermerEntityManager();
    }
    
    public static LatLng calculerCoord(String adresse){
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
        if (listePers.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    
    /*public static void afficherPersonnesPersistantes(){
        Query query = JpaUTIL.obtenirEntityManager().createQuery("SELECT p FROM Personne p");
        List<Personne> res = (List<Personne>)query.getResultList();
        for (Iterator<Personne> it = res.iterator(); it.hasNext();){
            Personne tmp = it.next();
            System.out.println(tmp);
        }
    }*/
    
    public static Personne SeConnecter (String mail, String mdp) {
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
            //List<String> mdpCorrespond = PersonneDAO.verifierCorrespondanceMdp(mail, mdp);
            JpaUTIL.fermerEntityManager();
            return pers.get(0);
        }
    }
    
    public static void AvertirEmploye (Employe emp, Intervention inter) {
        //TODO
        //On vérifie si l'entity manager est ouvert
        JpaUTIL.creerEntityManager();
        //On commence une transaction
        
        Personne cli = PersonneDAO.findPersonneByIndex(inter.getClient().getId());
        System.out.println("Intervention" + inter.getClass() + "demandée le "
                + inter.getHorodate() + "pour " + cli.getNom() + " " 
                + cli.getPrenom() + "" + "(#" + cli.getId() + ")," 
                + cli.getAdresse() + " : " + inter.getDescription());
        
        //TODO: ajouter la distance entre l'employé et l'intervention
        
        JpaUTIL.fermerEntityManager();
    }
    
    public static void AvertirClient (Intervention intervention) {
        System.out.println("L'intervention a été effectuée à " + intervention.getHeureFin()
            + ".\r\n Etat de l'intervention : " + intervention.getEtat() + "\r\n");
        if (!intervention.getCommentaireEmp().equals("")){
            System.out.println("Commentaire de l'employé : " + intervention.getCommentaireEmp());
        }
    }
    
    public static List<Intervention> AfficherOpeDuJour (Employe emp) {
        //TODO
        //On vérifie si l'entity manager est ouvert
        JpaUTIL.creerEntityManager();
        JpaUTIL.ouvrirTransaction();
        
        Date today = new Date();
        //int jour = today.getDayOfYear();
        ArrayList<Intervention> listInterv; // Verifier s'il ne faut pas faire un new
        listInterv = (ArrayList<Intervention>) InterventionDAO.RechercherInterventionParHorodateEmploye(emp.getId(), today);
        JpaUTIL.validerTransaction();
        JpaUTIL.fermerEntityManager();
        return listInterv;
    }
    
    public static List<Intervention> AfficherOpeFiltreJour (Employe emp, Date filtreDate) {
        //TODO
        //On vérifie si l'entity manager est ouvert
        JpaUTIL.creerEntityManager();
        JpaUTIL.ouvrirTransaction();
        
        //int jour = today.getDayOfYear();
        ArrayList<Intervention> listInterv; // Verifier s'il ne faut pas faire un new
        listInterv = (ArrayList<Intervention>) InterventionDAO.RechercherInterventionParHorodateEmploye(emp.getId(), filtreDate);
        JpaUTIL.validerTransaction();
        JpaUTIL.fermerEntityManager();
        return listInterv;
    }
    
    public static List<Intervention> consulterHistorique(Client cli) {
        //TODO
        //On vérifie si l'entity manager est ouvert
        JpaUTIL.creerEntityManager();
        
        Date today = new Date();
        List<Intervention> listInterv;
        listInterv = PersonneDAO.RechercherInterventionParIdClient(cli.getId());
        JpaUTIL.fermerEntityManager();
        return listInterv;
    }
    
    // Ne pas multiplier les méthodes, une avec plusieurs parametres si on fait les filtres
    public static List<Intervention> AfficherHistoriqueFiltreDate(Client cli){
        JpaUTIL.creerEntityManager();
        
        Date today = new Date();
        List<Intervention> listInterv;
        listInterv = InterventionDAO.RechercherInterventionParHorodateClient(cli.getId(), today);
        
        JpaUTIL.fermerEntityManager();
        return listInterv;
    }
    
    public static Intervention demanderIntervention (Client cli, Intervention interv) {
        // On met pas le client dans le constructeur de Intervention, on l'affecte
        // Permet d'ajouter l'intervention dans la base 
        JpaUTIL.creerEntityManager();
        JpaUTIL.ouvrirTransaction();
        
        Intervention intervention = InterventionDAO.update(interv);
        // On recherche les employes dispo (voir condition dans DAO)
        Date heureIntervention = interv.getHorodate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(heureIntervention);
        int heure = calendar.get(Calendar.HOUR);
        System.out.println("ok");
        System.out.println(heure); // ERREUR ICI VOIR CODE LAU
        System.out.println("ok");
        
        // Gérer la distance et aussi trier par employe les plus proches !
        // attention ... stepvpar par défaut que je m'en fou mais pour google maps teste la possibilité de gérer les escales
        List<Employe> listEmployeDispo = PersonneDAO.RechercherEmployeDisponible(cli.getCoords(), heure);
        
        boolean success = false;
        if (listEmployeDispo.isEmpty()){
            JpaUTIL.fermerEntityManager();
            return null;
        }
        else {
            while (!success && !listEmployeDispo.isEmpty()){
                Employe emp;
                emp = PersonneDAO.mergeEmploye(listEmployeDispo.get(0));
                emp.setDisponibilite(false);
                interv.setClient(cli);
                interv.setEmploye(emp);
                try {
                    PersonneDAO.persistEmploye(emp);
                    InterventionDAO.persist(interv);
                    JpaUTIL.validerTransaction();
                    success = true;
                } catch (Exception e){
                    // rollback et verifier qu'il reste des employés dispo
                    // Rafrachir la liste des employes au cas ou d'autres se serait rendu dispo
                    success = false;
                    JpaUTIL.annulerTransaction();
                    listEmployeDispo = PersonneDAO.RechercherEmployeDisponible(cli.getCoords(), heure);
                }
            }
        }
        if (listEmployeDispo.isEmpty()){
            JpaUTIL.fermerEntityManager();
            return null;
        }
        else {
            JpaUTIL.fermerEntityManager();
            return intervention;
        }
        
    }
    
    public static void finIntervention (Employe emp, String etat, String commentaireEmp, Intervention interv) throws ParseException {
        //TODO
        Intervention intervention;
        JpaUTIL.creerEntityManager();
        JpaUTIL.ouvrirTransaction();
        // On récupére les infos
        intervention = update(interv);
        
        Date today = Calendar.getInstance().getTime();
        intervention.setHeureFin(today);
        
        intervention.setCommentaireEmp(commentaireEmp);
        intervention.setEtat(etat);
        intervention.setEstFini(true);
        
        // On persist et valide (pour l'instant)
        InterventionDAO.persist(intervention);
        JpaUTIL.validerTransaction();
        JpaUTIL.fermerEntityManager();
        
        AvertirClient(intervention);
    }
    
}

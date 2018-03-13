/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.maps.model.LatLng;
import dao.InterventionDAO;
import dao.JpaUTIL;
import dao.PersonneDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
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
    
    public static void ajouterEmploye() {
        //On crée l'objet Personne correspondant
        //Employe emp = new Employe(true,heuredebut,heurefin,civilite,nom,prenom,dateNaissance,age,adresse,motdepasse);
        
        LocalTime t1 = LocalTime.now();
        LocalTime t2 = LocalTime.of(10,0,0,0);
        LocalTime t3 = LocalTime.of(15,0,0,0);
        LocalTime t4 = LocalTime.of(17,0,0,0);
        LocalTime t5 = LocalTime.of(20,0,0,0);
        
        Employe emp2 = new Employe(true,t2,t3,"mme","eti","julie",LocalDate.of(1997,12,31),"4, rue du général", "julie.eti@hotmail.fr", "pdp");
        Employe emp3 = new Employe(true,t3,t4,"mme","occ","marine",LocalDate.of(1995,05,02),"4, rue du president","ocmarine999@hotmail.fr","01239485");
        Employe emp4 = new Employe(true,t2,t4,"m","barg","pauline",LocalDate.of(1986,11,25),"4, rue du coul","pauline.barg@gmail.com","0aedi55");
        Employe emp5 = new Employe(true,t3,t5,"m","bell","gerard",LocalDate.of(1989,03,03),"4, rue du carabine", "gegedu90@gmail.com","coucouhesa");
        
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
        
        if (mailValide){
            System.out.println("Bonjour " + cli.getPrenom() + ",\r\n"
                    + "Nous vous confirmons votre inscription"
                    + "au service PROACT'IF. Votre numéro de client"
                    + " est : " + cli.getId());
        }
        else {
            System.out.println("Bonjour " + cli.getPrenom() + ",\r\n"
                    + "Votre inscription au service PROACT'IF a"
                    + "malencontreusement échoué... Merci de recommencer"
                    + " ultérieurement.");
        }
        //On rend l'objet persistant
        PersonneDAO.persistClient(cli);
        //On commit la transaction
        JpaUTIL.validerTransaction();
    }
    
    public static LatLng calculerCoord(String adresse){
        return GeoTest.getLatLng(adresse);
    }
    
    public static boolean verifierValiditeMail(String mail){
       //TODO
       // Verifier qu'elle n'existe pas deja dans la bdd 
       // 2 sol : comaparer avec tous les e-mails ou faire une requete
       // avec une condition sur le mail et si aucun resultat alors
       // l'e-mail est valide
       // Coder la fonction JSQL dans Personne DAO
        int i;
        boolean estEgal = false;
        List<String> listeMail = PersonneDAO.obtenirEmail();
        for (i=0; i<listeMail.size(); i++){
            if (listeMail.get(i).equals(mail)){
                estEgal = true;
            }
        }
        
        if (estEgal){
            return false;
        }
        
        return true;
    }
    
    /*public static void afficherPersonnesPersistantes(){
        Query query = JpaUTIL.obtenirEntityManager().createQuery("SELECT p FROM Personne p");
        List<Personne> res = (List<Personne>)query.getResultList();
        for (Iterator<Personne> it = res.iterator(); it.hasNext();){
            Personne tmp = it.next();
            System.out.println(tmp);
        }
    }*/
    
    public static void SeConnecter () {
        //TODO
        // Idée : Comparer avec tous les pseudo de la base (e-mail ici) et voir 
        // s'il existe déja. Si oui --> verifier que le mot de passe est le même
        int i;
        
    }
    
    public static void SeDeconnecter () {
        //TODO
    }
    public static void AvertirEmploye (Employe emp, Intervention inter) {
        //TODO
        Personne cli = PersonneDAO.findPersonneByIndex(inter.getIdClient());
        System.out.println("Intervention" + inter.getClass() + "demandée le "
                + inter.getHorodate() + "pour " + cli.getNom() + " " 
                + cli.getPrenom() + "" + "(#" + cli.getId() + ")," 
                + cli.getAdresse() + " : " +inter.getDescription());
        //TODO: ajouter la distance entre l'employé et l'intervention
    }
    public static void AvertirClient (Client cli) {
        //TODO
    }
    
    public static List<Intervention> AfficherOpeDuJour (Employe emp) {
        //TODO
        LocalDateTime today = LocalDateTime.now();
        ArrayList<Intervention> listInterv; // Verifier s'il ne faut pas faire un new
        listInterv = (ArrayList<Intervention>) InterventionDAO.RechercherInterventionParHorodateClient(emp.getId(), today);
        return listInterv;
    }
    
    public static List<Intervention> AfficherHistorique(Client cli) {
        //TODO
        LocalDateTime today = LocalDateTime.now();
        ArrayList<Intervention> listInterv;
        listInterv = (ArrayList<Intervention>) InterventionDAO.RechercherInterventionParHorodateClient(cli.getId(), today);
        return listInterv;
    }
    
    public static void DemanderIntervention (Client cli, Intervention interv) {
        //TODO intervention DAO.Persist
        interv.setIdClient(cli.getId());
        
    }
    
    public static void FinIntervention (Employe emp, String etat, String commentaireEmp, Intervention interv) {
        //TODO
        LocalTime t1 = LocalTime.now();
        interv.setHeureFin(t1);
        interv.setCommentaireEmp(commentaireEmp);
        interv.setEtat(etat);
        interv.setEstFini(true);
        System.out.println("L'intervention a été effectuée à " + t1 + ".\r\n Etat de"
            + " l'intervention : " + etat + "\r\n");
        if (commentaireEmp != ""){
            System.out.println("Commentaire de l'employé : " + commentaireEmp);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.maps.model.LatLng;
import dao.JpaUTIL;
import dao.PersonneDAO;
import java.util.Date;
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
        Employe emp2 = new Employe(true,10,12,"mme","eti","julie",new Date(97,03,03),"4, rue du général", "julie.eti@hotmail.fr", "pdp");
        Employe emp3 = new Employe(true,15,18,"mme","occ","marine",new Date(91,05,06),"4, rue du president","ocmarine999@hotmail.fr","01239485");
        Employe emp4 = new Employe(true,15,18,"m","barg","pauline",new Date(87,02,17),"4, rue du coul","pauline.barg@gmail.com","0aedi55");
        Employe emp5 = new Employe(true,15,18,"m","bell","gerard",new Date(94,02,23),"4, rue du carabine", "gegedu90@gmail.com","coucouhesa");
        
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
    }
    
    public static void SeDeconnecter () {
        //TODO
    }
    public static void AvertirEmploye () {
        //TODO
    }
    public static void AvertirClient () {
        //TODO
    }
    
    public static void AfficherOpeDuJour (Employe emp) {
        //TODO
    }
    
    public static void AfficherHistorique( Client cli) {
        //TODO
    }
    
    public static void DemanderIntervention (Client cli, Intervention interv) {
        //TODO intervention DAO.Persist
    }
    
    public static void FinIntervention (Employe emp) {
        //TODO
    }
    
}

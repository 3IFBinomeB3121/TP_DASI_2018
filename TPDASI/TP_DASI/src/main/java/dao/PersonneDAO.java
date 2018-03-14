/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.maps.model.LatLng;
import static dao.JpaUTIL.obtenirEntityManager;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.Client;
import modele.Employe;
import modele.Personne;

/**
 *
 * @author cetienne
 */
public class PersonneDAO {
    
    public static void persistEmploye(Employe emp) {
        obtenirEntityManager().persist(emp);
    }
    
    public static void mergeEmploye (Employe emp) {
        obtenirEntityManager().merge(emp);
    }
        
    public static void persistClient(Client cli) {
        obtenirEntityManager().persist(cli);
    }
    
    public static void mergeClient (Client cli) {
        obtenirEntityManager().merge(cli);
    }
    
    public static Personne findPersonneByIndex (Long index) {
        return obtenirEntityManager().find(Employe.class, index);
    }
    
    public static List<Employe> RechercherEmployeDisponible(LatLng coord, LocalTime heureInter) {
        List<Employe> listEmploye; 
        //TODO :
        // Rechercher un employé en fction de sa disponibilité et comparer avec les coordonnées
        // du client pour une range de 5km
        // Convertir l'heure en un int de deux chiffre !
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("select e from Employe e where"
                + " disponibilite AND heureDebutDispo<=:heureDebInter "
                        + "AND :heureDebInter <= heureFinDispo");
        query.setParameter("heureDebInter", heureInter);
        listEmploye = query.getResultList();
        return listEmploye;
    }
    
    public static List<String> obtenirEmail(){
        
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("select c.mail from Client c");
        List<String> listMail = query.getResultList();
        // Retourner l'ensemble des mails des clients/employés pour voir s'il n'est pas déja inscrit
        return listMail;
    }

    public static boolean verifierExistenceMail(String mail){

        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("select c.mail from Client c where c.mail=:mailAverif");
        query.setParameter("mailAverif", mail);
        List<String> listMail = query.getResultList();
        if (listMail.size() == 0){
            return false;
        }
        // Retourner true si le mail correspond à un mail de la base
        // Meme methode qu'obtenir mail -> Voir avec William laquelle garder 
        return true;
    }
    
    public static boolean verifierCorrespondanceMdp(String mail, String leMotDePasse){
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("select c.motdepasse from Client c where c.mail=:mailAverif");
        query.setParameter("mailAverif", mail);
        String mdpAcomparer = query.getSingleResultList();
        if (mdpAcomparer.equals(leMotDePasse)){
            return true;
        }
        // Retourner true si mot de passe correspond au pseudo sinon false
        return false;
    }
    
}

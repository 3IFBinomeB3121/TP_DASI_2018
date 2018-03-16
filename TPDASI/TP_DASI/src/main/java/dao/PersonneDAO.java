/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.maps.model.LatLng;
import static dao.JpaUTIL.obtenirEntityManager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import modele.Client;
import modele.Employe;
import modele.Intervention;
import modele.Personne;

/**
 *
 * @author cetienne
 */
public class PersonneDAO {
    
    public static void persistEmploye(Employe emp) {
        obtenirEntityManager().persist(emp);
    }
    
    public static Employe mergeEmploye (Employe emp) {
        return obtenirEntityManager().merge(emp);
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
    
    public static List<Employe> RechercherEmployeDisponible(LatLng coord, int heureInter) {
        List<Employe> listEmploye; 
        //TODO :
        // Rechercher un employé en fction de sa disponibilité et comparer avec les coordonnées
        // du client pour une distance de 5km
        // heureFinDispo et heureDebutDispo sont des LocalTime --> Voir comment on fait : int ou conversion ?
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("select e from Employe e where"
                + " e.disponibilite = TRUE AND e.heureDebutDispo<=:heureDebInter "
                        + "AND e.heureFinDispo>=:heureDebInter");
        query.setParameter("heureDebInter", heureInter);
        listEmploye = query.getResultList();
        return listEmploye;
    }
    

    public static List<Personne> verifierExistencePersonne(String mail, String mdp){

        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("select c from Client c where c.mail=:mailAverif AND c.motdepasse=:mdpAverifier");
        query.setParameter("mailAverif", mail);
        query.setParameter("mdpAverifier", mdp);
        List<Personne> listPers = query.getResultList();
        return listPers;
        
        // Retourner true si le mail correspond à un mail de la base
        // Meme methode qu'obtenir mail -> Voir avec William laquelle garder 
    }

    public static List<Personne> verifierDoublonEmail(String mail) {
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("select c from Client c where c.mail=:mailAverif");
        query.setParameter("mailAverif", mail);
        List<Personne> personneListe = query.getResultList();
        
        // Retourner true si mot de passe correspond au pseudo sinon false
        return personneListe;
    }
    
    public static List<Intervention> RechercherInterventionParIdClient(Long idClient) {
        List<Intervention> listIntervention; 
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("SELECT c FROM Client c where"
                + " c.id=:idDuClient");
        query.setParameter("idDuClient", idClient);
        listIntervention = query.getResultList();
        //TODO :
        // Rechercher les interventions en fonction de l'id du client (pour AfficherHistorique)
        return listIntervention ;
    }
}

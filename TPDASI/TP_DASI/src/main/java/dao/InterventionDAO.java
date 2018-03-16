/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//TODO : ajouter les classes Intervention, Animal, Incident et livraison
//dans le contexte de dépendance pour qu'ils puissent persister

import static dao.JpaUTIL.obtenirEntityManager;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.Animal;
import modele.Employe;
import modele.Incident;
import modele.Intervention;
import modele.Livraison;

/**
 *
 * @author William
 */
public class InterventionDAO {
    // Faire un persist général
    // et un merge général !
    public static void persist(Intervention inter){
        obtenirEntityManager().persist(inter);
    }
    
    public static Intervention update(Intervention inter){
        return obtenirEntityManager().merge(inter);
    }
    
    public static void persistIncident(Incident inci) {
        obtenirEntityManager().persist(inci);
    }
    
    public static void mergeIncident (Incident inci) {
        obtenirEntityManager().merge(inci);
    }
        
    public static void persistAnimal(Animal ani) {
        obtenirEntityManager().persist(ani);
    }
    
    public static void mergeAnimal (Animal ani) {
        obtenirEntityManager().merge(ani);
    } 
    
    public static void persistLivraison(Livraison liv) {
        obtenirEntityManager().persist(liv);
    }
    
    public static void mergeLivraison (Livraison liv) {
        obtenirEntityManager().merge(liv);
    } 
    
    public static Intervention findInterventionByIndex (Long index) {
        return obtenirEntityManager().find(Intervention.class, index);
    }
    
    
    
    public static List<Intervention> RechercherInterventionParEmploye(Employe emp) {
        List<Intervention> listIntervention; 
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("SELECT i FROM Intervention i where"
                + " i.employe =:emp");
        query.setParameter("emp", emp);
        listIntervention = query.getResultList();
        //TODO :
        // Rechercher les interventions en fonction de l'id du client (pour AfficherHistorique)
        return listIntervention ;
    }
    
    
    public static List<Intervention> RechercherInterventionParHorodateEmploye(Employe emp, Calendar today, Calendar tomorrow) {
        //TODO :
        // Rechercher les interventions en fonction de l'id de l'employe (pour AfficherOpeDuJour)
        // et de la date demandée
        List<Intervention> listIntervention; 
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("SELECT i FROM Intervention i WHERE"
                + " i.horodate >=:aujourdhui AND i.horodate<=:demain AND i.employe = :emp"); 
        query.setParameter("aujourdhui", today.getTime());
        query.setParameter("aujourdhui", tomorrow.getTime());
        query.setParameter("idDeEmp", emp.getId());
        listIntervention = query.getResultList();
        return listIntervention;
    }

}

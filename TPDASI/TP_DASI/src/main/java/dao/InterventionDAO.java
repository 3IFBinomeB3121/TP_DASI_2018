/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.JpaUTIL.obtenirEntityManager;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.Animal;
import modele.Client;
import modele.Employe;
import modele.Incident;
import modele.Intervention;
import modele.Livraison;

/**
 * Classe gérant le stockage des données. Elle permet de réaliser des opérations
 * classiques de stockage telles que la lecture, merge (rattachement d'une 
 * instance à l'entity manager courant) et persist (rendre persistant une 
 * instance). Ces opérations sont réalisées pour les instances 
 * d'{@link Intervention}, d'{@link Animal}, d'{@link Incident} et 
 * de {@link Livraison}
 * 
 * @author Christophe Etienne
 * @author William Occelli
 */
public class InterventionDAO {
    
    /**
     * Méthode permettant de rendre persistant une instance {@link Intervention} 
     * passée en paramètre
     * 
     * @param inter {@link Intervention} l'intervention de type 
     * {@link Intervention} à rendre persistante
     */
    public static void persist(Intervention inter){
        obtenirEntityManager().persist(inter);
    }
    
    /**
     * Méthode permettant d'attacher une instance {@link Intervention} passée en 
     * paramétre à l'entity manager courant
     * 
     * @param inter {@link Intervention} l'intervention à attaché
     * à l'entity manager
     * 
     * @return Une instance {@link Animal} correspondante à l'intervention 
     * de type Animal qu'on vient de rattacher à l'entity manager
     */
    public static Intervention merge(Intervention inter){
        return obtenirEntityManager().merge(inter);
    }
    
    /**
     * Méthode permettant de rendre persistant une instance {@link Incident} 
     * passée en paramètre
     * 
     * @param inci {@link Incident} l'intervention de type {@link Incident}
     * à rendre persistante
     */
    public static void persistIncident(Incident inci) {
        obtenirEntityManager().persist(inci);
    }
    
    /**
     * Méthode permettant d'attacher une instance {@link Incident} passée en 
     * paramétre à l'entity manager courant
     * 
     * @param inci {@link Incident} l'intervention de type Incident
     * à attaché à l'entity manager
     * 
     * @return Une instance {@link Incident} correspondante à l'intervention
     * de type Incident qu'on vient de rattacher à l'entity manager
     */
    public static Incident mergeIncident (Incident inci) {
        return obtenirEntityManager().merge(inci);
    }
        
    /**
     * Méthode permettant de rendre persistant une instance {@link Animal} 
     * passée en paramètre
     * 
     * @param ani {@link Animal} l'intervention de type {@link Animal}
     * à rendre persistante
     */
    public static void persistAnimal(Animal ani) {
        obtenirEntityManager().persist(ani);
    }
    
    /**
     * Méthode permettant d'attacher une instance {@link Animal} passée en 
     * paramétre à l'entity manager courant
     * 
     * @param ani {@link Animal} l'intervention de type Animal à attaché 
     * à l'entity manager
     * 
     * @return Une instance {@link Animal} correspondante à l'intervention de
     * type Animal qu'on vient de rattacher à l'entity manager
     */
    public static Animal mergeAnimal (Animal ani) {
        return obtenirEntityManager().merge(ani);
    } 
    
    /**
     * Méthode permettant de rendre persistant une instance {@link Livraison} 
     * passée en paramètre
     * 
     * @param liv {@link Livraison} l'intervention de type {@link Livraison}
     * à rendre persistante
     */
    public static void persistLivraison(Livraison liv) {
        obtenirEntityManager().persist(liv);
    }
    
    /**
     * Méthode permettant d'attacher une instance {@link Livraison} passée en 
     * paramétre à l'entity manager courant
     * 
     * @param liv {@link Livraison} l'intervention de type Animal à attaché 
     * à l'entity manager
     * 
     * @return Une instance {@link Livraison} correspondante à l'intervention de
     * type Livraison qu'on vient de rattacher à l'entity manager
     */
    public static Livraison mergeLivraison (Livraison liv) {
        return obtenirEntityManager().merge(liv);
    } 
    
    /**
     * Méthode permettant de trouver une instance {@link Intervention} grâce 
     * à son id dans la base de données.
     * 
     * @param index {@link Long} l'index de l'instance {@link Intervention} à 
     * retourné
     * 
     * @return {@link Intervention} l'intervention à qui appartient l'Id passé
     * en paramètre
     */
    public static Intervention findInterventionByIndex (Long index) {
        return obtenirEntityManager().find(Intervention.class, index);
    }
    
    /**
     * Méthode permettant de rechercher et récupérer toutes les interventions 
     * demandées par un client.
     * 
     * @param cli {@link Client} le client dont on cherche l'ensemble des 
     * interventions
     * 
     * @return {@link List} d'{@link Intervention} contenant l'ensemble des 
     * interventions demandées par le client. Si aucune demande d'intervention
     * n'a encore été effectuée par celui-ci, retourne une liste vide.
     */
    public static List<Intervention> rechercherInterventionDuClient(Client cli) {
        List<Intervention> listIntervention; 
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("SELECT i FROM Intervention i where"
                + " i.client =:cli");
        query.setParameter("cli", cli);
        listIntervention = query.getResultList();
        return listIntervention ;
    }
    
    /**
     * Méthode permettant de rechercher et récupérer toutes les interventions
     * effectuées par un employé dans la journée. 
     * 
     * @param emp {@link Employe} l'employe dont on cherche l'ensemble des 
     * interventions du jour
     * @param today {@link Date} la date d'aujourd'hui avec l'heure
     * configurée à 00h00min00sec00ms
     * @param tomorrow {@link Date} la date du lendemain avec l'heure configurée
     * à 00h00min00sec00ms
     * 
     * @return {@link List} d'{@link Intervention} contenant l'ensemble des 
     * interventions effectuées par l'employé dans la journée.
     */
    public static List<Intervention> rechercherInterventionParHorodateEmploye(Employe emp, Calendar today, Calendar tomorrow) {
        //TODO :
        // Rechercher les interventions en fonction de l'id de l'employe (pour AfficherOpeDuJour)
        // et de la date demandée
        List<Intervention> listIntervention; 
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("SELECT i FROM Intervention i WHERE"
                + " i.horodate >=:aujourdhui AND i.horodate<=:demain AND i.employe = :emp"); 
        query.setParameter("aujourdhui", today.getTime());
        query.setParameter("demain", tomorrow.getTime());
        query.setParameter("emp", emp);
        listIntervention = query.getResultList();
        return listIntervention;
    }

}

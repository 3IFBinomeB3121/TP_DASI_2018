/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//TODO : ajouter les classes Intervention, Animal, Incident et livraison
//dans le contexte de dépendance pour qu'ils puissent persister

import static dao.JpaUTIL.obtenirEntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import modele.Animal;
import modele.Incident;
import modele.Intervention;
import modele.Livraison;

/**
 *
 * @author William
 */
public class InterventionDAO {
    
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
    
    public static List<Intervention> RechercherInterventionParClient(Long idClient) {
        List<Intervention> listIntervention = new ArrayList<>() ; 
        //TODO :
        // Rechercher les interventions en fonction de l'id du client (pour AfficherHistorique)
        return listIntervention ;
    }
    
    public static List<Intervention> RechercherInterventionParEmploye(Long idEmploye) {
        List<Intervention> listIntervention = new ArrayList<>() ; 
        //TODO :
        // Rechercher les interventions en fonction de l'id de l'employe (pour AfficherOpeDuJour)
        return listIntervention ;
    }
    
    public static List<Intervention> RechercherInterventionParHorodateClient(Long idClient, LocalDateTime horodate) {
        List<Intervention> listIntervention = new ArrayList<>() ; 
        //TODO :
        // Rechercher les interventions en fonction de l'id du client (pour AfficherHistorique)
        //et de la date demandée
        return listIntervention ;
    }
    
    public static List<Intervention> RechercherInterventionParHorodateEmploye(Long idEmploye, LocalDateTime horodate) {
        List<Intervention> listIntervention = new ArrayList<>() ; 
        //TODO :
        // Rechercher les interventions en fonction de l'id de l'employe (pour AfficherOpeDuJour)
        // et de la date demandée
        return listIntervention ;
    }
    
}

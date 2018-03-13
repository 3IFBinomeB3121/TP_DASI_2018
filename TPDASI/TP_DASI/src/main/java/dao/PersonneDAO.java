/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.maps.model.LatLng;
import static dao.JpaUTIL.obtenirEntityManager;
import java.util.ArrayList;
import java.util.List;
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
    
    public static List<Employe> RechercherEmploye(LatLng coord) {
        List<Employe> listEmploye = new ArrayList<>() ; 
        //TODO :
        // Rechercher un employé en fction de sa disponibilité et comparer avec les coordonnées
        // du client pour une range de 5km
        return listEmploye ;
    }
    
    
    
}

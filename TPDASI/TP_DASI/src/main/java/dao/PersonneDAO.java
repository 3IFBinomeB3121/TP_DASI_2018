/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.JpaUTIL.obtenirEntityManager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.Client;
import modele.Employe;
import modele.Personne;

/**
 *
 * @author Christophe Etienne
 * @author William Occelli
 */
public class PersonneDAO {
    
    /**
     * Méthode permettant de rendre persistant une instance {@link Employe} 
     * passé en paramètre
     * 
     * @param emp {@link Employe} l'employé à rendre persistant
     */
    public static void persistEmploye(Employe emp){
            obtenirEntityManager().persist(emp);
    }
    
    /**
     * Méthode permettant d'attacher une instance {@link Employe} passé en 
     * paramétre à l'entity manager courant
     * 
     * @param emp {@link Employe} l'employé à attaché à l'entity manager
     * 
     * @return Une instance {@link Employe} correspondant à l'employé qu'on vient
     * de rattacher à l'entity manager
     */
    public static Employe mergeEmploye (Employe emp) {
        return obtenirEntityManager().merge(emp);
    }
        
    /**
     * Méthode permettant de rendre persistant une instance {@link Client} 
     * passé en paramètre
     * 
     * @param cli {@link Client} le client à rendre persistant
     */
    public static void persistClient(Client cli) {
        obtenirEntityManager().persist(cli);
    }
    
    /**
     * Méthode permettant d'attacher une instance {@link Client} passé en 
     * paramétre à l'entity manager courant
     * 
     * @param cli {@link Client} le client à attaché à l'entity manager
     * 
     * @return Une instance {@link Client} correspondant au client qu'on vient
     * de rattacher à l'entity manager
     */
    public static Client mergeClient (Client cli) {
        return obtenirEntityManager().merge(cli);
    }
    /**
     * Méthode permettant de trouver une instance {@link Personne} grâce à son 
     * id dans la base de données.
     * 
     * @param index {@link Long} l'index de l'instance {@link Personne} à 
     * retourné
     * 
     * @return {@link Personne} la personne à qui appartient l'Id passé
     * en paramètre
     */
    public static Personne findPersonneByIndex (Long index) {
        return obtenirEntityManager().find(Personne.class, index);
    }
    
    /**
     * Méthode permettant de rechercher toutes les instances {@link Employe}
     * disponibles. Un employé est disponible si son attribut disponible 
     * est true et si l'heure de la demande d'intervention est comprise 
     * dans l'intervalle de ses heures de travail.
     * 
     * @param heureInter {@link int} l'heure de la demande d'intervention
     * 
     * @return {@link List} d'instance {@link Employe} contenant tous les 
     * employés disponibles
     */
    public static List<Employe> rechercherEmployeDisponible(int heureInter) {
        List<Employe> listEmploye; 
        
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("select e from Employe e where"
                + " e.disponibilite = TRUE AND e.heureDebutDispo<=:heureDebInter "
                        + "AND e.heureFinDispo>=:heureDebInter");
        query.setParameter("heureDebInter", heureInter);
        listEmploye = query.getResultList();
        
        return listEmploye;
    }
    
    /**
     * Méthode permettant de vérifier l'existence d'une instance 
     * {@link Personne} dans la base de données par comparaison de son e-mail et
     * de son mot de passe. 
     * 
     * @param mail {@link String} l'e-mail de la personne 
     * @param mdp {@link String} le mot de passe de la personne
     * 
     * @return {@link List} d'instance {@link Personne} contenant la personne
     * si elle est bien inscrite dans la base de données sinon renvoie une liste
     * vide.
     * 
     */
    public static List<Personne> verifierExistencePersonne(String mail, String mdp){

        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("select c from Client c where "
                + "c.mail=:mailAverif AND c.motdepasse=:mdpAverifier");
        query.setParameter("mailAverif", mail);
        query.setParameter("mdpAverifier", mdp);
        List<Personne> listPers = query.getResultList();
        return listPers;
    }
    
    /**
     * Méthode permettant de vérifier si un e-mail existe déjà dans la base de
     * données.
     * 
     * @param mail {@link String} e-mail dont on cherche à vérifier l'unicité
     * 
     * @return {@link List} d'instance {@link Personne} qui est vide si l'e-mail
     * n'existe pas dans la base de données sinon contient l'instance de 
     * {@link Personne} qui posséde cette e-mail
     */
    public static List<Personne> verifierDoublonEmail(String mail) {
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("select c from Client c where c.mail=:mailAverif");
        query.setParameter("mailAverif", mail);
        List<Personne> personneListe = query.getResultList();
        
        return personneListe;
    }

    /**
     * Méthode permettant de retourner toutes les instances de {@link Client}
     * que contient la base de données. 
     * 
     * @return {@link List} d'instance {@link Client} qui contient tous les 
     * clients de la base de données.
     */
    public static List<Client> recupererTousLesClients() {
        List<Client> lesClients; 
        EntityManager em = JpaUTIL.obtenirEntityManager();
        Query query = em.createQuery("SELECT c FROM Client c");
        lesClients = query.getResultList();
        return lesClients ;
    }

}

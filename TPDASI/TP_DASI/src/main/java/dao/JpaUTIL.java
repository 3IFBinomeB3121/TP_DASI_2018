package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 * Cette classe fournit des mÃ©thodes statiques utiles pour accÃ©der aux
 * fonctionnalitÃ©s de JPA (Entity Manager, Entity Transaction). Le nom de
 * l'unitÃ© de persistance (PERSISTENCE_UNIT_NAME) doit Ãªtre conforme Ã  la
 * configuration indiquÃ©e dans le fichier persistence.xml du projet.
 *
 * @author DASI Team
 */
public class JpaUTIL {

    // *************************************************************************************
    // * TODO: IMPORTANT -- Adapter le nom de l'UnitÃ© de Persistance (cf. persistence.xml) *
    // *************************************************************************************
    /**
     * Nom de l'unitÃ© de persistance utilisÃ©e par la Factory de Entity Manager.
     * <br><strong>VÃ©rifier le nom de l'unitÃ© de persistance
     * (cf.&nbsp;persistence.xml)</strong>
     */
    public static final String PERSISTENCE_UNIT_NAME = "persistence_unit";
    /**
     * Factory de Entity Manager liÃ©e Ã  l'unitÃ© de persistance.
     * <br/><strong>VÃ©rifier le nom de l'unitÃ© de persistance indiquÃ©e dans
     * l'attribut statique PERSISTENCE_UNIT_NAME
     * (cf.&nbsp;persistence.xml)</strong>
     */
    private static EntityManagerFactory entityManagerFactory = null;
    /**
     * GÃ¨re les instances courantes de Entity Manager liÃ©es aux Threads.
     * L'utilisation de ThreadLocal garantie une unique instance courante par
     * Thread.
     */
    private static final ThreadLocal<EntityManager> threadLocalEntityManager = new ThreadLocal<EntityManager>() {

        @Override
        protected EntityManager initialValue() {
            return null;
        }
    };

    // MÃ©thode pour avoir des messages de Log dans le bon ordre (pause)
    private static void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            ex.hashCode();
        }
    }

    // MÃ©thode pour avoir des messages de Log dans le bon ordre (log)
    private static void log(String message) {
        System.out.flush();
        pause(5);
        System.err.println("[JpaUtil:Log] " + message);
        System.err.flush();
        pause(5);
    }

    /**
     * Initialise la Factory de Entity Manager.
     * <br><strong>Ã€ utiliser uniquement au dÃ©but de la mÃ©thode main() [projet
     * Java Application] ou dans la mÃ©thode init() de la Servlet ContrÃ´leur
     * (ActionServlet) [projet Web Application].</strong>
     */
    public static synchronized void init() {
        log("Initialisation de la factory de contexte de persistance");
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    /**
     * LibÃ¨re la Factory de Entity Manager.
     * <br><strong>Ã€ utiliser uniquement Ã  la fin de la mÃ©thode main() [projet
     * Java Application] ou dans la mÃ©thode destroy() de la Servlet ContrÃ´leur
     * (ActionServlet) [projet Web Application].</strong>
     */
    public static synchronized void destroy() {
        log("LibÃ©ration de la factory de contexte de persistance");
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }

    /**
     * CrÃ©Ã©e l'instance courante de Entity Manager (liÃ©e Ã  ce Thread).
     * <br><strong>Ã€ utiliser uniquement au niveau Service.</strong>
     */
    public static void creerEntityManager() {
        log("CrÃ©ation du contexte de persistance");
        threadLocalEntityManager.set(entityManagerFactory.createEntityManager());
    }

    /**
     * Ferme l'instance courante de Entity Manager (liÃ©e Ã  ce Thread).
     * <br><strong>Ã€ utiliser uniquement au niveau Service.</strong>
     */
    public static void fermerEntityManager() {
        log("Fermeture du contexte de persistance");
        EntityManager em = threadLocalEntityManager.get();
        em.close();
        threadLocalEntityManager.set(null);
    }

    /**
     * DÃ©marre une transaction sur l'instance courante de Entity Manager.
     * <br><strong>Ã€ utiliser uniquement au niveau Service.</strong>
     */
    public static void ouvrirTransaction() {
        log("Ouverture de la transaction (begin)");
        try {
            EntityManager em = threadLocalEntityManager.get();
            em.getTransaction().begin();
        } catch (Exception ex) {
            log("Erreur lors de l'ouverture de la transaction");
            throw ex;
        }
    }

    /**
     * Valide la transaction courante sur l'instance courante de Entity Manager.
     * <br><strong>Ã€ utiliser uniquement au niveau Service.</strong>
     *
     * @exception RollbackException lorsque le <em>commit</em> n'a pas rÃ©ussi.
     */
    public static void validerTransaction() throws RollbackException {
        log("Validation de la transaction (commit)");
        try {
            EntityManager em = threadLocalEntityManager.get();
            em.getTransaction().commit();
        } catch (Exception ex) {
            log("Erreur lors de la validation (commit) de la transaction");
            throw ex;
        }
    }

    /**
     * Annule la transaction courante sur l'instance courante de Entity Manager.
     * Si la transaction courante n'est pas dÃ©marrÃ©e, cette mÃ©thode n'effectue
     * aucune opÃ©ration.
     * <br><strong>Ã€ utiliser uniquement au niveau Service.</strong>
     */
    public static void annulerTransaction() {
        try {
            log("Annulation de la transaction (rollback)");

            EntityManager em = threadLocalEntityManager.get();
            if (em.getTransaction().isActive()) {
                log("Annulation effective de la transaction (rollback d'une transaction active)");
                em.getTransaction().rollback();
            }

        } catch (Exception ex) {
            log("Erreur lors de l'annulation (rollback) de la transaction");
            throw ex;
        }
    }

    /**
     * Retourne l'instance courante de Entity Manager.
     * <br><strong>Ã€ utiliser uniquement au niveau DAO.</strong>
     *
     * @return instance de Entity Manager
     */
    protected static EntityManager obtenirEntityManager() {
        log("Obtention du contexte de persistance");
        return threadLocalEntityManager.get();
    }
}

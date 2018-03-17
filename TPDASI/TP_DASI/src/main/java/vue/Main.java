/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import dao.JpaUTIL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Animal;
import modele.Client;
import modele.Employe;
import modele.Incident;
import modele.Intervention;
import modele.Livraison;
import service.Service;

/**
 *
 * @author cetienne
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JpaUTIL.init();
        
        // TODO code application logic here
        
        // Test de inscrireClient
        // Test de seConnecter
        // Test de chargerHistorique
        // Test de consulterOpeDuJour
        // Test de demanderIntervention
        // Test de confirmerFinIntervention
        
        // Scénario: je m'inscris, l'historique se charge, je demande une intervention
        // Un employé est affecté. Il déclare la fin de l'intervention. L'historique
        // se recharge à nouveau avec les modifications apportées. De même pour le tableau de bord
        // de l'employé
        
        // Test cas particulier : 
        //      - Aucun employe dispo 
        //          --> Non dispo car deja en intervention
        //          --> Non dispo a cause des horaires de travail
        //      - Mail deja existant dans la bd
        //      - Mail correspond pour la connection mais pas le mot de passe
        //      - Adresse rentrée non valide donc pas de coordonnées
        //      - Deux accés aux mêmes employés en même temps (Accés concurrentiels)
        //      - Verification que l'employe selectionne est bien dispo
        //      - Verification que l'employe selectionne est bien celui dont la durée est la plus courte
        //      - Verification de la demande d'intervention dans les 3 cas possibles
        //
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String date = "12-05-1982";
        Date d1 = new Date();
        try {
            d1 = sdf.parse(date);
        } catch (ParseException pe){
            System.out.println("erreur pour parser la date");
        }
        
        Client cli = new Client("Monsieur", "Jackson", "Michael", d1, "1900 route de Vins, 83143, LE VAL", "chris@hotmail.fr", "0630276677", "coucou");
        Client cli2 = new Client("Madame", "Carlita", "Josette", d1, "5 Rue Léon Fabre, Villeurbanne", "carlita.Josette@hotmail.fr", "0630276677", "carli");
        Client cli3 = new Client("Mademoiselle", "iverson", "sophie", d1, "12 Rue de la Prevoyance, Villeurbanne", "soph.ivers@hotmail.fr", "0630276677", "iverson");
        try {
            Service.ajouterEmploye();
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Service.inscrireClient(cli);
        Service.inscrireClient(cli2);
        Service.inscrireClient(cli3);
        System.out.println(cli.toString() + "\r\n");
        System.out.println(cli2.toString() + "\r\n");
        System.out.println(cli3.toString() + "\r\n");
        
        
        String dateInString = "12-01-2018 17:40:56";
        Date dateInter1 = null;
        try{
            dateInter1 = sdf.parse(dateInString);
        } catch (ParseException pe){
            System.out.println("erreur pour parser la date");
        }
        // On ajoute des interventions
        Incident inter = new Incident("Fuite d'eau dans le sous-sol");
        Animal inter2 = new Animal("Sortir le chien 15 minutes", "chien");
        Livraison inter3 = new Livraison("vase", "Amazon", "Livraison colis fragile");
        
        Intervention intervention1 = Service.demanderIntervention(cli, inter);
        Intervention intervention2 = Service.demanderIntervention(cli, inter);
        Intervention intervention3 = Service.demanderIntervention(cli, inter);
        
        System.out.println(intervention1.toString()+ "\r\n");
        System.out.println(intervention2.toString()+ "\r\n");
        System.out.println(intervention3.toString()+ "\r\n");
        
        System.out.println(Service.confirmerFinIntervention(intervention1, "Problème", "Fuite trop importante, obliger d'appeler des professionnels.").toString() + "\r\n");
        System.out.println(Service.confirmerFinIntervention(intervention2, "Terminée", "").toString() + "\r\n");
        System.out.println(Service.confirmerFinIntervention(intervention3, "Terminée", "Colis réceptionné et déposé chez la voisine d'en face").toString() + "\r\n");
        
        
        // Test de la méthode AfficheOpeDuJour
        /*LocalTime t2 = LocalTime.of(10,0,0,0);
        LocalTime t3 = LocalTime.of(15,0,0,0);
        Employe emp2 = new Employe(true,t2,t3,"mme","test","julia",LocalDate.of(1997,12,31),"4, rue du général", "julie.eti@gmail.fr", "pdp");
        List<Intervention> liste = ServicePersonne.AfficherOpeDuJour(emp2);
        if (!liste.isEmpty()){
            for (int i = 0; i<liste.size(); i++){
                System.out.println(liste.get(i).toString() + "\r\n");
            }
        }*/
        
        // Test de la méthode AfficheHistorique
        System.out.println("Test affichage historique");
        List<Client> clientInfo = Service.chargerHistorique(cli);
        System.out.println(clientInfo.toString());
        if (!clientInfo.isEmpty()){
            /*List<Intervention> historiqueClient = clientInfo.get(0).getInterventions();
            for (Intervention intervention: historiqueClient){
                System.out.println("testDebug1");
                System.out.println(intervention.toString() + "\r\n");
            }*/
        }
        else{
            System.out.println("Vous n'avez jamais fait de demande d'intervention");
        }
        
        JpaUTIL.destroy();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import dao.JpaUTIL;
import dao.PersonneDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Animal;
import modele.Client;
import modele.Employe;
import modele.Incident;
import modele.Intervention;
import modele.Livraison;
import modele.Personne;
import service.Service;
import util.Saisie;

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
        
        List<Client> clientsInfos = Service.recupererInfosClients();
        System.out.println(clientsInfos.get(0).toString() + "\r\n");
        System.out.println(clientsInfos.get(1).toString() + "\r\n");
        System.out.println(clientsInfos.get(2).toString() + "\r\n");
        
        Saisie.pause();
        
        // Test de seConnecter (client et employe)
        
        
        Personne persConnexionSuccess = Service.seConnecter(clientsInfos.get(0).getMail(), clientsInfos.get(0).getMotdepasse());
        System.out.println(persConnexionSuccess.toString());
        Personne persConnexionFail = Service.seConnecter("mail_non_existant@hotmail.fr", "motdepasse");
        if (persConnexionFail == null){
            System.out.println("Le mail ou mot de passe rentré n'est pas valide.");
        }
        
        Saisie.pause();
        
        // Test de demanderIntervention
        
        String comments = "";
        boolean typeCorrect = false;
        boolean plusieursInterventions = false;
        Integer nbInter = Saisie.lireInteger("Combien de demandes d'interventions souhaitez-vous faire (pour test)");
        Intervention interventionDemandee;
        for (int i = 0; i< nbInter; i++){
            while (!typeCorrect){
                Integer type = Saisie.lireInteger("Quel type d'intervention souhaitez-vous demander ?\r\n1 - Incident\r\n2 - Animal\r\n3 - Livraison\r\n");
                switch (type){
                    case 1:
                        comments = Saisie.lireChaine("Des commentaires sur l'intervention ?");
                        Incident inter = new Incident(comments);
                        interventionDemandee = Service.demanderIntervention(clientsInfos.get(0), inter);
                        System.out.println(interventionDemandee.toString() + "\r\n");
                        typeCorrect = true;
                        break;
                    case 2:
                        String typeAnimal = Saisie.lireChaine("Quel type d'animal concerne l'intervention?");
                        comments = Saisie.lireChaine("Commentaires sur l'intervention ?");
                        Animal inter2 = new Animal(comments, typeAnimal);
                        interventionDemandee = Service.demanderIntervention(clientsInfos.get(0), inter2);
                        System.out.println(interventionDemandee.toString() + "\r\n");
                        typeCorrect = true;
                        break;
                    case 3:
                        String objet = Saisie.lireChaine("Quel objet allez-vous vous faire livrer ?");
                        String entreprise = Saisie.lireChaine("Par quel entreprise de livraison?");
                        comments = Saisie.lireChaine("Commentaires sur l'intervention ?");
                        Livraison inter3 = new Livraison(objet, entreprise, comments);
                        interventionDemandee = Service.demanderIntervention(clientsInfos.get(0), inter3);
                        System.out.println(interventionDemandee.toString() + "\r\n");
                        typeCorrect = true;
                        break;
                    default:
                        System.out.println("Chiffre rentré non conforme, veuillez le renseignez à nouveau");
                }
            }
        }
        
        Saisie.pause();
        
        // Test de confirmerFinIntervention
        
        Incident inter = new Incident("Fuite d'eau dans le sous-sol");
        Animal inter2 = new Animal("Sortir le chien 15 minutes", "chien");
        Livraison inter3 = new Livraison("vase", "Amazon", "Livraison colis fragile");
        
        Intervention intervention1 = Service.demanderIntervention(cli, inter);
        Intervention intervention2 = Service.demanderIntervention(cli, inter2);
        Intervention intervention3 = Service.demanderIntervention(cli, inter3);
        
        Intervention intervention1fini = Service.confirmerFinIntervention(intervention1, "Problème", "Fuite trop importante, obliger d'appeler des professionnels.");
        Intervention intervention2fini = Service.confirmerFinIntervention(intervention2, "Terminée", "");
        Intervention intervention3fini = Service.confirmerFinIntervention(intervention3, "Terminée", "Colis réceptionné et déposé chez la voisine d'en face");
        
        System.out.println(intervention1fini.toString() + "\r\n");
        System.out.println(intervention2.toString() + "\r\n");
        System.out.println(intervention3fini.toString() + "\r\n");
        
        Saisie.pause();
        
        // Test de chargerHistorique
        
        List<Intervention> historiqueClient = Service.chargerHistorique(cli);
        int numeroInter = 1;
        if (!historiqueClient.isEmpty()){
            for (Intervention intervention: historiqueClient){
                System.out.println("Intervention numéro " + numeroInter + " :\r\n");
                System.out.println(intervention.toString() + "\r\n");
                numeroInter++;
            }
        }
        else{
            System.out.println("Vous n'avez jamais fait de demande d'intervention");
        }
        
        Saisie.pause();
        
        // Test de consulterOpeDuJour
        
        System.out.println("Operation du jour de l'employe :\r\n " + historiqueClient.get(0).getEmploye());
        List<Intervention> interEmpToday = Service.consulterOpeDuJour(historiqueClient.get(0).getEmploye());
        numeroInter = 1;
        for (Intervention intervention : interEmpToday){
            System.out.println("Intervention numéro " + numeroInter + " :\r\n");
            System.out.println(intervention.toString() + "\r\n");
            numeroInter++;
        }
        
        Saisie.pause();
        
        // Scénario: je m'inscris, l'historique se charge, je demande une intervention
        // Un employé est affecté. Il déclare la fin de l'intervention. L'historique
        // se recharge à nouveau avec les modifications apportées. De même pour le tableau de bord
        // de l'employe
        
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
        // Reste à faire au niveau du code: 
        //      - Javadoc
        //      - 
        
        // On ajoute des interventions
        
        
        
        
        
        
        
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
        
        JpaUTIL.destroy();
    }
    
}

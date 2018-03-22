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
import modele.Personne;
import service.Service;
import util.Saisie;

/**
 * Classe simulant le côté IHM de notre application. Elle permet d'effectuer
 * des tests et de dérouler un scénario correspondant à la navigation sur le
 * site PROACT'IF. L'ensemble des tests se terminent par un affichage
 * dans la console des informations permettant d'attester du succès du test
 * 
 * @author Christophe Etienne
 * @author William Occelli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JpaUTIL.init();
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
        //
        
        
        Integer scenario = Saisie.lireInteger("Quel scenario voulez-vous "
                + "jouer ?\r\n 1 : Client\r\n 2 : Employé\r\n 3 : "
                + "Demande d'intervention "
                + "sans employé disponible");
        
        Integer ajoutEmp = Saisie.lireInteger("Ajouter des employés fictifs "
                + "dans la base ?\r\n1 : Oui    2 : Non ");
        if (ajoutEmp == 1){
            try {
                Service.ajouterEmploye();
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date laDateDeNaissance = new Date();
        switch (scenario){
            case 1:
                Integer membre = 0;
                Client connexionClient = null;
                while (membre != 1 && membre != 2){
                    membre = Saisie.lireInteger("Etes-vous deja membre du "
                            + "site ? \r\n 1 : oui\r\n 2 : non");
                }
                if (membre == 2){
                    String civilite = Saisie.lireChaine("Entrer votre civilité");
                    String nom = Saisie.lireChaine("Entrer votre nom");
                    String prenom = Saisie.lireChaine("Entrer votre prenom");
                    String dateDeNaissance = Saisie.lireChaine("Entrer votre"
                            + " date de naissance sous la forme 'dd-MM-yyyy'");
                    try {
                        laDateDeNaissance = sdf.parse(dateDeNaissance);
                    } catch (ParseException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String mailDuClient = Saisie.lireChaine("Entrer votre mail");
                    String numero = Saisie.lireChaine("Entrer votre numéro de téléphone");
                    String adresse = Saisie.lireChaine("Entrer votre adresse (Ex: 1900 route de Vins, 83143, LE VAL)");
                    String mdpClient = Saisie.lireChaine("Votre mot de passe : ");
                    connexionClient = new Client(civilite, nom, prenom, laDateDeNaissance, adresse, mailDuClient, numero, mdpClient);
                    Client inscriptionClient = Service.inscrireClient(connexionClient);
                    System.out.println(inscriptionClient.toString());
                }
                else{
                    String emailClientInscrit = "";
                    String mdpClientInscrit = "";
                    while (connexionClient == null){
                        emailClientInscrit = Saisie.lireChaine("Entre votre e-mail");
                        mdpClientInscrit = Saisie.lireChaine("Entre votre mot de passe");
                        connexionClient = (Client) Service.seConnecter(emailClientInscrit, mdpClientInscrit);
                        if (connexionClient == null){
                            System.out.println("Le mail ou mot de passe rentré n'est pas valide."); 
                        }
                    }
                    System.out.println("Affichage des informations du Client : ");
                    System.out.println(connexionClient.toString());
                    Saisie.pause();
                    chargerHistoriqueClient(connexionClient);
                    Saisie.pause();
                }
                Integer demanderIntervention = 0;
                boolean demandeFini = false;
                while (demanderIntervention != 1 && demanderIntervention != 2){
                    demanderIntervention = Saisie.lireInteger("Demander une intervention ? \r\n1 : Oui    2 : Non");
                }
                if (demanderIntervention == 1){
                    while (!demandeFini){
                        Integer typeInterventionDemandee = 0;
                        while (typeInterventionDemandee != 1 && typeInterventionDemandee != 2 && typeInterventionDemandee != 3){
                            typeInterventionDemandee = Saisie.lireInteger("Quelle type d'intervention ?"
                                + "\r\n1 : Incident\r\n2 : Animal\r\n3 : Livraison");
                        }
                        String descriptionIntervention = Saisie.lireChaine("Commentaire sur l'intervention :");
                        String infoSup = "";
                        String entreprise = "";
                        if (typeInterventionDemandee == 2){
                            infoSup = Saisie.lireChaine("Quel type d'animal?");
                            Animal interAnimal = new Animal(descriptionIntervention, infoSup);
                            Intervention intAnimal = Service.demanderIntervention(connexionClient, interAnimal);
                            Intervention etatInterAni = null;
                            Integer confInter = Saisie.lireInteger("Confirmer la fin de l'intervention ?\r\n1 : Oui     2 : Non ");
                            if (intAnimal != null && confInter==1){
                                etatInterAni = Service.confirmerFinIntervention(intAnimal, "Terminée", "Résolu sans problème");
                                Saisie.pause();
                                System.out.println("Affichage des infos de l'intervention :\r\n" 
                                        + etatInterAni.toString());
                            }
                            Saisie.pause();
                        }
                        else if (typeInterventionDemandee == 3){
                            infoSup = Saisie.lireChaine("Quel objet allez-vous vous faire livrez ?");
                            entreprise = Saisie.lireChaine("Par quelle entreprise ?");
                            Livraison interLivraison = new Livraison(descriptionIntervention, infoSup, entreprise);
                            Intervention intLivraison = Service.demanderIntervention(connexionClient, interLivraison);
                            Saisie.pause();
                            Intervention etatInterLiv = null;
                            Integer confInter = Saisie.lireInteger("Confirmer la fin de l'intervention ?\r\n1 : Oui     2 : Non ");
                            if (intLivraison != null && confInter==1){
                                etatInterLiv = Service.confirmerFinIntervention(intLivraison, "Terminée", "Résolu sans problème");
                                Saisie.pause();
                                System.out.println("Affichage des infos de l'intervention :\r\n" 
                                        + etatInterLiv.toString());
                            }
                            Saisie.pause();
                        }
                        else {
                            Incident interIncident = new Incident(descriptionIntervention);
                            Intervention intIncident = Service.demanderIntervention(connexionClient, interIncident);
                            Saisie.pause();
                            Intervention etatInterInci = null;
                            Integer confInter = Saisie.lireInteger("Confirmer la fin de l'intervention ?\r\n1 : Oui     2 : Non ");
                            if (intIncident != null && confInter==1){
                                etatInterInci = Service.confirmerFinIntervention(intIncident, "Terminée", "Résolu sans problème");
                                Saisie.pause();
                                System.out.println("Affichage des infos de l'intervention :\r\n" 
                                        + etatInterInci.toString());
                            }
                        }

                        Saisie.pause();
                        
                        System.out.println("Affichage de l'historique :");
                        chargerHistoriqueClient(connexionClient);
                        
                        Saisie.pause();
                        
                        Integer nouvelleDemande = Saisie.lireInteger("Demander une nouvelle intervention ?\r\n0 : Non     1 : Oui");
                        if (nouvelleDemande == 0){
                            demandeFini = true;
                        }
                    }
                }
                    
                Saisie.pause();
                break;
            case 2:
                Employe emp = null;
                while (emp == null){
                    String mailEmp = Saisie.lireChaine("Entrer votre e-mail :");
                    String mdpEmp = Saisie.lireChaine("Entre votre mot de passe :");
                    emp = (Employe) Service.seConnecter(mailEmp, mdpEmp);
                }
                System.out.println("Affichage des informations de l'employé : ");
                System.out.println(emp.toString());
                Saisie.pause();
                System.out.println("Affichage des opérations du jour\r\n");
                Service.consulterOpeDuJour(emp);
                Saisie.pause();
                break;
            case 3:
                String date = "12-05-1982";
                Date d1 = new Date();
                try {
                    d1 = sdf.parse(date);
                } catch (ParseException pe){
                    System.out.println("erreur pour parser la date");
                }
                Client clientDemandeInter = new Client("Madame", "Carlita", "Josette", d1, "5 Rue Léon Fabre, Villeurbanne", "carlita.Josette@hotmail.fr", "0630276677", "carli");
                Client clientInscritTest = Service.inscrireClient(clientDemandeInter);
                System.out.println("Affichage des infos du client :");
                System.out.println(clientInscritTest.toString());
                Saisie.pause();
                int nbEmpDispo = Service.recupererNbEmpDispo();
                System.out.println("Nombre d'employé actuellement disponible : " + nbEmpDispo);
                Incident interTest = new Incident("ordinateur en panne");
                Intervention clientTestIntervention = Service.demanderIntervention(clientDemandeInter, interTest);
                Saisie.pause();
                System.out.println(clientTestIntervention.toString());
                Saisie.pause();
                break;
            default: // Par défaut, on lance nos tests unitaires
                    // permettant de tester chaque méthode séparement
                
                // Test de inscrireClient
                String dateRandom = "12-05-1982";
                Date dateFictive = new Date();
                try {
                    dateFictive = sdf.parse(dateRandom);
                } catch (ParseException pe){
                    System.out.println("erreur pour parser la date");
                }

                Client cli = new Client("Monsieur", "Jackson", "Michael", dateFictive, "1900 route de Vins, 83143, LE VAL", "chris@hotmail.fr", "0630276677", "coucou");
                Client cli2 = new Client("Madame", "Carlita", "Josette", dateFictive, "5 Rue Léon Fabre, Villeurbanne", "carlita.Josette@hotmail.fr", "0630276677", "carli");
                Client cli3 = new Client("Mademoiselle", "iverson", "Sophie", dateFictive, "12 Rue de la Prevoyance, Villeurbanne", "soph.ivers@hotmail.fr", "0630276677", "iverson");
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

                System.out.println(intervention1fini.toString());
                System.out.println(intervention2fini.toString());
                System.out.println(intervention3fini.toString());

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
                
        }
        JpaUTIL.destroy();
    }
    
    public static void chargerHistoriqueClient(Client cli){
        List<Intervention> historiqueDuClient = Service.chargerHistorique(cli);
        int numeroInterventionClient = 1;
        if (!historiqueDuClient.isEmpty()){
            for (Intervention intervention: historiqueDuClient){
                System.out.println("Intervention numéro " 
                        + numeroInterventionClient + " :\r\n");
                System.out.println(intervention.toString() + "\r\n");
                numeroInterventionClient++;
            }
        }
        else{
            System.out.println("Vous n'avez jamais fait de demande d'intervention");
        }
    }
    
}



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
        
        // On ajoute des employés et des clients à notre base
        
        // Pour les dates de naissances des clients
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
        
        System.out.println(Service.finIntervention(intervention1, "Problème", "Fuite trop importante, obliger d'appeler des professionnels.").toString() + "\r\n");
        System.out.println(Service.finIntervention(intervention2, "Terminée", "").toString() + "\r\n");
        System.out.println(Service.finIntervention(intervention3, "Terminée", "Colis réceptionné et déposé chez la voisine d'en face").toString() + "\r\n");
        
        
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
        List<Client> clientInfo = Service.consulterHistorique(cli);
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

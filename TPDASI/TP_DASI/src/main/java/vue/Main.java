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
import service.ServicePersonne;

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
        
        Client cli = new Client("Monsieur", "Jackson", "Michael", d1, "1900 route de Vins, 83143, LE VAL", "chris@hotmail.fr", "coucou");
        Client cli2 = new Client("Madame", "Carlita", "Josette", d1, "20 avenue albert einstein", "carlita.Josette@hotmail.fr", "carli");
        Client cli3 = new Client("Mademoiselle", "iverson", "sophie", d1, "4, rue de la pastorale d'issy", "soph.ivers@hotmail.fr", "iverson");
        try {
            ServicePersonne.ajouterEmploye();
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServicePersonne.inscrireClient(cli);
        ServicePersonne.inscrireClient(cli2);
        ServicePersonne.inscrireClient(cli3);
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
        Incident inter = new Incident("Fuite d'eau dans le sous-sol", dateInter1);
        Animal inter2 = new Animal("Sortir le chien 15 minutes", dateInter1, "chien");
        Livraison inter3 = new Livraison("vase", "Amazon", "Livraison colis fragile", dateInter1);
        System.out.println(inter.toString() + "\r\n");
        System.out.println(inter2.toString() + "\r\n");
        System.out.println(inter3.toString() + "\r\n");
        System.out.println(ServicePersonne.demanderIntervention(cli, inter).toString()+ "\r\n");
        System.out.println(ServicePersonne.demanderIntervention(cli,inter2).toString()+ "\r\n");
        System.out.println(ServicePersonne.demanderIntervention(cli,inter3).toString()+ "\r\n");
        
        
        
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
        List<Intervention> historiqueClient = ServicePersonne.consulterHistorique(cli);
        if (!historiqueClient.isEmpty()){
            for (Intervention intervention: historiqueClient){
                System.out.println(intervention.toString() + "\r\n");
            }
        }
        
        JpaUTIL.destroy();
    }
    
}

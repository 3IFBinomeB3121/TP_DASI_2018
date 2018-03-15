/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import dao.JpaUTIL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import modele.Client;
import modele.Employe;
import modele.Incident;
import modele.Intervention;
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
        
        LocalDate d1 = LocalDate.of(1997,03,03);
        LocalDateTime ldt1 = LocalDateTime.of(2018, 01, 12, 17, 40, 0, 0);
        
        // TODO code application logic here
        
        // On ajoute des employés et des clients à notre base
        Client cli = new Client("Monsieur", "Jackson", "Michael", d1, "1900 route de Vins, 83143, LE VAL", "chris@hotmail.fr", "coucou");
        Client cli2 = new Client("Madame", "Carlita", "Josette", d1, "20 avenue albert einstein", "carlita.Josette@hotmail.fr", "carli");
        Client cli3 = new Client("Mademoiselle", "iverson", "sophie", d1, "4, rue de la pastorale d'issy", "soph.ivers@hotmail.fr", "iverson");
        ServicePersonne.ajouterEmploye();
        ServicePersonne.inscrireClient(cli);
        ServicePersonne.inscrireClient(cli2);
        ServicePersonne.inscrireClient(cli3);
        System.out.println(cli.toString() + "\r\n");
        System.out.println(cli2.toString() + "\r\n");
        System.out.println(cli3.toString() + "\r\n");
        
        
        // On ajoute des interventions
        Incident inter = new Incident("Fuite d'eau dans le sous-sol", cli.getId(), ldt1);
        Incident inter2 = new Incident("Sortir le chien 15 minutes", cli.getId(), ldt1);
        Incident inter3 = new Incident("Fuite d'eau dans le sous-sol", cli.getId(), ldt1);
        ServicePersonne.demanderIntervention(inter);
        ServicePersonne.demanderIntervention(inter2);
        ServicePersonne.demanderIntervention(inter3);
        //ServicePersonne.modifierIntervention(cli.getCoords(), inter);
        
        
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
        List<Intervention> historiqueClient = ServicePersonne.AfficherHistorique(cli);
        if (!historiqueClient.isEmpty()){
            for (int i = 0; i<historiqueClient.size(); i++){
                System.out.println(historiqueClient.get(i).toString() + "\r\n");
            }
        }
        
        JpaUTIL.destroy();
    }
    
}

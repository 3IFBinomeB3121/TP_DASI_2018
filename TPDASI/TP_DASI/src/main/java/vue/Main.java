/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import dao.JpaUTIL;
import java.time.LocalDate;
import java.util.Date;
import modele.Client;
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
        // TODO code application logic here
        Client cli = new Client("Monsieur", "Michael", "Jackson", d1,"1900 route de Vins, 83143, LE VAL", "chris@hotmail.fr", "coucou");
        ServicePersonne.ajouterEmploye();
        ServicePersonne.inscrireClient(cli);
        JpaUTIL.destroy();
    }
    
}

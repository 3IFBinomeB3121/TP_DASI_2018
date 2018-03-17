/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author William
 */
@Entity
public class Incident extends Intervention {

    public Incident() {
    }

    public Incident(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "Incident{" + "id=" + id + ", description=" + description + ", horodate=" + horodate + ", estFini=" + estFini + ", etat=" + etat + ", heureFin=" + heureFin + ", commentaireEmp=" + commentaireEmp + ", client=" + client + ", employe=" + employe + ", distance=" + distance + '}';
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.time.LocalDateTime;
import javax.persistence.Entity;

/**
 *
 * @author William
 */
@Entity
public class Incident extends Intervention {

    public Incident() {
    }

    public Incident(String description, LocalDateTime horodate) {
        super(description, horodate);
    }

    @Override
    public String toString() {
        return "Incident{" + '}';
    }
    
}

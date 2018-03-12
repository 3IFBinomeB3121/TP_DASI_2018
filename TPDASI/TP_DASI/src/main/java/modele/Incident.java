/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.persistence.Entity;

/**
 *
 * @author William
 */
@Entity
public class Incident extends Intervention {

    public Incident() {
    }

    public Incident(String description, String horodate, boolean estFini, int heureFin, String commentaireEmp) {
        super(description, horodate, estFini, heureFin, commentaireEmp);
    }

    @Override
    public String toString() {
        return "Incident{" + '}';
    }
    
}

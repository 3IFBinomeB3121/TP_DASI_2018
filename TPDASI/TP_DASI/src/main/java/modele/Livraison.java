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
public class Livraison extends Intervention{
    
    private String objet;
    private String entreprise;

    public Livraison() {
    }

    public Livraison(String objet, String entreprise, String description, LocalDateTime horodate) {
        super(description, horodate);
        this.objet = objet;
        this.entreprise = entreprise;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    @Override
    public String toString() {
        return "Livraison{" + "objet=" + objet + ", entreprise=" + entreprise + '}';
    }
    
    
}
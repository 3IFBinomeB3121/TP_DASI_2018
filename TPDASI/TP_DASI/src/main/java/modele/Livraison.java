/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public Livraison(String objet, String entreprise, String description) {
        super(description);
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

    /**
     * Redéfinition de la méthode {@link toString()}
     * Affiche toutes les informations concernant l'intervention de type Livraison
     * 
     * @return {@link String} une chaîne de caractère contenant toutes les 
     * informations de l'{@link Intervention} de type Livraison
     */
    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd HH:mm");
        String laDate = sf.format(horodate);
        return "Livraison{" + "id=" + id + ", description=" + description + ", horodate=" + laDate + ", estFini=" + estFini + ", etat=" + etat + ", heureFin=" + heureFin + ", commentaireEmp=" + commentaireEmp + ", client=" + client + ", employe=" + employe +", distancet=" + distance + ", objet=" + objet + ", entreprise=" + entreprise + '}';
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.SimpleDateFormat;
import javax.persistence.Entity;

/**
 * Objet métier Incident. Classe persistante héritant de l'objet métier
 * {@link Intervention}.
 * Objet métier qui définit une intervention concernant un incident
 * 
 * @author Christophe Etienne
 * @author William Occelli
 */
@Entity
public class Incident extends Intervention {

    /**
     * Constructeur par défaut de l'objet métier Incident
     */
    public Incident() {
    }

    /**
     * Constructeur paramétré qui fait appel au constructeur de la classe parente
     * {@link Intervention}
     * 
     * @param description {@link String} représentant la description
     * de l'intervention
     */
    public Incident(String description) {
        super(description);
    }

    /**
     * Redéfinition de la méthode {@link toString()}
     * Affiche toutes les informations concernant l'intervention de type Incident
     * 
     * @return {@link String} une chaîne de caractère contenant toutes les 
     * informations de l'{@link Intervention} de type Incident
     */
    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd HH:mm");
        String laDate = sf.format(horodate);
        return "Incident{" + "id=" + id + ", description=" + description + ", horodate=" + laDate + ", estFini=" + estFini + ", etat=" + etat + ", heureFin=" + heureFin + ", commentaireEmp=" + commentaireEmp + ", client=" + client + ", employe=" + employe + ", distance=" + distance + '}';
    }
    
}

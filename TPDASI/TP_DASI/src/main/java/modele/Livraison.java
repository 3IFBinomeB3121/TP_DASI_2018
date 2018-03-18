/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.SimpleDateFormat;
import javax.persistence.Entity;

/**
 * Objet métier Livraison. Classe persistante héritant de l'objet métier 
 * {@link Intervention}.
 * Objet métier qui définit une intervention concernant une livraison.
 * 
 * @author Christophe Etienne
 * @author William Occelli
 */
@Entity
public class Livraison extends Intervention{
    
    /**
     * {@link String} indiquant l'objet à receptionné
     */
    private String objet;
    
    /**
     * {@link String} indiquant l'entreprise effectuant la livraison
     */
    private String entreprise;

    /**
     * Constructeur par défaut de l'objet métier Livraison
     */
    public Livraison() {
    }

    /**
     * Constructeur paramétré qui fait appel au constructeur de la classe parente
     * {@link Intervention} et initialise les attributs 'objet' et 'entreprise' 
     * 
     * @param objet {@link String} représentant l'objet à receptionné
     * @param entreprise {@link String} représentant l'entreprise effectuant
     * la livraison
     * @param description {@link String} représentant la description
     * de l'intervention
     */
    public Livraison(String objet, String entreprise, String description) {
        super(description);
        this.objet = objet;
        this.entreprise = entreprise;
    }

    /**
     * Méthode permettant d'obtenir un objet d'une {@link Intervention}
     * 
     * @return {@link String} l'objet concerné par l'{@link Intervention}
     */
    public String getObjet() {
        return objet;
    }

    /**
     * Méthode permettant d'affecter un objet à une {@link Intervention}
     * @param objet 
     */
    public void setObjet(String objet) {
        this.objet = objet;
    }

    /**
     * Méthode permettant d'obtenir une entreprise effectuant la livraison d'une
     * {@link Intervention}
     * 
     * @return {@link String} l'entreprise effectuant la livraison
     */
    public String getEntreprise() {
        return entreprise;
    }

    /**
     * Méthode permettant d'affecter une entreprise effectuant une livraison
     * pour une {@link Intervention}
     * 
     * @param entreprise {@link String} représentant l'entreprise effectuant la
     * livraison pour l'{@link Intervention}
     */
    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    /**
     * Redéfinition de la méthode {@link toString()}
     * Affiche toutes les informations concernant l'intervention de 
     * type Livraison
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Version;

/**
 * Objet métier Employe. Classe persistante héritant de l'objet métier
 * {@link Personne}.
 * Objet métier représentant un employé de l'entreprise PROACT'IF
 * 
 * @author Christophe Etienne
 * @author William Occelli
 */
@Entity
public class Employe extends Personne {
    
    /**
     * {@link boolean} indiquant si l'employé est disponible c'est-à-dire s'il
     * est déja en train de réalisé une intervention ou non
     */
    private boolean disponibilite;
    
    /**
     * {@link int} indiquant l'heure de début de travail de l'employé
     */
    private int heureDebutDispo;
    
    /**
     * {@link int} indiquant l'heure de fin de travail de l'employé
     */
    private int heureFinDispo;
    
    /**
     * {@link int} indiquant la version, c'est-à-dire le nombre de modification
     * qu'a subit l'objet métier Employe. Cette attribut est géré
     * automatiquement par le système de gestion de la base de données.
     */
    @Version
    private int version;

    /**
     * Constructeur paramétré de l'objet métier Employe qui fait appel au 
     * constructeur paramétré de la classe parente {@link Personne}
     * 
     * @param disponibilite {@link boolean} indique si l'employé est disponible.
     * true si l'employé est disponibleet false sinon
     * @param heureDebutDispo {@link int} l'heure de début de travail de 
     * l'employé
     * @param heureFinDispo {@link int} l'heure de fin de travail de 
     * l'employé
     * @param civilite {@link String} la civilité de l'employé (Mr ou Mme)
     * @param nom {@link String} le nom de famille de l'employé
     * @param prenom {@link String} le prenom de l'employé
     * @param dateNaissance {@link Date} la date de naissance de l'employé
     * @param adresse {@link String} l'adresse de l'employé
     * @param mail {@link String} l'adresse mail de l'employé
     * @param numerotel {@link String} le numéro de téléphone de l'employé
     * @param motdepasse {@link String} le mot de passe de l'employé
     */
    public Employe(boolean disponibilite, int heureDebutDispo, int heureFinDispo, String civilite, String nom, String prenom, Date dateNaissance,String adresse, String mail, String numerotel, String motdepasse) {
        super(civilite, nom, prenom, dateNaissance, adresse, mail, numerotel, motdepasse);
        this.disponibilite = disponibilite;
        this.heureDebutDispo = heureDebutDispo;
        this.heureFinDispo = heureFinDispo;
    }

    /**
     * Constructeur par défaut de l'objet métier Employe
     */
    public Employe() {
    }

    /**
     * Méthode permettant de connaître la disponibilité d'un employé
     * 
     * @return {@link boolean} true si l'employé est disponible et false sinon
     */
    public boolean isDisponibilite() {
        return disponibilite;
    }

    /**
     * Méthode permettant d'affecter la disponibilité d'un employé.
     * Un employé est disponible si il n'a pas d'intervention en cours.
     * 
     * @param disponibilite {@link boolean} représentant la disponibilité 
     * de l'employé true si l'employé est disponible et false sinon
     */
    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
    
    /**
     * Méthode permettant d'obtenir l'heure de début de travail d'un employé
     * 
     * @return {@link int} représentant l'heure de début de travail de l'employé
     */
    public int getHeureDebutDispo() {
        return heureDebutDispo;
    }

    /**
     * Méthode permettant d'affecter une heure de début de travail à un employé
     * 
     * @param heureDebutDispo {@link int} représentant l'heure de début de 
     * travail de l'employé
     */
    public void setHeureDebutDispo(int heureDebutDispo) {
        this.heureDebutDispo = heureDebutDispo;
    }

    /**
     * Méthode permettant d'obtenir l'heure de fin de travail d'un employé
     * 
     * @return {@link int} représentant l'heure de fin de travail de l'employé
     */
    public int getHeureFinDispo() {
        return heureFinDispo;
    }

    /**
     * Méthode permettant d'affecter une heure de fin de travail à un employé
     * 
     * @param heureFinDispo {@link int} représentant l'heure de fin de 
     * travail de l'employé
     */
    public void setHeureFinDispo(int heureFinDispo) {
        this.heureFinDispo = heureFinDispo;
    }

    /**
     * Redéfinition de la méthode {@link toString()}
     * Affiche toutes les informations de l'employé
     * 
     * @return {@link String} une chaîne de caractère contenant toutes les 
     * informations de l'employé
     */
    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
        String laDate = sf.format(dateNaissance);
        String lePrenom = prenom.substring(0,1).toUpperCase() 
                + prenom.substring(1, prenom.length()).toLowerCase();
        String leNom = nom.substring(0,1).toUpperCase() 
                + nom.substring(1, nom.length()).toLowerCase();
        return "Employe{ id= " + id + "  civilite=" + civilite 
                + ", nom=" + leNom + ", prenom=" + lePrenom 
                + ", dateNaissance=" + laDate + ", adresse=" 
                + adresse + ", mail=" + mail + ", coords=" + coords
                + ", motdepasse=" + motdepasse + ", disponibilite=" 
                + disponibilite + ", heureDebutDispo=" + heureDebutDispo
                + ", heureFinDispo=" + heureFinDispo + ", version=" 
                + version + '}';
    }
    
    
}

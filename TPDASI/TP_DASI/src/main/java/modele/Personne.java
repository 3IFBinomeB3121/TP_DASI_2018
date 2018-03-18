/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;

/**
 * Objet métier Personne. Classe persistante et abstraite qui a deux descendants : 
 * {@link Employe} et {@link Client}. Elle contient l'ensemble des informations
 * liées à une personne.
 * La stratégie d'héritage utilisée est d'associer dans la base de données une
 * table par classe descendante. 
 * 
 * @author Christophe Etienne
 * @author William Occelli
 */
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Personne implements Serializable {
    
    //------------------------------------------------ Parameters
    
    /**
     * {@link Long} représentant l'id du client dans la base de données. Cette
     * attribut est géré automatiquement par le système de gestion de la base 
     * de données. La génération de sa valeur se fait avec la stratégie AUTO
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    /**
     * {@link String} indiquant la civilité de la personne : 'M' ou 'Mme'
     */
    protected String civilite;
    
    /**
     * {@link String} indiquant le nom de famille de la personne
     */
    protected String nom;
    
    /**
     * {@link String} indiquant le prénom de la personne
     */
    protected String prenom;
    
    /**
     * {@link Date} indiquant la date de naissance de l'employé. On précise avec
     * l'annotation @Temporal que l'on ne conserve que l'information de la date
     * dans la base de données
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date dateNaissance;
    
    /**
     * {@link String} indiquant l'adresse de la personne
     */
    protected String adresse;
    
    /**
     * {@link String} indiquant l'adresse e-mail de la personne
     */
    protected String mail;
    
    /**
     * {@link String} indiquant le numéro de téléphone de la personne
     */
    protected String numTel;
    
    /**
     * {@link LatLng} indiquant les coordonnées GPS de l'adresse de la personne
     */
    protected LatLng coords;
    
    /**
     * {@link String} indiquant le mot de passe de la personne
     */
    protected String motdepasse;

    //------------------------------------------------ Constructor
    
    /**
     * Constructeur par défaut de l'objet métier Personne
     */
    public Personne() {
        
    }

    /**
     * Constructeur paramétré de l'objet métier Personne
     * 
     * @param civilite {@link String} la civilité de l'employé (Mr ou Mme)
     * @param nom {@link String} le nom de famille de l'employé
     * @param prenom {@link String} le prenom de l'employé
     * @param dateNaissance {@link Date} la date de naissance de l'employé
     * @param adresse {@link String} l'adresse de l'employé
     * @param mail {@link String} l'adresse mail de l'employé
     * @param numerotel {@link String} le numéro de téléphone de l'employé
     * @param motdepasse {@link String} le mot de passe de l'employé
     */
    public Personne( String civilite, String nom, String prenom, Date dateNaissance, String adresse, String mail, String numerotel, String motdepasse) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.mail = mail;
        this.numTel = numerotel;
        //this.interventions = new ArrayList<>();
        this.motdepasse = motdepasse;
    }

    //--------------------------------------------------- Getters 
    
    /**
     * Méthode permettant d'obtenir l'Id de la personne dans la base de données
     * 
     * @return {@link Long} l'id du client dans la base de données
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Méthode permettant d'obtenir le nom de famille d'une personne
     * 
     * @return {@link String} le nom de famille de la personne
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Méthode permettant d'obtenir le prénom d'une personne
     * 
     * @return {@link String} le prénom de la personne
     */
    public String getPrenom() {
        return prenom;
    }
    
    /**
     * Méthode permettant d'obtenir la date de naissance d'une personne
     * 
     * @return {@link Date} la date de naissance de la personne
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Méthode permettant d'obtenir la civilité d'une personne
     * 
     * @return {@link String} la civilité de la personne
     */
    public String getCivilite() {
        return civilite;
    }

    /**
     * Méthode permettant d'obtenir l'adresse d'une personne
     * 
     * @return {@link String} l'adresse de la personne 
     */
    public String getAdresse() {
        return adresse;
    }
    
    /**
     * Méthode permettant d'obtenir le mot de passe d'une personne
     * 
     * @return {@link String} le mot de passe de la personne
     */
    public String getMotdepasse() {
        return motdepasse;
    }
    
    /**
     * Méthode permettant d'obtenir l'adresse e-mail d'une personne
     * 
     * @return {@link String} l'adresse e-mail de la personne
     */
    public String getMail() {
        return mail;
    }

    /**
     * Méthode permettant d'obtenir le numéro de téléphone d'une personne
     * 
     * @return {@link String} le numéro de téléphone de la personne
     */
    public String getNumTel() {
        return numTel;
    }
    
    /**
     * Méthode permettant d'obtenir les coordonnées GPS correspondantes à 
     * l'adresse d'une personne
     * 
     * @return {@link LatLng} les coordonnées GPS correspondantes à l'adresse
     * de la personne
     */
    public LatLng getCoords() {
        return coords;
    }
    
    //--------------------------------------------------- Setters
    
    /**
     * Méthode permettant d'affecter un nom à une personne
     * 
     * @param nom {@link String} représentant le nom de famille de la personne
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * Méthode permettant d'affecter un prénom à une personne
     * 
     * @param prenom {@link String} représentant le prénom de la personne
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    /**
     * Méthode permettant d'affecter une date de naissance à une personne
     * 
     * @param dateNaissance {@link Date} représentant la date de naissance 
     * de la personne
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Méthode permettant d'affecter une civilité à une personne
     * 
     * @param civilite {@link String} représentant la civilité de la personne :
     * M ou Mme
     */
    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    /**
     * Méthode permettant d'affecter les coordonnées GPS correspondantes à une
     * l'adresse à une personne
     * 
     * @param coords {@link LatLng} représentant les coordonnées GPS de la 
     * personne
     */
    public void setCoords(LatLng coords) {
        this.coords = coords;
    }
    
    /**
     * Méthode permettant d'affecter une adresse à une personne
     * 
     * @param adresse {@link String} représentant l'adresse de la personne
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Méthode permettant d'affecter un mot de passe à une personne
     * 
     * @param motdepasse {@link String} représentant le mot de passe de la 
     * personne
     */
    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
    
    /**
     * Méthode permettant d'affecter une adresse e-mail à une personne
     * 
     * @param mail {@link String} représentant l'adresse e-mail de la personne
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Méthode permettant d'affecter un numéro de téléphone à une personne
     * 
     * @param numTel {@link String} représentant le numéro de téléphone de la 
     * personne
     */
    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
    
    //--------------------------------------------------- toString

    /**
     * Redéfinition de la méthode {@link toString()}
     * Affiche toutes les informations d'une personne
     * 
     * @return {@link String} une chaîne de caractère contenant toutes les 
     * informations de la personne
     */
    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
        String laDate = sf.format(dateNaissance);
        String lePrenom = prenom.substring(0,1).toUpperCase() + prenom.substring(0, prenom.length()).toLowerCase();
        String leNom = nom.substring(0,1).toUpperCase() + nom.substring(0, nom.length()).toLowerCase();
        return "Personne{" + "id=" + id + ", civilite=" + civilite 
                + ", nom=" + leNom + ", prenom=" + lePrenom 
                + ", dateNaissance=" + laDate + ", adresse=" 
                + adresse + ", mail=" + mail + ", coords=" 
                + coords + ", motdepasse=" + motdepasse + '}';
    }

}

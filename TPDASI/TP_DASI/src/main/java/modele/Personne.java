/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import util.GeoTest;

/**
 *
 * @author William
 */
@Inheritance (strategy = InheritanceType.JOINED)
@Entity
public abstract class Personne implements Serializable {
    //------------------------------------------------ Parameters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String civilite;
    protected String nom;
    protected String prenom;
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date dateNaissance;
    protected int age;
    protected String adresse;
    protected String mail;
    protected LatLng coords;
    protected String motdepasse;

    
    
    
    //------------------------------------------------ Constructor
    public Personne() {
    }

    public Personne( String civilite, String nom, String prenom, Date dateNaissance, String adresse, String mail, String motdepasse) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.mail = mail;
        this.motdepasse = motdepasse;
    }

    //--------------------------------------------------- Getters 
    
    public Long getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public LatLng getCoords() {
        return coords;
    }

    public void setCoords(LatLng coords) {
        this.coords = coords;
    }

    public String getAdresse() {
        return adresse;
    }
    
    public String getMotdepasse() {
        return motdepasse;
    }
    
    public String getMail() {
        return mail;
    }
    
    //--------------------------------------------------- Setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }

    //--------------------------------------------------- toString

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance +  ", adresse=" + adresse + ", coords=" + coords + ", motdepasse=" + motdepasse + '}';
    }

  



    



}

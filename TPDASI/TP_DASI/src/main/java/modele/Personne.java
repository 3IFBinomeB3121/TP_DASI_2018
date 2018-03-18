/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import util.GeoTest;

/**
 *
 * @author William
 */
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
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
    protected String adresse;
    protected String mail;
    protected String numTel;
    protected LatLng coords;
    protected String motdepasse;
    /*@OneToMany
    @OrderBy("horodate DESC")
    protected List<Intervention> interventions;*/

    
    
    
    //------------------------------------------------ Constructor
    public Personne() {
    }

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

    public String getNumTel() {
        return numTel;
    }
    

   /* public List<Intervention> getInterventions() {
        return interventions;
    }*/
    
    
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

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
    
   /* public void setInterventions(List<Intervention> interventions) {
       this.interventions = interventions;
    }
    
    public void addInterventions(Intervention intervention){
        this.interventions.add(intervention);
        if (this.getClass() == Client.class){
            intervention.setClient((Client) this);
        }
        else
        {
            intervention.setEmploye((Employe) this);
        }
    }*/
    
    //--------------------------------------------------- toString

    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
        String laDate = sf.format(dateNaissance);
        return "Personne{" + "id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + laDate + ", adresse=" + adresse + ", mail=" + mail + ", coords=" + coords + ", motdepasse=" + motdepasse + '}';
    }

}

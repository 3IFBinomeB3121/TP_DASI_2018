/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Version;

/**
 * Objet métier Employe. Classe héritant de l'objet métier {@link Personne}.
 * Objet métier représentant un employé de l'entreprise PROACT'IF
 * 
 * @author Christophe Etienne
 * @author William Occelli
 */
@Entity
public class Employe extends Personne {
    
    private boolean disponibilite;
    private int heureDebutDispo;
    private int heureFinDispo;
    @Version
    private int version;

    public Employe(boolean disponibilite, int heureDebutDispo, int heureFinDispo, String civilite, String nom, String prenom, Date dateNaissance,String adresse, String mail, String numerotel, String motdepasse) {
        super(civilite, nom, prenom, dateNaissance, adresse, mail, numerotel, motdepasse);
        this.disponibilite = disponibilite;
        this.heureDebutDispo = heureDebutDispo;
        this.heureFinDispo = heureFinDispo;
    }

    public Employe() {
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public int getHeureDebutDispo() {
        return heureDebutDispo;
    }

    public void setHeureDebutDispo(int heureDebutDispo) {
        this.heureDebutDispo = heureDebutDispo;
    }

    public int getHeureFinDispo() {
        return heureFinDispo;
    }

    public void setHeureFinDispo(int heureFinDispo) {
        this.heureFinDispo = heureFinDispo;
    }

    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
        String laDate = sf.format(dateNaissance);
        return "Employe{ id= " + id + "  civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + laDate + ", adresse=" + adresse + ", mail=" + mail + ", coords=" + coords + ", motdepasse=" + motdepasse + ", disponibilite=" + disponibilite + ", heureDebutDispo=" + heureDebutDispo + ", heureFinDispo=" + heureFinDispo + ", version=" + version + '}';
    }
    
    
}

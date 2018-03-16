/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Version;

/**
 *
 * @author cetienne
 */
@Entity
public class Employe extends Personne {
    
    private boolean disponibilite;
    private int heureDebutDispo;
    private int heureFinDispo;
    @Version
    private int version;

    public Employe(boolean disponibilite, int heureDebutDispo, int heureFinDispo, String civilite, String nom, String prenom, Date dateNaissance,String adresse, String mail, String motdepasse) {
        super(civilite, nom, prenom, dateNaissance, adresse, mail, motdepasse);
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
        return "Employe{ id= " + id + "  civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", mail=" + mail + ", coords=" + coords + ", motdepasse=" + motdepasse + ", interventions=" + interventions + "disponibilite=" + disponibilite + ", heureDebutDispo=" + heureDebutDispo + ", heureFinDispo=" + heureFinDispo + ", version=" + version + '}';
    }
    
    
}

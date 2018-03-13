/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;

/**
 *
 * @author cetienne
 */
@Entity
public class Employe extends Personne {
    
    private boolean disponibilite;
    private LocalTime heureDebutDispo;
    private LocalTime heureFinDispo;

    public Employe(boolean disponibilite, LocalTime heureDebutDispo, LocalTime heureFinDispo, String civilite, String nom, String prenom, LocalDate dateNaissance,String adresse, String mail, String motdepasse) {
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

    public LocalTime getHeureDebutDispo() {
        return heureDebutDispo;
    }

    public void setHeureDebutDispo(LocalTime heureDebutDispo) {
        this.heureDebutDispo = heureDebutDispo;
    }

    public LocalTime getHeureFinDispo() {
        return heureFinDispo;
    }

    public void setHeureFinDispo(LocalTime heureFinDispo) {
        this.heureFinDispo = heureFinDispo;
    }
    
    
    @Override
    public String toString() {
        return "Employe{" + "disponibilite=" + disponibilite + ", heureDebutDispo=" + heureDebutDispo + ", heureFinDispo=" + heureFinDispo + '}';
    }
    
}

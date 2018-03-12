/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author cetienne
 */
@Entity
public class Employe extends Personne {
    
    private boolean disponibilite;
    private int heureDebutDispo;
    private int heureFinDispo;

    public Employe(boolean disponibilite, int heureDebutDispo, int heureFinDispo,String civilite, String nom, String prenom, Date dateNaissance,String adresse, String mail, String motdepasse) {
        super(civilite, nom, prenom, dateNaissance, adresse, mail, motdepasse);
        this.disponibilite = disponibilite;
        this.heureDebutDispo = heureDebutDispo;
        this.heureFinDispo = heureFinDispo;
    }

    public Employe() {
    }
    
    @Override
    public String toString() {
        return "Employe{" + "disponibilite=" + disponibilite + ", heureDebutDispo=" + heureDebutDispo + ", heureFinDispo=" + heureFinDispo + '}';
    }
    
}

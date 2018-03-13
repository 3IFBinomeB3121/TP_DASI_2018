/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author cetienne
 */
@Entity
public class Client extends Personne {

    public Client( String civilite, String nom, String prenom, LocalDate dateNaissance, String adresse, String mail, String motdepasse) {
        super(civilite, nom, prenom, dateNaissance, adresse, mail, motdepasse);
    }

    public Client() {
    }

    @Override
    public String toString() {
        return "Client{" + '}';
    }
    
    
}

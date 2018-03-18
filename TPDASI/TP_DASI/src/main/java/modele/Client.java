/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author cetienne
 */
@Entity
public class Client extends Personne {

    public Client(String civilite, String nom, String prenom, Date dateNaissance, String adresse, String mail, String numerotel, String motdepasse) {
        super(civilite, nom, prenom, dateNaissance, adresse, mail, numerotel, motdepasse);
    }

    public Client() {
    }

    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
        String laDate = sf.format(dateNaissance);
        return "Client{id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + laDate + ", adresse=" + adresse + ", mail=" + mail + ", coords=" + coords + ", motdepasse=" + motdepasse + "}";
    }

    
}

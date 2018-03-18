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
 * Objet métier Client. Classe héritant de l'objet métier {@link Personne}.
 * Objet métier représentant un client du site PROACT'IF
 * 
 * @author Christophe Etienne
 * @author William Occelli
 */
@Entity
public class Client extends Personne {

    /**
     * Constructeur paramétré faisant appel au constructeur de la clase mère
     * {@link Personne}
     * 
     * @param civilite {@link String} la civilité du client (m ou mr)
     * @param nom
     * @param prenom
     * @param dateNaissance
     * @param adresse
     * @param mail
     * @param numerotel
     * @param motdepasse 
     */
    public Client(String civilite, String nom, String prenom, Date dateNaissance, String adresse, String mail, String numerotel, String motdepasse) {
        super(civilite, nom, prenom, dateNaissance, adresse, mail, numerotel, motdepasse);
    }

    public Client() {
    }

    /**
     * Redéfinition de la méthode {@link toString()}
     * Affiche toutes les informations du client
     * 
     * @return {@link String} une chaîne de caractère contenant toutes les 
     * informations du Client
     */
    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
        String laDate = sf.format(dateNaissance);
        return "Client{id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + laDate + ", adresse=" + adresse + ", mail=" + mail + ", coords=" + coords + ", motdepasse=" + motdepasse + "}";
    }

    
}

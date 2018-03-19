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
 * Objet métier Client. Classe persistante héritant de l'objet métier
 * {@link Personne}.
 * Objet métier représentant un client du site PROACT'IF
 * 
 * @author Christophe Etienne
 * @author William Occelli
 */
@Entity
public class Client extends Personne {

    /**
     * Constructeur paramétré faisant appel au constructeur de la classe parente
     * {@link Personne}
     * 
     * @param civilite {@link String} la civilité du client (M ou Mme)
     * @param nom {@link String} le nom du client
     * @param prenom {@link String} le prenom du client
     * @param dateNaissance {@link Date} la date de naissance du client
     * @param adresse {@link String} l'adresse du client
     * @param mail {@link String} le mot de passe du client
     * @param numerotel {@link String} le numéro de téléphone du client
     * @param motdepasse {@link String} le mot de passe du client
     */
    public Client(String civilite, String nom, String prenom, Date dateNaissance, String adresse, String mail, String numerotel, String motdepasse) {
        super(civilite, nom, prenom, dateNaissance, adresse, mail, numerotel, motdepasse);
    }

    /**
     * Constructeur par défaut de l'objet métier Client
     */
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
        String lePrenom = prenom.substring(0,1).toUpperCase() 
                + prenom.substring(1, prenom.length()).toLowerCase();
        String leNom = nom.substring(0,1).toUpperCase() 
                + nom.substring(1, nom.length()).toLowerCase();
        return "Client{id=" + id + ", civilite=" + civilite + ", nom=" + leNom + ", prenom=" + lePrenom + ", dateNaissance=" + laDate + ", adresse=" + adresse + ", mail=" + mail + ", coords=" + coords + ", motdepasse=" + motdepasse + "}";
    }

    
}

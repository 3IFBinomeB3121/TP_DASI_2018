/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.SimpleDateFormat;
import javax.persistence.Entity;

/**
 * Objet métier Animal. Classe héritant de l'objet métier {@link Intervention}.
 * Objet métier qui définit une intervention concernant un animal
 * 
 * @author Christophe Etienne
 * @author William Occelli
 */
@Entity
public class Animal extends Intervention{
    
    /**
     * {@link String} Indique le type d'animal concerné par l'intervention
     */
    private String typeAnimal;
    
    /**
     * Constructeur par défaut
     */
    public Animal() {
    }

    /**
     * Constructeur paramétré qui fait appel au constructeur de la classe mère
     * {@link Intervention} et initialise l'attribut typeAnimal avec la chaîne 
     * de caractère passée en paramètre
     * 
     * @param description {@link String} la description de l'intervention
     * @param unTypeAnimal {@link String} le type d'animal concerné par 
     * l'{@link Intervention}
     */
    public Animal(String description, String unTypeAnimal) {
        super(description);
        this.typeAnimal = unTypeAnimal;
    }

    /**
     * 
     * @return {@link String} le type d'animal concernée par 
     * l'{@link Intervention}
     */
    public String getTypeAnimal() {
        return typeAnimal;
    }

    /**
     * Affecte à l'attribut typeAnimal le type d'animal concernée 
     * par l'{@link Intervention}
     * 
     * @param typeAnimal {@ String} le type de l'animal
     */
    public void setTypeAnimal(String typeAnimal) {
        this.typeAnimal = typeAnimal;
    }

    /**
     * Redéfinition de la méthode {@link toString()}
     * Affiche toutes les informations concernant l'intervention de type Animal
     * 
     * @return {@link String} une chaîne de caractère contenant toutes les 
     * informations de l'{@link Intervention} de type animal
     */
    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd HH:mm");
        String laDate = sf.format(horodate);
        return "Animal{" + "id=" + id + ", description=" + description + ", horodate=" + laDate + ", estFini=" + estFini + ", etat=" + etat + ", heureFin=" + heureFin + ", commentaireEmp=" + commentaireEmp + ", client=" + client + ", employe=" + employe + "}" + "typeAnimal=" + typeAnimal + ", distance=" + distance + "}";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.SimpleDateFormat;
import javax.persistence.Entity;

/**
 *
 * @author William
 */
@Entity
public class Animal extends Intervention{
    
    private String typeAnimal;
    
    public Animal() {
    }

    public Animal(String description, String unTypeAnimal) {
        super(description);
        this.typeAnimal = unTypeAnimal;
    }

    public String getTypeAnimal() {
        return typeAnimal;
    }

    public void setTypeAnimal(String typeAnimal) {
        this.typeAnimal = typeAnimal;
    }

    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd HH:mm");
        String laDate = sf.format(horodate);
        return "Animal{" + "id=" + id + ", description=" + description + ", horodate=" + laDate + ", estFini=" + estFini + ", etat=" + etat + ", heureFin=" + heureFin + ", commentaireEmp=" + commentaireEmp + ", client=" + client + ", employe=" + employe + "}" + "typeAnimal=" + typeAnimal + ", distance=" + distance + "}";
    }
}

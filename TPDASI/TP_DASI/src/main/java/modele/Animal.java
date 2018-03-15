/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.time.LocalDateTime;
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

    public Animal(String description, LocalDateTime horodate, Long idclient, String unTypeAnimal) {
        super(description, idclient, horodate);
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
        return "Animal{" + "typeAnimal=" + typeAnimal + '}';
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author William
 */
@Entity
public abstract class Intervention implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    private String description;
    private LocalDateTime horodate;
    private boolean estFini;
    private int heureFin;
    private String commentaireEmp;
    private Long idClient;
    private Long idEmploye;

    public Intervention() {
        this.estFini = false;
    }
    
    public Intervention(String description, LocalDateTime horodate) {
        this.description = description;
        this.horodate = horodate;
        this.estFini = false;
    }
    
    public Long getIdEmploye() {
        return idEmploye;
    }

    public Long getIdClient() {
        return idClient;
    }

    public String getDescription() {
        return description;
    }
   
    public LocalDateTime getHorodate() {
        return horodate;
    }

    public boolean isEstFini() {
        return estFini;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public String getCommentaireEmp() {
        return commentaireEmp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHorodate(LocalDateTime horodate) {
        this.horodate = horodate;
    }

    public void setEstFini(boolean estFini) {
        this.estFini = estFini;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    public void setCommentaireEmp(String commentaireEmp) {
        this.commentaireEmp = commentaireEmp;
    }
    
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public void setIdEmploye(Long idEmploye) {
        this.idEmploye = idEmploye;
    }
    

    @Override
    public String toString() {
        return "Intervention{" + "description=" + description + ", horodate=" + horodate + ", estFini=" + estFini + ", heureFin=" + heureFin + ", commentaireEmp=" + commentaireEmp + '}';
    }
    
    
}

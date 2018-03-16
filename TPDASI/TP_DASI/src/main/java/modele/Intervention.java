/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author William
 */
@Entity
public abstract class Intervention implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date horodate;
    protected boolean estFini;
    protected String etat;
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date heureFin;
    protected String commentaireEmp;
    @ManyToOne
    protected Client client;
    @ManyToOne
    protected Employe employe;

    public Intervention() {
        this.estFini = false;
    }
    
    public Intervention(String description, Date horodate) {
        this.description = description;
        this.horodate = horodate;
        this.estFini = false;
    }
    
    public Employe getEmploye() {
        return employe;
    }

    public Client getClient() {
        return client;
    }

    public String getDescription() {
        return description;
    }
   
    public Date getHorodate() {
        return horodate;
    }

    public boolean isEstFini() {
        return estFini;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public String getCommentaireEmp() {
        return commentaireEmp;
    }

    public String getEtat() {
        return etat;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHorodate(Date horodate) {
        this.horodate = horodate;
    }

    public void setEstFini(boolean estFini) {
        this.estFini = estFini;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public void setCommentaireEmp(String commentaireEmp) {
        this.commentaireEmp = commentaireEmp;
    }
    
    public void setClient(Client unClient) {
        this.client = unClient;
    }

    public void setEmploye(Employe unEmploye) {
        this.employe = unEmploye;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Intervention{" + "id=" + id + ", description=" + description + ", horodate=" + horodate + ", estFini=" + estFini + ", etat=" + etat + ", heureFin=" + heureFin + ", commentaireEmp=" + commentaireEmp + ", client=" + client + ", employe=" + employe + '}';
    }

}

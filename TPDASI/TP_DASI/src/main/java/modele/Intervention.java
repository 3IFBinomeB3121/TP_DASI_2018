/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 * Objet métier Intervention. Classe persistante et abstraite qui a trois 
 * descendants : {@link Incident}, {@link Animal} et {@link Livraison}. Elle 
 * contient l'ensemble des informations liées à une intervention.
 * La stratégie d'héritage utilisée est d'associer dans la base de données une
 * table par classe descendante. 
 * 
 * @author Christophe Etienne
 * @author William Occelli
 */
@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Intervention implements Serializable {
    
    /**
     * {@link Long} représentant l'id de l'intervention dans la base de données.
     * Cette attribut est géré automatiquement par le système de gestion
     * de la base de données. La génération de sa valeur se fait avec
     * la stratégie AUTO
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    /**
     * {@link String} indiquant la description de l'intervention
     */
    protected String description;
    
    /**
     * {@link Date} indiquant la date et l'horaire de demande de l'intervention.
     * On précise avec l'annotation @Temporal que l'on souhaite conservé les 
     * informations de la date et de l'heure dans la base de données (TIMESTAMP)
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date horodate;
    
    /**
     * {@link boolean} représentant la situation de l'intervention c'est-à-dire
     * si elle est en cours ou bien fini
     */
    protected boolean estFini;
    
    /**
     * {@link String} représentant l'état de l'intervention : 'Terminée' ou 
     * 'Problème' c'est-à-dire si celle-ci s'est déroulée sans problèmes ou non.
     */
    protected String etat;
    
    /**
     * {@link Date} indiquant la date et l'horaire de confirmation de fin de
     * l'intervention.
     * On précise avec l'annotation @Temporal que l'on souhaite conservé les 
     * informations de la date et de l'heure dans la base de données (TIMESTAMP)
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date heureFin;
    
    /**
     * {@link String} représentant le commentaire de l'employé lorsque la 
     * mission a été réalisée.
     */
    protected String commentaireEmp;
    
    /**
     * {@link Client} représentant le client qui demande l'intervention. 
     * Association au {@link Client} avec un lien uni-directionnel @ManyToOne
     */
    @ManyToOne(targetEntity=Client.class)
    protected Client client;
    
    /**
     * {@link Employe} représentant l'employé réalisant l'intervention. 
     * Association à l'{@link Employe} avec un lien uni-directionnel @ManyToOne
     */
    @ManyToOne(targetEntity=Employe.class)
    //@PrimaryKeyJoinColumn
    //@JoinColumn(name = "employe", insertable=false, updatable=false)
    protected Employe employe;
    
    /**
     * {@link Double} représentant la distance en km entre l'adresse du 
     * {@link Client} demandant l'intervention et l'adresse de 
     * l'{@link Employe} réalisant l'intervention
     */
    Double distance;

    /**
     * Constructeur par défaut de l'objet métier Intervention
     */
    public Intervention() {
        this.estFini = false;
    }
    
    /**
     * Constructeur paramétré de l'objet métier Intervention
     * 
     * @param description {@link String} représentant la description du client
     * concernant l'intervention demandée
     */
    public Intervention(String description) {
        this.description = description;
        this.estFini = false;
    }

    /**
     * Méthode permettant d'obtenir l'Id dans la base de données d'une 
     * intervention
     * 
     * @return {@link Long} l'Id de l'intervention dans la base de données
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Méthode permettant d'obtenir l'{@link Employe} réalisant une intervention
     * 
     * @return {@link Employe} l'employé réalisant l'intervention
     */
    public Employe getEmploye() {
        return employe;
    }

    /**
     * Méthode permettant d'obtenir le {@link Client} demandant une intervention
     * 
     * @return {@link Client} le client demandant l'intervention
     */
    public Client getClient() {
        return client;
    }

    /**
     * Méthode permettant d'obtenir la description d'une intervention
     * 
     * @return {@link String} la description de l'intervention
     */
    public String getDescription() {
        return description;
    }
   
    /**
     * Méthode permettant d'obtenir la date et l'horaire à laquelle la demande
     * d'une intervention a été faite
     * 
     * @return {@link Date} la date et l'horaire de la demande de l'intervention
     */
    public Date getHorodate() {
        return horodate;
    }

    /**
     * Méthode permettant de savoir si une intervention est en cours ou terminée
     * 
     * @return {@link boolean} true si l'intervention est terminée et false
     * sinon
     */
    public boolean isEstFini() {
        return estFini;
    }

    /**
     * Méthode permettant d'obtenir la date et l'horaire auxquelles la
     * confirmation d'un employé de la réalisation d'une intervention a été
     * faite
     * 
     * @return {@link Date} la date et l'heure auxquelles la confirmation d'un employé
     * de la réalisation d'une intervention a été faite
     */
    public Date getHeureFin() {
        return heureFin;
    }

    /**
     * Méthode permettant d'obtenir le commentaire d'un {@link Employe} pour une
     * intervention
     * 
     * @return {@link String} le commentaire de l'employé sur l'intervention
     */
    public String getCommentaireEmp() {
        return commentaireEmp;
    }

    /**
     * Méthode permettant d'obtenir l'état d'une intervention c'est-à-dire si
     * celle-ci s'est déroulée sans problèmes ou non
     * 
     * @return {@link String} l'état de l'intervention : 'Terminée' si elle s'est
     * déroulée sans problème, 'Problème' sinon
     */
    public String getEtat() {
        return etat;
    }

    /**
     * Méthode permettant d'obtenir la distance entre l'adresse du {@link Client]
     * et l'adresse de l'{@link Employe} pour une intervention
     * 
     * @return {@link Double} la distance entre l'adresse du {@link Client} et
     * l'adresse de l'{@link Employe} pour l'intervention
     */
    public Double getDistance() {
        return distance;
    }
    
    /**
     * Méthode permettant d'affecter une description à une intervention
     * 
     * @param description {@link String} représentant la description de
     * l'intervention
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Méthode permettant d'affecter une date et un horaire concernant la
     * demande d'un {@link Client} pour une intervention
     * 
     * @param horodate {@link Date} représentant la date et l'horaire de la demande 
     * d'intervention faite par le {@link Client}
     */
    public void setHorodate(Date horodate) {
        this.horodate = horodate;
    }

    /**
     * Méthode permettant d'affecter l'état d'une intervention c'est-à-dire si
     * elle est en cours ou terminée
     * 
     * @param estFini {@link boolean} représentant l'état de l'intervention :
     * true si celle-ci est fini, false sinon
     */
    public void setEstFini(boolean estFini) {
        this.estFini = estFini;
    }

    /**
     * Méthode permettant d'affecter une date et un horaire de fin à une 
     * intervention
     * 
     * @param heureFin {@link Date} représentant la date et l'horaire à laquelle
     * la fin de l'intervention a été confirmée par l'employé
     */
    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    /**
     * Méthode permettant d'affecter le commentaire d'un {@link Employe} concernant
     * la réalisation d'une intervention
     * 
     * @param commentaireEmp {@link String} représentant le commentaire de 
     * l'{@link Employe} concernant l'intervention
     */
    public void setCommentaireEmp(String commentaireEmp) {
        this.commentaireEmp = commentaireEmp;
    }
    
    /**
     * Méthode permettant d'affecter un {@link Client} à une intervention
     * 
     * @param unClient {@link Client} représentant le client effectuant la 
     * demande d'intervention
     */
    public void setClient(Client unClient) {
        this.client = unClient;
    }

    /**
     * Méthode permettant d'affecter un {@link Employe} à une intervention
     * 
     * @param unEmploye {@link Employe} représentant l'employé réalisant 
     * l'intervention
     */
    public void setEmploye(Employe unEmploye) {
        this.employe = unEmploye;
    }

    /**
     * Méthode permettant d'affecter un état à une intervention
     * 
     * @param etat {@link String} représentant l'état de l'intervention : 
     * 'Terminée' si celle-ci s'est déroulée sans problème, 'Problème' sinon
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }

    /**
     * Méthode permettant d'affecter la distance en km entre l'adresse du 
     * {@link Client} et celle de l'{@link Employe} pour une intervention
     * 
     * @param distanceDeLinter {@link Double} représenant la distance en km
     * entre l'adresse du {@link Client} et celle de l'{@link Employe}
     */
    public void setDistance(Double distanceDeLinter) {
        this.distance = distanceDeLinter;
    }

    /**
     * Redéfinition de la méthode {@link toString()}
     * Affiche toutes les informations concernant une intervention 
     * 
     * @return {@link String} une chaîne de caractère contenant toutes les 
     * informations de l'intervention
     */
    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd HH:mm");
        String laDate = sf.format(horodate);
        return "Intervention{" + "id=" + id + ", description=" + description + ", horodate=" + laDate + ", estFini=" + estFini + ", etat=" + etat + ", heureFin=" + heureFin + ", commentaireEmp=" + commentaireEmp + ", client=" + client + ", employe=" + employe + ", distance=" + distance + '}';
    }
    
}

package modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modele.Client;
import modele.Employe;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-22T17:23:44")
@StaticMetamodel(Intervention.class)
public abstract class Intervention_ { 

    public static volatile SingularAttribute<Intervention, Boolean> estFini;
    public static volatile SingularAttribute<Intervention, Date> horodate;
    public static volatile SingularAttribute<Intervention, Double> distance;
    public static volatile SingularAttribute<Intervention, Employe> employe;
    public static volatile SingularAttribute<Intervention, String> description;
    public static volatile SingularAttribute<Intervention, Client> client;
    public static volatile SingularAttribute<Intervention, Long> id;
    public static volatile SingularAttribute<Intervention, Date> heureFin;
    public static volatile SingularAttribute<Intervention, String> etat;
    public static volatile SingularAttribute<Intervention, String> commentaireEmp;

}
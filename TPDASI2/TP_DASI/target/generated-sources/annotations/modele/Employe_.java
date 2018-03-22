package modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-22T17:23:44")
@StaticMetamodel(Employe.class)
public class Employe_ extends Personne_ {

    public static volatile SingularAttribute<Employe, Integer> heureDebutDispo;
    public static volatile SingularAttribute<Employe, Boolean> disponibilite;
    public static volatile SingularAttribute<Employe, Integer> heureFinDispo;
    public static volatile SingularAttribute<Employe, Integer> version;

}
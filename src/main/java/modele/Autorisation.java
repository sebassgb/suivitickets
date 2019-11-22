package modele;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Autorisation {

    @Id
    private String aut_id;//ID pour identifier l'autorisation
    private String aut_desc;//cette variable permettre décrire les permissions avec cet autorisation

}

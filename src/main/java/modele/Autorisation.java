package modele;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Autorisation {

    @Id
    private String aut_id;//ID pour identifier l'autorisation
    private String aut_desc;//cette variable permettre décrire les permissions avec cet autorisation

    public String getAut_id() {
        return aut_id;
    }

    public void setAut_id(String aut_id) {
        this.aut_id = aut_id;
    }

    public String getAut_detail() {
        return aut_desc;
    }

    public void setAut_detail(String aut_detail) {
        this.aut_desc = aut_detail;
    }
}

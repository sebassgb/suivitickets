package modele;

import javax.persistence.Entity;
import javax.persistence.Id;

public class Bidon {
    @Id
    private int id;
    private String texte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}

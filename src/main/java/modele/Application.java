package modele;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Application {

    @Id
    private String app_id;//ID pour identifier l'application
    private String app_nom;//cette variable pour savoir le nom
    private String app_proj_id;//cette variable pour savoir le nom

    public String getAppProj_id() {
        return app_proj_id;
    }

    public void setAppProj_id(String app_proj_id) {
        this.app_proj_id = app_proj_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_nom() {
        return app_nom;
    }

    public void setApp_nom(String app_nom) {
        this.app_nom = app_nom;
    }
}

package dto;

import java.util.Collection;

public class MembreDetailDTO {
    private String nom;
    private Collection<String> resp;
    private Collection<String> part;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Collection<String> getResp() {
        return resp;
    }

    public void setResp(Collection<String> resp) {
        this.resp = resp;
    }

    public Collection<String> getPart() {
        return part;
    }

    public void setPart(Collection<String> part) {
        this.part = part;
    }
}

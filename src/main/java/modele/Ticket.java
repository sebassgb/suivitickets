package modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Random;

public class Ticket {

    private Integer ticket_id;
    private String ticket_appnom;
    private Boolean ticket_aut = false; // false = permettre de prendre en charge
    private String ticket_date;
    private String ticket_title;
    private String ticket_trace;
    private String ticket_desc;
    private Integer ticket_status; // 0 = Non resolu, 1 = resolu
    private Utilisateur ticket_responsable;
    private String ticket_client_creator;
    private String ticket_commentaire;
    private String ticket_date_resolution;
    Random rand = new Random();

    public Ticket(String ticket_client_creator, String ticket_appnom, String ticket_title, String ticket_date,
            String ticket_desc) {
        this.ticket_id = rand.nextInt(100);
        this.ticket_client_creator = ticket_client_creator;
        this.ticket_appnom = ticket_appnom;
        this.ticket_date = ticket_date;
        this.ticket_desc = ticket_desc;
        this.ticket_status = 0;
        this.ticket_title = ticket_title;
    }

    public String getTicket_client_creator() {
        return ticket_client_creator;
    }

    public Integer getTicket_id() {
        return ticket_id;
    }

    public Boolean getTicket_aut() {
        return ticket_aut;
    }

    public void setTicket_aut(Boolean ticket_aut) {
        this.ticket_aut = ticket_aut;
    }

    public String getTicket_date() {
        return ticket_date;
    }

    public String getTicket_title() {
        return ticket_title;
    }

    public String getTicket_desc() {
        return ticket_desc;
    }

    public void setTicket_date(String ticket_date) {
        this.ticket_date = ticket_date;
    }

    public void setTicket_desc(String ticket_desc) {
        this.ticket_desc = ticket_desc;
    }

    public Integer getTicket_status() {
        return ticket_status;
    }

    public void setTicket_status(Integer ticket_status) {
        this.ticket_status = ticket_status;
    }

    public Utilisateur getTicket_responsable() {
        return ticket_responsable;
    }

    public void setTicket_responsable(Utilisateur ticket_responsable) {
        this.ticket_responsable = ticket_responsable;
        this.ticket_aut = true; // to block the another
    }

    public String getTicket_trace() {
        return ticket_trace;
    }

    public String getTicket_appnom() {
        return ticket_appnom;
    }

    public void setTicket_trace(String ticket_trace) {
        this.ticket_trace = ticket_trace;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getTicket_commentaire() {
        return ticket_commentaire;
    }

    public void setTicket_commentaire(String ticket_commentaire) {
        this.ticket_commentaire = ticket_commentaire;
    }

    public String getTicket_date_resolution() {
        return ticket_date_resolution;
    }

    public void setTicket_date_resolution(String ticket_date_resolution) {
        this.ticket_date_resolution = ticket_date_resolution;
    }
}

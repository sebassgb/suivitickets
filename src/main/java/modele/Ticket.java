package modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Ticket {

    @Id
    private Integer ticket_id;
    private String ticket_appnom;
    private Boolean ticket_aut = false;
    private String ticket_date;
//    private String ticket_trace;
    private String ticket_desc;
    private Integer ticket_status;
    private Utilisateur ticket_responsable;
    public Ticket(Integer ticket_id, String ticket_appnom, String ticket_date, String ticket_desc, Integer ticket_status) {
        this.ticket_id = ticket_id;
        this.ticket_appnom = ticket_appnom;
        this.ticket_date = ticket_date;
        this.ticket_desc = ticket_desc;
        this.ticket_status = ticket_status;
    }

    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getTicket_appnom() {
        return ticket_appnom;
    }

    public void setTicket_appnom(String ticket_appnom) {
        this.ticket_appnom = ticket_appnom;
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

    public void setTicket_date(String ticket_date) {
        this.ticket_date = ticket_date;
    }

    public String getTicket_desc() {
        return ticket_desc;
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
        if(this.ticket_aut != true) {
            this.ticket_responsable = ticket_responsable;
            this.ticket_aut = true;
        }
        else {
            System.out.printf("APRES");
        }
    }
}

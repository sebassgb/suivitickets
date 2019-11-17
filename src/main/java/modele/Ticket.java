package modele;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticket {

    @Id
    private String ticket_id;
    private String ticket_appnom;
    private String ticket_aut;
    private String ticket_date;
    private String ticket_trace;
    private String ticket_desc;
    private String ticket_status;

    public String getTicketId() {
        return ticket_id;
    }

    public void setTicketId(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getTicketAppnom() {
        return ticket_appnom;
    }

    public void setTicketAppnom(String ticket_appnom) {
        this.ticket_appnom = ticket_appnom;
    }

    public String getTickeAut() {
        return ticket_aut;
    }

    public void setTicketAut(String ticket_aut) {
        this.ticket_aut = ticket_aut;
    }

    public String getTicketDate() {
        return ticket_date;
    }

    public void setTicket_date(String ticket_date) {
        this.ticket_date = ticket_date;
    }

    public String getTicket_trace() {
        return ticket_trace;
    }

    public void setTicket_trace(String ticket_trace) {
        this.ticket_trace = ticket_trace;
    }

    public String getTicket_desc() {
        return ticket_desc;
    }

    public void setTicket_desc(String ticket_desc) {
        this.ticket_desc = ticket_desc;
    }

    public String getTicket_status() {
        return ticket_status;
    }

    public void setTicket_status(String ticket_status) {
        this.ticket_status = ticket_status;
    }
}

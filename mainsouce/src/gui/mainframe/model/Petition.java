package gui.mainframe.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Petition {
    private String number;
    private String title;
    private String organization;
    private String date;
    private Date date2;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Petition(String number, String title, String organization, String date) {
        this.number = number;
        this.title = title;
        this.organization = organization;
        this.date = date;
    }

    public Petition(String number, String title, String organization, Date date2) {
        this.number = number;
        this.title = title;
        this.organization = organization;
        this.date = sdf.format(date2);
    }

    public String getNumber() { return number; }
    public String getTitle() { return title; }
    public String getOrganization() { return organization; }
    public String getDate() { return date; }
}

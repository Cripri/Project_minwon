package gui.mainframe.model;

public class Petition {
    private String number;
    private String title;
    private String organization;
    private String date;

    public Petition(String number, String title, String organization, String date) {
        this.number = number;
        this.title = title;
        this.organization = organization;
        this.date = date;
    }

    public String getNumber() { return number; }
    public String getTitle() { return title; }
    public String getOrganization() { return organization; }
    public String getDate() { return date; }
}

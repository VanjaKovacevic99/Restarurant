package models;



import java.util.Date;

public class Osoba {
    private String JMB;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String osJBM;

    public Osoba(){}
    public Osoba(String JBM, String ime, String prezime, Date datumRodjenja){
        this.JMB=JBM;
        this.ime=ime;
        this.prezime=prezime;
        this.datumRodjenja=datumRodjenja;
    }
}

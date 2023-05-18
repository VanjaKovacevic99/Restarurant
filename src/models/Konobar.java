package models;

import java.util.Date;

public class Konobar extends Osoba {

    private String JBM;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private double plata;
    private String korisnickoIme;
    private String lozinka;
    private Osoba osoba;


    public Konobar(Osoba osoba, double plata, String korisnickoIme, String lozinka){
        this.osoba=osoba;
        this.plata=plata;
        this.korisnickoIme=korisnickoIme;
        this.lozinka=lozinka;
    }

    public Konobar(String JBM, String ime, String prezime, Date datumRodjenja, double plata, String korisnickoIme, String lozinka){
        this.JBM=JBM;
        this.ime=ime;
        this.prezime=prezime;
        this.datumRodjenja=datumRodjenja;
        this.plata=plata;
        this.korisnickoIme=korisnickoIme;
        this.lozinka=lozinka;
    }



}

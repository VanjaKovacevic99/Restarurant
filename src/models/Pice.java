package models;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Pice {
    private int id;
    private String naziv;
    private String proizvodjac;
    private double cijena;
    private String tip;
    private double kolicina;

    public Pice(int id, String naziv, String proizvodjac, double cijena, String tip, double kolicina) {
        this.id = id;
        this.naziv = naziv;
        this.proizvodjac = proizvodjac;
        this.cijena = cijena;
        this.tip = tip;
        this.kolicina = kolicina;
    }

    public String getNaziv(){
        return naziv;
    }

    public double getCijena(){
        return cijena;
    }

    public int getId(){ return id; }

    public String getProizvodjac(){ return proizvodjac;}

    public String getTip(){ return tip; }

    public double getKolicina(){ return  kolicina; }

    public static ArrayList<Pice> getPice() {
        ArrayList<Pice> arrayList= new ArrayList<Pice>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            String query = "select * from pice";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                arrayList.add(new Pice(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4),
                        resultSet.getString(5), resultSet.getDouble(6)));
            }

        } catch (SQLException e) {
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,
                            "Problem sa citanjem iz baze!", "Opomena",
                            JOptionPane.ERROR_MESSAGE);
                }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            ConnectionPool.getInstance().checkIn(connection);
        }
        return arrayList;
    }
}

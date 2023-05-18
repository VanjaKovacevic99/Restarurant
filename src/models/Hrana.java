package models;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hrana {

        private int id;
        private String naziv;
        private String opis;
        private double cijena;

        public Hrana(int id, String naziv, String opis, double cijena) {
            this.id = id;
            this.naziv = naziv;
            this.opis = opis;
            this.cijena = cijena;
        }
        public Hrana(){}
        public String getNaziv(){
            return naziv;
        }

        public int getId(){
            return id;
        }
        public double getCijena(){
            return cijena;
        }
        public String getOpis(){
            return opis;
        }

        public static ArrayList<Hrana> getHrana() {
            ArrayList<Hrana> arrayList = new ArrayList<>();
            Statement statement = null;
            ResultSet resultSet = null;
            Connection connection = null;


            try {
                connection = ConnectionPool.getInstance().checkOut();
                statement = connection.createStatement();
                String query = "select * from hrana";
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    arrayList.add(new Hrana(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4)));
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




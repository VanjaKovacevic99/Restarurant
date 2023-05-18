package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NarudzbaPice {
    private int idNarudzbe;
    private int idPica;
    private double cijena;
    private int kolicina;

    public NarudzbaPice(int idNarudzbe, int idPica, double cijena, int kolicina){
        this.idNarudzbe=idNarudzbe;
        this.idPica=idPica;
        this.cijena=cijena;
        this.kolicina=kolicina;
    }
    public NarudzbaPice(){}

    public void addNarudzbaPice(){
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();

            callableStatement = connection.prepareCall("{call add_narudzba_pice(?, ?, ?, ?)}");
            callableStatement.setInt(1, idNarudzbe);
            callableStatement.setInt(2,idPica);
            callableStatement.setDouble(3,cijena);
            callableStatement.setInt(4,kolicina);

            resultSet = callableStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (callableStatement != null)
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            ConnectionPool.getInstance().checkIn(connection);
        }
    }

    public static int daLiPicePostojiNaNarudzbi(int idNarudzbe, int idPica){
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String query="select IdNarudzbe,Kolicina from narudzba_detaljno where IdNarudzbe= ";
                query+=idNarudzbe +" and Narudba_Pice_IdPica=";
                query+=idPica +";";
        int idPronadjeneNarudzbe= -1;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(query);
            resultSet = callableStatement.executeQuery();
            while(resultSet.next()) {
                idPronadjeneNarudzbe=resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (callableStatement != null)
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            ConnectionPool.getInstance().checkIn(connection);
        }
        return idPronadjeneNarudzbe;


    }
    public static void updateNarudzbaPice( int idNarudzbe, int idPica, int kolicina){
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();

            callableStatement = connection.prepareCall("{call update_narudzba_pice(?, ?, ?)}");
            callableStatement.setInt(1, idNarudzbe);
            callableStatement.setInt(2,idPica);
            callableStatement.setInt(3,kolicina);

            resultSet = callableStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (callableStatement != null)
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            ConnectionPool.getInstance().checkIn(connection);
        }
    }


}

package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NarudzbaHrana {

    private int idNarudzbe;
    private int idHrane;
    private double cijena;
    private int kolicina;

    public NarudzbaHrana(int idNarudzbe, int idHrane, double cijena, int kolicina){
        this.idNarudzbe=idNarudzbe;
        this.idHrane=idHrane;
        this.cijena=cijena;
        this.kolicina=kolicina;
    }

    public void addNarudzbaHrana(){
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();

            callableStatement = connection.prepareCall("{call add_narudzba_hrana(?, ?, ?, ?)}");
            callableStatement.setInt(1, idNarudzbe);
            callableStatement.setInt(2,idHrane);
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

    public static int daLiPicePostojiNaNarudzbi(int idNarudzbe, int idHrane){
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String query="select IdNarudzbe,Kolicina from narudzba_detaljno_hrana where IdNarudzbe= ";
        query+=idNarudzbe +" and Hrana_IdHrane=";
        query+=idHrane +";";
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

    public static void updateNarudzbaHrana( int idNarudzbe, int idHrane, int kolicina){
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();

            callableStatement = connection.prepareCall("{call update_narudzba_hrana(?, ?, ?)}");
            callableStatement.setInt(1, idNarudzbe);
            callableStatement.setInt(2, idHrane);
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

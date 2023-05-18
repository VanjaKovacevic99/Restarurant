package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Sto {
    private int id;
    private int brojStola;
    private int brojMijesta;
    private String JMB_Konobara;

    public Sto (int id, int brojStola,int brojMijesta, String JMB_Konobara){
        this.id=id;
        this.brojStola=brojStola;
        this.brojMijesta=brojMijesta;
        this.JMB_Konobara=JMB_Konobara;
    }

    public int getBrojStola(){ return brojStola; }
    public int getId(){ return id;}


    public static ArrayList<Sto> stoloviKonobara(String JMB){
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String query="select * from sto where Konobar_Osoba_JMB=";
        query+=JMB;
        ArrayList<Sto> stoloviKonobara =new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(query);
            resultSet = callableStatement.executeQuery();
            while(resultSet.next()) {
                stoloviKonobara.add(new Sto(resultSet.getInt(4), resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));
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
        return stoloviKonobara;


}

    public static int getBrStolaNaOsnovuId(int id){
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String query="select BrojStola from sto where IdStola=";
        query+=id;
        int result=-1;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(query);
            resultSet = callableStatement.executeQuery();
            while(resultSet.next()) {
                result = resultSet.getInt(1);
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
        return result;


    }

}

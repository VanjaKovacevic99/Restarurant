package models;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


public class Narudzba {

    private int id;
    private String JBM_konobara;
    private int idStola;
    private Boolean odneseno;

    public Narudzba(){}

    public int getId() {
        return id;
    }

    public int getIdStola() {
        return idStola;
    }

    public Narudzba(int id, String JBM_konobara, int idStola, Boolean odneseno){
        this.id=id;
        this.JBM_konobara = JBM_konobara;
        this.idStola = idStola;
        this.odneseno=odneseno;
    }

    public Narudzba( String JBM_konobara, int idStola){
        this.JBM_konobara = JBM_konobara;
        this.idStola = idStola;
    }

    public int addNarudzba(){
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        int result = -1;
        try {
            connection = ConnectionPool.getInstance().checkOut();

            callableStatement = connection.prepareCall("{call add_narudzba(?, ?)}");
            callableStatement.setString(1, JBM_konobara);
            callableStatement.setInt(2, idStola);
            resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
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
    public static ArrayList<Narudzba> getAllNarudzbe(){
        ArrayList<Narudzba> arrayList= new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            String query = "select * from narudzba ";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                arrayList.add(new Narudzba(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getBoolean(4)));

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


    public static ArrayList<Narudzba> neodneseneNarudzbeKonobara(String JBM_konobara){
        ArrayList<Narudzba> arrayList= new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            String query = "select * from narudzba where Odneseno = 0 and Konobar_Osoba_JMB=";
            query+=JBM_konobara;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                arrayList.add(new Narudzba(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getBoolean(4)));

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

    public ArrayList<String> listaPicaSaNarudzbe(){
        ArrayList<String> arrayList= new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            String query = "select * from narudzba_detaljno_pice where IdNarudzbe =";
            query+=id;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                arrayList.add(resultSet.getString(5) + "     " +String.valueOf(resultSet.getDouble(3)) + " KM     x " + String.valueOf(resultSet.getInt(4)));


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
    public static ArrayList<String> listaPicaNarudzba(int id){
        ArrayList<String> arrayList= new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            String query = "select * from narudzba_detaljno_pice where IdNarudzbe =";
            query+=id;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                arrayList.add(resultSet.getString(5) + String.valueOf(resultSet.getDouble(3)) + String.valueOf(resultSet.getInt(4)));

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

    public static int pronadjiNarudzbuZaSto(int id){
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int result=-1;

        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            String query = "select * from narudzba where Sto_IdStola =";
            query+=id;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                result=resultSet.getInt(1);

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
        return result;

    }



    public void oznaciOdnesenuNarudzbu(){
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().checkOut();

            callableStatement = connection.prepareCall("{call odnesena_narudzba_promijeni(?)}");
            callableStatement.setInt(1, id);
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
    public ArrayList<String> listaHraneSaNarudzbe(){
        ArrayList<String> arrayList= new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            String query = "select * from narudzba_detaljno_hrana where IdNarudzbe =";
            query+=id;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                arrayList.add(resultSet.getString(6) + "     " +String.valueOf(resultSet.getDouble(4)) + " KM     x " + String.valueOf(resultSet.getInt(5))  );

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

    public static ArrayList<String> listaHraneNarudzbe(int id){
        ArrayList<String> arrayList= new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            String query = "select * from narudzba_detaljno_hrana where IdNarudzbe =";
            query+=id;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                arrayList.add(resultSet.getString(6) + "    x " + String.valueOf(resultSet.getInt(5)));

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

    public static int daLiJeZaNarudzbuIzdanRacun(int id){
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int result = -1;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            String query = "select IdFiskalnogRacuna from FiskalniRacun where Narudzba_IdNarudzbe=";
            query+=id;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                result=resultSet.getInt(1);
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
        return result;


    }


}






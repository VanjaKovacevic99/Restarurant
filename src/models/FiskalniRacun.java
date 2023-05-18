package models;

import java.sql.*;

public class FiskalniRacun {

    private int id;
    private Timestamp datumIzdavanja;
    private Timestamp vrijemeIzdavanja;
    private int idNarudzbe;

    public FiskalniRacun(int id, Timestamp datumIzdavanja, Timestamp vrijemeIzdavanja, int idNarudzbe){
        this.id = id;
        this.datumIzdavanja = datumIzdavanja;
        this.vrijemeIzdavanja = vrijemeIzdavanja;
        this.idNarudzbe = idNarudzbe;
    }

    public int addFiskalniRacun(){

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        int result = -1;
        try {
            connection = ConnectionPool.getInstance().checkOut();

            callableStatement = connection.prepareCall("{call add_racun(?, ?, ?)}");
            callableStatement.setTimestamp(1, datumIzdavanja );
            callableStatement.setTimestamp(2, vrijemeIzdavanja);
            callableStatement.setInt(3, idNarudzbe);
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

}

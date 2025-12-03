package org.jasper;

import org.jasper.pojo.HolidayPojo;

import java.sql.*;


public class Repository {
    public static void insertHoliday(HolidayPojo pojo){
        try(
                Connection connection = ConnectionProvider.connect();
                PreparedStatement statement = connection.prepareStatement(
                        "insert into holidays(name, country, date) values (?,?,?)"
                );
                ){
                statement.setString(1, pojo.getNAME());
                statement.setString(2, pojo.getCOUNTRY());
                statement.setString(3, String.valueOf(pojo.getDATE()));
                statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet allHolidays(){
        try
        {
            Connection connection = ConnectionProvider.connect();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select name, country, CAST(date AS DATE) AS date from holidays");
            return set;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

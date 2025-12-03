package org.jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetGenerator {
    public static void generate() throws  SQLException {
        ResultSet holidaysSet = Repository.allHolidays();
        Connection connection = holidaysSet.getStatement().getConnection();
        Statement statement = holidaysSet.getStatement();
        JRResultSetDataSource dataSource = new JRResultSetDataSource(holidaysSet);
        try{
            JasperReport report = JasperCompileManager.compileReport(FileProvider.readJRXMLForResultSet());
            JasperPrint printer = JasperFillManager.fillReport(report, null, dataSource);
            JasperViewer.viewReport(printer);
        }catch (JRException e){
            throw new RuntimeException(e.getMessage());
        }finally {
            if(!holidaysSet.isClosed()){
                try{
                    holidaysSet.close();
                }catch (SQLException ignored){}
            }
            if(!statement.isClosed()){
                try{
                    statement.close();
                }catch (SQLException ignored){}
            }
            if(!connection.isClosed()){
                try{
                   connection.close();
                }catch (SQLException ignored){}
            }

        }
    }

    public static void main(String[] a) throws SQLException {
        generate();
    }
}

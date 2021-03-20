package main.database;

import com.utilities.ConfigurationReader;

import java.sql.*;
import java.util.*;

public class ResultSetMetaData {
    public static void main(String[] args) throws SQLException {int count = 1;
        String url = ConfigurationReader.getProperty("dataBaseURL");
        String username = "qa_user" ;
        String password = "Cybertek11!" ;
        Connection con = DriverManager.getConnection(url , username , password );
        Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stm.executeQuery("SELECT * FROM users");
        //MetaData is data about the data most often the column name / column count
        java.sql.ResultSetMetaData rsmd = rs.getMetaData() ;
        System.out.println("rsmd.getColumnCount() = "+ rsmd.getColumnCount());


        for(int i = 1; i <= rsmd.getColumnCount(); i++ ){
            System.out.println("rsmd.getColumnName("+i+") = " + rsmd.getColumnName(i));
        }
        //SAVE ALL COLUMN NAME INTO A LIST

        List<String> columnList = new ArrayList<>();
        for(int i = 1; i <= rsmd.getColumnCount(); i++ ){
            columnList.add(rsmd.getColumnName(i));
        }
        System.out.println(Collections.singletonList(columnList));
    }
}

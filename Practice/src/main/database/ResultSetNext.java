package main.database;

import com.utilities.ConfigurationReader;

import java.sql.*;

public class ResultSetNext {


    public ResultSetNext() throws SQLException {

    }

    public static void main(String[] args) {
       try{
           Connection con = DriverManager.getConnection(ConfigurationReader.getProperty("dataBaseURL"),"qa_user","Cybertek11!");
           Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
           ResultSet rs = stm.executeQuery("SELECT * FROM ROOM");

           while (rs.next()){
               System.out.println(rs.getString(1)+" : "+ rs.getString(2)+" : " +rs.getString(3)+" : "
               + rs.getString(4)+" : "+rs.getString(5));
           }

       }catch (SQLException e){
           e.printStackTrace();
       }
    }
}

package main.database;

import com.utilities.ConfigurationReader;

import java.sql.*;

public class FirstConnection {

    public static void main(String[] args) {
        /**
         *  Host : 11.111.111.111
         *  Port : 1111
         *  SID  : XE
         *  User :
         *  Pass :
         *   jdbc:postgresql://room-reservation-qa.cxvqfpt4mc2y.us-east-1.rds.amazonaws.com:5432/room_reservation_qa
         *   JDBC URL AKA CONNECTION STRING
         *   SYNTAX:
         *   jdbc : vendorName : driverType @YourHost : PORT : SID
         *
         *   jdbc:oracle:thin:@52.206.182.184:1521:XE
         */

        String postgreSQL = ConfigurationReader.getProperty("dataBaseURL");
        String oracleSQL = "jdbc:oracle:thin:@52.206.182.184:1521:XE";

            try {
                var con = DriverManager.getConnection(oracleSQL, "hr", "hr");
                Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                var rslt = statement.executeQuery("SELECT * FROM REGIONS");

                while(rslt.next()) {
                    System.out.println("Fourth Row REGION NAME IS " + rslt.getString("REGION_NAME"));
                }
                rslt = statement.executeQuery("SELECT * FROM JOBS");
                while(rslt.next()){
                    System.out.println( rslt.getString("JOB_TITLE"));
                }
                rslt.close();
                System.out.println("CONNECTION PASS to Oracle");
            } catch (SQLException e) {
                System.out.println("CONNECTION FAIL to Oracle "+ e.getMessage() );
            }
/*
ResultSet Methods:
this will only work if resultSet is: type scroll insensitive
.next()
.previous()
.first()
.last()
.beforeFirst()
.afterLast()
.absolute(3)

 */

            try {
                Connection con2 = DriverManager.getConnection(postgreSQL, "qa_user", "Cybertek11!");
                // This way of creating statement object
                // will allow us to move forward and backward easily when navigating through the result we got
                Statement statement = con2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultSet = statement.executeQuery("SELECT * FROM roomswithtv");
                while(resultSet.next()){
                    System.out.println("ROOMS with tv: "+ resultSet.getString("id")+" : "+ resultSet.getString("name") );
                }
                resultSet = statement.executeQuery("SELECT * FROM team");
                while(resultSet.next()){
                    System.out.print(resultSet.getString(1));
                    System.out.println(" : "+resultSet.getString(2));
                }
                System.out.println("CONNECTION PASS to PostGre");
            } catch (SQLException e) {
                System.out.println("CONNECTION FAIL to PostGre "+ e.getMessage() );
            }


    }
}

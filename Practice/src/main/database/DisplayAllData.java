package main.database;

import com.utilities.ConfigurationReader;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayAllData {
    public static void main(String[] args) throws SQLException {
        var url = ConfigurationReader.getProperty("dataBaseURL");
        var username = "qa_user";
        var password = "Cybertek11!";


        var con = DriverManager.getConnection(url, username, password);
        var stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        var rs = stm.executeQuery("SELECT * FROM users");
        var rsmd = rs.getMetaData();

        //How to move to last row ---> rs.last()
        //How to get current  row number --->> rs.getRow()

        rs.last();
        var rowNum = rs.getRow();
        System.out.println("rowNum = " + rowNum);
        var columnCount = rsmd.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        rs.beforeFirst();
        var count = 0;

        while (rs.next()) {

            for (int i = 1; i <= columnCount; i++)

                System.out.print(toCell(rsmd.getColumnLabel(i), i == 1 ? 6: i == 2 ? 5: i == 3 ? 25 : i == 4? 8: i == 5 ? 60:
                         i == 6 ? 15 : i == 7 ? 22 : i == 8 ? 32 : i == 9 ? 2 : i == 10 ? 5 : 10));
            System.out.println();
            for (int col = 1; col <= columnCount; col++) {
                System.out.printf("%-2s",rs.getString(col)+ " |");
            //    System.out.print( toCell(rs.getString(col), col == 1 ? 6: col == 2 ? 5: col == 3 ? 30 : col == 4? 8: col == 5 ? 62:
                //        col == 6 ? 15 : col == 7 ? 22 : col == 8 ? 35 : col == 9 ? 2 : col == 10 ? 5 : 10));
            }

            count++;
            System.out.println(count);
        }

        rs.close();


    }

    private static String toCell(String text, int charLength) {
        String spaces = "";
        for (int i = 0; i < (charLength - text.length()); i++) {
            spaces += " ";
        }
        return text += spaces + "| ";

    }
}
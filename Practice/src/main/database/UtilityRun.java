package main.database;

import com.utilities.ConfigurationReader;
import com.utilities.DB_Utility;

import java.sql.ResultSet;

public class UtilityRun {
    public static void main(String[] args) {
        //CONNECTING TO BOOKIT
        ResultSet result = DB_Utility.createConnection("SELECT id,name,capacity,withtv,withwhiteboard FROM room where capacity > 5");
        //ASSIGNING QUERY TO ResultSet


        //Loop Through result
        for (int i = 1; i <= DB_Utility.getRowCount(); i++) {
            var asList = DB_Utility.getRowDataAsList(i); //PUTS DATA INTO LIST
            System.out.println(i + ": " + asList);
        }
        DB_Utility.resetCursor();
        DB_Utility.displayAll();//GET VALUE
        System.out.println();
        DB_Utility.tableName();
        System.out.println("{{{{" + DB_Utility.getCellValue(2, 1) + " : " + DB_Utility.getCellValue(1, "NAME") + "}}}");
        //CLEAN UP METHOD
        for (int i = 1; i <= DB_Utility.getRowCount(); i++) {
            System.out.println("DB_Utility.getRowMap(" + i + ") = " + DB_Utility.getRowMap(i));
        }
        DB_Utility.destroy();

        DB_Utility.createConnection(ConfigurationReader.getProperty("oracleURL"), "hr", "hr");
        result = DB_Utility.runQuery("SELECT * FROM employees ");
        var lst = DB_Utility.getColumnDataAsList(1);

        System.out.println(lst.subList(0, 5));
        for (int i = 1; i <= 5; i++) {
            var asList = DB_Utility.getRowDataAsList(i);
            System.out.println(i + ": " + asList);
        }
        System.out.println("DB_Utility.getAllRowAsListOfMap()= " + DB_Utility.getAllRowAsListOfMap().parallelStream().limit(10L));

    }


}

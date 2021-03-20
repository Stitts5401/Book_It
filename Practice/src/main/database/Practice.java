package main.database;

import com.utilities.DB_Utility;

public class Practice {
    public static void main(String[] args) {

        DB_Utility.createConnection("SELECT * FROM team ");

        System.out.println("DB_Utility.getRowCount() = \n\t" + DB_Utility.getRowCount());
        System.out.println("DB_Utility.getColumnCount() = \n\t" + DB_Utility.getColumnCount());
        System.out.println("DB_Utility.getAllColumnNamesAsList() = \n\t" + DB_Utility.getAllColumnNamesAsList());
        DB_Utility.displayAll();

        System.out.println("DB_Utility.getAllRowAsListOfMap() = \n\t " + DB_Utility.getAllRowAsListOfMap());
        System.out.println("DB_Utility.getRowMap() = \n\t" + DB_Utility.getRowMap(1));
        System.out.println("DB_Utility.getRowDataAsList(1) = \n\t" + DB_Utility.getRowDataAsList(1));
        DB_Utility.destroy();

    }

}

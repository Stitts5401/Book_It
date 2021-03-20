package main.database;

import com.utilities.DB_Utility;

public class PracticeUtilityLibrary {
    public static void main(String[] args) {
        DB_Utility.createConnection("SELECT * FROM student");

        DB_Utility.printTable();


        DB_Utility.destroy();
    }
}

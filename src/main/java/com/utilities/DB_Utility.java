package com.utilities;

import java.sql.*;
import java.util.*;

public class DB_Utility {

    private static Connection con;
    private static Statement stm;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;

    /**
     * CONNECTS TO BOOK_IT AND RETURNS RESULT_SET
     *
     * @param Query QUERY TO REACH
     * @return resultSet Returned
     */
    public static ResultSet createConnection(String Query) {
        String url = ConfigurationReader.getProperty("postGreURL");
        String username = "qa_user";
        String password = "Cybertek11!";
        createConnection(url, username, password);
        return runQuery(Query);
    }

    /**
     * Create Connection by jdbc url and username, password provided
     *
     * @param url      db url/string
     * @param username db usern
     * @param password db pword
     */
    public static void createConnection(String url, String username, String password) {
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection success: " + url.substring(5, 11));
        } catch (SQLException e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    /**
     * Runs the SQL query provided and return ResultSet object
     *
     * @param sql "SELECT * FROM *"
     * @return ResultSet object that contains data
     */
    public static ResultSet runQuery(String sql) {

        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             rs = stm.executeQuery(sql); // setting the value of ResultSet object
             rsmd = rs.getMetaData() ;  // setting the value of ResulSetMetaData for reuse
        } catch (SQLException e) {
            System.out.println("Fail: " + e.getMessage());
        }

        return rs;
    }

    /**
     * destroy method:
     * clean up all the resources after being used.
     */
    public static void destroy() {
        //WE HAVE TO C HECK IF WE HAVE THE VALID OBJECT FIRST BEFORE CLOSING THE RESOURCE
        //BECAUSE WE CAN NOT TAKE ACTION ON AN OBJECT THAT DOES NOT EXIST
        try {
            if (rs != null) rs.close();
            if (rs != null) stm.close();
            if (rs != null) con.close();

        } catch (SQLException e) {
            System.out.println("Failure: " + e.getMessage());
        }
    }

    /**
     * RowCount returns number of rows in result set
     *
     * @return Number of Rows
     */
    public static int getRowCount() {
        var rowCount = 0;
        try {
            rs.last();
            rowCount = rs.getRow();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resetCursor();
        }
        return rowCount;
    }

    /**
     * find out the column count
     *
     * @return column count of this ResultSet
     */
    public static int getColumnCount() {
        int columnCount = 0;
        try {
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnCount;
    }
    // Get all the Column names into a list object
    public static List<String> getAllColumnNamesAsList(){
        List<String> columnNameLst = new ArrayList<>();
        try {
            for (int colIndex = 1; colIndex <= getColumnCount() ; colIndex++) {
                String columnName =  rsmd.getColumnName(colIndex) ;
                columnNameLst.add(columnName) ;
            }
        }catch (SQLException e){
            System.out.println("ERROR OCCURRED WHILE getAllColumnNamesAsList "+ e.getMessage());
        }
        return columnNameLst ;
    }
    // get entire row of data according to row number

    /**
     * Getting entire row of data in a List of String
     *
     * @param rowNum row# to retrieve
     * @return row data as List of String
     */
    public static List<String> getRowDataAsList(int rowNum) {
        List<String> rowDataAsLst = new ArrayList<>();
        int colCount = getColumnCount();
        try {
            rs.absolute(rowNum);
            for (int colIndex = 1; colIndex <= colCount; colIndex++) {
                String cellValue = rs.getString(colIndex);
                rowDataAsLst.add(cellValue);
            }

        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE getRowDataAsList " + e.getMessage());
        } finally {
            resetCursor();
        }
        return rowDataAsLst;
    }

    /**
     * getting entire column data as list according to column number
     *
     * @param columnNum column# to retrieve
     * @return List object that contains all rows of that column
     */
    public static List<String> getColumnDataAsList(int columnNum) {
        List<String> columnDatalist = new ArrayList<>();

        try {
            rs.beforeFirst();
            while (rs.next()) {
                var cellValue = rs.getString(columnNum);
                columnDatalist.add(cellValue);
            }


        } catch (SQLException e) {
            System.out.println("Failure: " + e.getMessage());
        } finally {
            resetCursor();
        }

        return columnDatalist;
    }

    /**
     * getting the cell value according to row num and column index
     *
     * @param rowNum      target row
     * @param columnIndex target column Index
     * @return the value in String at that location
     */
    public static String getCellValue(int rowNum, int columnIndex) {
        String cellValue = "";
        try {
            rs.absolute(rowNum);
            cellValue = rs.getString(columnIndex);
        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE getCellValue " + e.getMessage());
        } finally {
            resetCursor();
        }
        return cellValue;
    }

    /**
     * Get First Cell Value at First Row Column
     */
    public static String getFirstData() {
        return getCellValue(1
                , 1);
    }

    /**
     * Getting the cell  value according to row  num and column index
     *
     * @param rowNum     target row
     * @param columnName target Column Name
     * @return returns value as String
     */
    public static String getCellValue(int rowNum, String columnName) {
        var cellValue = "";
        try {
            rs.absolute(rowNum);
            cellValue = rs.getString(columnName);

        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE getCellValue " + e.getMessage());
        } finally {
            resetCursor();
        }
        return cellValue;
    }

    /**
     * Display all data from the ResultSet Object
     */
    public static void displayAll() {
        int columnCount = getColumnCount();
        try {

            while (rs.next()) {

                for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                    System.out.printf("%-15s", rs.getString(colIndex)+"\t||");

                }
                System.out.println();

            }

        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE displayALL: " + e.getMessage());
        } finally {
            resetCursor();
        }


    }
    public static void printTable(){
        for(int i = 0 ; i <= getColumnCount()-1 ; i++) {
            System.out.printf("%-15s", getAllColumnNamesAsList().get(i)+"\t||");
        }
        System.out.println("\n");
        displayAll();

        }


    /**
     * Reset Cursor so no try blocks needed in main code
     */
    public static void resetCursor() {
        try {
            rs.beforeFirst();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public static void printAllAvailableWithTv() {
        createConnection("SELECT * FROM room where withtv = true");
        displayAll();
    }

    /**
     * Print Selected TableName
     */
    public static void tableName() {
        try {
            System.out.println(rsmd.getTableName(1));
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    /**
     * Creates a map of specified Row
     *
     * @param rowNum specified Row
     * @return LinkedHashMap
     */
    public static Map<String, String> getRowMap(int rowNum) {
        Map<String, String> rowMap = new LinkedHashMap<>();
        var colCount = getColumnCount();
        try {
            rs.absolute(rowNum);
            for (int colIndex = 1; colIndex <= colCount; colIndex++) {
                var columnName = rsmd.getColumnName(colIndex);
                var cellValue = rs.getString(columnName);
                rowMap.put(columnName, cellValue);
            }


        } catch (SQLException e) {
            System.out.println("ERROR CREATING ROW MAP: " + e.getMessage());
        } finally {
            resetCursor();
        }


        return rowMap;
    }

    /**
     * we know how to store one row as map object
     * now store all rows as list of map object
     *
     * @return list of list of map object that contians each row data as Map<String,String>
     */
    public static List<Map<String, String>> getAllRowAsListOfMap() {
        List<Map<String, String>> listMap = new ArrayList<>();
        var rowCount = getRowCount();

        //move from first row till last row
        // get each row as map object and add it to the list

        for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {
            Map<String, String> rowMap = getRowMap(rowIndex);
            listMap.add(rowMap);
        }

        resetCursor();

        return listMap;
    }

    public static void displayAllDataAsTable() {
        try {
            int colCount = getColumnCount();
            int[] cellLength = new int[colCount];
            List<String> colNames = getAllColumnNamesAsList();
            String sql2 = "select ";
            for (int i=0; i<colNames.size(); i++) {
                sql2 += "max(length("+colNames.get(i)+"))"+(i!=colNames.size()-1?", ":"");
            }
            sql2 += "from employees";
            Statement stm2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs2 = stm2.executeQuery(sql2);
            ResultSetMetaData rsmd2 = rs2.getMetaData();
            int colCount2 = rsmd2.getColumnCount();
            for (int col = 1, i=0; col <=colCount2 ; col++, i++) {
                rs2.first();
                cellLength[i] = Integer.parseInt(rs2.getString(col)) + 3; // becouse " | "
            }
            stm2.close();
            rs2.close();
            System.out.println("cellLength = " + Arrays.toString(cellLength));
            rs.beforeFirst(); // make sure the cursor is at before first location
            while (rs.next()) {
                for (int col = 1, i=0; col <= colCount; col++, i++) {
                    String cellValue = rs.getString(col);
                    System.out.printf("%-"+cellLength[i]+"s", " | "+ cellValue);
                }
                System.out.println();
            }
            //rs.beforeFirst(); // reset the cursor to before first location
        } catch (SQLException e) {
            System.out.println("error occurred while displayAllDataTable " + e.getMessage());
        } finally {
            resetCursor();
        }
    }
}

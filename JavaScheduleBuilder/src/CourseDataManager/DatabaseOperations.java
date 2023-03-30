package CourseDataManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
    
    //Returns the entire contents of the given SQL table
    //Ex. fetchAllTableData("courses") will return a String[] with all course data 
    public String[] fetchAllTableData(String tableName) {
        List<String> dataList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(resultSet.getString(i));
                    if (i < columnCount) {
                        row.append(", ");
                    }
                }
                dataList.add(row.toString());
            }

        } catch (Exception e) {
            System.err.println("Error fetching data from the database: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }

        return dataList.toArray(new String[0]);
    }

    //Builds a course query from given abbreviation and courseNumber and returns all sections of that course
    //Ex. fetchDataByCourseCode("ACCT", "1001") would returns all sections of ACCT 1001
    public String[] fetchDataByCourseCode(String abbreviation, String courseNumber) {
        List<String> dataList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM courses WHERE courses.abbr='" + abbreviation + "' AND courses.num='" + courseNumber + "'");
            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(resultSet.getString(i));
                    if (i < columnCount) {
                        row.append(", ");
                    }
                }
                dataList.add(row.toString());
            }

        } catch (Exception e) {
            System.err.println("Error fetching data from the database: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }

        return dataList.toArray(new String[0]);
    }
}


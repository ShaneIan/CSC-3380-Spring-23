package CourseDataManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
    
    public String[] fetchData(String tableName) {
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
}


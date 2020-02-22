package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void establishDBConnection(DBType dbType) throws SQLException {

        switch (dbType) {
            case ORACLE:
                connection = DriverManager.getConnection(ConfigReader.readProperty("dbURL"),
                                                         ConfigReader.readProperty("dbUsername"),
                                                         ConfigReader.readProperty("dbPassword"));
            break;
            case MYSQL:
                connection = null;
                break;
            case MARIADB:
                connection = null;
                break;
            default:
                connection = null;
        }
    }

    public static int getRowsCount(String sql) throws SQLException {
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(sql);
        resultSet.last();

        return resultSet.getRow();
    }

    public static List<Map<String,Object>> runSQLQuery(String query) throws SQLException {
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(query);

        List<Map<String,Object>> list = new ArrayList<>();
        ResultSetMetaData rsMdata = resultSet.getMetaData();

        int colCount = rsMdata.getColumnCount();

        while (resultSet.next()){
            Map<String,Object> rowMap = new HashMap<>();
            for(int i=1; i<=colCount; i++)
                rowMap.put(rsMdata.getColumnName(i),resultSet.getObject(i));

            list.add(rowMap);
        }
        return list;
    }

    public static void closeConnections(){
        try {
            if(resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
        catch (Exception e){e.printStackTrace();}
    }


}

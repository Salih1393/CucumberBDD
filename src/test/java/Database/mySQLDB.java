package Database;

import com.mysql.cj.protocol.Resultset;
import util.DBConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mySQLDB {

    public static void main(String[] args) throws SQLException {
        String q = "select * from students";
        String a [][]=FetchDataDB(q);
        System.out.println(a);
    }

    public static String [][] FetchDataDB(String query) throws SQLException {

        String url = "jdbc:mysql://db4free.net:3306/techleadacademy?user=techlead&password=students";

        DBConnectionManager dbInstance = DBConnectionManager.getInstance(url);
        Connection conn = dbInstance.getConn();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        int columnCount = rs.getMetaData().getColumnCount();

        rs.last();
        int rowCount = rs.getRow();
        rs.beforeFirst();

        String[][] results = new String[rowCount][columnCount];

        int i = 0;

        while (rs.next()) {
            for (int j = 0; j < columnCount; j++)
             results[i][j] = rs.getString(j+1);

            i++;
        }

        return results;
    }
}
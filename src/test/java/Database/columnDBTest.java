package Database;

import org.junit.Test;
import java.sql.*;

public class columnDBTest {

    // ec2-3-81-9-99.compute-1.amazonaws.com

    String dbURL = "jdbc:oracle:thin:@ec2-3-81-9-99.compute-1.amazonaws.com:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void employees150and160() throws SQLException {

        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees where employee_id IN (150,160)");

        while (resultSet.next())
            System.out.println(resultSet.getString(1)+
                    " --- " + resultSet.getString(2)+
                    " --- " + resultSet.getString(3));

    resultSet.close();
    statement.close();
    connection.close();

    }
}

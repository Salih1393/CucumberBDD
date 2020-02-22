package Database;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
import util.ConfigReader;
import util.DBType;
import util.DBUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UsingDBUtil {

    @BeforeClass
    public static void setUp() throws SQLException {DBUtil.establishDBConnection(DBType.ORACLE);}

    @AfterClass
    public static void closeConnection(){
        DBUtil.closeConnections();
    }

    @Test
    public void departmentName() throws SQLException {
        List<Map<String,Object>> result = DBUtil.runSQLQuery("select department_name from departments");

        System.out.println(result);
        System.out.println(result.get(1));
    }

    @Test
    public void test2()throws SQLException{
        String query = "select first_name, last_name from employees where employee_id = 105";
        List<Map<String,Object>> result = DBUtil.runSQLQuery(query);

        Assert.assertEquals(result.get(0).get("FIRST_NAME"),"David");
        Assert.assertEquals(result.get(0).get("LAST_NAME"),"Austin");
    }

    @Test
    public void test3()throws SQLException{
        String query = "select country_id from countries where country_id = 'BR'";
        List<Map<String,Object>> result = DBUtil.runSQLQuery(query);

        Assert.assertEquals(result.get(0).get("COUNTRY_ID"),"BR");

    }


}

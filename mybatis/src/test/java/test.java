
import org.junit.Test;

import java.sql.*;

public class test {
    @Test
    public void testMysqlConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://192.168.99.100:3306/test?" +
                "user=root&password=root");

        con.setAutoCommit(false);
        PreparedStatement pStatement = con.prepareStatement("select * from test");

        try{
            ResultSet rs = pStatement.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            if(pStatement != null) {
                pStatement.close();
            }
            if(con != null) {
                con.close();
            }
        }

        return;
    }
}

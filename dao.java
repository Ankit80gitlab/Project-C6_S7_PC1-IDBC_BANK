import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dao {

    static Connection con;
    static PreparedStatement ps;

    public static void checkingConnection() throws SQLException {

        String user = "root";
        String pass = "Root";
        String url = "jdbc:mysql://localhost:3306/idbcbank";
        con = DriverManager.getConnection(url, user, pass);
        ps = con.prepareStatement("use idbcbank;");
        ps.executeUpdate();

    }

    public static List<IDBC> accessingUserDData() throws SQLException
    {
        List<IDBC> list = new ArrayList<>();

        String query = "select accNo, accId, uName, age, balance, pass, accType from userD;";
        ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            long accNo = rs.getLong(1);
            int accId = rs.getInt(2);
            String uName = rs.getString(3);
            int age = rs.getInt(4);
            int balance = rs.getInt(5);
            String pass = rs.getString(6);
            String accType = rs.getString(7);

            list.add(new IDBC(accNo,accId,uName,age,balance,pass,accType));
        }
        return list;
    }
}

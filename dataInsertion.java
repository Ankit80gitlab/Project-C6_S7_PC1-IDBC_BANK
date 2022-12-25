import jdk.swing.interop.SwingInterOpUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dataInsertion {

    static Connection con;
    static PreparedStatement ps;

    public static void checkingConnection() throws SQLException {

        String user="root";
        String pass="Root";
        String url="jdbc:mysql://localhost:3306/idbcbank";
        con = DriverManager.getConnection(url,user,pass);
        ps = con.prepareStatement("use idbcbank;");
        ps.executeUpdate();
    }

    public static int insertIntoUserD (long accNo, int accId, String uName, int age, int balance, String pass, String accType) throws SQLException
    {
        String query ="insert into userD values("+accNo+","+accId+",'"+uName+"',"+age+","+balance+",'"+pass+"','"+accType+"')";
        ps = con.prepareStatement(query);
        int count=ps.executeUpdate();

        return count;
    }

    public static void updateDeposit (long accNo,int dAmount, int newBalance) throws SQLException {
        String query="insert into trans values("+accNo+",null,"+dAmount+",null,null,"+newBalance+");";
        ps = con.prepareStatement(query);
        ps.executeUpdate();
        ps = con.prepareStatement("update userD set balance = ? where accNo = ?;");
        ps.setInt(1,newBalance);
        ps.setLong(2,accNo);
        ps.executeUpdate();
        System.out.println("AMOUNT "+dAmount+" HAS BEEN SUCCESSFULLY DEPOSITED ON YOUR ACCOUNT");
        System.out.println("UPDATED BALANCE - "+newBalance);
        System.out.println("----------------------------------------------------------------------------");
    }

    public static void updateWithdraw (long accNo, int wAmount, int newBalance) throws SQLException {
        String query="insert into trans values("+accNo+",null,null,"+wAmount+",null,"+newBalance+");";
        ps = con.prepareStatement(query);
        ps.executeUpdate();
        ps = con.prepareStatement("update userD set balance = ? where accNo = ?;");
        ps.setInt(1,newBalance);
        ps.setLong(2,accNo);
        ps.executeUpdate();
        System.out.println("AMOUNT "+wAmount+" HAS BEEN SUCCESSFULLY WITHDRAW FROM YOUR ACCOUNT");
        System.out.println("UPDATED BALANCE - "+newBalance);
        System.out.println("----------------------------------------------------------------------------");
    }

    public static void updateTransfer (long fromAccNo, long toAccNo, int tAmount, int newBalance) throws SQLException {
        String query="insert into trans values("+fromAccNo+","+toAccNo+",null,null,"+tAmount+","+newBalance+");";
        ps = con.prepareStatement(query);
        ps.executeUpdate();
        ps = con.prepareStatement("update userD set balance = ? where accNo = ?;");
        ps.setInt(1,newBalance);
        ps.setLong(2,fromAccNo);
        System.out.println("AMOUNT "+tAmount+" HAS BEEN SUCCESSFULLY TRANSFERRED FROM YOUR ACCOUNT");
        System.out.println("UPDATED BALANCE - "+newBalance);
        System.out.println("----------------------------------------------------------------------------");
    }
}

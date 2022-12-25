import java.sql.*;

public class connection {

    static Connection con;
    static Statement stmt;

    public static void establishConnection()
    {
        try
        {
            //register the driver and load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("DRIVER LOADED SUCCESSFULLY");

            // establish a connection
            String user="root";
            String pass="Root";
            String url="jdbc:mysql://localhost:3306/";

            con = DriverManager.getConnection(url,user,pass);
            System.out.println("CONNECTION ESTABLISHED");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("CLASS NOT FOUND EXCEPTION - PLEASE CHECK CONNECTION");
        }
        catch (SQLException e)
        {
            System.out.println("CONNECTION ERROR - PLEASE CHECK CONNECTION");
        }
    }

    public static void createDatabase ()
    {
        try
        {
            stmt = con.createStatement();

            String query = "create database idbcbank;";
            stmt.executeUpdate(query);
            System.out.println("DATABASE SUCCESSFULLY CREATED");

            String query2 = "use idbcbank;";
            stmt.executeUpdate(query2);
            System.out.println("----------------------------------------------------------------------------");
        }
        catch(SQLException e)
        {
            System.out.println("ERROR CREATING DATABASE - DATABASE IS ALREADY CREATED");
        }
    }

    public static void createTableAndKey () throws SQLException
    {

        try
        {
            stmt = con.createStatement();

            String query="use idbcbank;";
            stmt.executeUpdate(query);

            String query1="create table accTrack(accId int primary key, accType varchar(30));";
            stmt.executeUpdate(query1);

            String query2="create table userD(accNo bigint primary key, accId int, uName varchar(30), age int, balance int, pass varchar(30), accType varchar(30));";
            stmt.executeUpdate(query2);

            String query3="create table trans(fromAccNo bigint, toAccNo bigint, deposit int, withdraw int, transfer int, balance int);";
            stmt.executeUpdate(query3);

            String query4="alter table userD add foreign key (accType) references accTrack(accType) on delete cascade on update cascade;";
            stmt.executeUpdate(query4);

        }
        catch (SQLException e)
        {
            System.out.println("SQLEXCEPTION DATABASE ERROR");
        }
    }

    public static void insertDefaultData () throws SQLException
    {
        stmt = con.createStatement();
        try

        {
            String query="use idbcbank;";
            stmt.executeUpdate(query);

            String query2="insert into accTrack values (1,'saving');";
            stmt.executeUpdate(query2);

            String query3="insert into accTrack values (2,'pay');";
            stmt.executeUpdate(query3);

            String query4="insert into userD values(825921210223,1,'ankit',22,25000,'ankit123','saving');";
            stmt.executeUpdate(query4);

            String query5="insert into userD values(855246580223,2,'ankit',22,5000,'ankit456','pay');";
            stmt.executeUpdate(query5);

        }
        catch (SQLException e)
        {
            System.out.println("SQLEXCEPTION DATABASE ERROR");
        }
    }
}

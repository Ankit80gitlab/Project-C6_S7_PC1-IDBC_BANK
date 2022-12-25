import java.sql.SQLException;
import java.util.Scanner;

public class main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        connection.establishConnection();
        connection.createDatabase();
        connection.createTableAndKey();
        connection.insertDefaultData();
        dao.checkingConnection();
        dao.accessingUserDData();
        dataInsertion.checkingConnection();

        boolean result = false;
        long sc1 = 0;
        String sc2 = "";

        while (result != true) {

            System.out.println("----------------------------------------------------------------------------");
            System.out.println("                             WELCOME TO IDBC BANK                           ");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println();
            System.out.println("1 LOGIN USING ACCOUNT");
            System.out.println("2 CREATE ACCOUNT");
            System.out.println("ENTER CHOICE");
            int c1 = sc.nextInt();
            switch (c1) {
                case 1:
                    System.out.println("ENTER YOUR 12 DIGIT ACCOUNT NUMBER");
                    sc1 = sc.nextLong();
                    System.out.println("ENTER YOUR PASSWORD");
                    sc2 = sc.next();
                    result = operationIDBC.checkLoginDetails(sc1, sc2);
                    if (!result) {
                        System.out.println("WRONG CREDENTIALS TRY AGAIN");
                    } else System.out.println("LOGGED IN SUCCESSFULLY");
                    break;

                case 2:
                    System.out.println("1 SAVING ACCOUNT");
                    System.out.println("2 PAY ACCOUNT");
                    int sc3 = sc.nextInt();
                    if (sc3 == 1) {
                        String accType = "saving";
                        System.out.println("ENTER YOUR NAME");
                        String sc4 = sc.next();
                        System.out.println("CREATE YOUR PASSWORD");
                        String sc5 = sc.next();
                        System.out.println("ENTER YOU AGE");
                        int sc6 = sc.nextInt();
                        System.out.println("OPENING BALANCE");
                        int sc7 = sc.nextInt();
                        if (sc6 >= 18) {
                            long accNo = operationIDBC.genAccNo();
                            int c = dataInsertion.insertIntoUserD(accNo, 1, sc4, sc6, sc7, sc5, accType);
                            if (c != 0) {
                                System.out.println("SAVING ACCOUNT HAS BEEN SUCCESSFULLY CREATED");
                            }
                            break;
                        } else
                            System.out.println("AGE MUST BE 18 OR MORE");
                        break;
                    } else if (sc3 == 2) {
                        String accType = "pay";
                        System.out.println("ENTER YOUR NAME");
                        String sc4 = sc.next();
                        System.out.println("CREATE YOUR PASSWORD");
                        String sc5 = sc.next();
                        System.out.println("ENTER YOU AGE");
                        int sc6 = sc.nextInt();
                        System.out.println("OPENING BALANCE");
                        int sc7 = sc.nextInt();
                        if (sc6 >= 18) {
                            long accNo = operationIDBC.genAccNo();
                            int c = dataInsertion.insertIntoUserD(accNo, 2, sc4, sc6, sc7, sc5, accType);
                            if (c != 0) {
                                System.out.println("PAY ACCOUNT HAS BEEN SUCCESSFULLY CREATED");
                            }
                            break;
                        } else
                            System.out.println("AGE MUST BE 18 OR MORE");
                        break;
                    }
            }
        }
        int [] accId = operationIDBC.showAccountDetails(sc1);
        operationIDBC.interestDetails(sc1);
        if (accId[0] == 1) {
            System.out.println("ACCOUNT LOGGED IN - SAVING");
        } else if (accId[0] == 2) {
            System.out.println("ACCOUNT LOGGED IN - PAY");
        }
        int ch = 0;
        while (ch != 5) {
            switch (accId[0]) {
                case 1:

                    System.out.println("1 DEPOSIT");
                    System.out.println("2 WITHDRAW");
                    System.out.println("3 TRANSFER");
                    System.out.println("4 CHECK BALANCE");
                    System.out.println("5 EXIT");
                    System.out.println("ENTER CHOICE");
                    ch = sc.nextInt();
                    switch (ch) {
                        case 1:
                            System.out.println("ENTER AMOUNT TO DEPOSIT");
                            int sch1 = sc.nextInt();
                            operationIDBC.deposit(sc1, sch1);
                            break;

                        case 2:
                            System.out.println("ENTER AMOUNT TO WITHDRAW");
                            int sch2 = sc.nextInt();
                            operationIDBC.withdraw(sc1, sch2);
                            break;

                        case 3:
                            System.out.println("ENTER ACCOUNT NUMBER OF PAYEE");
                            long sch3 = sc.nextLong();
                            System.out.println("ENTER AMOUNT TO TRANSFER");
                            int sch4 = sc.nextInt();
                            operationIDBC.transfer(sc1, sch3, sch4);
                            break;

                        case 4:
                            int balance = operationIDBC.compareAccNo(sc1);
                            System.out.println("UPDATED BALANCE - " + balance);
                            break;
                    }
                    break;

                case 2:
                    System.out.println("1 DEPOSIT");
                    System.out.println("2 WITHDRAW");
                    System.out.println("3 PURCHASE");
                    System.out.println("4 CHECK BALANCE");
                    System.out.println("5 EXIT");
                    System.out.println("ENTER CHOICE");
                    ch = sc.nextInt();
                    switch (ch)
                    {
                        case 1:
                            System.out.println("ENTER AMOUNT TO DEPOSIT");
                            int sch1 = sc.nextInt();
                            operationIDBC.deposit(sc1, sch1);
                            break;

                        case 2:
                            System.out.println("ENTER AMOUNT TO WITHDRAW");
                            int sch2 = sc.nextInt();
                            operationIDBC.withdraw(sc1, sch2);
                            break;

                        case 3:
                            System.out.println("ENTER THE BILL AMOUNT");
                            int sch3 = sc.nextInt();
                            operationIDBC.withdraw(sc1, sch3);
                            break;

                        case 4:
                            int balance = operationIDBC.compareAccNo(sc1);
                            System.out.println("UPDATED BALANCE - " + balance);
                            break;
                    }
                    break;
            }
        }
    }
}

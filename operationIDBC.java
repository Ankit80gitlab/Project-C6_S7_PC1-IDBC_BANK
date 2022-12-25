import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class operationIDBC {

    public static boolean checkLoginDetails(long accNo, String pass) throws SQLException
    {
        boolean result = false;
        List<IDBC> list = dao.accessingUserDData();
        ListIterator li = list.listIterator();
        while(li.hasNext())
        {
            IDBC i = (IDBC) li.next();
            if(i.getAccNo()==accNo && i.getPass().equals(pass))
            {
                result=true;
            }
        }
        return result;
    }

    public static long genAccNo() {
        Random random = new Random();
        long acc = random.nextLong(1000000000000l);
        System.out.println("YOUR NEW ACCOUNT NUMBER IS " + acc);
        return acc;
    }

    public static int [] showAccountDetails(long accNo) throws SQLException
    {
        int[] arr = new int[2];
        int accId=0;
        int amount=0;
        List<IDBC> list = dao.accessingUserDData();
        ListIterator li = list.listIterator();
        System.out.println("----------------------------------------------------------------------------");
        while(li.hasNext())
        {
            IDBC i = (IDBC) li.next();
            if(i.getAccNo()==accNo)
            {
                System.out.format("%-40s %-10s","ACCOUNT HOLDER - "+i.getuName().toUpperCase(),"ACCOUNT TYPE - "+i.getAccType().toUpperCase());
                System.out.println();
                System.out.format("%-40s %-10s","ACCOUNT NUMBER - "+i.getAccNo(),"ACCOUNT BALANCE - "+i.getBalance());
                System.out.println();
                accId=i.getAccId();
                amount=i.getBalance();
            }
        }
        System.out.println("----------------------------------------------------------------------------");
        arr[0]=accId;
        arr[1]=amount;
        return arr;
    }

    public static void interestDetails(long accNo) throws SQLException {
        int [] accId = showAccountDetails(accNo);
        double intrest=0;
        if(accId[0]==1)
        {
            intrest=(accId[1]*2.5)/100;
        }
        System.out.println("CURRENT INTEREST AMOUNT ON YOU MAIN BALANCE IS "+intrest);
    }

    public static int compareAccNo (long accNo) throws SQLException
    {
        int balance=0;

        List<IDBC> list = dao.accessingUserDData();
        ListIterator<IDBC> li = list.listIterator();
        while(li.hasNext())
        {
            IDBC i = (IDBC) li.next();
            if(i.getAccNo()==accNo)
            {
                balance=i.getBalance();
            }

        }
        System.out.println("----------------------------------------------------------------------------");
        return balance;
    }

    public static void deposit(long accNo, int amount) throws SQLException
    {
        int balance=compareAccNo(accNo);
        int newBalance=balance+amount;
        dataInsertion.updateDeposit(accNo,amount,newBalance);
    }
    public static void withdraw(long accNo, int amount) throws SQLException
    {
        int balance=compareAccNo(accNo);
        int newBalance = balance-amount;
        if(amount>balance)
        {
            System.out.println("INSUFFICIENT FUNDS");
            System.out.println("----------------------------------------------------------------------------");
        }
        else if(amount<balance)
        {
            dataInsertion.updateWithdraw(accNo,amount,newBalance);
        }
    }

    public static void transfer(long fromAccNo, long toAccNo, int amount) throws SQLException {
        int balance=compareAccNo(fromAccNo);
        int newBalance = balance-amount;
        if(amount>balance)
        {
            System.out.println("INSUFFICIENT FUNDS");
            System.out.println("----------------------------------------------------------------------------");
        }
        else if(amount<balance)
        {
            dataInsertion.updateTransfer(fromAccNo,toAccNo,amount,newBalance);
        }
    }
}

public class IDBC {

    private long accNo;
    private int accId;
    private String uName;
    private int age;
    private int balance;
    private String pass;
    private String accType;

    public IDBC(long accNo, int accId, String uName, int age, int balance, String pass, String accType) {
        this.accNo = accNo;
        this.accId = accId;
        this.uName = uName;
        this.age = age;
        this.balance = balance;
        this.pass = pass;
        this.accType = accType;
    }

    public long getAccNo() {
        return accNo;
    }

    public void setAccNo(long accNo) {
        this.accNo = accNo;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    @Override
    public String toString() {
        return "IDBC{" +
                "accNo=" + accNo +
                ", accId=" + accId +
                ", uName='" + uName + '\'' +
                ", age=" + age +
                ", balance=" + balance +
                ", pass='" + pass + '\'' +
                ", accType='" + accType + '\'' +
                '}';
    }
}

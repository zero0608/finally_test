import java.util.ArrayList;
import java.sql.*;
import java.util.Scanner;
public class Class {
    Connection conn1 = null;
    Statement stmt = null;
    ResultSet rs = null;
    public Connection connection(){
        try {
            String url1 = "jdbc:mysql://localhost:3306/test";
            String user = "root";
            String password = "";

            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test1");
            }

        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
        return conn1;
    }

    public void close(){
        if (conn1 != null) {
            try {
                conn1.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

        public class Customer {
            private int id;
            private String fullName;
            private float moneyNumber;
            private float taxNumber;
            public Customer() {
            }

            public Customer(int id, String fullName, float moneyNumber, float taxNumber) {
                this.id = id;
                this.fullName = fullName;
                this.moneyNumber = moneyNumber;
                this.taxNumber = taxNumber;
            }

            @Override
            public String toString() {
                return "[" +
                        "id=" + id +
                        ", fullName='" + fullName + '\'' +
                        ", moneyNumber=" + moneyNumber +
                        ", taxNumber=" + taxNumber +
                        ']';
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public float getMoneyNumber() {
                return moneyNumber;
            }

            public void setMoneyNumber(float moneyNumber) {
                this.moneyNumber = moneyNumber;
            }

            public float getTaxNumber() {
                return taxNumber;
            }

            public void setTaxNumber(float taxNumber) {
                this.taxNumber = taxNumber;
            }

            public float CalculateTheAmount(){
                return this.getMoneyNumber()  - this.getTaxNumber()*this.getMoneyNumber();
            }
        }

    //t???o array list ch???a ?????i t?????ng m???i l???n kh???i t???o
//    ArrayList<Customer> guests = new ArrayList<>();
    //nh???p th??ng tin ?????i t?????ng v?? add v??o array list
    public void inputData() throws SQLException {
        Scanner scanner = new Scanner(System.in);
            String fullName, address, phoneNumber;
            float moneyNumber, discountNumber, taxNumber;
            System.out.println("Nh???p t??n sp: ");
            fullName = scanner.nextLine();
            System.out.println("Nh???p gi?? ti???n: ");
            moneyNumber = scanner.nextFloat();
            System.out.println("Nh???p tri???t kh???u: ");
            taxNumber = scanner.nextFloat();
            String sql="INSERT INTO `product`(`TenSp`, `Gia`, `Trietkhau`) VALUES (?,?,?)";
            Connection conn = connection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,fullName);
            stmt.setFloat(2, moneyNumber);
            stmt.setFloat(3, taxNumber);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        close();
    }

    // in ra danh s??ch ?????i t?????ng
    public void printData() throws SQLException {
        ArrayList<Customer> pb = new ArrayList<Customer>();
        Connection conn = connection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `product`");
        while (rs.next()) {
            int a= rs.getInt(1);
            String b= rs.getString(2);
            Float c = rs.getFloat(3);
            Float d = rs.getFloat(4);
            Customer arr = new Customer(a,b,c,d);
            pb.add(arr);
        }
        close();
        showPbInfo(pb);
    }
    // show oject
    private static void showPbInfo(ArrayList<Customer> pb) {
        System.out.println("_________________Th??ng tin pb trong mysql_________________");
        for (var b : pb) {
            System.out.println(b);
        }
        System.out.println("_________________________________________________________");
    }
//        t??m ?????i tg
    public int findByID(int id) throws SQLException{
        int tse=-1;
        Customer arr = new Customer();
        Connection conn = connection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `product` where id ="+id);
        while (rs.next()) {
            int a = rs.getInt(1);
            String b = rs.getString(2);
            Float c = rs.getFloat(3);
            Float d = rs.getFloat(4);
            arr = new Customer(a, b, c, d);
                System.out.println("_________________Th??ng tin sp trong mysql_________________");
                System.out.println(arr);
                System.out.println("Gia cuoi cung(da tinh triet khau) :" + arr.CalculateTheAmount());
                System.out.println("_________________________________________________________");
                tse =1;

        }
        close();
        return tse;
    }
    public void menu() throws SQLException {
        System.out.println("1. Nh???p s???n ph???m");
        System.out.println("2. Danh s??ch s???n ph???m");
        System.out.println("3. T??m ki???m s???n ph???m");
        Scanner sc = new Scanner(System.in);
        System.out.println("M???i ch???n");
        System.out.println("???n 0 ????? tho??t ch????ng tr??nh");
        int choice = sc.nextInt();
        switch(choice){
            case 1: inputData(); menu();break;
            case 2: printData(); menu();break;
            case 3: System.out.println("m???i nh???p m?? sp");
                int idFind = sc.nextInt();
                int find = findByID(idFind);
                if (find<0){
                    System.out.println("KH??NG T??M TH???Y SP");
                }
                menu();
            break;
            default: System.exit(0);
        }
    }

    public static void main(String[] args) throws SQLException {
           Class  t = new Class();
        t.menu();
    }

}

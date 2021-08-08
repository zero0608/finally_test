import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StudentDAO {
// tạo đối tượng
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


    //tạo array list chứa đối tượng mỗi lần khởi tạo
    ArrayList<Customer> guests = new ArrayList<>();
    //nhập thông tin đối tượng và add vào array list
    public void inputData(){
        Scanner scanner = new Scanner(System.in);
        String check = ("y");
        do {
            String fullName, address, phoneNumber;
            float moneyNumber, discountNumber, taxNumber;
            System.out.println("Nhập tên sp: ");
            fullName = scanner.nextLine();
            System.out.println("Nhập giá tiền: ");
            moneyNumber = scanner.nextFloat();
            System.out.println("Nhập triết khấu: ");
            taxNumber = scanner.nextFloat();
            System.out.println("Nhập mã: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Customer k1 = new Customer(id,fullName, moneyNumber, taxNumber);
            guests.add(k1);
            System.out.println("Tiếp tục nhập(=>y or n)");
            check = scanner.nextLine();
//            int find = findByID(id);
//            if (find>= 0 ){
//                inputData();
//            }
        } while(check.equalsIgnoreCase("y"));
    }
// in ra danh sách đối tượng
    public void printData(){
        for (int i = 0; i < guests.size(); i++) {
            System.out.println("=>"+guests.get(i));
        }
    }
//    tìm đối tg
    public int findByID(int id){
        for (int i = 0; i < guests.size(); i++) {
            if (guests.get(i).getId()==id){

                System.out.println("Sản Phẩm cần tìm : "+guests.get(i));
                System.out.println("Giá cuối cùng (có tính triết khấu ) : "+guests.get(i).CalculateTheAmount());
                return i;
            }
        }
        return -1;
    }
    public void menu()
    {
        System.out.println("1. Nhập sản phẩm");
        System.out.println("2. Danh sách sản phẩm");
        System.out.println("3. Tìm kiếm sản phẩm");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choice");
        int choice = sc.nextInt();
        switch(choice){
            case 1: inputData(); menu();
            case 2: printData(); menu();
            case 3: System.out.println("mời nhập mã sp");
                int idFind = sc.nextInt();
                int find = findByID(idFind);
                if (find<0){
                    System.out.println("KHÔNG TÌM THẤY SP");
                }

                menu();

            default: System.exit(0);
        }
    }

    public static void main(String[] args) {
        StudentDAO t = new StudentDAO();
        t.menu();
    }
}
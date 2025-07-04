import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Parking parking = new Parking(20, 29);

        whileLoop:
        while (true) {

            System.out.println("Выберите действие:");
            System.out.println("1. Въезд");
            System.out.println("2. Выезд");
            System.out.println("3. Список автомобилей");
            System.out.println("4. Выйти из программы");
            System.out.println();
            System.out.print("Введите номер действия: ");

            int userChoice = scan.nextInt();

            switch (userChoice){
                case 1:
                    System.out.print("Госномер автомобиля: ");
                    String carNumber = scan.next();
                    System.out.println(carNumber);
                    parking.checkIn(carNumber);
                    break;
                case 2:
                    break;
                case 3:
                    parking.printAllCars();
                    break;
                case 4:
                    break whileLoop;
                default:
                    System.out.println("Enter a valid number");
            }

        }


    }
}
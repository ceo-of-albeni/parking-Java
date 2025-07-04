import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parking {

    int pricePerHour;
    Car[] cars;

    public Parking (int pricePerHour, int parkingCapacity) {
        this.pricePerHour = pricePerHour;
        this.cars = new Car[parkingCapacity];
    }

    public boolean checkIn(String carNumber) {
        if (carNumber.isBlank()) {
            System.out.println("Ошибка: Неверный госномер!");
            return false;
        }

        for (int i = 0; i < cars.length; i++) {
            if(cars[i] == null) {
                continue;
            }

            if (cars[i].carNumber.equals(carNumber)){
                System.out.println("Ошибка: Автомобиль уже на парковке!");
                return false;
            }
        }

        int parkingPlaceNumber = -1;
        for (int i = 0; i < cars.length; i++) {
            if(cars[i] == null) {
                parkingPlaceNumber = i;
                break;
            }
        }

        if (parkingPlaceNumber == -1) {
            System.out.println("Ошибка: Нет мест на парковке!");
        }

        Operation operation = new Operation();
        operation.checkInTime = LocalTime.now();

        Car car = new Car(carNumber);

        car.operation = operation;

        cars[parkingPlaceNumber] = car;

        return true;

    }

    public void printAllCars(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm");
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null)
                continue;
            System.out.println("Госномер: " + cars[i].carNumber + "; Время заезда: " + dateTimeFormatter.format(cars[i].operation.checkInTime));
        }
    }

    public boolean checkOut(String carNumber) {
        Car car = null;
        int carIndex = -1;

        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                continue;
            }
            if (cars[i].carNumber.equals(carNumber))
                car = cars[i];
                carIndex = i;
        }

        if( car == null) {
            System.out.println("Ошибка: Автомобиль не найден!");
        }

        car.operation.checkOutTime = LocalTime.now();
        car.operation.duration = (int) Math.ceil((double)Duration.between(car.operation.checkInTime, car.operation.checkOutTime).toHours());
        car.operation.totalPrice = car.operation.duration * pricePerHour;

        System.out.println("Время парковки: " + car.operation.duration + " ч. ");
        System.out.println("Стоимость: " + car.operation.totalPrice + " сомов");

        cars[carIndex] = null;

        return true;

    }

}

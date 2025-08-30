import java.time.Year;
public class Car {
    String brand;
    String model;
    int year;
    String color;
    boolean isRunning;

    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false;
    }
    public void startEngine() {
        isRunning = true;
        System.out.println(brand + " " + model + " engine started.");
    }
    public void stopEngine() {
        isRunning = false;
        System.out.println(brand + " " + model + " engine stopped.");
    }
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Model: " + model + ", Year: " + year + ", Color: " + color + ", Running: " + isRunning);
    }
    public int getAge() {
        return Year.now().getValue() - year;
    }
    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Corolla", 2018, "White");
        Car car2 = new Car("Honda", "Civic", 2020, "Black");
        Car car3 = new Car("Ford", "Mustang", 2022, "Red");

        car1.startEngine();
        car1.displayInfo();
        System.out.println("Car Age: " + car1.getAge());
        car1.stopEngine();

        car2.startEngine();
        car2.displayInfo();
        System.out.println("Car Age: " + car2.getAge());
        car2.stopEngine();

        car3.startEngine();
        car3.displayInfo();
        System.out.println("Car Age: " + car3.getAge());
        car3.stopEngine();
    }
}

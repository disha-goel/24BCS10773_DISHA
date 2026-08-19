interface Drivable {
    void drive();
}

class Vehicle {
    String brand;
    int speed;

    Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    void move() {
        System.out.println(brand + " is moving");
    }
}

class Car extends Vehicle implements Drivable {
    int seats;

    Car(String brand, int speed, int seats) {
        super(brand, speed);
        this.seats = seats;
    }

    public void drive() {
        System.out.println(brand + " is driving");
    }

    void display() {
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed + " kmph");
        System.out.println("Seats: " + seats);
    }
}

public class Interface {
    public static void main(String[] args) {

        Car c = new Car("Audi", 120, 5);

        c.move();
        c.drive();
        c.display();
    }
}

/* 
Output:

Audi is moving
Audi is driving
Brand: Audi
Speed: 120 kmph
Seats: 5

*/
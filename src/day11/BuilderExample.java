package day11;

class MotorCycle{
    //Required Field
    private final String make;
    private final String model;

    //Optional field
    private final int year;
    private final String color;

    //to prevent direct instantiation
    public MotorCycle(String make, String model, int year, String color) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    @Override
    public String toString() {
        return "TwoWheeler{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

class MotorCycleBuilder {
    // Required fields
    private final String make;
    private final String model;

    // Optional fields with default values
    private int year = 0;
    private String color = "Unknown";

    // Constructor for required fields
    public MotorCycleBuilder(String make, String model) {
        this.make = make;
        this.model = model;
    }

    // Setter for optional field 'year'
    public MotorCycleBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    // Setter for optional field 'color'
    public MotorCycleBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    // Build method to create Car instance
    public MotorCycle build() {
        return new MotorCycle(make, model, year, color);
    }
}

public class BuilderExample {
    public static void main(String[] args) {
        MotorCycle motorCycle = new MotorCycleBuilder("Honda","Activa 7G")
                .setYear(2025)
                .setColor("Siren Blue")
                .build();

        System.out.println(motorCycle);
    }
}

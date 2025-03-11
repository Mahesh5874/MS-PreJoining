package day4;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;

class Employee{
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private double salary;
    private String dept;

    public Employee(int id, String firstName, String lastName, LocalDate dob, double salary, String dept) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.salary = salary;
        this.dept = dept;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public double getSalary() {
        return salary;
    }

    public String getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", salary=" + salary +
                ", dept='" + dept + '\'' +
                '}';
    }
}

class User{
    private int id;
    private String usename;
    private String password;

    public User(int id, String usename, String password) {
        this.id = id;
        this.usename = usename;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsename() {
        return usename;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", usename='" + usename + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

@FunctionalInterface
interface UserNameGenerater {
    String generate(String firtanem, String lastname, int year, int id);
}

public class FunctionalInterfaceLambdas {
    public static void main(String[] args) throws InterruptedException {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Shubhman", "Gill", LocalDate.of(1999, Month.SEPTEMBER, 8), 2500.0, "IT"));
        employees.add(new Employee(2, "Steve", "Smith", LocalDate.of(1989, Month.JUNE, 2), 1500.0, "HR"));
        employees.add(new Employee(3, "Virat", "Kohli", LocalDate.of(1988, Month.NOVEMBER, 5), 3000.0, "Finance"));
        employees.add(new Employee(4, "Rohit", "Sharma", LocalDate.of(1987, Month.APRIL, 30), 2200.0, "Marketing"));
        employees.add(new Employee(5, "Axar", "Patel", LocalDate.of(1994, Month.JANUARY, 20), 2200.0, "Marketing"));

        // print list of employess whose salary is above 2000.
        System.out.println("***** Printing List of Employees greater than 2000 *****");
        Consumer<Employee> employeeConsumer = emp -> System.out.println(emp);
        Predicate<Employee> employeePredicate = emp -> emp.getSalary() > 2000;
        employees.stream()
                .filter(employeePredicate)
                .forEach(employeeConsumer);


        System.out.println("***** Printing List of Employees greater than 2000 using BiPredicate *****");
        BiPredicate<Employee, Double> employeeBiPredicate = (emp, threshold) -> emp.getSalary() > threshold;
        employees.stream()
                .filter(e -> employeeBiPredicate.test(e, 2000.0))
                .forEach(employeeConsumer);

        Supplier<String> passwordSupplier = () -> {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(16);
            for (int i = 0; i < 16; i++) {
                sb.append(chars.charAt(random.nextInt(chars.length())));
            }
            return sb.toString();
        };

//        System.out.println(passwordSupplier.get());

        Function<Employee, User> employeeUserFunction = emp -> {
          int year = emp.getDob().getYear();
          String username = emp.getFirstName()+emp.getLastName()+year+emp.getId();
          String password = passwordSupplier.get();
          return new User(emp.getId(), username, password);
        };

        List<User> users = employees.stream()
                .map(employeeUserFunction)
                .toList();

        System.out.println("***** Printing List of Users *****");
        users.forEach(System.out::println);


        System.out.println("***** Printing List of Employees sorted by birth month *****");
        Collections.sort(employees, (e1, e2) ->
            Integer.compare(e1.getDob().getMonthValue(), e2.getDob().getMonthValue())
        );
        employees.forEach(employeeConsumer);

        Thread thread1 = new Thread(() -> {
            System.out.println("Employee Thread: Printing Employees");
            employees.forEach(employeeConsumer);
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("User Thread: Printing Users");
            users.forEach(System.out::println);
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        UserNameGenerater userNameGenerater = (fname, lname, year, id) -> fname+lname+year+id;

        String generatedUsername = userNameGenerater.generate(
                employees.get(3).getFirstName(),
                employees.get(3).getLastName(),
                employees.get(3).getDob().getYear(),
                employees.get(3).getId()
        );
        System.out.println("Generated username using custom UserNameGenerator "+generatedUsername);
    }

}

package day5;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Employee class with additional joinDate field for joining year
class Employee {
    private String firstName;
    private String lastName;
    private int id;
    private LocalDate dateOfBirth;
    private double salary;
    private String dept;
    private LocalDate joinDate;

    public Employee(String firstName, String lastName, int id, LocalDate dateOfBirth, double salary, String dept, LocalDate joinDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.dept = dept;
        this.joinDate = joinDate;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getId() { return id; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public double getSalary() { return salary; }
    public String getDept() { return dept; }
    public LocalDate getJoinDate() { return joinDate; }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", salary=" + salary +
                ", dept='" + dept + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }
}

public class StreamsOperations {
    public static void main(String[] args) {
        // Create sample employee data
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Rohit", "Sharma", 1, LocalDate.of(1990, Month.JANUARY, 10), 3000.0, "IT", LocalDate.of(2023, Month.FEBRUARY, 1)));
        employees.add(new Employee("Yashaswi", "Jaiswal", 2, LocalDate.of(1985, Month.MARCH, 20), 3500.0, "IT", LocalDate.of(2022, Month.APRIL, 15)));
        employees.add(new Employee("Hardik", "Pandya", 3, LocalDate.of(1992, Month.MAY, 30), 2800.0, "HR", LocalDate.of(2021, Month.JUNE, 5)));
        employees.add(new Employee("Virat", "Kohli", 4, LocalDate.of(1988, Month.JULY, 12), 3200.0, "IT", LocalDate.of(2023, Month.AUGUST, 10)));
        employees.add(new Employee("Jasprit", "Bumrah", 5, LocalDate.of(1995, Month.SEPTEMBER, 25), 4000.0, "Sales", LocalDate.of(2023, Month.OCTOBER, 3)));

        System.out.println("Employees who joined in 2023 (First Names):");
        employees.stream()
                .filter(e -> e.getJoinDate().getYear() == 2023)
                .map(Employee::getFirstName)
                .forEach(System.out::println);

        // 2. Print count, min, max, sum, and average of salary of all employees in a particular department.
        // For this example, we choose the department "IT".
        String deptToAnalyze = "IT";
        DoubleSummaryStatistics stats = employees.stream()
                .filter(e -> e.getDept().equalsIgnoreCase(deptToAnalyze))
                .mapToDouble(Employee::getSalary)
                .summaryStatistics();

        System.out.println("Salary statistics for department: " + deptToAnalyze);
        System.out.println("Count: " + stats.getCount());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Average: " + stats.getAverage());

        // 3. Print sorted list of employees by firstName in all departments except HR.
        System.out.println("Sorted list of employees by firstName (excluding HR department):");
        List<Employee> sortedEmployees = employees.stream()
                .filter(e -> !e.getDept().equalsIgnoreCase("HR"))
                .sorted((e1, e2) -> e1.getFirstName().compareToIgnoreCase(e2.getFirstName()))
                .collect(Collectors.toList());
        sortedEmployees.forEach(System.out::println);

        // 4. Increment salary of employees in a particular department by 10%.
        // For this example, the chosen department is "Sales".
        String deptToIncrement = "IT";
        System.out.println("Incrementing salary by 10% for department: " + deptToIncrement);
        employees.stream()
                .filter(e -> e.getDept().equalsIgnoreCase(deptToIncrement))
                .forEach(e -> e.setSalary(e.getSalary() * 1.10));

        // Print updated employees from the Sales department
        employees.stream()
                .filter(e -> e.getDept().equalsIgnoreCase(deptToIncrement))
                .forEach(System.out::println);

        // 5. Using stream, print 50 odd numbers after 100.
        System.out.println("50 odd numbers after 100:");
        IntStream.iterate(101, n -> n + 2)
                .limit(50)
                .forEach(n -> System.out.print(n + " "));

        // 6. Create a comma separated list of First names of employees ordered by dateOfBirth.
        String commaSeparatedNames = employees.stream()
                .sorted((e1, e2) -> e1.getDateOfBirth().compareTo(e2.getDateOfBirth()))
                .map(Employee::getFirstName)
                .collect(Collectors.joining(", "));
        System.out.println("\nComma separated list of first names ordered by date of birth:");
        System.out.println(commaSeparatedNames);
    }
}


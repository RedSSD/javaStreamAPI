package zad3;
import java.util.*;
import java.util.stream.Collectors;

public class Zadanie3 {

    private static final double SALARY_THRESHOLD = 5000.0;

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee("Anna Kowalska", "IT", 8000.00),
                new Employee("Jan Nowak", "IT", 7500.00),
                new Employee("Maria Wiśniewska", "HR", 4500.00),
                new Employee("Piotr Zieliński", "IT", 9000.00),
                new Employee("Katarzyna Lewandowska", "HR", 4800.00),
                new Employee("Tomasz Kamiński", "Sprzedaż", 5500.00),
                new Employee("Agnieszka Wójcik", "Sprzedaż", 6000.00),
                new Employee("Michał Kowalczyk", "IT", 8500.00),
                new Employee("Ewa Szymańska", "HR", 5000.00),
                new Employee("Paweł Dąbrowski", "Sprzedaż", 5800.00),
                new Employee("Magdalena Król", "Marketing", 6500.00),
                new Employee("Krzysztof Piotrowski", "Marketing", 7000.00),
                new Employee("Joanna Grabowska", "HR", 4200.00),
                new Employee("Adam Pawlak", "Sprzedaż", 6200.00)
        );

        Map<String, Double> averageSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > SALARY_THRESHOLD)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> roundToTwoDecimals(e.getValue())
                ));

        System.out.println(averageSalaryByDepartment);
    }

    private static double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}


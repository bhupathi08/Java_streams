package practice;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsTest {
    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 125000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        method1();
    }

    public static void method1() {
        System.out.println("Query 1 : How many male and female employees are there in the organization?");
        Map<String, Map<String, Set<String>>> res = employeeList.stream().collect
                (Collectors.groupingBy(Employee::getGender, Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toSet()))));
        System.out.println(res);
    }

    public static void method2() {
        System.out.println("Query 2 : Print the name of all departments in the organization?");
        String res = employeeList.stream().map(x -> x.department).distinct().collect(Collectors.joining(", "));
        System.out.println(res);
    }

    public static void method3() {
        System.out.println("Query 3 : What is the average age of male and female employees?");
        Map<String, Double> res = employeeList.stream().collect(
                Collectors.groupingBy(Employee::getGender, Collectors.averagingLong(Employee::getAge)));
        System.out.println(res);
    }

    public static void method4() {
        System.out.println("Query 4 : Get the details of highest paid employee in the organization?");
        Optional<Double> res = employeeList.stream().map(x -> x.salary).min(Comparator.naturalOrder());
        System.out.println(res.get());
    }

    public static void method5() {
        System.out.println("Query 5 : Get the names of all employees who have joined after 2015?");
        System.out.println(employeeList.stream().filter(x -> x.yearOfJoining > 2015).map(Employee::getName).collect(Collectors.joining(", ")));
    }

    public static void method6() {
        System.out.println("Query 6 : Count the number of employees in each department?");
        System.out.println(employeeList.stream().collect(Collectors.groupingBy
                (Employee::getDepartment, Collectors.counting())));
    }

    public static void method7() {
        System.out.println("Query 7 : What is the average salary of each department?");
        employeeList.stream().collect(Collectors.groupingBy(
                Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary))).
                entrySet().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));
    }

    public static void method8() {
        System.out
                .println("Query 8 : Get the details of youngest male employee in the product development department?");
        System.out.println(employeeList.stream().filter(x -> x.gender == "Male" && x.department == "Product Development").
                min(Comparator.comparingDouble(Employee::getSalary)));

    }

    public static void method9() {
        System.out.println("Query 9 : Who has the most working experience in the organization?");
        System.out.println(employeeList.stream().min(Comparator.comparingInt(Employee::getYearOfJoining)).get());
    }

    public static void method10() {
        System.out.println("Query 10 : How many male and female employees are there in the sales and marketing team?");
        System.out.println(employeeList.stream().filter(x -> x.department == "Sales And Marketing").
                collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())));
    }

    public static void method11() {
        System.out.println("Query 11 : What is the average salary of male and female employees?");
        System.out.println(employeeList.stream().collect(Collectors.groupingBy(
                Employee::getGender, Collectors.averagingDouble(Employee::getSalary)
        )));
    }

    public static void method12() {
        System.out.println("Query 12 : List down the names of all employees in each department?");
        employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment)).
                entrySet().forEach(x -> System.out.println(x.getKey() + ": " +
                x.getValue().stream().map(y -> y.name).collect(Collectors.joining(", "))));
    }

    public static void method13() {
        System.out.println("Query 13 : What is the average salary and total salary of the whole organization?");
        System.out.println(employeeList.stream().map(Employee::getSalary).collect(Collectors.summingDouble(Double::doubleValue)));
        System.out.println(employeeList.stream().map(Employee::getSalary).collect(Collectors.averagingDouble(Double::doubleValue)));

        DoubleSummaryStatistics employeeSalaryStatistics =
                employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println("Average Salary = " + employeeSalaryStatistics.getAverage());

        System.out.println("Total Salary = " + employeeSalaryStatistics.getSum());
    }

    public static void method14() {
        System.out.println(
                "Query 14 : Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.");
        System.out.println("Less than or equal to 25");employeeList.stream().filter(x -> x.age <= 25).map(Employee::getName).forEach(System.out::println);
        System.out.println();
        System.out.println("More than 25");employeeList.stream().filter(x -> x.age > 25).map(Employee::getName).forEach(System.out::println);
    }

    public static void method15() {
        System.out.println(
                "Query 15 : Who is the oldest employee in the organization? What is his age and which department he belongs to?");
        System.out.println(employeeList.stream().max(Comparator.comparingInt(value -> value.age)).get());

        System.out.println(employeeList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList()).get(3));
    }

    public static void method16() {
        String x = "12345";
        System.out.println(Arrays.stream(x.split("")).mapToInt(Integer::parseInt).reduce((a, y) -> y + a).getAsInt());
    }
}

package EmployeeManagement;
import java.util.*;
import java.util.stream.Collectors;
public class EmpMangement {
    static class Employee {
        private int id;
        private String name;
        private double salary;

        public Employee(int id, String name, double salary)
        {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }


        public int getId() { return id; }
        public String getName() { return name; }
        public double getSalary() { return salary; }
        public void setSalary(double salary) { this.salary = salary; }

        @Override
        public String toString() {
            return "Employee [ID=" + id + ", Name=" + name + ", Salary=" + salary + "]";
        }
    }

    public class EmployeeManagementSystem
    {
        private static List<Employee> employees = new ArrayList<>();
        private static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) {
            int choice;
            do {
                System.out.println("\n====== Employee Management System ======");
                System.out.println("1. Add Employee");
                System.out.println("2. Search Employee by Name");
                System.out.println("3. Delete Employee by ID");
                System.out.println("4. Sort Employees by Salary (Ascending)");
                System.out.println("5. Sort Employees by Salary (Descending)");
                System.out.println("6. Increase Salary of all Employees by 10%");
                System.out.println("7. Calculate Total Salary");
                System.out.println("8. Display All Employees");
                System.out.println("9. Display Employees with Salary > given amount");
                System.out.println("10. Display All Employee Names in UPPERCASE");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1: addEmployee(); break;
                    case 2: searchEmployee(); break;
                    case 3: deleteEmployee(); break;
                    case 4: sortEmployeesAsc(); break;
                    case 5: sortEmployeesDesc(); break;
                    case 6: increaseSalary(); break;
                    case 7: calculateTotalSalary(); break;
                    case 8: displayAll(); break;
                    case 9: displaySalaryGreater(); break;
                    case 10: displayNamesUppercase(); break;
                    case 0: System.out.println("Exiting..."); break;
                    default: System.out.println("Invalid choice!");
                }
            } while (choice != 0);
        }


        private static void addEmployee() {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            employees.add(new Employee(id, name, salary));
            System.out.println("Employee added successfully!");
        }


        private static void searchEmployee() {
            System.out.print("Enter name to search: ");
            String searchName = sc.nextLine().toLowerCase();

            List<Employee> results = employees.stream()
                    .filter(e -> e.getName().toLowerCase().contains(searchName))
                    .collect(Collectors.toList());

            if (results.isEmpty()) {
                System.out.println("No employees found.");
            } else {
                results.forEach(System.out::println);
            }
        }


        private static void deleteEmployee() {
            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();
            boolean removed = employees.removeIf(e -> e.getId() == id);

            if (removed) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee not found!");
            }
        }


        private static void sortEmployeesAsc() {
            employees.stream()
                    .sorted(Comparator.comparingDouble(Employee::getSalary))
                    .forEach(System.out::println);
        }


        private static void sortEmployeesDesc() {
            employees.stream()
                    .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                    .forEach(System.out::println);
        }


        private static void increaseSalary() {
            employees.forEach(e -> e.setSalary(e.getSalary() * 1.10));
            System.out.println("All salaries increased by 10%!");
            displayAll();
        }


        private static void calculateTotalSalary() {
            double total = employees.stream()
                    .mapToDouble(Employee::getSalary)
                    .sum();
            System.out.println("Total Salary Paid: " + total);
        }


        private static void displayAll() {
            if (employees.isEmpty()) {
                System.out.println("No employees in system.");
            } else {
                employees.forEach(System.out::println);
            }
        }

        private static void displaySalaryGreater() {
            System.out.print("Enter salary amount: ");
            double amount = sc.nextDouble();

            employees.stream()
                    .filter(e -> e.getSalary() > amount)
                    .forEach(System.out::println);
        }


        private static void displayNamesUppercase() {
            employees.stream()
                    .map(e -> e.getName().toUpperCase())
                    .forEach(System.out::println);
        }
    }

}

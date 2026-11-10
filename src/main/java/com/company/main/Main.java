package com.company.main;

import com.company.entity.Employee;
import com.company.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext("com.company.config")) {

            EmployeeService service = context.getBean(EmployeeService.class);
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n===== EMPLOYEE MANAGEMENT SYSTEM =====");
                System.out.println("1. Add employee");
                System.out.println("2. Find employee by ID");
                System.out.println("3. Find all employees");
                System.out.println("4. Update employee");
                System.out.println("5. Delete employee");
                System.out.println("6. Send SMS notification demo");
                System.out.println("7. Demonstrate transaction rollback");
                System.out.println("0. Exit");
                System.out.print("Choose option: ");

                int choice = readInt(scanner);
                try {
                    switch (choice) {
                        case 1 -> {
                            System.out.print("Name: ");
                            String name = scanner.nextLine().trim();
                            System.out.print("Email: ");
                            String email = scanner.nextLine().trim();
                            System.out.print("Department: ");
                            String department = scanner.nextLine().trim();
                            System.out.print("Salary: ");
                            BigDecimal salary = readDouble(scanner);

                            Employee employee = new Employee(name, email, department, salary);
                            service.addEmployee(employee);
                            System.out.println("Employee registered successfully. ID: " + employee.getId());
                        }
                        case 2 -> {
                            System.out.print("Employee ID: ");
                            long id = readLong(scanner);
                            Employee employee = service.getEmployeeById(id);
                            System.out.println(employee == null ? "Employee not found." : employee);
                        }
                        case 3 -> {
                            List<Employee> employees = service.getAllEmployees();
                            if (employees.isEmpty()) System.out.println("No employees found.");
                            else employees.forEach(System.out::println);
                        }
                        case 4 -> {
                            System.out.print("Employee ID: ");
                            long id = readLong(scanner);
                            Employee employee = service.getEmployeeById(id);
                            if (employee == null) {
                                System.out.println("Employee not found.");
                                break;
                            }
                            System.out.print("New name: ");
                            employee.setName(scanner.nextLine().trim());
                            System.out.print("New email: ");
                            employee.setEmail(scanner.nextLine().trim());
                            System.out.print("New department: ");
                            employee.setDepartment(scanner.nextLine().trim());
                            System.out.print("New salary: ");
                            employee.setSalary(readDouble(scanner));
                            service.updateEmployee(employee);
                            System.out.println("Employee updated successfully.");
                        }
                        case 5 -> {
                            System.out.print("Employee ID: ");
                            long id = readLong(scanner);
                            System.out.println(service.deleteEmployee(id)
                                    ? "Employee deleted successfully." : "Employee not found.");
                        }
                        case 6 -> {
                            System.out.print("Employee name: ");
                            String name = scanner.nextLine().trim();
                            service.sendSmsDemo(name);
                        }
                        case 7 -> {
                            System.out.println("This will insert a test employee and then throw an exception.");
                            System.out.println("The transaction should roll back; check the employees table afterwards.");
                            try {
                                service.demonstrateRollback();
                            } catch (RuntimeException ex) {
                                System.out.println("Expected rollback exception: " + ex.getMessage());
                            }
                        }
                        case 0 -> running = false;
                        default -> System.out.println("Invalid option. Try again.");
                    }
                } catch (IllegalArgumentException ex) {
                    System.out.println("Validation error: " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Operation failed: " + ex.getMessage());
                }
            }
            System.out.println("Closing application...");
        }
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException ex) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    private static long readLong(Scanner scanner) {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.print("Enter a valid employee ID: ");
            }
        }
    }

    private static BigDecimal readDouble(Scanner scanner) {
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.print("Enter a valid salary: ");
            }
        }
    }

}

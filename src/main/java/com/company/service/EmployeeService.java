package com.company.service;

import com.company.entity.Employee;
import com.company.notification.NotificationService;
import com.company.notification.SmsNotificationService;
import com.company.repository.EmployeeRepo;
import com.company.util.EmployeeIdGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepository;
    private final NotificationService notificationService; // @Primary selects email
    private final SmsNotificationService smsNotificationService; // explicit SMS implementation
    private final EmployeeIdGenerator employeeIdGenerator;

    public EmployeeService(EmployeeRepo employeeRepository,
                           NotificationService notificationService,
                           @Qualifier("smsNotificationService") SmsNotificationService smsNotificationService,
                           EmployeeIdGenerator employeeIdGenerator) {
        this.employeeRepository = employeeRepository;
        this.notificationService = notificationService;
        this.smsNotificationService = smsNotificationService;
        this.employeeIdGenerator = employeeIdGenerator;
    }

    @Transactional
    public void addEmployee(Employee employee) {
        validate(employee);
        employeeRepository.save(employee);
        notificationService.sendNotification("Welcome " + employee.getName()
                + "! Your employee record has been created.");
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee updateEmployee(Employee employee) {
        if (employee.getId() == null || employee.getId() <= 0) {
            throw new IllegalArgumentException("A valid employee ID is required.");
        }
        if (employeeRepository.findById(employee.getId()) == null) {
            throw new IllegalArgumentException("Employee ID " + employee.getId() + " does not exist.");
        }
        validate(employee);
        return employeeRepository.update(employee);
    }

    @Transactional
    public boolean deleteEmployee(long id) {
        return employeeRepository.delete(id);
    }

    public void sendSmsDemo(String employeeName) {
        smsNotificationService.sendNotification("SMS demo for " + employeeName);
    }

    // The generated value is a demonstration utility; the database identity remains the real employee ID.
    public long generateDemoReference() {
        return employeeIdGenerator.nextId();
    }

    @Transactional
    public void demonstrateRollback() {
        Employee testEmployee = new Employee(
                "Rollback Test", "rollback-" + System.nanoTime() + "@example.com",
                "Testing", BigDecimal.valueOf(1.0)
        );
        employeeRepository.save(testEmployee);
        throw new IllegalStateException("Intentional exception after persist; transaction must roll back.");
    }

    private void validate(Employee employee) {
        if (employee == null) throw new IllegalArgumentException("Employee details are required.");
        if (employee.getName() == null || employee.getName().isBlank())
            throw new IllegalArgumentException("Name is required.");
        if (employee.getEmail() == null || !employee.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            throw new IllegalArgumentException("Enter a valid email address.");
        if (employee.getDepartment() == null || employee.getDepartment().isBlank())
            throw new IllegalArgumentException("Department is required.");
        if (employee.getSalary() == null ||
                employee.getSalary().compareTo(BigDecimal.ZERO) <= 0)

            throw new IllegalArgumentException(
                    "Salary must be greater than zero"
            );

    }
}

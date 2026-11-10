package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

//@Entity
//@Table(name = "employees", uniqueConstraints = {
//        @UniqueConstraint(name = "uk_employee_email", columnNames = "email")
//})
//public class Employee {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, length = 100)
//    private String name;
//
//    @Column(nullable = false, unique = true, length = 150)
//    private String email;
//
//    @Column(nullable = false, length = 100)
//    private String department;
//
//    @Column(nullable = false)
//    private double salary;
//
//    public Employee() {
//    }
//
//    public Employee(String name, String email, String department, double salary) {
//        this.name = name;
//        this.email = email;
//        this.department = department;
//        this.salary = salary;
//    }
//
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }
//    public String getDepartment() { return department; }
//    public void setDepartment(String department) { this.department = department; }
//    public double getSalary() { return salary; }
//    public void setSalary(double salary) { this.salary = salary; }
//
//    @Override
//    public String toString() {
//        return "Employee{id=" + id + ", name='" + name + "', email='" + email
//                + "', department='" + department + "', salary=" + salary + "}";
//    }
//}
@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
public  class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
@Column(nullable = false, unique = true)
    private String email;

    private String department;

    private BigDecimal salary;

    public Employee(String name, String email, String department, BigDecimal salary) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }
}

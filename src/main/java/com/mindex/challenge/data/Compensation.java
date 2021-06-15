package com.mindex.challenge.data;

public class Compensation {
    private Employee employee;
    private int salary;
    private String effectiveDate;

    public Compensation(Employee employee, int salary, String effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getSalary() {
        return salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    @Override
    public String toString() {
        return "Compensation{" +
                "employee=" + employee +
                ", salary=" + salary +
                ", effectiveDate='" + effectiveDate + '\'' +
                '}';
    }
}

package com.capgemini.jdbc;

import java.time.LocalDate;

public class Employee_payroll_Data {
	public int id;
	public String name;
	public double salary;
	public LocalDate start;
	public String gender;

	public Employee_payroll_Data(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public Employee_payroll_Data(int id, String name, double salary, LocalDate start) {
		this(id, name, salary);
		this.start = start;
	}

	public Employee_payroll_Data(int id, String name, double salary, LocalDate start, String gender) {
		this(id, name, salary, start);
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Employee_payroll_Data [id=" + id + ", name=" + name + ", salary=" + salary + ", startDate=" + start
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Employee_payroll_Data that = (Employee_payroll_Data) obj;
		return id == that.id  &&  Double.compare(that.salary, salary) == 0 && name.equals(that.name);
	}
}

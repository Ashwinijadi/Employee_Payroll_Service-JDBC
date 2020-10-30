package com.capgemini.jdbc;

import java.time.LocalDate;

public class Employee_payroll_Data {
	public int id;
	public String name;
	public double salary;
	public LocalDate startDate;
	public String gender;

	public Employee_payroll_Data(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public Employee_payroll_Data(int id, String name, double salary, LocalDate startDate) {
		this(id, name, salary);
		this.startDate = startDate;
	}
	
	public Employee_payroll_Data(int id, String name, double salary, LocalDate startDate,String gender) {
		this(id, name, salary,startDate);
		this.gender=gender;
	}

	@Override
	public String toString() {
		return "Employee_payroll_Data [id=" + id + ", name=" + name + ", salary=" + salary + ", startDate=" + startDate
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Employee_payroll_Data emp_payroll = (Employee_payroll_Data) obj;
		return id == emp_payroll.id && Double.compare(emp_payroll.salary, salary) == 0 && name.equals(emp_payroll.name);
	}
}

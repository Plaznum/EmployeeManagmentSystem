package com.cognixia.jump.advancedjava.employeeproject;

import java.io.Serializable;

public class Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int idCounter = 1;
	
	int empID;
	String fName;
	String lName;
	String position;
	int age;
	String address;
	int salary;
	Department department;
	
	public Employee() {
		this.empID = idCounter++;
		
	}
	
	public Employee(String fName, String lName, String position, int age, String address, int salary,
			Department department) {
		super();
		this.empID = idCounter++;
		this.fName = fName;
		this.lName = lName;
		this.position = position;
		this.age = age;
		this.address = address;
		this.salary = salary;
		this.department = department;
	}
	
	public int getempID() {
		return empID;
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", fName=" + fName + ", lName=" + lName + ", position=" + position
				+ ", age=" + age + ", address=" + address + ", salary=" + salary + ", department=" + department.getName() + "]";
	}
	
	
	
	
	
	
}

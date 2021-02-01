package com.cognixia.jump.advancedjava.employeeproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Department implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int idCounter = 1;
	
	int deptID;
	Employee deptHead;
	String name;
	int budget;
	int phone;
	List<Employee> empList;
	
	public Department() {
		this.deptID = idCounter++;
		empList = new ArrayList<Employee>();
	}
	
	public Department(String name) {
		this.name=name;
		empList = new ArrayList<Employee>();
	}
	public Department(String name, int budget, int phone) {
		super();
		this.deptID = idCounter++;
		this.name = name;
		this.budget = budget;
		this.phone = phone;
		empList = new ArrayList<Employee>();
		
	}
	public Department(Employee deptHead, String name, int budget, int phone) {
		super();
		this.deptID = idCounter++;
		this.deptHead = deptHead;
		this.name = name;
		this.budget = budget;
		this.phone = phone;
		empList = new ArrayList<Employee>();
		
	}
	public int getDeptID() {
		return deptID;
	}
	public Employee getDeptHead() {
		return deptHead;
	}
	public void setDeptHead(Employee deptHead) {
		this.deptHead = deptHead;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public List<Employee> getEmpList() {
		return this.empList;
	}
	
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	
	public void removeEmp(int id) {
			
			Stream<Employee> empStream = empList.stream();
			
			//filter out the employee
			List<Employee> newDeptList = empStream.filter(e -> e.getempID() != id)
						.collect(Collectors.toList());
			
			//copy the list back to the department
			empList = newDeptList;
	}
	
	public void addEmp(Employee employee) {
		empList.add(employee);
	}

	@Override
	public String toString() {
		return "Department [deptID=" + deptID + ", deptHead=" + deptHead + ", name=" + name + ", budget=" + budget
				+ ", phone=" + phone + "]";
	}
	
	
}

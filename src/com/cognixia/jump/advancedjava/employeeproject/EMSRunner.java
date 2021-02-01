package com.cognixia.jump.advancedjava.employeeproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class EMSRunner {

	
	
	public static void main(String[] args) {
		List<Employee> userEmpList = new ArrayList<Employee>();
		List<Department> userDeptList = new ArrayList<Department>();
		File file1 = new File ("output/employee.data");
		File file2 = new File ("output/department.data");
		
		Department general = new Department("General");
		userDeptList.add(general);
		

		//SCANNER MENUING
		Scanner scan = new Scanner(System.in);
		int input = 0;
		boolean loopCheck = true;
		while (loopCheck) {
			System.out.println("Welcome to the Employee Managment System!");
			System.out.println("What would you like to manage? (enter the corresponding number)");
			System.out.println(""
					+ "1 - Employee\n"
					+ "2 - Department\n"
					+ "3 - View\n"
					+ "4 - Load from DataSheet\n"
					+ "5 - Output to DataSheet\n"
					+ "6 - Exit");
			input = scan.nextInt();
			switch(input) {
			case 1:
				System.out.println("Employee Menu: What would you like to do?\n"
						+ "1 - Create New Employee\n"
						+ "2 - Change Employee Data\n"
						+ "3 - Remove Employee\n"
						+ "4 - Return to Main Menu\n"
						+ "5 - Exit");
				input = scan.nextInt();
				switch(input) {
				case 1:
					createEmpUI(userEmpList, userDeptList);
					break;
				case 2:
					changeEmpUI(userEmpList, userDeptList);
					break;
				case 3:
					removeEmpUI(userEmpList, userDeptList);
					break;
				case 4:
					break;
				case 5:
					scan.close();
					exitUI();
				}
				break;
			case 2:
				System.out.println("Department Menu: What would you like to do?\n"
						+ "1 - Create New Department\n"
						+ "2 - Change Department Data\n"
						+ "3 - Remove Department\n"
						+ "4 - Return to Main Menu\n"
						+ "5 - Exit");
				input = scan.nextInt();
				switch(input) {
				case 1:
					createDeptUI(userEmpList, userDeptList);
					break;
				case 2:
					changeDeptUI(userEmpList, userDeptList);
					break;
				case 3:
					removeDeptUI(userEmpList, userDeptList);
					break;
				case 4:
					//return to main menu
					break;
				case 5:
					scan.close();
					exitUI();
				}
				break;
			case 3: 
				System.out.println("View Menu: What would you like to view?\n"
						+ "1 - All Employees\n"
						+ "2 - All Departments\n"
						+ "3 - Return to Main Menu\n"
						+ "4 - Exit");
				input = scan.nextInt();
				switch(input) {
				case 1:
					displayEmpUI(userEmpList, userDeptList);
					break;
				case 2:
					displayDeptUI(userEmpList, userDeptList);
					break;
				case 3:
					//return to main menu
					break;
				case 4:
					scan.close();
					exitUI();
				}
				break;
			case 4:
				loadFromDatasheet(userEmpList, userDeptList);
				break;
			case 5:
				outputToDatasheet(userEmpList, userDeptList);
				break;
			case 6:
				scan.close();
				exitUI();
				break;
			default:
					
			}
		
		}
		
		
	}
	
	//when called - move any remaining employees to General department
	//create General department on startup?
	public static void removeDept(Department dept, List<Department> userDeptList) {
		Stream<Department> userDeptStream = userDeptList.stream();
		
		//filter out the employee
		List<Department> newUserDeptList = userDeptStream.filter(e -> e.getDeptID() != dept.getDeptID())
					.collect(Collectors.toList());
		
		//copy the list back to the department
		userDeptList = newUserDeptList;
	}
	
	public static void removeEmp(Employee employee, List<Employee> userEmpList) {
		employee.getDepartment().removeEmp(employee.getempID());
		Stream<Employee> userEmpStream = userEmpList.stream();
		
		//filter out the employee
		List<Employee> newUserEmpList = userEmpStream.filter(e -> e.getempID() != employee.getempID())
					.collect(Collectors.toList());
		
		//copy the list back to the department
		userEmpList = newUserEmpList;
	}
	
	public static void printEmployees(List<Employee> userEmpList) {
		for(Employee emp : userEmpList)
		System.out.println(emp);
	}
	
	public static void printDepartments(List<Department> userDeptList) {
		for(Department dept : userDeptList)
			System.out.println(dept);
	}
	
	public static void writeEmployee(Employee emp) {
		
		File file = new File("output/employee.data");

		try (FileOutputStream out = new FileOutputStream(file, true);
				ObjectOutputStream writer = new ObjectOutputStream(out)) {

			writer.writeObject(emp);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readEmployee(File file, List<Employee> userEmpList, List<Department> userDeptList) {
		try(FileInputStream in = new FileInputStream(file);
				ObjectInputStream reader = new ObjectInputStream(in)){
			
			Employee employee = (Employee) reader.readObject();
			
			userEmpList.add(employee);
			
			//System.out.println(employee.toString());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeDepartment(Department dept) {
		
		File file = new File("output/department.data");

		try (FileOutputStream out = new FileOutputStream(file, true);
				ObjectOutputStream writer = new ObjectOutputStream(out)) {

			writer.writeObject(dept);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readDepartment(File file, List<Employee> userEmpList, List<Department> userDeptList) {
		try(FileInputStream in = new FileInputStream(file);
				ObjectInputStream reader = new ObjectInputStream(in)){
			
			Department department = (Department) reader.readObject();
			
			userDeptList.add(department);
			
			//System.out.println(department.toString());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//USER INTERFACE METHODS
	
	
	public static void exitUI() {
		System.out.println("GoodBye!");
		
		System.exit(0);
	}
	
	public static void createEmpUI(List<Employee> userEmpList, List<Department> userDeptList) {
		Scanner scan = new Scanner(System.in);
		try {
			String fName;
			String lName;
			String position;
			int age;
			String address;
			int salary;
			String departmentName;
			Department department;
			System.out.println("Enter Employee Info here: \n"
					+ "What is the employee's first name?");
			fName = scan.next();
			System.out.println("What is the employee's last name?");
			lName = scan.next();
			System.out.println("What is the employee's position?");
			position = scan.next();
			System.out.println("What is the employee's age?");
			age = scan.nextInt();
			validate(age);
			System.out.println("What is the employee's address?");
			address = scan.next();
			System.out.println("What is the employee's salary?");
			salary = scan.nextInt();
			System.out.println("What is the employee's department?");
			departmentName = scan.next();
			//department integration not setup yet
			Employee emp = new Employee(fName, lName, position, age, address, salary, userDeptList.get(0));
			userEmpList.add(emp);
			System.out.println("Employee Created Successfully!");
				
			} catch (Exception m) {
				System.out.println("Exception occured " + m);
			}
		}
	
	
	public static void changeEmpUI(List<Employee> userEmpList, List<Department> userDeptList) {
		int empEdit = 0;
		String inputS = "";
		int input = 0;
		try {
			while (true){
				Scanner scan = new Scanner(System.in);
				System.out.println("Which employee's information would you like to edit?");
				for(int i = 0; i < userEmpList.size(); i++) {
					System.out.println((i + 1) + " - ID: " + userEmpList.get(i).getempID() + " Name: " + userEmpList.get(i).getfName() + " " + userEmpList.get(i).getlName());
				}
				System.out.println("0 - Cancel");
				empEdit = scan.nextInt() - 1;
				if(empEdit == -1)
					break;
				System.out.println("Editing " + userEmpList.get(empEdit).getfName() + " " + userEmpList.get(empEdit).getlName() + "'s Information. What would you like to modify?\n"
						+ "1 - First Name\n"
						+ "2 - Last Name\n"
						+ "3 - Position\n"
						+ "4 - Age\n"
						+ "5 - Address\n"
						+ "6 - Salary\n"
						+ "7 - Department\n"
						+ "8 - Cancel\n");
				input = scan.nextInt();
				switch (input) {
				case 1:
					System.out.println("What would the first name for employee #" + userEmpList.get(empEdit).getempID() + " be changed to?");
					inputS = scan.next();
					//trim the input????
					userEmpList.get(empEdit).setfName(inputS);
					System.out.println("First Name Changed successfully");
					break;
				case 2:
					System.out.println("What would the last name for employee #" + userEmpList.get(empEdit).getempID() + " be changed to?");
					inputS = scan.next(); 
					//trim the input????
					userEmpList.get(empEdit).setlName(inputS);
					System.out.println("Last Name Changed successfully");
					break;
				case 3:
					System.out.println("What would the position for employee #" + userEmpList.get(empEdit).getempID() + " be changed to?");
					inputS = scan.next(); 
					//trim the input????
					userEmpList.get(empEdit).setPosition(inputS);
					System.out.println("Position Changed successfully");
					break;
				case 4:
					System.out.println("What would the age for employee #" + userEmpList.get(empEdit).getempID() + " be changed to?");
					input = scan.nextInt(); 
					validate(input);
					userEmpList.get(empEdit).setAge(input);
					System.out.println("Age Changed successfully");
					break;
				case 5:
					System.out.println("What would the address for employee #" + userEmpList.get(empEdit).getempID() + " be changed to?");
					inputS = scan.next(); 
					//trim the input????
					userEmpList.get(empEdit).setAddress(inputS);
					System.out.println("Address Changed successfully");
					break;
				case 6:
					System.out.println("What would the salary for employee #" + userEmpList.get(empEdit).getempID() + " be changed to?");
					input = scan.nextInt(); 
					userEmpList.get(empEdit).setSalary(input);
					System.out.println("Salary Changed successfully");
					break;
				case 7:
					System.out.println("What would the department for employee #" + userEmpList.get(empEdit).getempID() + " be changed to?");
					inputS = scan.next(); 
					boolean match = false;
					int matchIndex = -1;
					for(int i = 0; i < userDeptList.size(); i++) {
						if(inputS.equals(userDeptList.get(i).getName())) {
							match = true;
							matchIndex = i;
						}
					}
					if(match) {
						userEmpList.get(empEdit).setDepartment(userDeptList.get(matchIndex));
					} else {
						System.out.println("Department does not exist.");
					}
					break;
				case 8:
					break;
				}
			}
		}catch(Exception m) {
			System.out.println("Exception occured" + m);
			
		}
		
	}

	public static void removeEmpUI(List<Employee> userEmpList, List<Department> userDeptList) {
		int empEdit = 0;
		int input = 0;
		while (true){
			Scanner scan = new Scanner(System.in);
			System.out.println("Which employee would you like to remove?");
			for(int i = 0; i < userEmpList.size(); i++) {
				System.out.println((i + 1) + " - ID: " + userEmpList.get(i).getempID() + " Name: " + userEmpList.get(i).fName + " " + userEmpList.get(i).lName);
			}
			System.out.println("0 - Cancel");
			empEdit = scan.nextInt() - 1;
			if(empEdit == -1)
				break;
			System.out.println("Employee [ID: " + userEmpList.get(empEdit).getempID() + " Name: " + userEmpList.get(empEdit).fName + " " + userEmpList.get(empEdit).lName + "] will be deleted.\n"
					+ "Confirm?\n"
					+ "1 - Yes\n"
					+ "2 - No");
			input = scan.nextInt();
			if(input == 1) {
				removeEmp(userEmpList.get(empEdit), userEmpList);
				System.out.println("Employee has been removed.");
				break;
			} else {
				System.out.println("Removal Canceled");
			}
		}
		
	}

	public static void createDeptUI(List<Employee> userEmpList, List<Department> userDeptList) {
		Scanner scan = new Scanner(System.in);
		/*
		 * int deptID;
		 * Employee deptHead;
		 * String name;
		 * int budget;
		 * int phone;
		 */
		int empID;
		String name;
		int budget;
		int phone;
		Department department;
		String input;
		System.out.println("Enter Department Info here: \n"
				+ "What is the department called?");
		name = scan.next();
		System.out.println("What is the budget?");
		budget = scan.nextInt();
		System.out.println("What is the department phone #?");
		phone = scan.nextInt();
		//department integration not setup yet
		Department dep = new Department(name, budget, phone);
		userDeptList.add(dep);
		System.out.println("Depatment Created Successfully!");
			
	}
	
	public static void changeDeptUI(List<Employee> userEmpList, List<Department> userDeptList) {
		int deptEdit = 0;
		String inputS = "";
		int input = 0;
		while (true){
			Scanner scan = new Scanner(System.in);
			System.out.println("Which department's information would you like to edit?");
			for(int i = 0; i < userDeptList.size(); i++) {
				System.out.println((i + 1) + " - ID: " + userDeptList.get(i).getDeptID() + " Name: " + userDeptList.get(i).name);
			}
			System.out.println("0 - Cancel");
			deptEdit = scan.nextInt() - 1;
			if(deptEdit == -1)
				break;
			System.out.println("Editing " + userDeptList.get(deptEdit).name + "'s Information. What would you like to modify?\n"
					+ "1 - Department Head\n"
					+ "2 - Department Name\n"
					+ "3 - Budget\n"
					+ "4 - Phone\n"
					);
			input = scan.nextInt();
			switch (input) {
			case 1:
				System.out.println("What would the department head for department #" + userDeptList.get(deptEdit).getDeptID() + " be changed to?");
				inputS = scan.next();
				//trim the input????
				// userDeptList.get(deptEdit).setDeptHead(inputS);
				System.out.println("Department Head Changed successfully");
				break;
			case 2:
				System.out.println("What would the name for department #" + userDeptList.get(deptEdit).getDeptID() + " be changed to?");
				inputS = scan.next();
				//trim the input????
				userDeptList.get(deptEdit).setName(inputS);
				System.out.println("Department name Changed successfully");
				break;
			case 3:
				System.out.println("What would the budget for department #" + userDeptList.get(deptEdit).getDeptID() + " be changed to?");
				input = scan.nextInt();
				//trim the input????
				userDeptList.get(deptEdit).setBudget(input);
				System.out.println("budget Changed successfully");
				break;
			case 4:
				System.out.println("What would the phone for department #" + userDeptList.get(deptEdit).getDeptID() + " be changed to?");
				input = scan.nextInt();
				userDeptList.get(deptEdit).setPhone(input);
				System.out.println("Phone Changed successfully");
				break;
			}
		}
	}
	
	public static void removeDeptUI(List<Employee> userEmpList, List<Department> userDeptList) {
		int deptEdit = 0;
		int input = 0;
		while (true){
			Scanner scan = new Scanner(System.in);
			System.out.println("Which department would you like to remove?");
			for(int i = 0; i < userDeptList.size(); i++) {
				System.out.println((i + 1) + " - ID: " + userDeptList.get(i).getDeptID() + " Name: " + userDeptList.get(i).name);
			}
			System.out.println("0 - Cancel");
			deptEdit = scan.nextInt() - 1;
			if(deptEdit == -1)
				break;
			System.out.println("Department [ID: " + userDeptList.get(deptEdit).getDeptID() + " Name: " + userDeptList.get(deptEdit).name + "] will be deleted.\n"
					+ "Confirm?\n"
					+ "1 - Yes\n"
					+ "2 - No");
			input = scan.nextInt();
			if(input == 1) {
				removeDept(userDeptList.get(deptEdit), userDeptList);
				System.out.println("Department has been removed.");
				break;
			} else {
				System.out.println("Removal Canceled");
			}
		}
	}
	
	public static void displayEmpUI(List<Employee> userEmpList, List<Department> userDeptList) {
		int input = 0;
		Scanner scan = new Scanner(System.in);
		printEmployees(userEmpList);
		while(true) {
			System.out.println("\nPress 1 to return to menu.");
			input = scan.nextInt();
			if(input == 1)
				break;
		}
	}
	
	public static void displayDeptUI(List<Employee> userEmpList, List<Department> userDeptList) {
		int input = 0;
		Scanner scan = new Scanner(System.in);
		printDepartments(userDeptList);
		while(true) {
			System.out.println("\nPress 1 to return to menu.");
			input = scan.nextInt();
			if(input == 1)
				break;
		}
		
	}
	
	public static void outputToDatasheet(List<Employee> userEmpList, List<Department> userDeptList) {
		Scanner scan = new Scanner(System.in);
		int input = 0;
		System.out.println("What would you like to output to file?\n"
				+ "1 - All Employees\n"
				+ "2 - All Departments\n"
				+ "3 - Both\n"
				+ "4 - Exit");
		input = scan.nextInt();
		switch (input) {
		case 1:
			for(int i = 0; i < userEmpList.size(); i++) {
				writeEmployee(userEmpList.get(i));
			}
			System.out.println("Employees successfully written to employees.data");
			break;
		case 2:
			for(int i = 0; i < userDeptList.size(); i++) {
				writeDepartment(userDeptList.get(i));
			}
			System.out.println("Departments successfully written to department.data");
			break;
		case 3:
			for(int i = 0; i < userEmpList.size(); i++) {
				writeEmployee(userEmpList.get(i));
			}
			System.out.println("Employees successfully written to employees.data");
			for(int i = 0; i < userDeptList.size(); i++) {
				writeDepartment(userDeptList.get(i));
			}
			System.out.println("Departments successfully written to department.data");
			break;
		case 4:
			break;
		}
	}
	
	public static void loadFromDatasheet(List<Employee> userEmpList, List<Department> userDeptList) {
		Scanner scan = new Scanner(System.in);
		int input = 0;
		File file1 = new File ("output/employee.data");
		File file2 = new File ("output/department.data");
		System.out.println("What would you like load from file?\n"
				+ "1 - All Employees\n"
				+ "2 - All Departments\n"
				+ "3 - Both\n"
				+ "4 - Exit");
		input = scan.nextInt();
		switch (input) {
		case 1:
			readEmployee(file1, userEmpList, userDeptList);
			System.out.println("Employees successfully imported from employees.data");
			break;
		case 2:
			readEmployee(file2, userEmpList, userDeptList);
			System.out.println("Departments successfully imported from department.data");
			break;
		case 3:
			readEmployee(file1, userEmpList, userDeptList);
			System.out.println("Employees successfully imported from employees.data");
			readEmployee(file2, userEmpList, userDeptList);
			System.out.println("Departments successfully imported from department.data");
			break;
		case 4:
			break;
		}
	}
	
	static void validate(int age) throws AgeException{
		if(age<18) {
			throw new AgeException("not a valid age");
		}
		else {
			System.out.println("Employee age validated");
		}
	}
}

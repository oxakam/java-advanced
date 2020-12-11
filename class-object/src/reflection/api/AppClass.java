package reflection.api;

import java.util.Arrays;

class User {	
	public String userName;
}

class Employee extends User {
	public int empId;
	private String empAddr;
	
	private void calculate() {		
	}
}

public class AppClass {

	public static void main(String[] args) throws ClassNotFoundException {

		Class<Employee> clazz = Employee.class;	
		
		System.out.println(Arrays.asList(clazz.getFields()));			// accessible fields in object - empId, empId
		System.out.println(Arrays.asList(clazz.getDeclaredFields()));	// fields declared in the object - empId, empAddr			
		System.out.println();

		Arrays.asList(clazz.getMethods()).forEach(System.out::println);				// all methods for object
		System.out.println();

		Arrays.asList(clazz.getDeclaredMethods()).forEach(System.out::println); 	// declared methods for object

		
/*		Class<Employee> cls1 = Employee.class;	
		Class<?> cls2 = Class.forName("reflection.api.Employee");					
		User u = new Employee();
		Class<? extends User> cls3 = u.getClass();	
		/// all cls = class reflection.api.Employee	
*/
	}

}

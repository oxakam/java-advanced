package reflection.api;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;

class Users {	
	public int id;
}

class Employees extends Users {
	private String name;
	private String address;
	
	private void setUpdated (Date updated) {		
	}
	
	private boolean setNum (int num) {
		return true;
	}

	private boolean doCalculate (int number) {
		number *= number;
		System.out.println("doCalculate: " + number);
		return true;
	}

	@Override
	public String toString() {
		return "Employees [name=" + name + ", address=" + address + "]";
	}	
}

public class AppClassFields {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, 
											      NoSuchMethodException, IllegalAccessException, 
											      IllegalArgumentException, InvocationTargetException {

		Class<Employees> clazz = Employees.class;	
	
		var nameField = clazz.getDeclaredField("name");
		System.out.println(nameField);
		
		var setUpdateMethod = clazz.getDeclaredMethod("setUpdated", Date.class);
		System.out.println(setUpdateMethod);
		
		var setNumMethod = clazz.getDeclaredMethod("setNum", int.class);
		System.out.println(setNumMethod);
		
		var methodExists = Arrays.stream(clazz.getDeclaredMethods())
				                              .anyMatch(m -> m.getName().equals("setUpdated"));
		System.out.println(methodExists);
		
		var doCalcMethod = clazz.getDeclaredMethod("doCalculate", int.class);
		doCalcMethod.setAccessible(true);		//get access if doCalcMethod is private
		doCalcMethod.invoke(new Employees(), 5);
		
		
		//setting fields
		var getNameField = clazz.getDeclaredField("name");		
		var user = new Employees();
		
		getNameField.setAccessible(true);
		getNameField.set(user, "Venus");
		
		System.out.println(user);
	}
}

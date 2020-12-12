package annotations;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Repository<T> {
	
	public void save(T t) {
		
		var clazz = t.getClass();
		var classAnnotations = clazz.getAnnotationsByType(Entity.class);
		
		var tableName = clazz.getSimpleName().toLowerCase();
		
		if (classAnnotations.length > 0  &&  classAnnotations[0].value().length() > 0) {
			
			tableName = classAnnotations[0].value();
		}		
				
		var fields = clazz.getDeclaredFields();
		
		ArrayList<String> fieldsList = new ArrayList<>();
		
		for(var field : fields) {
			
			var annotations = field.getAnnotationsByType(Field.class);
			
			if(annotations.length == 0) {	//if no fields on annotation we not going to save it to database
				continue;					
			}
			var annotation = annotations[0];
			
			var fieldName = annotation.columnName();
			var isKey = annotation.isKey();

			if(fieldName.length() == 0) {
				fieldName = field.getName();
			}
			
			if (!isKey) {
				fieldsList.add(fieldName);
			}			
		}	
		
		String sqlFields = fieldsList.stream().collect(Collectors.joining(","));
		
		String sqlPlaceholders = fieldsList.stream().map(s -> "?").collect(Collectors.joining(",")); 	
	
		String sql = String.format("insert into %s (%s) values (%s)", tableName, sqlFields, sqlPlaceholders);
		
		System.out.println(sql); //insert into user (name,password) values (?,?)
		
		
//		// @formatter:off
//			
//		var fieldList = Arrays					//list of @Field fields
//			.stream(clazz.getDeclaredFields())
//			.filter(f -> f.getAnnotationsByType(Field.class).length > 0)
//			.collect(Collectors.toList());  
//		
//		// @formatter:on

	}
}

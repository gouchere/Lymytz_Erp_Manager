package lym.com.api.model.builder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.repository.jdbc.Colonnes;

public class CustomerEntityBuilder {
	public CustomerEntityBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<LtzCtlCustomer> buildCustomers(List<Object[]> datas,Colonnes[] colonnes ){
		List<LtzCtlCustomer> result=new ArrayList<>();
		for(Object[] data:datas) {
			result.add(buildFromObject(data, colonnes));
		}
		return result;
	}

	public static LtzCtlCustomer buildFromObject(Object[] line, Colonnes[] colonnes) {
		LtzCtlCustomer re = new LtzCtlCustomer();
		if (line != null) {
			int i=0;
			for(Object o:line) {
				System.err.println(colonnes[i].getType());
				getByName(re, colonnes[i].getName(), o, getClass(colonnes[i].getType()));
				i++;
			}

		}
		return re;
	}

	private static LtzCtlCustomer getByName(LtzCtlCustomer y, String columnName, Object value, Class type) {
		Class<? extends LtzCtlCustomer> c = y.getClass();
		for (Field f : c.getDeclaredFields()) {
			f.setAccessible(true);
			String champ=hasColumnOrJoinColumnByName(f, columnName);
			if(champ!=null) {
				Method method;
				try {
					method = c.getMethod("set"+champ.substring(0, 1).toUpperCase()+champ.substring(1), type);
					method.setAccessible(true);
					method.invoke(y, value);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
			}
		}
		return y;
	}

	private static String hasColumnOrJoinColumnByName(Field field, String columnName) {
		if (field != null) {
			try {
				Column an0 = (Column) field.getAnnotation(Column.class);
				JoinColumn an1 = (JoinColumn) field.getAnnotation(JoinColumn.class);
				if (an0 != null || an1 != null) {
					if (an0 != null) {
						if (an0.name().equals(columnName)) {
							return field.getName();
						}
					} else {
						if (an1.name().equals(columnName)) {
							return field.getName();
						}
					}
				}
			} catch (SecurityException ex) {
				Logger.getLogger(CustomerEntityBuilder.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}
	
	private static Class getClass(String sqlType) {
		switch (sqlType) {
		case "character varying":
		case "varchar":			
			return String.class;
		case "double precision":
		case "double":			
			return Double.class;
		case "integer":
		case "serial":			
		case "int":			
			return Integer.class;
		case "long":
		case "bigint":			
		case "bigserial":			
			return Long.class;
		case "bool":			
		case "boolean":			
			return Boolean.class;
		case "timestamp":			
		case "date":			
			return Date.class;

		default:
			return Object.class;
		}
	}

}

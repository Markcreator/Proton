package me.Markcreator.Proton.util;

import java.lang.reflect.Method;

public class ReflectionUtils {

	public static void invoke(Object obj, String methodStr, Object... args) {
		try {
			Method method = obj.getClass().getMethod(methodStr, getTypes(args));
			method.invoke(obj, args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Class<?>[] getTypes(Object... objs) {
		Class<?>[] types = new Class<?>[objs.length];
		
			for(int i = 0; i < objs.length; i++) {
				// Get primitive class or object class
				Class<?> type;
				try {
					type = (Class<?>) objs[i].getClass().getField("TYPE").get(null);
				} catch (Exception e) {
					type = objs[i].getClass();
				}
				
				types[i] = type;
			}
		
		return types;
	}
}

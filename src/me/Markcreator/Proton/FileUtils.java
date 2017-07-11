package me.Markcreator.Proton;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileUtils {

	public static String readFile(String file) {
		try {
		    BufferedReader reader = new BufferedReader(new FileReader (file));
		    String         line = null;
		    StringBuilder  stringBuilder = new StringBuilder();
		    String         ls = System.getProperty("line.separator");
	
		    try {
		        while((line = reader.readLine()) != null) {
		            stringBuilder.append(line);
		            stringBuilder.append(ls);
		        }
	
		        return stringBuilder.toString();
		    } finally {
		        reader.close();
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

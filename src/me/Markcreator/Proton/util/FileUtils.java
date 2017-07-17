package me.Markcreator.Proton.util;

public class FileUtils {

	public static String getResourcePath(Class<?> root, String path) {
		return root.getResource(path).toExternalForm();
	}
	
}

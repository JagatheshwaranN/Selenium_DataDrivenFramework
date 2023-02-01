package com.jtaf.w2a.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileReader {

	public static Properties properties = new Properties();
	public static File file;
	public static FileInputStream fileInputStream;
	public static String objectRepoPath = "\\src\\test\\resources\\properties\\objectrepo.properties";
	public static String configPath = "\\src\\test\\resources\\properties\\config.properties";

	public static void loadPropertyFiles() {

		file = new File(System.getProperty("user.dir") + configPath);
		try {
			fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getDataFromPropFile(String key) {
		String data = null;
		if (key != null && properties != null) {
			data = properties.getProperty(key).trim();
		}
		return data;

	}

	public static void main(String[] args) {
		loadPropertyFiles();
		System.out.println(getDataFromPropFile("browser"));
	}
}
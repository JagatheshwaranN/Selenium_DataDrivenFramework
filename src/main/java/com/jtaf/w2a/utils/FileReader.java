package com.jtaf.w2a.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileReader {

	public static Properties properties = new Properties();
	public static File configFileObj, ObjectRepoFileObj;
	public static FileInputStream configfileInputStream, ObjectRepofileInputStream;
	public static String objectRepoPath = "\\src\\test\\resources\\properties\\objectrepo.properties";
	public static String configPath = "\\src\\test\\resources\\properties\\config.properties";

	public static void loadPropertyFiles() {

		configFileObj = new File(System.getProperty("user.dir") + configPath);
		ObjectRepoFileObj = new File(System.getProperty("user.dir") + objectRepoPath);
		try {
			configfileInputStream = new FileInputStream(configFileObj);
			ObjectRepofileInputStream = new FileInputStream(ObjectRepoFileObj);
			properties.load(configfileInputStream);
			properties.load(ObjectRepofileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getDataFromPropFile(String key) {
		String dataFromPropFile = null;
		if (key != null && properties != null) {
			dataFromPropFile = properties.getProperty(key).trim();
		}
		return dataFromPropFile;
	}

	public static void main(String[] args) {
		loadPropertyFiles();
		System.out.println(getDataFromPropFile("url"));
		System.out.println(getDataFromPropFile("BankManagerLogin"));
	}
}

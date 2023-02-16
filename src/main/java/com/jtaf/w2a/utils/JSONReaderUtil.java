package com.jtaf.w2a.utils;

import java.io.File;
import java.io.IOException;

import com.jayway.jsonpath.JsonPath;

public class JSONReaderUtil {

	private File file;
	private String fileName;

	public JSONReaderUtil(String fileName) {
		this.fileName = fileName;
		file = new File(this.fileName);
	}

	public String getLocatorFromJSON(String path) {
		String data = null;
		try {
			data = JsonPath.read(file, "$." + path).toString().trim();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return data;
	}
}

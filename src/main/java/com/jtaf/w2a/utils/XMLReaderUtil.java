package com.jtaf.w2a.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLReaderUtil {

	private Document doc;
	private String fileName;
	private String data;

	public XMLReaderUtil(String fileName) {
		this.fileName = fileName;
	}

	public String getLocatorFromXML(String path) {
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(fileName);
		} catch (DocumentException ex) {
			ex.printStackTrace();
		}
		data = doc.selectSingleNode("//" + path.replace(".", "/")).getText().trim();
		return data;
	}
}

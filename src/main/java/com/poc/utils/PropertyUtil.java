/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2016                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.poc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
		
	public static String getPropertyFromFile(String sEnv,String sKey) throws IOException {
		
		Properties prop = new Properties();
		String sPropVal;		
		
		File file = new File(FileLoader.getFilePath("env/", sEnv + ".properties"));
				
		if (!file.exists())	{
			System.err.println("FATAL ERROR: " + sEnv+".properties" + " does not exist...");
		}
		
		try{
			loadFromFile(prop, file);
			sPropVal = prop.getProperty(sKey);
		}
		catch(Exception e){
			sPropVal = "";
		}		
		return sPropVal;		
	}
	
	private static void loadFromFile(Properties p, File file) throws IOException {
		if (p == null) {
			return;
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			p.load(fis);
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}
}

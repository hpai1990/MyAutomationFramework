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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileLoader {
	
	public static String getFilePath(String filepath,String filename){
		StringBuilder fileurl = new StringBuilder(filepath).append(filename);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		if(classloader.getResourceAsStream(fileurl.toString()).toString().contains("jar")){//used when file inside a jar
			try {
	            InputStream input = classloader.getResourceAsStream(fileurl.toString());
	            File file = new File(new StringBuilder(System.getenv("HOME")).append("/").append(filename).toString());
	            OutputStream out = new FileOutputStream(file);
	            int read;
	            byte[] bytes = new byte[1024];

	            while ((read = input.read(bytes)) != -1) {
	                out.write(bytes, 0, read);
	            }
	            out.close();
	            return (file.getAbsolutePath());
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            return null;
	        }
		}
		else{
			return classloader.getResource(fileurl.toString()).getFile();
		}
	}

}

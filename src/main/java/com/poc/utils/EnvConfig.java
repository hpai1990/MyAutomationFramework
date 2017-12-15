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


import java.io.IOException;

public class EnvConfig {
	
	private static String sEnv;
	
	public static String hostURL;
	public static String hostPort;
	public static String hostProtocol;
	public static StringBuilder hostBaseURL; 
	
	
	static{
		sEnv = System.getenv("TEST_ENVIRONMENT");
				
		try {
			hostURL = PropertyUtil.getPropertyFromFile(sEnv, "API_HOST");
			hostPort = PropertyUtil.getPropertyFromFile(sEnv, "API_PORT");
			hostProtocol = PropertyUtil.getPropertyFromFile(sEnv, "API_PROTOCOL");
			hostBaseURL = new StringBuilder(hostProtocol).append("://").append(hostURL).append(":").append(hostPort);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

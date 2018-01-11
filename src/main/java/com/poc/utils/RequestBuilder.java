package com.poc.utils;

import java.util.Map;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * @author hrishikesh_pai
 * Object Model for api request specification which stores api url,headers etc.	
 */
public class RequestBuilder {
	
	private String apiPath;
	private RequestSpecBuilder builder;
	private RequestSpecification spec;
	
	public RequestBuilder() {
		builder = new RequestSpecBuilder();
	}
	
	public RequestSpecification getRequestSpecification(){
		return spec;
	}
	
	public void buildRequestSpecification(){
        spec = builder.build();
	}
	
	public void addRequestHeader(String name,String value){
		builder.addHeader(name,value);
	}
	
	public void addRequestHeaders(Map<String,String> headers){
		builder.addHeaders(headers);
	}
	
	public void setRequestBasePath(String apiPath){
		this.apiPath = apiPath;
	}
	
	public String getRequestBasePath(){
		return apiPath;
	}
	
	
}

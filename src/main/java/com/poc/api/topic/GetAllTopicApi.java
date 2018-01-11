package com.poc.api.topic;

import com.poc.rest.constants.TestConstants;
import com.poc.utils.RequestBuilder;

public class GetAllTopicApi {
	
	private RequestBuilder request;
	private final String basePath = "/topics";
		
	public void buildRequest() {
		
		request = new RequestBuilder();
		request.setRequestBasePath(basePath);
		request.addRequestHeader(TestConstants.header_contentType, TestConstants.header_contentType_json);
		
		request.buildRequestSpecification();
		
	}
	
	public RequestBuilder getRequestSpecification(){
		return request;
	}

}

package com.poc.api.test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.api.metadata.beans.TopicApiBeans;
import com.poc.api.topic.GetAllTopicApi;
import com.poc.api.topic.PostTopicApi;
import com.poc.rest.constants.TestConstants;
import com.poc.rest.executor.RestExecutor;
import com.poc.rest.initializer.TestInitializer;
import com.poc.utils.FileLoader;






@Feature("Test feature")
@Story("Backend Springboot app 1")


public class TopicApiTests extends TestInitializer {
	GetAllTopicApi test1 = new GetAllTopicApi();
	PostTopicApi test2 = new PostTopicApi();
		
	
	@BeforeClass(groups = {"bvt","regression"})
	public void apiSetup() throws Exception{
	   
		
		test1.buildRequest();
		test2.createRequest();
	}
	
	
	
	
	
	@Test(groups = {"bvt","regression"} , priority=7 ,dataProvider="testDataProvider")
	
	public void test007(Map <String,String> params){
		
			
	
		RestExecutor.get(test1.getRequestSpecification())
					.expectCode(200)
					.expectHeader(TestConstants.header_contentType, TestConstants.header_contentType_json)
					.expectInJSONResponse("Dying Light","[0].name");
		
		
	}
	
	@Test(groups = {"bvt","regression"} ,priority=6 , dataProvider="testDataProvider")
	
	public void test008(Map <String,String> params) throws JsonParseException, JsonMappingException, IOException{
		
	    
		/*TopicApiBeans topic = new TopicApiBeans();
		topic.setId("666");
		topic.setName("doom");
		topic.setDescription("the bells of vashundol rings twice");*/
	    ObjectMapper mapper = new ObjectMapper();
	   //TopicApiBeans topic = new TopicApiBeans();
	    TopicApiBeans topic = mapper.readValue(new File(FileLoader.getFilePath("jsontemplates/", "topics.json")), TopicApiBeans.class);
		topic.setId(params.get("id"));
	    
		
		RestExecutor.post(test2.getRequestSpecification(),mapper.writeValueAsString(topic))
				.expectCode(201)
				.expectHeader(TestConstants.header_applicationContext, TestConstants.header_applicationContext_dev);
		
		
		
		
	}

}

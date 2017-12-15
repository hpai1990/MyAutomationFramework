package com.poc.api.test;

import static org.testng.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.io.IOException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.poc.api.metadata.TopicApiTasks;
import com.poc.api.metadata.beans.TopicApiBeans;





@Epic("POC Regression Test")
@Feature("Backend Springboot app")

public class TopicApiTests {
	
	@Test(groups = {"bvt","regression"} , description="Validate Create Topic Api" , priority=0)
	@Story("POC Backend api tests")
	public void test001() throws FileNotFoundException, IOException, ParseException{
		
		Response response;
		TopicApiTasks newtopictask = new TopicApiTasks();
		TopicApiBeans newtopicbean = new TopicApiBeans();
		
		newtopicbean.setTopicId("123");
		newtopicbean.setTopicName("Test POC Topic");
		newtopicbean.setTopicDescription("Sample description for new poc topic");
		
		newtopictask.jsonBuilder(newtopicbean);
		response = newtopictask.createTopic();
		
		assertEquals(response.getStatusCode(), 201);
	}
	
	@Test(groups = {"regression"} , description="Validate Update Topic Api", priority=1)
	@Story("POC Backend api tests")
	public void test002() throws FileNotFoundException, IOException, ParseException{
		
		Response response;
		TopicApiTasks newtopictask = new TopicApiTasks();
		TopicApiBeans newtopicbean = new TopicApiBeans();
		
		newtopicbean.setTopicId("123");
		newtopicbean.setTopicName("Test POC Topic");
		newtopicbean.setTopicDescription("Updated Sample description for new poc topic");
		
		newtopictask.jsonBuilder(newtopicbean);
		response = newtopictask.updateTopic(newtopicbean);
		
		assertEquals(response.getStatusCode(), 204);
	}
	
	@Test(groups = {"bvt","regression"} , description="Validate Get All Topics Api", priority=2)
	@Story("POC Backend api tests")
	public void test003() throws FileNotFoundException, IOException, ParseException{
		
		Response response;
		TopicApiTasks newtopictask = new TopicApiTasks();
		
		response = newtopictask.getAllTopics();
		
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(groups = {"regression"} , description="Validate Get Topics By ID Api", priority=3)
	@Story("POC Backend api tests")
	public void test004() throws FileNotFoundException, IOException, ParseException{
		
		Response response;
		TopicApiTasks newtopictask = new TopicApiTasks();
		TopicApiBeans newtopicbean = new TopicApiBeans();
		
		newtopicbean.setTopicId("123");
		response = newtopictask.getTopicById(newtopicbean);
		
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(groups = {"regression"} , description="Validate Get Topics By Name Api", priority=4)
	@Story("POC Backend api tests")
	public void test005() throws FileNotFoundException, IOException, ParseException{
		
		Response response;
		
		TopicApiTasks newtopictask = new TopicApiTasks();
		TopicApiBeans newtopicbean = new TopicApiBeans();
		
		newtopicbean.setTopicName("Test POC Topic");
		response = newtopictask.getTopicByName(newtopicbean);
		
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(groups = {"bvt","regression"} , description="Validate Delete Topic Api", priority=5)
	@Story("POC Backend api tests")
	public void test006() throws FileNotFoundException, IOException, ParseException{
		
		Response response;
		TopicApiTasks newtopictask = new TopicApiTasks();
		TopicApiBeans newtopicbean = new TopicApiBeans();
		
		newtopicbean.setTopicId("123");
		response = newtopictask.deleteTopic(newtopicbean);
		
		assertEquals(response.getStatusCode(), 204);
	}

}

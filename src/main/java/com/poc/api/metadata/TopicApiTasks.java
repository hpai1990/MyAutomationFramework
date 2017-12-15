package com.poc.api.metadata;


import static io.restassured.RestAssured.given;
import java.io.FileNotFoundException;
import java.io.IOException;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import com.poc.api.metadata.beans.TopicApiBeans;
import com.poc.utils.EnvConfig;
import com.poc.utils.JsonTemplateReader;



public class TopicApiTasks {
	
	private static final String API_PATH= "/topics";
	private static final StringBuilder ABS_API_PATH = new StringBuilder(EnvConfig.hostBaseURL).append(API_PATH);
	private String jsonfile = "Topics.json";
	private Response apiResponse;
	public JSONObject testjson;
	
	
	
	@SuppressWarnings("unchecked")
	public void jsonBuilder(TopicApiBeans topic) throws FileNotFoundException, IOException, ParseException {

		testjson = JsonTemplateReader.getJsonTemplate(jsonfile);
		
		testjson.put("id", topic.getTopicId());
		testjson.put("name", topic.getTopicName());
		testjson.put("description", topic.getTopicDescription());
		
	}
	
	@Step("Execute Create Topic Api")
	public Response createTopic() {
		apiResponse = 
				given().
					filter(new AllureRestAssured()).
					contentType("application/json").
					body(testjson.toString()).
					config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).
				when().
					post(ABS_API_PATH.toString()).
					then().
				extract().response();

		
		return apiResponse;
	}
	
	@Step("Execute Update Topic Api")
	public Response updateTopic(TopicApiBeans topic) {
		
		String id=topic.getTopicId();
		StringBuilder API_Id_Path = new StringBuilder(ABS_API_PATH.toString()).append("/").append(id);
		
		apiResponse = 
				given().
					filter(new AllureRestAssured()).
					contentType("application/json").
					body(testjson.toString()).
					config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).
				when().
					put(API_Id_Path.toString()).
					then().
				extract().response();
		
		return apiResponse;
	}
	
	@Step("Execute Get all Topic Api")
	public Response getAllTopics() {
		apiResponse = 
				given().
					filter(new AllureRestAssured()).
					contentType("application/json").
					config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).
				when().
					get(ABS_API_PATH.toString()).
					then().
				extract().response();
		
		return apiResponse;
	}
	
	@Step("Execute Delete Topic Api")
	public Response deleteTopic(TopicApiBeans topic) {
		
		String id=topic.getTopicId();
		StringBuilder API_Id_Path = new StringBuilder(ABS_API_PATH.toString()).append("/").append(id);

		apiResponse = 
				given().
					filter(new AllureRestAssured()).
					contentType("application/json").
					config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).
				when().
					delete(API_Id_Path.toString()).
					then().
				extract().response();
		
		return apiResponse;
	}
	
	@Step("Execute Get Topic By Id Api")
	public Response getTopicById(TopicApiBeans topic) {
		String id=topic.getTopicId();
		StringBuilder API_Id_Path = new StringBuilder(ABS_API_PATH.toString()).append("/").append(id);
		
		apiResponse = 
				given().
					filter(new AllureRestAssured()).
					contentType("application/json").
					config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).
				when().
					get(API_Id_Path.toString()).
					then().
				extract().response();
		
		return apiResponse;
	}

	@Step("Execute Get Topic By Name Api")
	public Response getTopicByName(TopicApiBeans topic) {
		
		String API_Name_Path= "/topics?name=";
		String name=topic.getTopicName();
		StringBuilder ABS_API_PATH = new StringBuilder(EnvConfig.hostBaseURL).append(API_Name_Path).append(name);
		
		apiResponse = 
				given().
					filter(new AllureRestAssured()).
					contentType("application/json").
					config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).
				when().
					get(ABS_API_PATH.toString()).
					then().
				extract().response();
		
		return apiResponse;
	}
}

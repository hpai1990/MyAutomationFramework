package com.poc.api.metadata.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TopicApiBeans {
	
	private String id;
	private String name;
	private String description;
	
	public void setTopicId(String id){
		if(id == null || id.isEmpty())
			this.id = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		else
			this.id = id;
	}
	
	public String getTopicId(){
		return id;
	}
	
	public void setTopicName(String name){
		if(name == null || name.isEmpty())
			this.name = "Test Topic";
		else
			this.name = name;
	}
	
	public String getTopicName(){
		return name;
	}
	
	public void setTopicDescription(String description){
		if(description == null || description.isEmpty())
			this.description = "Sample Test Topic";
		else
			this.description = description;
	}
	
	public String getTopicDescription(){
		return description;
	}

}

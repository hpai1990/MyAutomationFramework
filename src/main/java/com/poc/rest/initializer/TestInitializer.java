package com.poc.rest.initializer;

import java.io.IOException;
import java.lang.reflect.Method;
import io.restassured.RestAssured;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import com.poc.api.dataprovider.XMLDataProvider;
import com.poc.utils.EnvConfig;

public class TestInitializer{
    
    private XMLDataProvider testData = new XMLDataProvider();
   

    
    @BeforeSuite(groups = {"bvt","regression"})
	public void initializeTest() throws IOException{
        RestAssured.baseURI = EnvConfig.hostBaseURL.toString();
	}
    
    @DataProvider(name="testDataProvider",parallel = true)
    public Object[][] xmlProvider(Method method) throws Exception {
        return testData.retrieveTestData(method);
    }
    
   
    
   
    
    
    @BeforeClass(alwaysRun = true)
    public void getTestdata() throws Exception {
        testData.loadXMLTestDataFile(this.getClass().getSimpleName());
        System.out.println("loaded test data provider");
    }

    


    

}

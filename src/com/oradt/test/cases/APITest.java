package com.oradt.test.cases;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oradt.test.core.TestDataHandler;
import com.oradt.test.tasks.APITask;

public class APITest  extends BaseTestCase {
	public APITask apiTask = new APITask();
	
	@BeforeClass
	public void doBeforeClass() {
		for(int i = 1; i <= 7; i+=2){
			
		}
	}
	
	@Test(dataProvider = "menu1")
	public void sendGet(Map<String, String> data){	
		apiTask.sendGetREQ(data);
//		System.out.println(": : : : : : : : " + data.get("api002"));
		
	}
	
	@DataProvider(name = "menu")
	public Iterator<Object[]> dataforAPI(Method method) throws IOException {
		return new TestDataHandler("API", "apiList", 5);
	}
	
	@DataProvider(name = "menu1")
	public Iterator<Object[]> dataforAPI1(Method method) throws IOException {
		return new TestDataHandler("API", "url", 1);
	}
	
	@AfterClass
	public void doAfterClass() {		
	}
}

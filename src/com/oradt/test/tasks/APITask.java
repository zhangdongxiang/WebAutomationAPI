package com.oradt.test.tasks;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.oradt.test.core.APIFunc;
import com.oradt.test.core.GlobalSettings;

public class APITask {
	private static String url = GlobalSettings.baseURL;
	
	private static Logger log = Logger.getLogger(APITask.class.getName());
    private static APIFunc apiFun;
	public void sendGetREQ(Map<String, String> data)
	{
		log.info ("Open the api: '" + data.get("API001") + "'");
		String str = APIFunc.sendPost(data.get("API001"), "mobile=8612011111111&passwd=123456");
		System.out.println("**********************************************************");
		System.out.println(str);
		APIFunc.parseJson(str);
		System.out.println("**********************************************************");
//		APIFunc.markCaseFail();
	}
	
}

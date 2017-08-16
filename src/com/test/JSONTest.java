/**   
* @Title: JSONTest.java 
* @Package com.test 
* @Description: TODO(描述JSONTest类) 
* @author zx583   
* @date 2017年4月27日 下午7:58:24 
* @version V1.0   
*/
package com.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/** 
* @ClassName: JSONTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zx583 
* @date 2017年4月27日 下午7:58:24 
*  
*/
public class JSONTest {
	@Test
	public void addTest() {
		JSONObject data = new JSONObject();
		
	}
	
	@Test
	public void arrayToob() throws JSONException {
		String times = "[{\"register_date\":\"2017-04-28\",\"start_time\":\"2017-04-28 17:24\",\"end_time\":\"2017-04-28 17:24\"},{\"register_date\":\"2017-04-28\",\"start_time\":\"2017-04-28 17:28\",\"end_time\":\"2017-04-28 17:28\"}]";
		JSONArray array = new JSONArray(times);
		System.out.println(array);
		JSONObject object = array.getJSONObject(0);
		System.out.println(object);
	}
}

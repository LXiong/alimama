package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Test {
	
	public String name="4";
	
	public static void main(String[] args) {
		Test object = new Test();
		String str = JSONObject.toJSONString(object);
		System.out.println(str);
		
		Object o=JSONObject.parseObject(str, Test.class);
		System.out.println(o.getClass());
	
		o=JSON.parse(str);
				System.out.println(o.getClass());
		
		
		Test t = (Test)o;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}

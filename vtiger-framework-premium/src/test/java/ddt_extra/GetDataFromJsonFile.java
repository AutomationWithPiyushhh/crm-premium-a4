package ddt_extra;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetDataFromJsonFile {
	public static void main(String[] args) throws IOException, ParseException {
//		step 1> create the java representation object of the physical file
		FileReader fr = new FileReader("./src/test/resources/commondata.json");

//		create the object of JSONParser and call parse 
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fr);

//		downcast it to jsonobject
		JSONObject jObj = (JSONObject) obj;
		String browser = jObj.get("bro").toString();
		String url = jObj.get("url").toString();
		String username = jObj.get("un").toString();
		String password = jObj.get("pwd").toString();

		System.out.println(browser);
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
	}
}

package ddt_extra;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetDataFromPropertiesFile {
	public static void main(String[] args) throws IOException {
//		step 1 > get the JRO of the physical file
		FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties");

//		step 2> by using load() of Properties <<C>>, load all the keys
		Properties pObj = new Properties();
		pObj.load(fis);

//		step 3> by using getProperty() and passing the key, get the value
		String UN = pObj.getProperty("un");
		System.out.println("username is : " + UN);
	}
}

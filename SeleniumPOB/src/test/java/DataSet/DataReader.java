package DataSet;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.*;
public class DataReader {
	
//	public List<HashMap<String, String>> dataRader() throws IOException
//	{
////		covert json to String format
//		 String jsonData=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\DataSet\\OrderEntry.json"),StandardCharsets.UTF_8);
//		 
////		 covert String to hashMap
//		 ObjectMapper mapper=new ObjectMapper();
//		 List<HashMap<String,String>> data=mapper.readValue(jsonData,new TypeReference<List<HashMap<String,String>>>(){});
//		 return data;

	  
	 
//	}

	public List<HashMap<String,String>> dataReader() throws IOException
	{
//		convert json to string
		String jsonData=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\DataSet\\OrderEntry.json"),StandardCharsets.UTF_8);
		
//		convert string to hashmap
		ObjectMapper mapper=new ObjectMapper();
		
		List<HashMap<String,String>> Data=mapper.readValue(jsonData,new TypeReference<List<HashMap<String,String>>>() {});
		return Data;
		
		
		
		
	}
	
	
}
 
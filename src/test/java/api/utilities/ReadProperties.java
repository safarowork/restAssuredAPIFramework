package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	public static Properties readPropertiesConig() throws IOException
	{
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/routes.properties");
		prop.load(fis);
		
		return prop;
		
	}

}

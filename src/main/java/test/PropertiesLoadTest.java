package test;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesLoadTest {
	
	private final static String properties = "config/data.properties";
	
	public static Configuration loadProp() {
		return loadProp(properties);
	}
	
	public static Configuration loadProp(String properties) {
		Configuration config = null;
		
		try {
			config = new PropertiesConfiguration(properties);
			
//			String dataType = config.getString("data.type");
//				
//			System.out.println("\n dtaType " + dataType);
//				
//			config.setProperty("colors.background", "#000000");
//			((PropertiesConfiguration)config).save();
			
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		return config;
	}
	
	public static void main(String[] args) {
		Configuration configuration = loadProp();
		String info = configuration.getString("info");
		System.out.println(info);
		
	}

}

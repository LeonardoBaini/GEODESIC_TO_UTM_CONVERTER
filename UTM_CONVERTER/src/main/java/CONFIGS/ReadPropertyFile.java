package CONFIGS;
/*Autor Leonardo Baini*/


import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ReadPropertyFile {
	private static final Logger logger = LogManager.getLogger(ReadPropertyFile.class);
	private static ReadPropertyFile readPropertyFile;
	
			
	
	public static ReadPropertyFile getInstance() {
		if(readPropertyFile==null) {
			readPropertyFile=new ReadPropertyFile();
		}
		return readPropertyFile;
	}
	public Properties obtenerPropiedades() {
		logger.info("leyendo propiedades del archivo configs/Configs.properties");
		Properties prop=new Properties();
		try {
			prop.load(ReadPropertyFile.class.getResourceAsStream("Configs.properties"));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			logger.error("Error "+e2.getMessage());
			
		}

		return prop;
	}
	

}

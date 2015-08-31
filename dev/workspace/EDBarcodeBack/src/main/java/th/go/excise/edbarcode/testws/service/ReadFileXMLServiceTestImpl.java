package th.go.excise.edbarcode.testws.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("readFileXMLServiceTest")
public class ReadFileXMLServiceTestImpl implements ReadFileXMLServiceTest {

private static final Logger logger = LogManager.getLogger(ReadFileXMLServiceTestImpl.class);
	
	@SuppressWarnings("resource")
	@Override
	public String callRequest(String strRmf) {
		
		ClassLoader classLoader = getClass().getClassLoader();
		File fi = new File(classLoader.getResource(strRmf).getFile());
		 StringBuilder  stringBuilder = null;
		BufferedReader reader = null;
		try {
			
		reader = new BufferedReader( new FileReader (fi));
	    String         line = null;
	    stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    	while( ( line = reader.readLine() ) != null ) {
	    		stringBuilder.append( line );
	    		stringBuilder.append( ls );
	    	}
		
		} catch (FileNotFoundException e) {
			logger.error(e.getStackTrace());
		} catch (IOException e) {
			logger.error(e.getStackTrace());
		}

	    return stringBuilder.toString();
	}

}

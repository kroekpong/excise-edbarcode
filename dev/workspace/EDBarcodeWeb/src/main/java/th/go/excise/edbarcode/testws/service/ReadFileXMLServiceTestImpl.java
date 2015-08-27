package th.go.excise.edbarcode.testws.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service("readFileXMLServiceTest")
public class ReadFileXMLServiceTestImpl implements ReadFileXMLServiceTest {

	@Override
	public StringBuilder callRequest(String strNameRequestController) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new FileReader(new File(strNameRequestController)));
		String line;
		br.readLine();
		StringBuilder sb = new StringBuilder();

		while((line=br.readLine())!= null){
		    sb.append(line.trim());
		}
		
		System.out.println("++++"+sb);
		
		return sb;
	}

}

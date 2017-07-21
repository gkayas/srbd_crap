package refapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlbeam.XBProjector;


import device.ResourceUtils;

public class XMLReader {

	private String xmlPath;

	private TCInfoData tcData;

	public XMLReader()
	{

	}
	public XMLReader(String xmlPath) throws IOException
	{
		this.tcData = new XBProjector().io().file(new File(xmlPath)).read(TCInfoData.class);	
	}

	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) throws IOException {
		this.xmlPath =ResourceUtils.getXmlPath() + xmlPath;
		this.tcData = new XBProjector().io().file(new File(this.xmlPath)).read(TCInfoData.class);	
	}

	public void printXMLData() throws IOException
	{
		for (TCInfoData.TCs tc:tcData.getAllTCInfo()) {
			System.out.println(tc.getLevel() + " " + tc.getName() + " " + tc.getPrecondition());  
		}
	}


	public List<TestInfo>getTestInfo()
	{

		List<TestInfo> testInfo = new ArrayList<TestInfo>();
		for (TCInfoData.TCs tc:tcData.getAllTCInfo()) {	    
			TestInfo t = new TestInfo();
			t.setLevel(tc.getLevel());
			t.setPreCondition(tc.getPrecondition());
			t.setResult(TCResult.NT);
			t.setTcName(tc.getName());

			testInfo.add(t);
		}
		return testInfo;

	}
}

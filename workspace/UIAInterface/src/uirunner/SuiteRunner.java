package uirunner;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import device.ResourceUtils;
//
//
public class SuiteRunner {
//	
//	public static boolean isTCRerun = false;
//	
//	private static TCRunner runner = null;
//	
//	public static TCRunner getRunner() {
//		return runner;
//	}
//
//	public static TCRunner updateRunner() throws IOException {
//		
//		for (AppInfo app: runner.getAppInfoList()) {
//			app.setTCResult("install", TCResult.NT);
//			app.setTCResult("exploratory", TCResult.NT);
//			app.setTCResult("uninstall", TCResult.NT);
//		}
//		
//		return runner;
//	}
//	
//	public static TCRunner getAppRunner(String profileName) throws IOException {
//		String resultPath = "";
//		String xmlFilePath = ""; 
//	
//		if(profileName.equals("Mobile")) {
//			xmlFilePath = "xml/storeapp_mobile.xml";
//			resultPath = "reports/result_mobile.csv";
//		}else {
//			xmlFilePath = "xml/storeapp_mobile.xml"; 
//			resultPath = "reports/result_mobile.csv";
//		}
//		
//		ArrayList<AppInfo> appInfos = JsoupXMLParser.parseXML(ResourceUtils.getResourcePath()+ xmlFilePath);
//		
//		File resultFile = new File(resultPath);
//		
//		if(resultFile.exists()) {
//			ArrayList<ResultRow> resultApps = ResultUtils.readResultFile();
//			
//			for (ResultRow result : resultApps) {
//				ArrayList<TestInfo> tcList = new ArrayList<TestInfo>();
//				tcList.add(new TestInfo("install", ResultUtils.convertTCResult(result.install)));
//				tcList.add(new TestInfo("exploratory", ResultUtils.convertTCResult(result.exploratory)));
//				tcList.add(new TestInfo("uninstall", ResultUtils.convertTCResult(result.uninstall)));
//				
//				AppInfo dummyApp = new AppInfo(tcList, result.name, result.binary);
//				
//				if(appInfos.contains(dummyApp)) {
//					appInfos.get(appInfos.indexOf(dummyApp)).copy(dummyApp);
//				}
//			}
//			
//		} else {
//			resultFile.createNewFile();
//		}
//		
//		ResultUtils.writeResultFile(appInfos);
//		
//		runner = new TCRunner();
//	    runner.setProfileName(profileName);
//	    runner.setAppInfoList(appInfos);
//		return runner;
//	}
//	
//	
//	public static void runModule(String profileName){
//		
//		try {
//			if(runner == null) {
//				runner = getAppRunner(profileName);	
//			}	
//			
//			runner.runAllApp(profileName);
//		} 
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//
//	public static void runTestCase(String moduleName,String testCaseName){
//		try {
//			if(runner == null) {
//				runner = getAppRunner(moduleName);	
//			}
//			runner.runSingleTC(moduleName,testCaseName);
//		} 
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	public static void reRunTestCase(String moduleName,String testCaseName){
//		try {
//			isTCRerun = true;
//			if(runner == null) {
//				runner = getAppRunner(moduleName);	
//			}
//			runner.reRunTC(moduleName,testCaseName);
//			isTCRerun = false;
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static boolean isTCRerun()
//	{
//		return isTCRerun;
//	}
}

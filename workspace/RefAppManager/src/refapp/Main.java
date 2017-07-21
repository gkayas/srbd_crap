package refapp;

import base.AFEngine;



public class Main {
	public static void main(String[] args) throws Exception{

		AFEngine.initialize();
		ReportUtils.initialize();

		if(args.length > 0) {
			if(args[0].equals("runModule")) {
				//SuiteRunner.runModule(args[1]);
			}
			else if(args[0].equals("runTestCase")) {		
				//SuiteRunner.reRunTestCase(args[1], args[2]);
			}
			else if(args[0].equals("reRunTestCase")) {		
				//SuiteRunner.reRunTestCase(args[1], args[2]);
			}	
		}
		else {			
			//SuiteRunner.runModule("Gallery");
			//SuiteRunner.runTestCase("Gallery","Gallery_102");
			SuiteRunner.reRunTestCase("Gallery","Gallery_085");
		
			//SuiteRunner.runModule("Calendar");
			//SuiteRunner.runTestCase("Calendar","Calendar_218");
			//SuiteRunner.reRunTestCase("Calendar","Calendar_162");
			
			
			
			// Re Run Loop
//			for (int i = 29; i <= 29; i++)
//			{
//				String index = "";
//				String tcName = "Gallery_";
//				
//				if(i >= 1 && i <= 9)
//					index = "00" + i;
//				else if (i >= 10 && i <= 99)
//					index = "0" + i;
//				else if (i >= 100 && i <= 999)
//					index = "" + i;
//
//				tcName = tcName + index;				
//				
//				//SuiteRunner.runTestCase("Calendar",tcName);
//				SuiteRunner.reRunTestCase("Gallery", tcName);
//			}			
		}
		
		AFEngine.deinitialize();
		System.exit(0);
	}
}

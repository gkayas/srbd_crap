package uirunner;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Scanner;
//
public class ResultUtils {
//	
//	public static String RESULT_FILE_PATH = "reports/result_mobile.csv";
//	
//	public static void showOutput(String result){
//		if(result.indexOf("Pass") > 0) {
//			System.out.println(result);	
//		}
//		else {
//			System.err.println(result);
//		}
//	}
//	
//	public static void writeResultFile(ArrayList<AppInfo> appList) {
//		try {
//			File resultFile = new File(RESULT_FILE_PATH);			
//			PrintWriter pw = new PrintWriter(resultFile);
//		
//			for (AppInfo appInfo : appList) {
//				
//				pw.append(appInfo.toString()+"\n");
//			}
//			pw.flush();
//			pw.close();
//			
//		} catch (FileNotFoundException e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	public static ArrayList<ResultRow> readResultFile() {
//		ArrayList<ResultRow> results = new ArrayList<ResultRow>();
//		
//		try {
//			File resultFile = new File(RESULT_FILE_PATH);
//			Scanner sc = new Scanner(resultFile);
//		
//			while(sc.hasNextLine()) {
//				String line = sc.nextLine();
//				
//				String [] values = line.split(",");
//				
//				if(values.length != 5) {
//					System.out.println("Wrong formatted result file found: " + resultFile);
//					return null;
//				} 
//				
//				results.add(new ResultRow(values[0], values[1], values[2], values[3], values[4]));
//			}
//			
//			sc.close();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		return results;
//	}
//
//	public static TCResult convertTCResult(String result) {
//		if(result.equals("Pass")) {
//			return TCResult.Pass;
//		} else if(result.equals("Fail")) {
//			return TCResult.Fail;
//		} else if(result.equals("NT")) {
//			return TCResult.NT;
//		} else if(result.equals("NR")) {
//			return TCResult.NR;
//		}
//		return null;
//	}
//	
//	public static void appendAppInResultFile(AppInfo app, File resultFile) {
////		ArrayList<AppInfo> exitingApps = readResultFile();
////		if(exitingApps.size() == 0) {
////			exitingApps.add(app);
////		} else {
////			for (AppInfo appInfo : exitingApps) {
////				System.out.println(appInfo);
////				if(exitingApps.contains(appInfo)) {
////					exitingApps.get(exitingApps.indexOf(appInfo)).copy(appInfo);
////				} else {
////					exitingApps.add(appInfo);
////				}
////			}
////		}
////		writeResultFile(exitingApps);
//	}
}
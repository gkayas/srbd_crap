//package fxviewer;
//
//import java.io.IOException;
//
//public class RunServerTask extends javax.swing.SwingWorker<String, String>{
//
//	StoreAppController controller;
//	Process process;
//	public RunServerTask(StoreAppController mainController) {
//		controller = mainController;
//	}
//
//	@Override
//	protected String doInBackground() throws Exception {
//		System.out.println("[LOG]: Start running VNC Server.............");
//		
//		//controller.toggleRunServerBtn();
////		String shFile = "./vncserver/"+ResourceUtils.getTestVersion()+"/";
////		if(UtilityBase.isWindows()) {
////			shFile = shFile + "execute_device.bat";
////		} else {
////			shFile = shFile + "execute_device.sh";
////		}
//
////		System.out.println("[LOG]: VNC Server is running .............");	    
//		
////		runProcess(shFile);
////		
////		if(process.exitValue() != 0) {
////			System.out.println("[LOG]: VNC Server launched failed  or stopped..........");
////		} 	
////		
//		//controller.toggleRunServerBtn();
//		return null;
//	}
//	
//	public void stopPrecess() {
//		process.destroyForcibly();
//	}
//	
//	public  Process runProcess(String... args ) {
//		
//		try {
//			process = new ProcessBuilder(args).start();
//
//			process.waitFor();
//
//			//BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//			//StringBuffer sb = new StringBuffer();
//			//String s = "";
//			process.destroy();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		return process;
//	}
//}

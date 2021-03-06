package uirunner;
//
//import java.io.BufferedOutputStream;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//
public class BinaryData {
//	
//	private String achivePath = null;
//	private String appId = null;
//	private String packageId = null;
//	
//	private final int BUFFER_SIZE = 4096;
//	
//	public BinaryData(String achivePath)
//	{
//		this.achivePath = achivePath;
//		updateInfo();
//	}
//	
//	public String getAppId(){
//    	return appId;
//    }
//    
//    public String getPackageId(){
//    	return packageId;
//    }
//    
//    public String getPath(){
//    	return achivePath;
//    }
//	
//	private String unzip() throws IOException {
//    	String destDirectory = new File(achivePath).getParent()+ File.separator +System.currentTimeMillis()+"";
//    	File destDir = new File(destDirectory);
//        if (!destDir.exists()) {
//            destDir.mkdir();
//        }
//        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(achivePath));
//        ZipEntry entry = zipIn.getNextEntry();
//        
//        while (entry != null) {
//            String filePath = destDirectory + File.separator + entry.getName();
//            if (!entry.isDirectory()) {
//            	extractFile(zipIn, filePath);
//            } else {
//                File dir = new File(filePath);
//                dir.mkdir();
//            }
//            zipIn.closeEntry();
//            entry = zipIn.getNextEntry();
//        }
//        zipIn.close();
//        return destDirectory;
//    }
//
//
//    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
//        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
//        byte[] bytesIn = new byte[BUFFER_SIZE];
//        int read = 0;
//        while ((read = zipIn.read(bytesIn)) != -1) {
//            bos.write(bytesIn, 0, read);
//        }
//        bos.close();
//    }
//    
//    private void deleteDirectory(String directory)
//	{
//		File file = new File(directory);
//		if(file.exists())
//		{
//			File[] files = file.listFiles();
//			for(File f:files)
//			{
//				if(f.isDirectory())
//				{
//					deleteDirectory(f.getAbsolutePath());
//					f.delete();
//				}
//				else
//					f.delete();
//			}
//			file.delete();
//		}
//	}
//    
//    private String getTagValue(String line, String tag) {
//		return line.split(tag)[1].trim().split("\"")[1];
//	}
//
//	private void updateInfo(){
//		String destDirectory;
//		try {
//			destDirectory = unzip();
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//			return;
//		}
//    	String tagAppId = null;
//    	String tagPackageId = null;
//    	BufferedReader bufferedReader;
//    	
//    	
//    	try {
//    		if(achivePath.contains(".wgt")){
//        		bufferedReader = new BufferedReader(new FileReader(destDirectory+ File.separator +"config.xml"));
//        		tagAppId = "id=";
//        		tagPackageId = "package=";
//        	}
//        	else{
//        		bufferedReader = new BufferedReader(new FileReader(destDirectory+ File.separator +"tizen-manifest.xml"));
//        		tagAppId = "appid=";
//        		tagPackageId = "package=";
//        	}
//    		String line = null;
//        	boolean isFoundAppId = false;
//        	boolean isFoundPackageId = false;
//        	
//        	while( (line = bufferedReader.readLine()) != null){
//        		if(line.contains(tagAppId)){
//        			appId = getTagValue(line,tagAppId);
//        			isFoundAppId = true;
//        		}
//        		
//        		if(line.contains(tagPackageId)){
//        			packageId =  getTagValue(line,tagPackageId);
//        			isFoundPackageId = true;
//        		}
//        		
//        		if(isFoundAppId && isFoundPackageId)
//        			break;
//        	}
//        	deleteDirectory(destDirectory);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    	
//	}
//
}

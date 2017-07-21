package com.turbovnc.vncviewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class ReadConfigUtil {

	static HashMap<String, String> devInfo = new HashMap<String, String>();


	static String getLiveMode() {
		return devInfo.get(Constants.LIVE_MODE);
	}

	static String getPlatformVersion() {
		return devInfo.get(Constants.PLATFORM_VERSION);
	}

	static String getProfileName() {
		return devInfo.get(Constants.PROFILE_NAME);
	}

	static String getProfileType() {
		return devInfo.get(Constants.PROFILE_TYPE);
	}

	static String getScreenWidth() {
		return devInfo.get(Constants.SCREEN_WIDTH);
	}

	static String getScreenHeight() {
		return devInfo.get(Constants.SCREEN_HEIGHT);
	}

	static String getEmulatorType() {
		return devInfo.get(Constants.EMULATOR_TYPE);
	}

	static String getAssertMode() {
		return devInfo.get(Constants.ASSERT_MODE);
	}

	static String getRefreshTime() {
		return devInfo.get(Constants.REFRESH_TIME);
	}

	static String getSearchTime() {
		return devInfo.get(Constants.SEARCH_TIME);
	}

	static String getShowSysMes() {
		return devInfo.get(Constants.SHOW_LOG_MESSAGE);
	}

	static String getCaptureRefImagePath() {
		return devInfo.get(Constants.CAPTURE_REF_IMAGE_PATH);
	}

	static void setValueInDevInfo(String key,String value) {
		devInfo.put(key, value);
	}

	static String getRotation() {
		return devInfo.get(Constants.ROTATION);
	}

	public static void readDeviceConfig() {

		String configPath;
		configPath = System.getProperty("user.dir") + "/" + Constants.DEVICE_CONFIG_FILE;

		try {
			Scanner sc = new Scanner(new File(configPath));
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				String tokens[] = line.split("=");
				devInfo.put(tokens[0].trim(), tokens[1].trim());
			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(configPath + " file not found");
		} catch (Exception e) {
			System.out.println("Wrong formated Config File.");
		}
	}

	public static void writeIntoDeviceConfig()  {
		String configPath;
		configPath = System.getProperty("user.dir") + "/" + Constants.DEVICE_CONFIG_FILE;

		try {
			PrintWriter pw = new PrintWriter(new File(configPath));

			pw.write(Constants.LIVE_MODE + "="+devInfo.get(Constants.LIVE_MODE)+"\n");
			pw.write(Constants.PLATFORM_VERSION + "="+devInfo.get(Constants.PLATFORM_VERSION)+"\n");
			pw.write(Constants.PROFILE_NAME + "="+devInfo.get(Constants.PROFILE_NAME)+"\n");
			pw.write(Constants.PROFILE_TYPE + "="+devInfo.get(Constants.PROFILE_TYPE)+"\n");
			pw.write(Constants.SCREEN_WIDTH + "="+devInfo.get(Constants.SCREEN_WIDTH)+"\n");
			pw.write(Constants.SCREEN_HEIGHT + "="+devInfo.get(Constants.SCREEN_HEIGHT)+"\n");
			pw.write(Constants.EMULATOR_TYPE + "="+devInfo.get(Constants.EMULATOR_TYPE)+"\n");
			pw.write(Constants.ASSERT_MODE + "="+devInfo.get(Constants.ASSERT_MODE)+"\n");
			pw.write(Constants.REFRESH_TIME + "="+devInfo.get(Constants.REFRESH_TIME)+"\n");
			pw.write(Constants.SEARCH_TIME + "="+devInfo.get(Constants.SEARCH_TIME)+"\n");
			pw.write(Constants.SHOW_LOG_MESSAGE + "="+devInfo.get(Constants.SHOW_LOG_MESSAGE)+"\n");
			pw.write(Constants.CAPTURE_REF_IMAGE_PATH + "="+devInfo.get(Constants.CAPTURE_REF_IMAGE_PATH)+"\n");
			pw.write(Constants.SDK_PATH + "="+devInfo.get(Constants.SDK_PATH));

			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Config file not found");
		}


	}
}

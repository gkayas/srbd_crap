import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadWorker  implements Runnable{

	private String path;
	public DownloadWorker(String path, String fileName, String destinationFolder) {
		this.path = path;
	}
	
	@Override
	public void run() {
		URL website;
		try {
			website = new URL("http://www.website.com/information.asp");
	
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("information.html");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	
}

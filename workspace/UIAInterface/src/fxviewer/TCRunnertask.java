package fxviewer;

import java.util.List;

import javax.swing.SwingWorker;

import javafx.scene.control.TextArea;

public class TCRunnertask extends SwingWorker<String, String> {
	TextArea textArea;


	public TCRunnertask(TextArea console) {
	
	}

	@Override
	public String doInBackground() {
		
		return null;
	}

	@Override
	protected void process(List<String> chunks) {
//		for (String string : chunks) {
//			textArea.appendText(string);
//		}
	}
}

package application;

import java.io.IOException;
import java.io.OutputStream;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class CustomConsole extends OutputStream
{
	private TextArea    output;

	public CustomConsole(TextArea ta)
	{
		this.output = ta;
	}

	public void appendText(String valueOf) {
		Platform.runLater(() -> output.appendText(valueOf));
	}

	@Override
	public void write(int i) throws IOException
	{
		Platform.runLater(() -> output.appendText(String.valueOf((char) i)));
	}

}
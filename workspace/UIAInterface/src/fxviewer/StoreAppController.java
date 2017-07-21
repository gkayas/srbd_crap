
package fxviewer;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import uirunner.JsoupXMLParser;
import uirunner.ReportUtils;
import uirunner.SampleAppInfo;
import uirunner.StoreAppInfo;
import uirunner.TCResult;


public class StoreAppController extends AppController implements Initializable, EventHandler<ActionEvent>  /*,ChangeListener*/{

	@FXML
	private Label appResultLbl;

	@FXML
	private AnchorPane storeAppAnchor;

	@FXML
	private TableView<StoreAppInfo> tcTable;

	@FXML
	private TableColumn<StoreAppInfo, Boolean> checkBox;

	@FXML
	private TableColumn<StoreAppInfo, String> appName;

	@FXML
	private TableColumn<StoreAppInfo, String> result;

	@FXML
	private TableColumn<StoreAppInfo, String> install;

	@FXML
	private TableColumn<StoreAppInfo, String> launch;

	@FXML
	private TableColumn<StoreAppInfo, String> exploratory;

	@FXML
	private TableColumn<StoreAppInfo, String> close;

	@FXML
	private TableColumn<StoreAppInfo, String> uninstall;

	@FXML
	private TableColumn<StoreAppInfo, String> crash;

	@FXML
	private CheckBox installCheck;

	@FXML
	private CheckBox launchCheck;

	@FXML
	private CheckBox exploratoryCheck;

	@FXML
	private CheckBox closeCheck;

	@FXML
	private CheckBox uninstallCheck;

	@FXML
	private CheckBox crashCheck;

	@FXML
	private CheckBox selectAllCheck;

	@FXML
	private Button browseBtn;

	@FXML
	private Button exploratoryCodeBtn;

	@FXML
	private Button runBtn;

	@FXML
	private Button rerunBtn;

	@FXML
	private Button exportReportBtn;

	@FXML
	private Button refreshBtn;

	@FXML
	private Button runSuspendBtn;

	@FXML
	private Button settingsBtn;

	@FXML
	private TextArea console;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setConsole();
		appResultLbl.setStyle("-fx-alignment: CENTER;");
		appResultLbl.setText("");
		result.setStyle("-fx-alignment: CENTER;");
		install.setStyle("-fx-alignment: CENTER;");
		launch.setStyle("-fx-alignment: CENTER;");
		exploratory.setStyle("-fx-alignment: CENTER;");
		close.setStyle("-fx-alignment: CENTER;");
		uninstall.setStyle("-fx-alignment: CENTER;");
		crash.setStyle("-fx-alignment: CENTER;");



		installCheck.setOnAction(this);
		launchCheck.setOnAction(this);
		exploratoryCheck.setOnAction(this);
		uninstallCheck.setOnAction(this);
		closeCheck.setOnAction(this);
		crashCheck.setOnAction(this);

		selectAllCheck.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CheckBox chk = (CheckBox) event.getSource();
				if (chk instanceof CheckBox) {
					boolean isSelected = chk.selectedProperty().getValue();
					checkAllTableRow(isSelected);
					runBtnCheck();
				}
			}

		});

//		installCheck.setSelected(true);
//		launchCheck.setSelected(true);
//		exploratoryCheck.setSelected(true);
//		uninstallCheck.setSelected(true);
//		closeCheck.setSelected(true);
//		crashCheck.setSelected(true);



		appName.setCellValueFactory(new PropertyValueFactory<StoreAppInfo, String>("appName"));

		result.setCellValueFactory(new PropertyValueFactory<StoreAppInfo, String>("result"));

		install.setCellValueFactory(new PropertyValueFactory<StoreAppInfo, String>("install"));

		launch.setCellValueFactory(new PropertyValueFactory<StoreAppInfo, String>("launch"));

		exploratory.setCellValueFactory(new PropertyValueFactory<StoreAppInfo, String>("exploratory"));

		close.setCellValueFactory(new PropertyValueFactory<StoreAppInfo, String>("close"));

		uninstall.setCellValueFactory(new PropertyValueFactory<StoreAppInfo, String>("uninstall"));

		crash.setCellValueFactory(new PropertyValueFactory<StoreAppInfo, String>("crash"));

		checkBox.setCellValueFactory(new PropertyValueFactory<StoreAppInfo, Boolean>("checkBox"));

		checkBox.setCellFactory(new Callback<TableColumn<StoreAppInfo, Boolean>, TableCell<StoreAppInfo, Boolean>>() {

            public TableCell<StoreAppInfo, Boolean> call(TableColumn<StoreAppInfo, Boolean> p) {
            	CheckBoxTableCell< StoreAppInfo, Boolean> cell = new CheckBoxTableCell<StoreAppInfo, Boolean>();
            	cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						StoreAppInfo app = (StoreAppInfo)cell.getTableRow().getItem();

						if(!app.getCheckBox()) {
							selectAllCheck.setSelected(false);
							runBtnCheck();
						} else {
							evaluateSelectAllCheckValue();
							runBtnCheck();
						}
					}

				});
                return cell;
            }
        });


		tcTable.setOnMouseClicked( new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				 if ( event.getClickCount() == 2  ) {
					 StoreAppInfo selectedAppInfo = tcTable.getSelectionModel().getSelectedItem();
					 try {
						 if(selectedAppInfo != null){
							 showJavaCodeWindow(selectedAppInfo);
						 }else{
							 System.out.println("[MESSAGE] No application found in table.");
						 }
					 } catch (FileNotFoundException e) {
						System.out.println("[MESSAGE] " + e.getMessage());
					 }
				}
			}
		});

		runBtn.setDisable(true);
		rerunBtn.setDisable(true);
		refreshBtn.setDisable(true);
		settingsBtn.setDisable(false);
		runSuspendBtn.setDisable(true);
		exportReportBtn.setDisable(true);
		exploratoryCodeBtn.setDisable(true);


	}

	private void setConsole() {
		if(console == null)
			System.out.println("[ERROR] Console is null");
		else{
			CustomConsole customConsole = new CustomConsole(console);
			PrintStream ps = new PrintStream(customConsole);

			System.setOut(ps);
			System.setErr(ps);
		}
	}

	@Override
	public void handle(ActionEvent event) {
		setConsole();
		if (event.getSource() instanceof CheckBox) {
			CheckBox chk = (CheckBox) event.getSource();

			if ("Exploratory".equals(chk.getText())) {
				if(chk.selectedProperty().getValue()) {
					installCheck.setSelected(true);
					launchCheck.setSelected(true);
				}

			} else if ("Launch".equals(chk.getText())) {
				if(chk.selectedProperty().getValue()) {
					installCheck.setSelected(true);
				} else {
					exploratoryCheck.setSelected(false);
					closeCheck.setSelected(false);
					crashCheck.setSelected(false);
				}
			}  else if ("Close".equals(chk.getText())) {
				if(chk.selectedProperty().getValue()) {
					installCheck.setSelected(true);
					launchCheck.setSelected(true);
				}
			} else if ("Uninstall".equals(chk.getText())) {
				if(chk.selectedProperty().getValue()) {
					installCheck.setSelected(true);
				}
			} else if ("Detect Crash".equals(chk.getText())) {
				if(chk.selectedProperty().getValue()) {
					installCheck.setSelected(true);
					launchCheck.setSelected(true);
				}
			} else if ("Install".equals(chk.getText())) {
				if(!chk.selectedProperty().getValue()) {
					launchCheck.setSelected(false);
					exploratoryCheck.setSelected(false);
					closeCheck.setSelected(false);
					uninstallCheck.setSelected(false);
					crashCheck.setSelected(false);
				}
			}

			runBtnCheck();
		}
	}

	private String getPascalCase(String incase){
		return incase.substring(0,1).toUpperCase() + incase.substring(1).toLowerCase();
	}

	public void browseBtnClicked() {
		setConsole();
		FileChooser fc = new FileChooser();
		fc.setTitle("Browse applications");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Binary files (*.wgt, *.tpk)", "*.wgt","*.tpk");
		fc.getExtensionFilters().add(extFilter);
		List<File> selectedFiles = fc.showOpenMultipleDialog(null);

		if (selectedFiles != null) {
			appResultLbl.setText("");
			ArrayList<StoreAppInfo> appList = new ArrayList<StoreAppInfo>();
			for (File f : selectedFiles) {
				StoreAppInfo app = new StoreAppInfo(f.getName());

				try {
					app.setFullPath(f.getCanonicalPath());
					app.setJavaFileName("Storeapp" + getPascalCase(f.getName()).replaceAll(".wgt", "").replaceAll(".tpk", "").replaceAll("\\.", "").replaceAll("-", "").replaceAll("_", ""));
				} catch (IOException e) {
					app.setFullPath(" ");
					e.printStackTrace();
				}

				generateJavaFileFromAppInfo(app);
				appList.add(app);
			}

			if(appList.size() > 0) {
				addAllToTcTable(appList);

				if(!isAllScenarioUnchecked()) {
					runBtn.setDisable(false);
					rerunBtn.setDisable(false);
				}

				refreshBtn.setDisable(false);
				exportReportBtn.setDisable(false);
				exploratoryCodeBtn.setDisable(false);

				selectAllCheck.setSelected(true);
			}
		}
	}

	public void exploratoryCodeBtnClicked() {
		setConsole();
		StoreAppInfo selected = tcTable.getSelectionModel().getSelectedItem();
		if(selected != null) {
			try {
				showExploratoryCodeWindow(selected);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("[MESSAGE] To write exploratory code please select any application from table");
		}
	}

	public void runBtnClicked() {
		setConsole();

		JsoupXMLParser.writeStoreAppXml(this, "run");

		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		alert.setHeaderText("This plan saved successfully.\nPlan can also execute with commandline \"java -jar uiautomator.jar run -p storeapp\" \nDo you want to run now?");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			clearConsole();
			appResultLbl.setText("");
			runBtn.setDisable(true);
			rerunBtn.setDisable(true);
			browseBtn.setDisable(true);
			refreshBtn.setDisable(true);
			settingsBtn.setDisable(true);
			runSuspendBtn.setDisable(false);
			exportReportBtn.setDisable(true);
			exploratoryCodeBtn.setDisable(true);

			checkBox.setEditable(false);
			selectAllCheck.setDisable(true);

			refreshTcTable();
			performCompileRun("run");
			writeSuspendFile("0");
		}
	}

	public void rerunBtnClicked() {
		setConsole();

		if(selectFailedRows() > 0) {
			clearConsole();
			appResultLbl.setText("");
			JsoupXMLParser.writeStoreAppXml(this, "rerun");

			runBtn.setDisable(true);
			rerunBtn.setDisable(true);
			browseBtn.setDisable(true);
			refreshBtn.setDisable(true);
			settingsBtn.setDisable(true);
			runSuspendBtn.setDisable(false);
			exportReportBtn.setDisable(true);
			exploratoryCodeBtn.setDisable(true);

			checkBox.setEditable(false);
			selectAllCheck.setDisable(true);

			refreshTcTable();
			performCompileRun("rerun");
			writeSuspendFile("0");
		}else {
			runBtnCheck();
			System.out.println("[MESSAGE] No failed TCs found to execute rerun.");
		}
	}

	public void exportReportBtnClicked() {
		setConsole();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Browse applications");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Report files (*.xls)", "*.xls");
		fileChooser.getExtensionFilters().add(extFilter);
		File reportDirectory = new File("./reports");

		if (! reportDirectory.exists())
			reportDirectory.mkdir();

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		fileChooser.setInitialFileName(format.format(date)+"_report_storeapp.xls");
		fileChooser.setInitialDirectory(reportDirectory);
		File selectedFile = fileChooser.showSaveDialog(null);

		if (selectedFile != null){
			String reportFilePath=selectedFile.getAbsolutePath();
			if(!reportFilePath.contains(".xls"))
				reportFilePath += ".xls";
			ReportUtils.exportStoreappReport(reportFilePath,getTableRows());
		}
	}

	public void refreshBtnClicked() {
		setConsole();
		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		alert.setHeaderText("All application will be removed from table. Do you want to refresh ?");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			clearConsole();
			appResultLbl.setText("");
			removeAllFromTcTable();

			runBtn.setDisable(true);
			rerunBtn.setDisable(true);
			exportReportBtn.setDisable(true);
			exploratoryCodeBtn.setDisable(true);
			refreshBtn.setDisable(true);

			selectAllCheck.setSelected(false);
		}
	}

	public void runSuspendBtnClicked() {
		setConsole();
		writeSuspendFile("1");
		runSuspendBtn.setDisable(true);
	}

	public void settingsBtnClicked() {
		setConsole();
		try {
			Stage settingsStage = new Stage();
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("StoreAppSettingsScene.fxml")));
			settingsStage.setScene(scene);
			settingsStage.setTitle("StoreApp Setting Window");
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			settingsStage.initModality(Modality.APPLICATION_MODAL);
			settingsStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isInstall() {
		return installCheck.selectedProperty().getValue();
	}

	public boolean isLaunch() {
		return launchCheck.selectedProperty().getValue();
	}

	public boolean isExploratory() {
		return exploratoryCheck.selectedProperty().getValue();
	}

	public boolean isCloseCheck() {
		return closeCheck.selectedProperty().getValue();
	}

	public boolean isUninstallCheck() {
		return uninstallCheck.selectedProperty().getValue();
	}

	public boolean isCrash() {
		return crashCheck.selectedProperty().getValue();
	}

	public TextArea getConsole() {
		return console;
	}

	public void clearConsole() {
		Platform.runLater(() -> console.clear());
	}

	public List<StoreAppInfo> getTableRows() {
		return tcTable.getItems();
	}

	private int selectFailedRows() {
		int count = 0;
		List<StoreAppInfo> rows = getTableRows();
		for (StoreAppInfo appInfo : rows) {
			if(appInfo.getAppResult().equals(TCResult.Fail)) {
				count++;
				appInfo.setCheckBox(true);
			} else {
				appInfo.setCheckBox(false);
			}
		}

		if(rows.size() == count) {
			selectAllCheck.setSelected(true);
		} else {
			selectAllCheck.setSelected(false);
		}

		return count;
	}

	private void refreshTcTable() {
		List<StoreAppInfo> apps = getTableRows();
		for (StoreAppInfo appInfo : apps) {
			if(!appInfo.getCheckBox()) {
				continue;
			}
			if(isInstall())
				appInfo.getInstall().setValue("NT");
			if(isLaunch())
				appInfo.getLaunch().setValue("NT");
			if(isExploratory())
				appInfo.getExploratory().setValue("NT");
			if(isCloseCheck())
				appInfo.getClose().setValue("NT");
			if(isUninstallCheck())
				appInfo.getUninstall().setValue("NT");
			if(isCrash())
				appInfo.getDetectCrash().setValue("NT");
			appInfo.resultProperty().set(appInfo.getAppResult() + "");
		}
	}

	public void addToTcTable(StoreAppInfo tc) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tcTable.getItems().add(tc);
			}
		});
	}

	public void addAllToTcTable(List<StoreAppInfo> appList) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				for (StoreAppInfo app : appList) {
					if(!getTableRows().contains(app)) {
						addToTcTable(app);
					}
				}
			}
		});
	}

	public void removeAllFromTcTable() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				ObservableList<StoreAppInfo> data = tcTable.getItems();
				data.remove(0, data.size());

			}
		});
	}

	public void resetTctable(List<StoreAppInfo> newList) {

		removeAllFromTcTable();
		addAllToTcTable(newList);
	}

	private void checkAllTableRow(Boolean value) {
		List<StoreAppInfo> rows = getTableRows();
		for (StoreAppInfo appInfo : rows) {
			appInfo.setCheckBox(value);
		}
	}

	private void evaluateSelectAllCheckValue() {
		List<StoreAppInfo> rows = getTableRows();
		boolean isChecked = true;
		for (StoreAppInfo appInfo : rows) {
			if(!appInfo.getCheckBox()){
				isChecked = false;
				break;
			}
		}

		selectAllCheck.setSelected(isChecked);
	}

	private boolean isAllRowUnchecked() {
		List<StoreAppInfo> rows = getTableRows();
		boolean unchecked = true;
		for (StoreAppInfo appInfo : rows) {
			if(appInfo.getCheckBox()){
				unchecked = false;
				break;
			}
		}
		return unchecked;
	}

	public void runBtnCheck() {
		if(!isAllRowUnchecked() &&
		   !exploratoryCodeBtn.isDisable()) {
			if(!isAllScenarioUnchecked()) {
				runBtn.setDisable(false);
				rerunBtn.setDisable(false);
			} else {
				runBtn.setDisable(true);
				rerunBtn.setDisable(true);
			}
		} else {
			runBtn.setDisable(true);
			rerunBtn.setDisable(true);
		}
	}

	private boolean isAllScenarioUnchecked() {
		boolean checked = (installCheck.selectedProperty().getValue() ||
				  		   uninstallCheck.selectedProperty().getValue() ||
				           exploratoryCheck.selectedProperty().getValue() ||
				           crashCheck.selectedProperty().getValue() ||
				           closeCheck.selectedProperty().getValue() ||
				           launchCheck.selectedProperty().getValue());

		return !checked;
	}

	private void writeSuspendFile(String line) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./temp/suspend"));
			bufferedWriter.write(line);
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showJavaCodeWindow(StoreAppInfo selected) throws FileNotFoundException {
		File exploratoryFile = new File("./src/storeapp/"+selected.getJavaFileName()+".java");
		String lines = readJavaCode(exploratoryFile.getAbsolutePath());
		JDialog frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setModal(true);
		frame.setTitle("Java Code: "+selected.appNameProperty().getValue());
		frame.setLayout(new BorderLayout());

		JPanel contentPane = new JPanel(new GridBagLayout());
		contentPane.setBorder(BorderFactory.createLineBorder(java.awt.Color.DARK_GRAY));

		GridBagConstraints codeContentConst = new GridBagConstraints();
		codeContentConst.fill = GridBagConstraints.BOTH;
		codeContentConst.weightx = 0.0;
		codeContentConst.weighty = 0.1;
		codeContentConst.gridwidth = 3;
		codeContentConst.gridx = 0;
		codeContentConst.gridy = 0;

		JPanel codeContent = new JPanel();

		codeContent.setLayout(new BoxLayout(codeContent, BoxLayout.X_AXIS));
		RTextScrollPane scrollPanel ;

		RSyntaxTextArea textArea = new RSyntaxTextArea();
		textArea.setFont(textArea.getFont().deriveFont(14.0f));
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		textArea.setText(lines);
		scrollPanel = new RTextScrollPane(textArea);

		codeContent.add(scrollPanel);
		contentPane.add(codeContent, codeContentConst);

		JPanel buttonPane = new JPanel(new GridBagLayout());
		buttonPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

		GridBagConstraints buttonPaneConst = new GridBagConstraints();
		buttonPaneConst.fill = GridBagConstraints.HORIZONTAL;
		buttonPaneConst.weightx = 1;
		buttonPaneConst.weightx = 0.9;
		buttonPaneConst.anchor = GridBagConstraints.PAGE_END;
		buttonPaneConst.gridx = 0;
		buttonPaneConst.gridy = 3;
		buttonPaneConst.gridwidth = 3;

		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					PrintWriter pw = new PrintWriter(exploratoryFile);
					pw.write(textArea.getText());
					pw.flush();
					pw.close();
				} catch (Exception ex) {
					System.out.println("[ERROR] " + ex.getMessage());
				}

				JOptionPane.showMessageDialog(frame, "Java Code: " + selected.appNameProperty().getValue() + " saved successfully.");
			}
		});

		GridBagConstraints saveBtnConst = new GridBagConstraints();
		saveBtnConst.fill = GridBagConstraints.HORIZONTAL;
		saveBtnConst.insets = new Insets(10, 0, 10, 5);
		saveBtnConst.gridx = 2;
		saveBtnConst.gridy = 0;
		saveBtnConst.gridwidth = 1;

		GridBagConstraints closeBtnConst = new GridBagConstraints();
		closeBtnConst.fill = GridBagConstraints.HORIZONTAL;
		closeBtnConst.insets = new Insets(10, 5, 10, 0);
		closeBtnConst.gridx = 3;
		closeBtnConst.gridy = 0;
		closeBtnConst.gridwidth = 1;
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				frame.dispose();

			}
		});
		buttonPane.add(saveBtn, saveBtnConst);
		buttonPane.add(closeBtn, closeBtnConst);

		contentPane.add(buttonPane, buttonPaneConst);
		frame.add(contentPane);


		frame.pack();
		frame.setSize(680, 600);
		frame.setPreferredSize(new Dimension(680, 600));
		frame.setResizable(true);
		frame.setVisible(true);
	}

    public void showExploratoryCodeWindow(StoreAppInfo selected) throws FileNotFoundException {
    	File expolatoryFile = new File("./src/storeapp/"+selected.getJavaFileName()+".java");

	   	String [] parts = readExploratoryCode(expolatoryFile.getAbsolutePath());
	   	JDialog frame = new JDialog();
	   	frame.setModalityType(ModalityType.APPLICATION_MODAL);
	   	frame.setModal(true);
	   	frame.setTitle("Exploratory Code: "+selected.getName().getValue());
		frame.setLayout(new BorderLayout());

		JPanel contentPane = new JPanel(new GridBagLayout());
		contentPane.setBorder(BorderFactory.createLineBorder(java.awt.Color.DARK_GRAY));

		GridBagConstraints codeContentConst = new GridBagConstraints();
		codeContentConst.fill = GridBagConstraints.BOTH;
		codeContentConst.weightx = 0.0;
		codeContentConst.weighty = 0.1;
		codeContentConst.gridwidth = 3;
		codeContentConst.gridx = 0;
		codeContentConst.gridy = 0;

		JPanel codeContent = new JPanel();

		codeContent.setLayout(new BoxLayout(codeContent, BoxLayout.X_AXIS));
		RTextScrollPane scrollPanel ;

		RSyntaxTextArea textArea = new RSyntaxTextArea();
		textArea.setFont(textArea.getFont().deriveFont(14.0f));
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		textArea.setText(parts[1].trim().replace("null", "")+"\n");
		scrollPanel = new RTextScrollPane(textArea);

		codeContent.add(scrollPanel);
		contentPane.add(codeContent, codeContentConst);

		JPanel buttonPane = new JPanel(new GridBagLayout());
		buttonPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

		GridBagConstraints buttonPaneConst = new GridBagConstraints();
		buttonPaneConst.fill = GridBagConstraints.HORIZONTAL;
		buttonPaneConst.weightx = 1;
		buttonPaneConst.weightx = 0.9;
		buttonPaneConst.anchor = GridBagConstraints.PAGE_END;
		buttonPaneConst.gridx = 0;
		buttonPaneConst.gridy = 3;
		buttonPaneConst.gridwidth = 3;

		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					PrintWriter pw = new PrintWriter(expolatoryFile);
					pw.write( parts[0].trim().replace("null", "")+"\n\t\t"+textArea.getText().replace("\n", "\n\t\t")+"\n\t}\n\n"+parts[2].trim().replace("null", ""));
					pw.flush();
					pw.close();
				} catch (Exception ex) {
					System.out.println("[ERROR] " + ex.getMessage());
				}

				JOptionPane.showMessageDialog(frame, "Exploratory Code: " + selected.getName().getValue() + " saved successfully.");
			}
		});

		GridBagConstraints saveBtnConst = new GridBagConstraints();
		saveBtnConst.fill = GridBagConstraints.HORIZONTAL;
		saveBtnConst.insets = new Insets(10, 0, 10, 5);
		saveBtnConst.gridx = 2;
		saveBtnConst.gridy = 0;
		saveBtnConst.gridwidth = 1;

		GridBagConstraints closeBtnConst = new GridBagConstraints();
		closeBtnConst.fill = GridBagConstraints.HORIZONTAL;
		closeBtnConst.insets = new Insets(10, 5, 10, 0);
		closeBtnConst.gridx = 3;
		closeBtnConst.gridy = 0;
		closeBtnConst.gridwidth = 1;
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				frame.dispose();

			}
		});
		buttonPane.add(saveBtn, saveBtnConst);
		buttonPane.add(closeBtn, closeBtnConst);

		contentPane.add(buttonPane, buttonPaneConst);
		frame.add(contentPane);


		frame.pack();
		frame.setSize(680, 600);
		frame.setPreferredSize(new Dimension(680, 600));
		frame.setResizable(true);
		frame.setVisible(true);
	}

    public void generateJavaFileFromAppInfo(StoreAppInfo appInfo) {
		try {
//			InputStream in = ClassLoader.getSystemResourceAsStream("template/StoreAppTemplate");
//			StringBuffer sb = new StringBuffer();
//	    	BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//	    	String line = null;
//	    	while ((line = reader.readLine()) != null) {
//				sb.append(line+"\n");
//			}

			StringBuffer sb = new StringBuffer();
			BufferedReader reader = new BufferedReader(new FileReader("./res/template/StoreAppTemplate"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line+"\n");
			}
			reader.close();

			String javaFileName = appInfo.getJavaFileName();
			File file = new File("src/storeapp/" + javaFileName + ".java");
			if(!file.exists()) {
				PrintWriter pw = new PrintWriter(file);
				String temp = sb.toString();
				temp = temp.replaceAll("ClassName", javaFileName);
				pw.write(temp);
				pw.flush();
				pw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    private String readJavaCode(String exploratoryFilePath) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(exploratoryFilePath));
		String lines = "";

		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			lines += line + "\n";
		}
		sc.close();
		return lines;
	}

	private String[] readExploratoryCode(String expolatoryFilePath) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(expolatoryFilePath));
		String expLine = "\tpublic void exploratory() {";
		String endLine = "\tpublic void close() {";
		String [] parts = new String[3];
		int currentIndex = 0;

		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			if(line.equals(expLine)) {
				parts[currentIndex] += line + "\n";
				currentIndex++;
			} else if(line.equals(endLine)) {
				currentIndex++;
				parts[currentIndex] += line+ "\n";
			} else {
				parts[currentIndex] += line+ "\n";
			}
		}

		sc.close();
		parts[1] = parts[1].trim().substring(0, parts[1].trim().length()-1);
		parts[1] = parts[1].replaceAll("\t", "");

		return parts;
	}


	private boolean isWindows() {
		String osName = System.getProperty("os.name");

		if (osName.toLowerCase().contains("windows")) {
			return true;
		}
		return false;
	}

	public void imagePopupWindowShow(String imgPath) {

		File imageFile;
		Image image;
		ImageView imageView;
		BorderPane pane;
		Scene scene;
		Stage stage;

		imageFile = new File(imgPath);
		image = new Image(imageFile.toURI().toString());
		imageView = new ImageView(image);

		// Image will sit in the middle of our popup.
		pane = new BorderPane();
		pane.setCenter(imageView);
		scene = new Scene(pane);

		// Create the actual window and display it.
		stage = new Stage();
		stage.setScene(scene);
		// Without this, the audio won't stop!
		stage.setOnCloseRequest(
				e -> {
					e.consume();
					stage.close();
				}
				);
		stage.showAndWait();
	}

	private static String getLogFileName() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String logFileName =  format.format(date)+"_uiautomator.log";
		return logFileName;
	}

	private void performCompileRun(String mode) {
		String logFileName = getLogFileName();
		new Thread(new Runnable() {

			@Override
			public void run() {
				String currentWorkingDirectory = System.getProperty("user.dir");
				ProcessBuilder compile, run;
				Process compileProcess, runProcess;

				try {
					if (!isWindows()) {
						compile = new ProcessBuilder("/bin/sh", "-c","javac -d "+
								currentWorkingDirectory + "/bin -cp "+ currentWorkingDirectory
								+ "/AFEngine/lib/*" + ":"+currentWorkingDirectory + "/lib/*:" + currentWorkingDirectory + "/src "+
								currentWorkingDirectory + "/src/apprunner/Main.java "+ currentWorkingDirectory + "/src/storeapp/*.java");
					} else {
						compile = new ProcessBuilder("cmd", "/c", "javac -d "+
								currentWorkingDirectory + "/bin -cp "+ currentWorkingDirectory
								+ "/AFEngine/lib/*" + ";"+currentWorkingDirectory + "/lib/*;" + currentWorkingDirectory + "/src "+
								currentWorkingDirectory + "/src/apprunner/Main.java "+ currentWorkingDirectory + "/src/storeapp/*.java");
					}

					compileProcess = compile.start();

					String line = "";
					BufferedReader msgReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
					BufferedReader errReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));

					while ((line = msgReader.readLine()) != null) {
						System.out.println(line);
					}

					int returnCode = compileProcess.waitFor();

					msgReader.close();
					errReader.close();
					compileProcess.destroy();

					if (returnCode == 0) {
						if (!isWindows()) {
							run = new ProcessBuilder("/bin/sh", "-c",
									"java -cp " + currentWorkingDirectory + "/AFEngine/lib/*:" + currentWorkingDirectory + "/lib/*:"
											+ currentWorkingDirectory + "/bin apprunner.Main storeapp " + mode + " " + logFileName);
						} else {
							run = new ProcessBuilder("cmd", "/c",
									"java -cp " + currentWorkingDirectory + "/AFEngine/lib/*;" + "lib/*;"
											+ currentWorkingDirectory + "/bin apprunner.Main storeapp " + mode + " " + logFileName);
						}

						runProcess = run.start();
						msgReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
						errReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));

						StringBuffer msgSb = new StringBuffer();
						StringBuffer errSb = new StringBuffer();
						String sm,se;

						while(true) {
							try{
								runProcess.exitValue();
								break;
							}catch(IllegalThreadStateException ex){

							}

							while (msgReader.ready()) {
								sm = msgReader.readLine();
								msgSb.append(sm);

								System.out.println(msgSb.toString());

								if(sm.contains("STATUS") && sm.contains("result") && sm.contains("time")) {
									String result = sm.split("\\[result")[1].split("\\]")[0].split("->")[1].trim();
									String time = sm.split("\\[time")[1].split("\\]")[0].split("->")[1].split("ms")[0].trim();

									sm = sm.split("\\[STATUS\\]")[1].trim();
						    		String[] parts = sm.split("\\]");

						    		ArrayList<String> data =new ArrayList<String>();
						    		for (String part : parts) {
						    			data.add(part.replaceAll("\\[", ""));
									}

						    		String appName = data.get(0);
						    		String tcName = data.get(1);

									StoreAppInfo app = new StoreAppInfo(appName);

									List<StoreAppInfo> appList = getTableRows();

									for (StoreAppInfo appInfo : appList) {
										if(appInfo.equals(app)) {
											if(tcName.equals("install")) {
												appInfo.getInstall().setValue(result);
												appInfo.setTimeInstall(Integer.parseInt(time));
											} else if(tcName.equals("launch")) {
												appInfo.getLaunch().setValue(result);
												appInfo.setTimeLaunch(Integer.parseInt(time));
											} else if(tcName.equals("exploratory")) {
												appInfo.getExploratory().setValue(result);
												appInfo.setTimeExporatory(Integer.parseInt(time));
											} else if(tcName.equals("close")) {
												appInfo.getClose().setValue(result);
												appInfo.setTimeClose(Integer.parseInt(time));
											} else if(tcName.equals("uninstall")) {
												appInfo.getUninstall().setValue(result);
												appInfo.setTimeUninstall(Integer.parseInt(time));
											} else if(tcName.equals("detectCrash")) {
												appInfo.getDetectCrash().setValue(result);
												appInfo.setTimeCrash(Integer.parseInt(time));
											}
											appInfo.resultProperty().set(appInfo.getAppResult() + "");
										}
									}
								}else if(sm.contains("selected apps execution finished") || sm.contains("Total selected apps")){
									final String resultTitle = sm.split("\\[INFO\\]")[1].trim();
									Platform.runLater(() -> appResultLbl.setText(resultTitle));
								}

								msgSb = new StringBuffer();
							}

							while (errReader.ready()) {
								se = errReader.readLine();
								errSb.append(se);
								System.out.println(errSb.toString());
								errSb = new StringBuffer();
							}

							Thread.sleep(10);
						}

						msgReader.close();
						errReader.close();
						runProcess.destroy();
					}

				} catch (Exception e) {

					e.printStackTrace();
				}

				runBtn.setDisable(false);
				rerunBtn.setDisable(false);
				browseBtn.setDisable(false);
				refreshBtn.setDisable(false);
				settingsBtn.setDisable(false);
				runSuspendBtn.setDisable(true);
				exportReportBtn.setDisable(false);
				exploratoryCodeBtn.setDisable(false);

				checkBox.setEditable(true);
				selectAllCheck.setDisable(false);

			}
		}).start();
	}

}
package refapp;

import javafx.beans.property.SimpleStringProperty;

public class TestInfo {
	
	private int level;
	private String tcName;
	private String preCondition;
	private TCResult result;
	private Boolean isMethodExists;
	
	private SimpleStringProperty testName;
	private SimpleStringProperty tcStatus;
	private SimpleStringProperty tcRemark;
	
	public TestInfo(String tcName, TCResult tcResult) {
		this.tcName = tcName;
		this.result = tcResult;
		testName = new SimpleStringProperty(tcName);
		tcStatus = new SimpleStringProperty(result.toString());
		tcRemark = new SimpleStringProperty("");
	} 
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getTcName() {
		return tcName;
	}
	public void setTcName(String tcName) {
		testName.set(tcName);
		this.tcName = tcName;
	}
	public String getPreCondition() {
		return preCondition;
	}
	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}
	public TCResult getResult() {
		return result;
	}
	public void setResult(TCResult result) {
		tcStatus.set(result.toString());
		this.result = result;
	}
	public Boolean isMethodExists() {
		return isMethodExists;
	}
	public void setIsMethodExists(Boolean isMethodExists) {
		this.isMethodExists = isMethodExists;
	}
	public SimpleStringProperty testNameProperty() {
		return testName;
	}
	public void setTestName(SimpleStringProperty testName) {
		this.testName = testName;
	}
	public SimpleStringProperty tcStatusProperty() {
		return tcStatus;
	}
	public void setTcStatus(SimpleStringProperty tcStatus) {
		this.tcStatus = tcStatus;
	}
	public SimpleStringProperty tcRemarkProperty() {
		return tcRemark;
	}
	public void setTcRemarkProperty(SimpleStringProperty tcRemark) {
		this.tcRemark = tcRemark;
	}
	

}
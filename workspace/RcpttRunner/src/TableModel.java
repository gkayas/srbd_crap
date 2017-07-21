import java.util.ArrayList;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableModel {
	public SimpleStringProperty name;
	public SimpleStringProperty count;
	private SimpleBooleanProperty checkBox;
	public ArrayList<CSVRow> csvRowList;
	
	public TableModel() {
		this.name = new SimpleStringProperty();
		this.count = new SimpleStringProperty();
		this.checkBox = new SimpleBooleanProperty(true);
		csvRowList = new ArrayList<CSVRow>();
		
	}
	
	public TableModel(String name, int count) {
		this.name = new SimpleStringProperty(name);
		this.count = new SimpleStringProperty(""+count);
		this.checkBox = new SimpleBooleanProperty(true);
		csvRowList = new ArrayList<CSVRow>();
	}
	
	public SimpleStringProperty nameProperty() {
		return name;
	}
	
	public SimpleStringProperty countProperty() {
		return count;
	}
	
	public SimpleBooleanProperty checkBoxProperty() {
		return checkBox;
	}
	
	public void setName(SimpleStringProperty name) {
		this.name = name;
	}
	
	public void setCount(SimpleStringProperty count) {
		this.count = count;
	}
	
	public void setCheckBox(SimpleBooleanProperty checkBox) {
		this.checkBox = checkBox;
	}
	
	 public void setCheckBox(boolean checked) {
	        this.checkBoxProperty().set(checked);
	    }
	
	public ArrayList<CSVRow> getCsvRowList() {
		return csvRowList;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TableModel)) {
			return false;
		}
		TableModel model = (TableModel)obj;
		return this.name.getValue().equals(model.name.getValue());
	}
}

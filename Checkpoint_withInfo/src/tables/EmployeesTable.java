package tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class EmployeesTable extends AbstractTableModel {
	private int columnCount = 3;
	private ArrayList<String[]> dataArrayList;
	
	public EmployeesTable(ArrayList<String []> dataArrayList) {
		this.dataArrayList = dataArrayList;
	}
	
	public int getRowCount() {
		return dataArrayList.size();
	}

	public int getColumnCount() {
		return columnCount;
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
			case 0:
				return "ID";
			case 1:
				return "Name";
			case 2:
				return "Surname";
		}
		return "";
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		String []rows = dataArrayList.get(rowIndex);
		return rows[columnIndex];
	}

}

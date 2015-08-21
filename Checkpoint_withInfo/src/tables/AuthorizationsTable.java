package tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class AuthorizationsTable extends AbstractTableModel {
	private int columnCount = 2;
	private ArrayList<String[]> dataArrayList;
	
	public AuthorizationsTable(ArrayList<String []> dataArrayList) {
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
				return "Time when came_in";
			case 1:
				return "Time when came_out";
		}
		return "";
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		String []rows = dataArrayList.get(rowIndex);
		return rows[columnIndex];
	}

}

package dialogs;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import database.MyQueries;
import tables.EmployeesTable;

public class EmployeesDialog extends JDialog {
	TableModel model;
	
	public EmployeesDialog(JDialog main, String title){
		super(main,title,true);
		initComponents(title);
	}
	public void initComponents(String title) {
		
		if (title.equals("EMPLOYEES")) {
			model = new EmployeesTable(MyQueries.getAllEmployees());
		}
		if (title.equals("EMPLOYEES INSIDE")) {
			model = new EmployeesTable(MyQueries.getEmployeesInside());
		}
		JTable table = new JTable(model);
		getContentPane().add(new JScrollPane(table));
		
		setPreferredSize(new Dimension(260, 220));
		pack();
		setLocationRelativeTo(null);
	}
}

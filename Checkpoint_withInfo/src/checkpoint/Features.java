package checkpoint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import checkpoint.BackPanel;
import database.MyQueries;
import dialogs.ComeDialog;
import dialogs.EmployeesDialog;
import dialogs.NewDelEmployee;
import dialogs.SimpleDialog;

public class Features extends JDialog {
	private JButton createEmpl, deleteEmpl, emplInfo, whenMax, whoIn, numberIn;
	private JPanel jPan;
	BackPanel picPan1;
	
	public Features(JDialog main) {
		super(main,"FEATURES",true);
		initComponents();
	} 
	
	private void initComponents() {
		createEmpl = new JButton("CREATE NEW EMPLOYEE");
		createEmpl.setMaximumSize(new Dimension(300, 100));
		createEmpl.setMinimumSize(new Dimension(100, 50));
		createEmpl.setPreferredSize(new Dimension(300, 50));
		createEmpl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				NewDelEmployee dialog = new NewDelEmployee(Features.this, "CREATE NEW EMPLOYEE");
				dialog.setVisible(true);
			}
		});
		
		deleteEmpl = new JButton("DELETE EMPLOYEE");
		deleteEmpl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				NewDelEmployee dialog = new NewDelEmployee(Features.this, "DELETE EMPLOYEE");
				dialog.setVisible(true);
			}
		});
		emplInfo = new JButton("EMPLOYEE INFORMATION");
		emplInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JDialog dialog = new EmployeesDialog(Features.this, "EMPLOYEES");
				dialog.setVisible(true);
			}
		});
		
		whenMax = new JButton("WHEN MAXIMUM EMPLOYEES");
		whenMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SimpleDialog dialog = new SimpleDialog(Features.this, "maximum employees time = "+MyQueries.timeWhenMax());
				dialog.setVisible(true);
			}
		});
		
		whoIn = new JButton("EMPLOYEES INSIDE");
		whoIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JDialog dialog = new EmployeesDialog(Features.this, "EMPLOYEES INSIDE");
				dialog.setVisible(true);
			}
		});
		
		numberIn = new JButton("NUMBER OF EMPLOYEES INSIDE");
		numberIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SimpleDialog dialog = new SimpleDialog(Features.this, "amount of employees inside = "+MyQueries.numberOfEmplInside());
				dialog.setVisible(true);
			}
		});
		
		picPan1 = new BackPanel();
		jPan = new JPanel();
		picPan1.setLayout(new BorderLayout());
		picPan1.setImageFile(new File("./src/images/back2.jpg"));
		jPan.setLayout(new GridLayout(3,2,20,70));
		jPan.setOpaque(false);
		
		jPan.add(createEmpl);
		jPan.add(whenMax);
		jPan.add(deleteEmpl);
		jPan.add(whoIn);
		jPan.add(emplInfo);
		jPan.add(numberIn);
		jPan.setBorder(new EmptyBorder(80,5,80,5));
		picPan1.add(jPan);		
		setContentPane(picPan1);
		
		pack();
		setResizable(false);
		
	}
}

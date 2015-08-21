package dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import checkpoint.Features;
import database.MyQueries;

public class NewDelEmployee extends JDialog {
	
	private JTextField loginField, nameField, surnameField;
	private JPasswordField passwordField;
	private JLabel loginLabel, passwordLabel, nameLabel, surnameLabel;
	private String password, name, surname;
	private int id;
	public NewDelEmployee(Features main, String title){
		super(main,title,true);
		initComponents(title);
	}
	
	public void initComponents(String title) {
		Box mainBox = Box.createVerticalBox();
		
		Box box2 = Box.createHorizontalBox();
		nameLabel = new JLabel("Name");
		nameField = new JTextField(15);
		box2.add(nameLabel);
		box2.add(Box.createHorizontalStrut(6));
		box2.add(nameField);
		
		Box box3 = Box.createHorizontalBox();
		surnameLabel = new JLabel("Surname");
		surnameField = new JTextField(15);
		box3.add(surnameLabel);
		box3.add(Box.createHorizontalStrut(6));
		box3.add(surnameField);
		
		if (title.equals("DELETE EMPLOYEE")) {
			Box box1 = Box.createHorizontalBox();
			loginLabel = new JLabel("Your ID");
			loginField = new JTextField(15);
			box1.add(loginLabel);
			box1.add(Box.createHorizontalStrut(6));
			box1.add(loginField);
			mainBox.add(box1);
			mainBox.add(Box.createVerticalStrut(12));
			nameLabel.setPreferredSize(surnameLabel.getPreferredSize());
			loginLabel.setPreferredSize(surnameLabel.getPreferredSize());
		}
		
		mainBox.add(box2);
		mainBox.add(Box.createVerticalStrut(12));
		mainBox.add(box3);
		mainBox.add(Box.createVerticalStrut(12));
		
		if (title.equals("CREATE NEW EMPLOYEE")) {
			Box box4 = Box.createHorizontalBox();
			passwordLabel = new JLabel("Password");
			passwordField = new JPasswordField(15);
			box4.add(passwordLabel);
			box4.add(Box.createHorizontalStrut(6));
			box4.add(passwordField);
			passwordField.enableInputMethods(true);
			mainBox.add(box4);
			mainBox.add(Box.createVerticalStrut(17));
			nameLabel.setPreferredSize(passwordLabel.getPreferredSize());
			surnameLabel.setPreferredSize(passwordLabel.getPreferredSize());
		}
		
		Box box5 = Box.createHorizontalBox();
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				name="";
				surname="";
				
				try {
					name = nameField.getText();
					surname = surnameField.getText();
				} catch (NumberFormatException e) {	}
				
				if (title.equals("DELETE EMPLOYEE")) {
					id = 0;
					try {
						id = Integer. parseInt(loginField.getText());
					} catch (NumberFormatException e) {	}
					if (MyQueries.doesExistsID(id)&&MyQueries.nameSurname(id).equals(name+" "+surname)) {
						MyQueries.deleteEmployee(id);
						SimpleDialog delete = new SimpleDialog(NewDelEmployee.this, "employee deleted");
						delete.setVisible(true);
					}
					else {
						SimpleDialog error = new SimpleDialog(NewDelEmployee.this, "Invalid Operation");
						error.setVisible(true);
					}
				}
				
				else {
					password="";
					try {
						for (int i=0; i<passwordField.getPassword().length; i++) {
							password+=""+passwordField.getPassword()[i];
						}
					} catch (NumberFormatException e) {	}
					
					if (password==""||name==""||surname=="") {
						SimpleDialog error = new SimpleDialog(NewDelEmployee.this, "Invalid operation");
						error.setVisible(true);
					}
					else {
						MyQueries.createEmployee(name, surname, password);
						id = MyQueries.getID(name, surname, password);
						SimpleDialog create = new SimpleDialog(NewDelEmployee.this, "Nice to meet you, "+name+" "+surname+". Your ID is "+id);
						create.setVisible(true);
					}
				}
				
				setVisible(false);
			}
		});
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
			}
		});
		box5.add(Box.createHorizontalGlue());
		box5.add(ok);
		box5.add(Box.createHorizontalStrut(12));
		box5.add(cancel);
		mainBox.add(box5);
	
		mainBox.setBorder(new EmptyBorder(12,12,12,12));
		setContentPane(mainBox);
		pack();
		setResizable(false);
	}
}

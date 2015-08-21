package dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.MyQueries;

public class ComeDialog  extends JDialog {
	private JTextField loginField;
	private JPasswordField passwordField;
	private JLabel loginLabel, passwordLabel;
	private String password;
	private int id;
	public ComeDialog(JFrame main, String title){
		super(main,title,true);
		initComponents(title);
	}
	
	public void initComponents(String title) {
		Box box1 = Box.createHorizontalBox();
		loginLabel = new JLabel("Your ID");
		loginField = new JTextField(15);
		box1.add(loginLabel);
		box1.add(Box.createHorizontalStrut(6));
		box1.add(loginField);
	
		Box box2 = Box.createHorizontalBox();
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField(15);
		box2.add(passwordLabel);
		box2.add(Box.createHorizontalStrut(6));
		box2.add(passwordField);
		passwordField.enableInputMethods(true);
	
		Box box3 = Box.createHorizontalBox();
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				password="";
				id = 0;
				try {
					id = Integer. parseInt(loginField.getText());
					for (int i=0; i<passwordField.getPassword().length; i++) {
						password+=""+passwordField.getPassword()[i];
					}
				} catch (NumberFormatException e) {	}
				setVisible(false);
				
				if (MyQueries.doesExistsID(id)) {
					
					if (!MyQueries.doesRightPSW(id, password)) {
						SimpleDialog notwelcome = new SimpleDialog(ComeDialog.this, "You entered wrong password");
						notwelcome.setVisible(true);
					}
					else {
						if (title.equals("COMING_IN")) {
							if (MyQueries.isInside(id)) {
								SimpleDialog wrong = new SimpleDialog(ComeDialog.this, "ERROR! You are allready inside, "+MyQueries.nameSurname(id));
								wrong.setVisible(true);
							}
							else {
								MyQueries.insertTimeIn(id);
								SimpleDialog welcome = new SimpleDialog(ComeDialog.this, "Welcome, "+MyQueries.nameSurname(id));
								welcome.setVisible(true);
							}
						}
						if (title.equals("COMING_OUT")) {
							if (MyQueries.numberOfEmplInside()==0) {
								SimpleDialog wrong = new SimpleDialog(ComeDialog.this, "ALARM! There are no employees inside!");
								wrong.setVisible(true);
							}
							else if (!MyQueries.isInside(id)) {
								SimpleDialog wrong = new SimpleDialog(ComeDialog.this, "ERROR! You allready had gone away, "+MyQueries.nameSurname(id));
								wrong.setVisible(true);
							}
							else {
								MyQueries.insertTimeOut(id);
								SimpleDialog bye = new SimpleDialog(ComeDialog.this, "Good-bye, "+MyQueries.nameSurname(id));
								bye.setVisible(true);
							}
						}
					}
				}
				else {
					if (password==""||id==0) {
						SimpleDialog notwelcome = new SimpleDialog(ComeDialog.this, "Invalid operation");
						notwelcome.setVisible(true);
						
					}
					else {
						SimpleDialog notwelcome = new SimpleDialog(ComeDialog.this, "There is no such employee");
						notwelcome.setVisible(true);
					}
				}
			}
		});
	
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
			}
		});
		box3.add(Box.createHorizontalGlue());
		box3.add(ok);
		box3.add(Box.createHorizontalStrut(12));
		box3.add(cancel);
	
		loginLabel.setPreferredSize(passwordLabel.getPreferredSize());
	
		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(new EmptyBorder(12,12,12,12));
		mainBox.add(box1);
		mainBox.add(Box.createVerticalStrut(12));
		mainBox.add(box2);
		mainBox.add(Box.createVerticalStrut(17));
		mainBox.add(box3);
		setContentPane(mainBox);
		pack();
		setResizable(false);
	}
}

package dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import checkpoint.Checkpoint;
import checkpoint.Features;

public class PassDialog extends JDialog {
	private JLabel textLabel;
	private JPasswordField passwordField;
	String attempt;
	
	public PassDialog(JFrame main) {
		super(main,null,true);
		
		textLabel = new JLabel("Enter the Password");
		passwordField = new JPasswordField(20);
		passwordField.enableInputMethods(true);
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				attempt="";
				for (int i=0; i<passwordField.getPassword().length; i++) {
					attempt+=passwordField.getPassword()[i];
				}
				setVisible(false);
				if (Checkpoint.correctPasswordFeatures(attempt)) {
					Features feat = new Features(PassDialog.this);
					feat.setVisible(true);
				}
				else {
					SimpleDialog notwelcome = new SimpleDialog(PassDialog.this, "Wrong Password");
					notwelcome.setVisible(true);
				}
			}
		});
		
		JPanel panel = new JPanel();
		JPanel tool = new JPanel();
		panel.add(textLabel);
		panel.add(passwordField);
		tool.add(ok);
		add(panel,BorderLayout.CENTER);
		add(tool,BorderLayout.SOUTH);
		pack();
	}
}
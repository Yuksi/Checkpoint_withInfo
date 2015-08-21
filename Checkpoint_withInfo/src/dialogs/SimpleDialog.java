package dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimpleDialog extends JDialog {
	private JLabel textLabel;
	
	public SimpleDialog(JDialog main, String text) {
		super(main,null,true);
		
		textLabel = new JLabel(text);
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
			}
		});
		
		JPanel panel = new JPanel();
		JPanel tool = new JPanel();
		panel.add(textLabel);
		tool.add(ok);
		add(panel,BorderLayout.CENTER);
		add(tool,BorderLayout.SOUTH);
		pack();
		setResizable(false);
	}
}

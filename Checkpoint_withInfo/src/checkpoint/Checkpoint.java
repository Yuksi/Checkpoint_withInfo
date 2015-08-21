package checkpoint;
 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import dialogs.ComeDialog;
import dialogs.PassDialog;


public class Checkpoint extends JFrame {
	
	private JPanel jPan;
	BackPanel picPan;
	JButton comeIn, comeOut, features;
	private static String passwordFeatures = "123";
	
	public static boolean correctPasswordFeatures(String attempt) {
		if (!attempt.equals(passwordFeatures))
			return false;
		else return true;
	}
	
	private void initComponents() {
		picPan = new BackPanel();
		jPan = new JPanel();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		picPan.setLayout(new BorderLayout());
		picPan.setImageFile(new File("./src/images/back3.jpg"));
		jPan.setLayout(new BoxLayout(jPan,BoxLayout.Y_AXIS));
		jPan.setOpaque(false);
		setResizable(true);
		setTitle("CHECKPOINT");
		
		comeIn = new JButton("emulate COMING_IN");
		comeIn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		comeIn.setMaximumSize(new Dimension(500, 100));
		comeIn.setMinimumSize(new Dimension(20, 20));
		comeIn.setPreferredSize(new Dimension(500, 50));
		
		ActionListener comeIn_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComeDialog comedialog = new ComeDialog(Checkpoint.this, "COMING_IN");
				comedialog.setVisible(true);
			}
		};
		comeIn.addActionListener(comeIn_listener);
		
		comeOut = new JButton("emulate COMING_OUT");
		comeOut.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		comeOut.setMaximumSize(new Dimension(500, 100));
		comeOut.setMinimumSize(new Dimension(100, 50));
		comeOut.setPreferredSize(new Dimension(500, 50));
		
		ActionListener comeOut_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComeDialog comedialog = new ComeDialog(Checkpoint.this, "COMING_OUT");
				comedialog.setVisible(true);
			}
		};
		comeOut.addActionListener(comeOut_listener);
		
		features = new JButton("FEATURES");
		features.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		features.setMaximumSize(new Dimension(500, 100));
		features.setMinimumSize(new Dimension(100, 50));
		features.setPreferredSize(new Dimension(500, 50));
		
		ActionListener features_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PassDialog featuresdial = new PassDialog(Checkpoint.this);
				featuresdial.setVisible(true);
			}
		};
		features.addActionListener(features_listener);
		
		Box box = Box.createVerticalBox();
		
		box.add(comeIn);
		box.add(Box.createVerticalStrut(50));
		box.add(comeOut);
		box.add(Box.createVerticalStrut(50));
		box.add(features);
		
		jPan.add(box);
		jPan.setBorder(new EmptyBorder(50,50,50,50));
		picPan.add(jPan, BorderLayout.NORTH);
		getContentPane().add(picPan, BorderLayout.CENTER);
		pack();
	}
	
	public Checkpoint() {
		initComponents();
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Checkpoint().setVisible(true);
			}
		});
	}
	
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
	JButton button;
	LinkedList<JCheckBox> checkBoxes;
	JTextField textField;
	JLabel lengthText;
	JLabel passwordText;
	JLabel passwordGenerated;
	String password;
	JPanel panel;
	final Font FONT = new Font("Consolas", Font.PLAIN, 20);
	
	public Frame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		//length text field
		lengthText = new JLabel("Length");
		lengthText.setFont(FONT);
		this.add(lengthText);
		textField = new JTextField();
		this.add(textField);
		textField.setMaximumSize(new Dimension(200, 20));

		//checkboxes
		checkBoxes = new LinkedList<JCheckBox>();
		checkBoxes.add(new JCheckBox("Numbers"));
		checkBoxes.add(new JCheckBox("Letters"));
		checkBoxes.add(new JCheckBox("Special Characters"));
		for(JCheckBox checkBox : checkBoxes) {
			checkBox.setFocusable(false);
			checkBox.setFont(FONT);
			this.add(checkBox);
		}
		
		//generate button
		button = new JButton("Generate");
		button.addActionListener(this);
		this.add(button);
		
		//password generated
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		passwordText = new JLabel("Password: ");
		passwordGenerated = new JLabel();
		passwordText.setFont(FONT);
		passwordGenerated.setFont(FONT);
		panel.add(passwordText);
		panel.add(passwordGenerated);
		panel.setAlignmentX(LEFT_ALIGNMENT);
		this.add(panel);
		
		this.pack();
		panel.setVisible(false);
		this.setResizable(false);
		this.setTitle("Password Generator");
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(button)) {
			boolean[] boxVals = new boolean[3];
			for(int i = 0; i < 3; i++) {
				boxVals[i] = checkBoxes.get(i).isSelected();
			}
			System.out.print("\n\n\n\n\n\n\n");
			System.out.println("Length: " + textField.getText());
			System.out.println("Numbers: " + boxVals[0]);
			System.out.println("Letters: " + boxVals[1]);
			System.out.println("Special Chars: " + boxVals[2]);
			
			try {
				passwordGenerated.setText(PasswordGenerator.generatePassword(Integer.parseInt(textField.getText()), boxVals[0], boxVals[1], boxVals[2]));
			} catch (Exception e1) {
				System.out.println("\u001B[31m" + "Invalid Length!" + "\u001B[0m");
			}
			
			//send password to clipboard
			StringSelection str = new StringSelection(passwordGenerated.getText());
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(str, null);
			
			
			panel.setVisible(true);
//			SwingUtilities.updateComponentTreeUI(this);
		}
		
	}

}

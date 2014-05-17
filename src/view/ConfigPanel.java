package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;

public class ConfigPanel extends JPanel {
    private Font police = new Font("Arial", Font.BOLD, 20);
    JLabel inputText = new JLabel("Input repository");
    JLabel outputText = new JLabel("Output repository");
    JLabel maxWidthText = new JLabel("Maximal width");
    JLabel maxHeightText = new JLabel("Maximal height");
    JLabel state = new JLabel("\n");
    JLabel BLANK = new JLabel("");
    JTextField inputTextField = new JTextField();
    JTextField outputTextField = new JTextField();
    JTextField maxWidthTextField = new JTextField("100");
    JTextField maxHeightTextField = new JTextField("100");
    JButton inputButton = new JButton("Browse");
    JButton outputButton = new JButton("Browse");


	public ConfigPanel()  {
 
        // remove redundant default border of check boxes - they would hinder
 
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
 
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(LEADING)
                    	.addComponent(inputText)
                        .addComponent(outputText)
                        .addComponent(BLANK)
                        .addComponent(BLANK)
                        .addComponent(BLANK))
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(inputTextField)
                .addComponent(outputTextField)
                .addComponent(maxWidthText)
                .addComponent(maxHeightText)
                .addComponent(state))
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(inputButton)
                .addComponent(outputButton)
                .addComponent(maxWidthTextField)
                .addComponent(maxHeightTextField)
                .addComponent(BLANK))
        );
        
        //layout.linkSize(SwingConstants.HORIZONTAL, inputButton, cancelButton);
 
        layout.setVerticalGroup(layout.createSequentialGroup()
             .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(inputText)
                        .addComponent(inputTextField)
                        .addComponent(inputButton))
            .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(outputText)
                        .addComponent(outputTextField)
                        .addComponent(outputButton))
            .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(BLANK)
                        .addComponent(maxWidthText)
                        .addComponent(maxWidthTextField))
            .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(BLANK)
                        .addComponent(maxHeightText)
                        .addComponent(maxHeightTextField))
            .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(BLANK)
                        .addComponent(state)
                        .addComponent(BLANK))
        );

        inputButton.addActionListener(new BrowserListener(inputTextField));
        outputButton.addActionListener(new BrowserListener(outputTextField));
    }

	public void initConfig() {
	    /// Config
	    this.setPreferredSize(new Dimension(500, 200));
	    this.setFont(police);
	    this.setBackground(Color.white);
		
	}
	
	public void setStateText(String txt) {
		state.setText(txt);
	}

	public JTextField getInputTextField() {
		return inputTextField;
	}
	public JTextField getOutputTextField() {
		return outputTextField;
	}
	public JTextField getMaxWidthTextField() {
		return maxWidthTextField;
	}
	public JTextField getMaxHeightTextField() {
		return maxHeightTextField;
	}

class BrowserListener implements ActionListener {
	JTextField order;
	public BrowserListener(JTextField to) {
		order = to;
	}
    public void actionPerformed(ActionEvent arg0){
    	// création de la boîte de dialogue
    	JFileChooser chooser = new JFileChooser();
    	chooser.setCurrentDirectory(new java.io.File("."));
    	chooser.setDialogTitle("Select an input file");
    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
    	File f = chooser.getSelectedFile();
    	String path = f.getPath();
    	path = path.replace("/./", "/");
    	if(f!=null) {
    		order.setText(path);
    	}
    }
}
}

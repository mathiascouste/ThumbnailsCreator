package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Reducteur;

public class Window extends JFrame {
	private Reducteur reducteur = new Reducteur(this);
	private JPanel container = new JPanel(new BorderLayout());
	private JButton helpButton = new JButton("HELP");
	private JButton quitButton = new JButton("QUIT");
	private JButton createButton = new JButton("CREATE");
	private JPanel logo = new JPanel(new BorderLayout());
	private ConfigPanel config  = new ConfigPanel();
	private JPanel buttons  = new JPanel(new BorderLayout());
	private Dimension dimButton = new Dimension(145, 145);
    private JLabel image = new JLabel( new ImageIcon( System.getProperty("user.dir")+"/image/logo.png"));
    
	public Window() {
		super();
	    this.setTitle("Thumbnail Creator");
	    this.setUndecorated(true);
	    this.setSize(500, 500);
		this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setContentPane(container);
	    initComposant();
	    this.pack();
	    this.setVisible(true);
	}
	
	private void initComposant() {
		initLogo();
	    
		initButtons();


	    container.add(logo,BorderLayout.NORTH);
	    container.add(config,BorderLayout.CENTER);
	    container.add(buttons,BorderLayout.SOUTH);
	}

	private void initLogo() {
	    /// Logo
	    logo.setPreferredSize(new Dimension(500, 150));
	    logo.add(image);
	}

	private void initButtons() {
	    /// Buttons
	    buttons.setPreferredSize(new Dimension(500, 150));
	    buttons.setBackground(Color.white);

	    helpButton.setPreferredSize(dimButton);
	    helpButton.addActionListener(new HelpListener());
	    buttons.add(helpButton,BorderLayout.WEST);
	    
	    createButton.setPreferredSize(dimButton);
	    createButton.addActionListener(new CreateListener());
	    buttons.add(createButton,BorderLayout.CENTER);
	    
	    quitButton.setPreferredSize(dimButton);
	    quitButton.addActionListener(new QuitListener());
	    buttons.add(quitButton,BorderLayout.EAST);
	}

	class HelpListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0){
	    }
	}
	class CreateListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0){
	    	String from = config.getInputTextField().getText();
	    	String to = config.getOutputTextField().getText();
	    	String width = config.getMaxWidthTextField().getText();
	    	String height = config.getMaxHeightTextField().getText();
	    	int iwi = new Integer(width).intValue();
	    	int ihe = new Integer(height).intValue();

	    	reducteur.setFrom(from);
	    	reducteur.setTo(to);
	    	reducteur.setMaxHeight(ihe);
	    	reducteur.setMaxWidth(iwi);
	    	
	    	Thread th = new Thread(reducteur);
	    	th.start();
	    }
	}
	
	class QuitListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0){
	    	System.exit(0);
	    }
	}
	public void writeState(String string) {
		int max = 30;
		if(string.length() > max) {
			string = string.substring(0,max-4);
			string += " ...";
		}
		config.setStateText(string);
	}
}

package project1_panel;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;

import project2_game.Game;
import project3_tabelSQL.JTable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;


public class Panel {
	
	JLabel background;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel window = new Panel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Panel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("RacingGame");
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("nfs.jpg");
		background = new JLabel("",img,JLabel.CENTER);
		background.setBounds(0, 0, 800, 500);
		frame.getContentPane().add(background);
		frame.getContentPane().setLayout(null);
		
		JButton singleplayer = new JButton("SINGLEPLAYER");
		singleplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Game game = new Game("RacingGame");
//				if(game.handleEvent(game)) {
					
				
//				JTable table = new JTable();	
//				if(game.gameover == true){
//				game.add(JTable);}
		
					
				
			
				
			}
		});
		singleplayer.setBounds(281, 40, 212, 103);
		frame.getContentPane().add(singleplayer);
		
		JButton option = new JButton("OPTION");
		option.setBounds(281, 179, 212, 103);
		frame.getContentPane().add(option);
		
		JButton exit = new JButton("EXIT");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setBounds(281, 322, 212, 98);
		frame.getContentPane().add(exit);
	}

}

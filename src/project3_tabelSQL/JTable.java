package project3_tabelSQL;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import project2_game.Game;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;


public class JTable {

	private JFrame frame;
	private JTextField name;
	private JTextField playerScore;
	private javax.swing.JTable table;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {				
				try {
					JTable window = new JTable();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}});
		}

	/**
	 * Create the application.
	 */
	public JTable() {
		initialize();
	}
      
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frame = new JFrame("Database");
		frame.setBounds(100, 100, 670, 496);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setBounds(0, 0, 654, 457);		
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("NAME:");
		lblNewLabel.setBounds(52, 74, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("YOU SCORE:");
		lblNewLabel_1.setBounds(26, 116, 88, 17);
		panel.add(lblNewLabel_1);

		name = new JTextField();
		name.setBounds(124, 71, 125, 17);
		panel.add(name);
		name.setColumns(10);
		
		
		playerScore = new JTextField();
		playerScore.setBounds(124, 114, 125, 20);
		panel.add(playerScore);
		playerScore.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 48, 319, 387);
		panel.add(scrollPane);

		table = new javax.swing.JTable();
		table.setBackground(new Color(255, 255, 255));
		model = new DefaultTableModel();
		Object[] column = { "NAME", "SCORE" };
		final Object[] row = new Object[2];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);

		JButton btnInsert = new JButton("INSERT");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (name.getText().equals("") || playerScore.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Fill Complete Information");
				} else {
					row[0] = name.getText();
					row[1] = playerScore.getText();
					model.addRow(row);

					name.setText("");
					playerScore.setText("");
					JOptionPane.showMessageDialog(null, "Saved Successfully");
				}
			}
		});
		btnInsert.setBounds(26, 177, 206, 59);
		panel.add(btnInsert);
		
		JButton save = new JButton("SAVE");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				if(tblModel.getRowCount() == 0) {
					//if table is empty than show message
				JOptionPane.showMessageDialog(null, "Table is Empty");
				}else{
					//if table is not empty than perform insertion
					//open and get db connection mysql
					String Name;
					String Score;
					String url, username = null, password = null;
					url = "jdbc:mysql://localhost:3306/racingcar";
					username = "root";
					password = "";
					
					try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, username, password);
					for(int i=0;i<tblModel.getRowCount();i++) {
						Name = tblModel.getValueAt(i, 0).toString();
						Score = tblModel.getValueAt(i, 1).toString();
						//now insert query string
						String query = "insert into player(Name,Score)values(?,?)";
						//in above query subject is table name
						//prepared statement insertion
						PreparedStatement prepstmt = con.prepareStatement(query);
						prepstmt.setString(1, Name);
						prepstmt.setString(2, Score);
						
						prepstmt.execute();						
					}
					//success mesage
					JOptionPane.showMessageDialog(null, "Data insert successfully");
					}catch(Exception en) {
//						en.printStackTrace();
					}
				}
			}
		});
		save.setBounds(26, 282, 104, 40);
		panel.add(save);
		
		JButton exit = new JButton("EXIT FROM GAME");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setBounds(26, 370, 137, 40);
		panel.add(exit);
		
		
		
		
	}
}

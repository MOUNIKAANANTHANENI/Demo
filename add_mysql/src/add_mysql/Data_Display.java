package add_mysql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Data_Display {

	private JFrame frame;
	private JTextField tb1;
	private JTextField tb2;
	private JTable table;
	private JTextField tb3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Data_Display window = new Data_Display();
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
	public Data_Display() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(22, 32, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Marks");
		lblNewLabel_1.setBounds(22, 95, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		tb1 = new JTextField();
		tb1.setBounds(62, 29, 86, 20);
		frame.getContentPane().add(tb1);
		tb1.setColumns(10);
		
		tb2 = new JTextField();
		tb2.setBounds(62, 92, 86, 20);
		frame.getContentPane().add(tb2);
		tb2.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n=tb1.getText();
				String m1=tb2.getText();
				float m=Float.parseFloat(m1);
				try {
					Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/mrecclg","root","mrec");
					String s="insert into stu values('"+n+"','"+m+"')";
					Statement s1=c.createStatement();
					s1.execute(s);
					c.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(43, 148, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(229, 32, 165, 134);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Load");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mrecclg","root","mrec");
					String s="Select * from stu";
					Statement s1=cn.createStatement();
					ResultSet r=s1.executeQuery(s);
					ResultSetMetaData rsmd=r.getMetaData();
					DefaultTableModel m=(DefaultTableModel) table.getModel();
					int col=rsmd.getColumnCount();
					String[] colName=new String[col];
					for(int i=0;i<col;i++)
					{
						colName[i]=rsmd.getColumnName(i+1);
						m.setColumnIdentifiers(colName);
						String n1,m1;
						while(r.next())
						{
							n1=r.getString(1);
							m1=r.getString(2);
							String[] row= {n1,m1};
							m.addRow(row);
						}
					}
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(229, 195, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				
			}
		});
		btnNewButton_2.setBounds(322, 195, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		tb3 = new JTextField();
		tb3.setBounds(62, 196, 86, 20);
		frame.getContentPane().add(tb3);
		tb3.setColumns(10);
		
		
		JButton btnNewButton_3 = new JButton("check");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String n=tb3.getText();
					float n1=Float.parseFloat(n);
					Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/mrecclg","root","mrec");
					String s="select name from stu where marks=?";
					
					PreparedStatement s1=c.prepareStatement(s);
							s1.setFloat(1,n1);
                   ResultSet r=s1.executeQuery(s);
                   
							c.close();
							JOptionPane.showMessageDialog(btnNewButton_3, r.getString(0));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}
		});
		btnNewButton_3.setBounds(153, 227, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		
	}
}

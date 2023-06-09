package Account_data;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class Registration_data {

	private JFrame frame;
	private JTextField t1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration_data window = new Registration_data();
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
	public Registration_data() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.GRAY);
		frame.setBounds(100, 100, 458, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setFont(new Font("Sitka Heading", Font.BOLD, 14));
		lblNewLabel.setBounds(159, 11, 92, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Sitka Display", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 44, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gender");
		lblNewLabel_2.setFont(new Font("Sitka Display", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 73, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Branch");
		lblNewLabel_3.setFont(new Font("Sitka Display", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 108, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(" Language");
		lblNewLabel_4.setFont(new Font("Sitka Display", Font.BOLD, 15));
		lblNewLabel_4.setBounds(10, 136, 72, 23);
		frame.getContentPane().add(lblNewLabel_4);
		
		t1 = new JTextField();
		t1.setBounds(114, 36, 86, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JRadioButton r1 = new JRadioButton("Female");
		r1.setBounds(114, 67, 72, 23);
		frame.getContentPane().add(r1);
		
		JRadioButton r2 = new JRadioButton("Male");
		r2.setBounds(199, 67, 80, 23);
		frame.getContentPane().add(r2);
		ButtonGroup b=new ButtonGroup();
		b.add(r1);
		b.add(r2);
		
		
		
		JComboBox co = new JComboBox();
		co.setFont(new Font("Sitka Display", Font.ITALIC, 15));
		co.setModel(new DefaultComboBoxModel(new String[] {"select", "cse", "csm", "csd", "cscs", "csiot", "ece"}));
		co.setBounds(114, 102, 86, 22);
		frame.getContentPane().add(co);
		
		JCheckBox cb1 = new JCheckBox("Python");
		cb1.setBounds(120, 134, 80, 23);
		frame.getContentPane().add(cb1);
		
		JCheckBox cb2 = new JCheckBox("Java");
		cb2.setBounds(207, 134, 72, 23);
		frame.getContentPane().add(cb2);
		
		JCheckBox cb3 = new JCheckBox("C++");
		cb3.setBounds(283, 134, 80, 23);
		frame.getContentPane().add(cb3);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n=t1.getText();
				String g;
				if(r1.isSelected())
				{
					g="Female";
					
				}
				else if(r2.isSelected())
				{
					g="Male";
				}
				else
				{
					g="invalid";
				}
				String b=(String) co.getSelectedItem();
				String d="";
				if(cb1.isSelected())
				{
					d=d+" python";
									}
				 if(cb2.isSelected())
				{
					d=d+" java";
					
					
				}
				if(cb3.isSelected())
				{
					d=d+" c++";
				}
				try
				{
					Connection	c = DriverManager.getConnection("jdbc:Mysql://localhost:3306/mrecclg","root","mrec");
					String s1="insert into registerdata values('"+n+"','"+g+"','"+b+"','"+d+"')";
					PreparedStatement s=c.prepareStatement(s1);
					ResultSet r=s.executeQuery();
					r.next();
					
					
					c.close();
				}
				catch (SQLException e2)
				{
					e2.printStackTrace();

				}
			
				
				try {
				Connection	c = DriverManager.getConnection("jdbc:Mysql://localhost:3306/mrecclg","root","mrec");
					String s1="insert into registerdata values('"+n+"','"+g+"','"+b+"','"+d+"')";
					Statement s=c.createStatement();
					s.execute(s1);
					c.close();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
				e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(co, "done");
				
				
			}
		});
		btnNewButton.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(159, 194, 103, 23);
		frame.getContentPane().add(btnNewButton);
	}
}

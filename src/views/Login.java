package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;

import assets.User;
import assets.UserCashier;
import dataConnector.DBConn;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Login {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}
	
	//masukkkk
	public void UserAction(UserCashier cashier) {
		frame.dispose();
		Splash splash = new Splash();
		splash.frmPika.setVisible(true);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		splash.frmPika.dispose();
		try {
			TimeUnit.MICROSECONDS.sleep(150);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Cashier cashierScreen = new Cashier(cashier);
		cashierScreen.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 450, 217);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(47, 83, 64, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(47, 121, 64, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(121, 80, 283, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnSubmit = new JButton("Login");
		btnSubmit.setForeground(new Color(102, 255, 255));
		btnSubmit.setBackground(new Color(0, 0, 51));
		btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = txtPassword.getText();
				String username = txtUsername.getText();
				try {
				User loggedIn = DBConn.login(username, password);
				if(loggedIn instanceof UserCashier ) UserAction((UserCashier)loggedIn);
				}
				catch(Exception args) {
					JOptionPane.showMessageDialog(null, "invalid login", "Login Error", JOptionPane.ERROR_MESSAGE);
					txtPassword.setText(null);
					txtUsername.setText(null);
				}
			}
		});
		btnSubmit.setBounds(47, 167, 357, 39);
		frame.getContentPane().add(btnSubmit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(47, 146, 357, 10);
		frame.getContentPane().add(separator);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(121, 118, 283, 20);
		frame.getContentPane().add(txtPassword);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/views/pika10025.png")));
		lblNewLabel_2.setBounds(165, 11, 125, 60);
		frame.getContentPane().add(lblNewLabel_2);
	}
}

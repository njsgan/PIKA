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
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 450, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Bebas Neue", Font.PLAIN, 18));
		lblLogin.setBounds(173, 34, 89, 14);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(47, 73, 64, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(47, 104, 64, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(121, 70, 266, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = txtPassword.getText();
				String username = txtUsername.getText();
				if(password.contains("1234") && username.contains("user")) {
					txtPassword.setText(null);
					txtUsername.setText(null);
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
					Cashier cashier = new Cashier();
					cashier.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid Login", "Login Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSubmit.setBounds(173, 150, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(47, 139, 340, 10);
		frame.getContentPane().add(separator);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(121, 101, 266, 20);
		frame.getContentPane().add(txtPassword);
	}
}

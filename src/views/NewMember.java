package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assets.Member;
import dataConnector.DBConn;
import dataContainer.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewMember extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtPhone;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NewMember frame = new NewMember();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public NewMember() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddNewMember = new JLabel("ADD NEW MEMBER");
		lblAddNewMember.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblAddNewMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewMember.setBounds(15, 16, 398, 37);
		contentPane.add(lblAddNewMember);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(15, 77, 69, 20);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(15, 113, 398, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Address");
		lblNewLabel.setBounds(15, 167, 69, 20);
		contentPane.add(lblNewLabel);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(15, 210, 398, 26);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(15, 268, 69, 20);
		contentPane.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(15, 313, 398, 26);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = txtName.getText();
				String phone = txtPhone.getText();
				String address = txtAddress.getText();
				Member newMember = new Member(name, address, phone);
				String UID = newMember.getUID();
				dispose();
				JOptionPane.showMessageDialog(null, "New member for "+name+" is successful with UID : "+UID	, "Member Registration Successful", JOptionPane.INFORMATION_MESSAGE);
				Cashier.setTxtMember(UID);
				Container.memberList.add(newMember);
				DBConn.NewMember(newMember);
				DBConn.UpdateMembers();
			}
		});
		btnSubmit.setBounds(15, 384, 398, 144);
		contentPane.add(btnSubmit);
	}
}

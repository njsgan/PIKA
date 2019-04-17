package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assets.Member;
import dataConnector.DBConn;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberDetail extends JFrame {

	private JPanel contentPane;
	private JTextField txtUID;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JTextField txtPoint;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MemberDetail frame = new MemberDetail();
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
	public MemberDetail(Member member) {
		setTitle("PiKA Point-of-Sales | Supervisor - Edit Member");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMemberDetails = new JLabel("Edit Member");
		lblMemberDetails.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblMemberDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemberDetails.setBounds(15, 16, 398, 37);
		contentPane.add(lblMemberDetails);
		
		JLabel lblUid = new JLabel("UID");
		lblUid.setBounds(15, 69, 69, 20);
		contentPane.add(lblUid);
		
		txtUID = new JTextField();
		txtUID.setEditable(false);
		txtUID.setBounds(15, 94, 398, 26);
		contentPane.add(txtUID);
		txtUID.setColumns(10);
		txtUID.setText(member.getUID());
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(15, 131, 69, 20);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(15, 160, 398, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		txtName.setText(member.getName());
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(15, 197, 69, 20);
		contentPane.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(15, 228, 398, 26);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		txtPhone.setText(member.getPhone());
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(15, 265, 69, 20);
		contentPane.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(15, 296, 398, 26);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		txtAddress.setText(member.getAddress());
		
		JLabel lblPoint = new JLabel("Point");
		lblPoint.setBounds(15, 333, 69, 20);
		contentPane.add(lblPoint);
		
		txtPoint = new JTextField();
		txtPoint.setBounds(15, 364, 398, 26);
		contentPane.add(txtPoint);
		txtPoint.setColumns(10);
		txtPoint.setText(member.getPhone().toString());
		
		JButton btnSubmit = new JButton("Apply Changes");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member.setName(txtName.getText());
				member.setAddress(txtAddress.getText());
				member.setPoint(Integer.parseInt(txtPoint.getText().toString()));
				member.setPhone(txtPhone.getText());
				DBConn.UpdateMembers();
				Supervisor.UpdateMembersList();
				dispose();
			}
		});
		btnSubmit.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnSubmit.setBounds(15, 420, 398, 74);
		contentPane.add(btnSubmit);
	}
}

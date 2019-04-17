package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assets.Item;
import dataConnector.DBConn;
import dataContainer.Container;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddItem extends JFrame {

	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtStock;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddItem frame = new AddItem();
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
	public AddItem() {
		setTitle("PiKA Point-of-Sales | Supervisor - Add New Item(s)");
		setBounds(100, 100, 450, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblAddNewItem = new JLabel("Please fill this form");
		lblAddNewItem.setFont(new Font("Segoe UI", Font.PLAIN, 31));
		lblAddNewItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewItem.setBounds(15, 16, 398, 52);
		contentPane.add(lblAddNewItem);
		
		JLabel lblItemCode = new JLabel("Item Code");
		lblItemCode.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblItemCode.setBounds(15, 108, 115, 20);
		contentPane.add(lblItemCode);
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblItemName.setBounds(15, 176, 138, 20);
		contentPane.add(lblItemName);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblPrice.setBounds(15, 244, 69, 20);
		contentPane.add(lblPrice);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblStock.setBounds(15, 312, 69, 20);
		contentPane.add(lblStock);
		
		txtCode = new JTextField();
		txtCode.setBounds(15, 139, 398, 26);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(15, 207, 398, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(15, 275, 398, 26);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(15, 343, 398, 26);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		JButton btnAdd = new JButton("Add Once");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String code = txtCode.getText();
				String name = txtName.getText();
				Integer price = Integer.parseInt(txtPrice.getText().toString());
				Integer stock = Integer.parseInt(txtStock.getText().toString());
				Container.items.add(new Item(code, name, price, stock));
				DBConn.InsertItemtoDB(code, name, price, stock);
				Supervisor.updateItemList();
				dispose();
			}
		});
		btnAdd.setBounds(15, 400, 193, 76);
		contentPane.add(btnAdd);
		
		JButton btnAddNew = new JButton("Add More");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = txtCode.getText();
				String name = txtName.getText();
				Integer price = Integer.parseInt(txtPrice.getText().toString());
				Integer stock = Integer.parseInt(txtStock.getText().toString());
				Container.items.add(new Item(code, name, price, stock));
				DBConn.InsertItemtoDB(code, name, price, stock);
				Supervisor.updateItemList();
				txtCode.setText(null);
				txtName.setText(null);;
				txtPrice.setText(null);
				txtStock.setText(null);
			}
		});
		btnAddNew.setBounds(220, 400, 193, 76);
		contentPane.add(btnAddNew);
	}
}

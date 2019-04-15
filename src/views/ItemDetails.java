package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assets.Item;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemDetails extends JFrame {

	private JPanel contentPane;
	private JTextField itemCode;
	private JTextField itemName;
	private JTextField itemStock;
	private JTextField itemPrice;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ItemDetails frame = new ItemDetails();
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
	public ItemDetails(Item item) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblItemDetails = new JLabel("Item Details");
		lblItemDetails.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblItemDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemDetails.setBounds(15, 16, 398, 39);
		contentPane.add(lblItemDetails);
		
		JLabel lblItemCode = new JLabel("Item Code");
		lblItemCode.setBounds(15, 96, 114, 20);
		contentPane.add(lblItemCode);
		
		itemCode = new JTextField();
		itemCode.setEditable(false);
		itemCode.setBounds(15, 132, 398, 26);
		contentPane.add(itemCode);
		itemCode.setColumns(10);
		itemCode.setText(item.getCode());
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setBounds(15, 174, 133, 20);
		contentPane.add(lblItemName);
		
		itemName = new JTextField();
		itemName.setBounds(15, 210, 398, 26);
		contentPane.add(itemName);
		itemName.setColumns(10);
		itemName.setText(item.getName());
		
		JLabel lblItemStock = new JLabel("Item Stock");
		lblItemStock.setBounds(15, 252, 152, 20);
		contentPane.add(lblItemStock);
		
		itemStock = new JTextField();
		itemStock.setBounds(15, 288, 398, 26);
		contentPane.add(itemStock);
		itemStock.setColumns(10);
		itemStock.setText(item.getStock().toString());
		
		JLabel lblItemPrice = new JLabel("Item Price");
		lblItemPrice.setBounds(15, 330, 152, 20);
		contentPane.add(lblItemPrice);
		
		itemPrice = new JTextField();
		itemPrice.setBounds(15, 366, 398, 26);
		contentPane.add(itemPrice);
		itemPrice.setColumns(10);
		itemPrice.setText(item.getPrice().toString());
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newName = itemName.getText();
				Integer newPrice = Integer.parseInt(itemPrice.getText().toString());
				Integer newStock = Integer.parseInt(itemStock.getText().toString());
				item.setName(newName);
				item.setPrice(newPrice);
				item.setStock(newStock);
				Supervisor.updateItemList();
				dispose();
			}
		});
		btnSubmit.setFont(new Font("SimSun", Font.PLAIN, 38));
		btnSubmit.setBounds(15, 408, 398, 120);
		contentPane.add(btnSubmit);
	}
}

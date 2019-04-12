package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import assets.Item;
import assets.Transaction;
import assets.TransactionHistory;
import assets.UserSupervisor;
import dataContainer.Container;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Supervisor extends JFrame {

	private JPanel contentPane;
	private JTable itemsList;
	private JTable transactionsList;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Supervisor frame = new Supervisor();
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
	private void updateItemList() {
		String col[] = {"Code", "Name", "Price", "Stock"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0){
			public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		itemsList.setModel(tableModel);
		//adding items :)
		for(Item item : Container.items) {
			String code = item.getCode();
			String name = item.getName();
			Integer price = item.getPrice();
			Integer stock = item.getStock();
			Object[] obj = {code, name, price, stock};
			tableModel.addRow(obj);
		}
	}
	
	private void updateTransactionList() {
		String col[] = {"TrxID", "Cashier", "Revenue"};
		DefaultTableModel tableModel = new DefaultTableModel(col,0) {
			public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		transactionsList.setModel(tableModel);
		for(TransactionHistory history : Container.transactionHistory) {
			String trxID = history.getID();
			String cashier = history.getCashier();
			Integer revenue = history.getRevenue();
			Object[] obj = {trxID, cashier, revenue};
			tableModel.addRow(obj);
		}
	}
	public Supervisor(UserSupervisor supervisor) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGreeting = new JLabel("Hi, "+supervisor.getFname());
		lblGreeting.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblGreeting.setBounds(28, 11, 135, 51);
		contentPane.add(lblGreeting);
		
		JLabel lblItemsList = new JLabel("Items List");
		lblItemsList.setBounds(28, 73, 46, 14);
		contentPane.add(lblItemsList);
		
		JLabel lblTransactionsList = new JLabel("Transactions List");
		lblTransactionsList.setBounds(421, 73, 110, 14);
		contentPane.add(lblTransactionsList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 98, 359, 452);
		contentPane.add(scrollPane);
		
		itemsList = new JTable();
		scrollPane.setViewportView(itemsList);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(421, 98, 369, 452);
		contentPane.add(scrollPane_1);
		
		transactionsList = new JTable();
		scrollPane_1.setViewportView(transactionsList);
		setLocationRelativeTo(null);
		
		updateItemList();
		updateTransactionList();
	}
}

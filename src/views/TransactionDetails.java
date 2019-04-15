package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import assets.TransactionHistory;
import dataConnector.DBConn;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TransactionDetails extends JFrame {

	private JPanel contentPane;
	private JTable detailsList;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TransactionDetails frame = new TransactionDetails();
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
	public TransactionDetails(TransactionHistory trx) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTransactionDetails = new JLabel("Transaction Details");
		lblTransactionDetails.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblTransactionDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransactionDetails.setBounds(15, 16, 448, 45);
		contentPane.add(lblTransactionDetails);
		
		JLabel trxID = new JLabel(trx.getID());
		trxID.setHorizontalAlignment(SwingConstants.CENTER);
		trxID.setBounds(25, 56, 438, 29);
		contentPane.add(trxID);
		
		JLabel lblCashier = new JLabel("Cashier : ");
		lblCashier.setBounds(15, 101, 69, 20);
		contentPane.add(lblCashier);
		
		JLabel cashier = new JLabel(trx.getCashier());
		cashier.setBounds(99, 101, 253, 20);
		contentPane.add(cashier);
		
		JLabel lblTotalRevenue = new JLabel("Total Revenue");
		lblTotalRevenue.setBounds(15, 126, 121, 20);
		contentPane.add(lblTotalRevenue);
		
		JLabel revenue = new JLabel(trx.getRevenue().toString());
		revenue.setBounds(133, 126, 237, 20);
		contentPane.add(revenue);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 162, 448, 366);
		contentPane.add(scrollPane);
		
		detailsList = new JTable();
		String[] col = {"Item", "Name", "Price", "Quantity", "Total"};
		DefaultTableModel tableModel = new DefaultTableModel(col,0) {
			public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		detailsList.setModel(tableModel);
		for(int i = 0; i<trx.getItems().size(); i++) {
			String item = trx.getItems().get(i);
			Integer price = trx.getPrices().get(i);
			Integer qty = trx.getQtys().get(i);
			Integer total = price*qty;
			String name = DBConn.getItemNameFromDB(item);
			Object[] obj = {item, name, price, qty, total};
			tableModel.addRow(obj);
		}
		scrollPane.setViewportView(detailsList);
	}
}

package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import assets.Items;
import assets.Purchase;
import assets.Transaction;
import dataContainer.Container;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Cashier extends JFrame {

	private JPanel contentPane;
	private JTextField txtFind;
	private JTable itemList;
	private JTable purchasesList;
	private JTextField textField_1;
	private JTextField txtQty;
	private JLabel lblTotalTop;
	private JLabel lblTotalBottom;
	
	private ArrayList<Items> items = new ArrayList<Items>();
	private ArrayList<Purchase> purchases = new ArrayList<Purchase>();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Cashier frame = new Cashier();
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
	
	private void AddItems() {
		items.add(new Items("123", "Item 1", 50000, 50));
		items.add(new Items("234", "Item 2", 50000, 50));
		items.add(new Items("345", "Item 3", 50000, 50));
	}
	
	private void AddPurchase(Items item, Integer qty) {
		purchases.add(new Purchase(item, qty));
	}
	
	private void SetTotal() {
		Integer total = 0;
		for(Purchase purchase : purchases) {
			total += purchase.getItem().getPrice() * purchase.getQty();
		}
		lblTotalTop.setText(total.toString());
		lblTotalBottom.setText(total.toString());
	}
	
	private void UpdatePurchaseList() {
		String colP[] = {"Name", "Price", "Qty"};
		DefaultTableModel purchaseModel = new DefaultTableModel(colP, 0);
		purchasesList.setModel(purchaseModel);
		for(Purchase purchase : purchases) {
			String name = purchase.getItem().getName();
			Integer price = purchase.getItem().getPrice();
			Integer qty = purchase.getQty();
			Object[] obj = {name, price, qty};
			purchaseModel.addRow(obj);
		}
	}
	
	private Items findItem(String code) {
		for(Items item : items) {
			if(item.getCode().equals(code)) return item;
		}
		return null;
	}
	
	private Purchase findItemToDelete(String name) {
		for(Purchase purchase : purchases) {
			if(purchase.getItem().getName().equals(name)) return purchase;
		}
		return null;
	}
	public Cashier() {
		AddItems();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 255));
		panel.setBounds(10, 11, 864, 60);
		contentPane.add(panel);
		
		JLabel lblTotal = new JLabel("Total : ");
		lblTotal.setForeground(new Color(255, 255, 255));
		lblTotal.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 34));
		panel.add(lblTotal);
		
		lblTotalTop = new JLabel("Rp. XXXXX");
		lblTotalTop.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 34));
		lblTotalTop.setForeground(new Color(255, 255, 255));
		panel.add(lblTotalTop);
		
		JLabel lblCashier = new JLabel("Cashier :");
		lblCashier.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCashier.setBounds(10, 82, 72, 14);
		contentPane.add(lblCashier);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(92, 82, 88, 14);
		contentPane.add(lblName);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 107, 864, 10);
		contentPane.add(separator);
		
		txtFind = new JTextField();
		txtFind.setBounds(10, 121, 296, 20);
		contentPane.add(txtFind);
		txtFind.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.setBounds(316, 120, 64, 23);
		contentPane.add(btnFind);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 152, 367, 289);
		contentPane.add(scrollPane);

		//items list table here
		String col[] = {"Code", "Name", "Price", "Stock"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		itemList = new JTable();
		itemList.setModel(tableModel);
		//adding items :)
		for(Items item : items) {
			String code = item.getCode();
			String name = item.getName();
			Integer price = item.getPrice();
			Integer stock = item.getStock();
			Object[] obj = {code, name, price, stock};
			tableModel.addRow(obj);
		}
		scrollPane.setViewportView(itemList);
		//selection
		//single selection
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		itemList.setRowSelectionInterval(0, 0);
		//get selected
		
		
		JLabel lblListBelanja = new JLabel("List Belanja");
		lblListBelanja.setFont(new Font("Maiandra GD", Font.PLAIN, 16));
		lblListBelanja.setBounds(414, 122, 189, 14);
		contentPane.add(lblListBelanja);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(414, 152, 367, 289);
		contentPane.add(scrollPane_1);
		
		purchasesList = new JTable();
		
		scrollPane_1.setViewportView(purchasesList);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setFont(new Font("SimSun", Font.PLAIN, 18));
		lblTotal_1.setBounds(414, 452, 72, 20);
		contentPane.add(lblTotal_1);
		
		JLabel lblPay = new JLabel("Pay");
		lblPay.setFont(new Font("SimSun", Font.PLAIN, 18));
		lblPay.setBounds(414, 483, 46, 20);
		contentPane.add(lblPay);
		
		textField_1 = new JTextField();
		textField_1.setBounds(479, 483, 149, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblReturn = new JLabel("Return");
		lblReturn.setFont(new Font("SimSun", Font.PLAIN, 18));
		lblReturn.setBounds(414, 514, 64, 23);
		contentPane.add(lblReturn);
		
		lblTotalBottom = new JLabel("Rp. xxxx");
		lblTotalBottom.setFont(new Font("SimSun", Font.PLAIN, 18));
		lblTotalBottom.setBounds(480, 452, 88, 20);
		contentPane.add(lblTotalBottom);
		
		JLabel lblReturnValue = new JLabel("Rp. xxxx");
		lblReturnValue.setFont(new Font("SimSun", Font.PLAIN, 18));
		lblReturnValue.setBounds(479, 514, 72, 23);
		contentPane.add(lblReturnValue);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setFont(new Font("SimSun", Font.PLAIN, 34));
		lblQty.setBounds(10, 502, 64, 48);
		contentPane.add(lblQty);
		
		txtQty = new JTextField();
		txtQty.setFont(new Font("SimSun", Font.PLAIN, 34));
		txtQty.setText("1");
		txtQty.setBounds(114, 513, 86, 30);
		contentPane.add(txtQty);
		txtQty.setColumns(10);
		
		
		JButton btnAdd = new JButton("ADD!!");
		
		btnAdd.setBounds(289, 489, 88, 48);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		
		btnRemove.setBounds(698, 128, 83, 23);
		contentPane.add(btnRemove);
		
		JButton btnCheckout = new JButton("Checkout");
		
		btnCheckout.setBounds(649, 452, 131, 85);
		contentPane.add(btnCheckout);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setFont(new Font("SimSun", Font.PLAIN, 34));
		lblItem.setBounds(10, 469, 88, 39);
		contentPane.add(lblItem);
		
		JLabel lblItemName = new JLabel("Name");
		lblItemName.setFont(new Font("SimSun", Font.PLAIN, 34));
		lblItemName.setBounds(114, 469, 116, 39);
		contentPane.add(lblItemName);
		
		
		//find item when clicked
		itemList.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int rowIndex = itemList.getSelectedRow();
					Items selected = findItem((String)itemList.getValueAt(rowIndex, 0));
					lblItemName.setText(selected.getName());
				}
				catch(Exception e1) {};
			}
		});
		
		//find item with search
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = -1;
				for(int i = 0; i<itemList.getRowCount(); i++) {
					if(txtFind.getText().equals(itemList.getValueAt(i, 0)) || txtFind.getText().equals(itemList.getValueAt(i, 1))) {
						idx = i;
						break;
					}
				}
				if(idx!=-1) {
					itemList.setRowSelectionInterval(idx, idx);
					Items selected = findItem((String)itemList.getValueAt(idx, 0));
					lblItemName.setText(selected.getName());
				}
				else {
					txtFind.setText(null);
					lblItemName.setText("Name");
					itemList.getSelectionModel().clearSelection();
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer qty = Integer.parseInt(txtQty.getText());
				int rowIndex = itemList.getSelectedRow();
				Items selected = findItem((String)itemList.getValueAt(rowIndex, 0));
				AddPurchase(selected, qty);
				UpdatePurchaseList();		
				SetTotal();
			}});
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowIndex = purchasesList.getSelectedRow();
				Purchase selected = findItemToDelete(purchasesList.getValueAt(rowIndex, 0).toString());
				purchases.remove(selected);
				UpdatePurchaseList();
				SetTotal();
			}
		});
		
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!purchases.isEmpty()) {
					Container.transactions.add(new Transaction("abc123", purchases, null));
					purchases.clear();
					UpdatePurchaseList();
					SetTotal();
				}
			}
		});
	}
}

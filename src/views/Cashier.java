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
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

public class Cashier extends JFrame {

	private JPanel contentPane;
	private JTextField txtFind;
	private JTable itemList;
	private JTable purchasesList;
	private JTextField textField_1;
	private JTextField txtQty;
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
		setResizable(false);
		setTitle("PiKA Point-of-Sales");
		AddItems();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCashier = new JLabel("Cashier :");
		lblCashier.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblCashier.setBounds(30, 82, 72, 21);
		contentPane.add(lblCashier);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblName.setBounds(112, 82, 88, 21);
		contentPane.add(lblName);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 107, 864, 10);
		contentPane.add(separator);
		
		txtFind = new JTextField();
		txtFind.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtFind.setBounds(10, 121, 296, 20);
		contentPane.add(txtFind);
		txtFind.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
		
		
		JLabel lblListBelanja = new JLabel("Purchased Item(s)  :");
		lblListBelanja.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblListBelanja.setBounds(414, 122, 189, 14);
		contentPane.add(lblListBelanja);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(414, 152, 367, 289);
		contentPane.add(scrollPane_1);
		
		purchasesList = new JTable();
		
		scrollPane_1.setViewportView(purchasesList);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTotal_1.setBounds(414, 452, 72, 20);
		contentPane.add(lblTotal_1);
		
		JLabel lblPay = new JLabel("Pay");
		lblPay.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPay.setBounds(414, 483, 46, 20);
		contentPane.add(lblPay);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField_1.setBounds(520, 487, 105, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblReturn = new JLabel("Return");
		lblReturn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblReturn.setBounds(414, 514, 64, 23);
		contentPane.add(lblReturn);
		
		lblTotalBottom = new JLabel("Rp. xxxx");
		lblTotalBottom.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTotalBottom.setBounds(480, 452, 88, 20);
		contentPane.add(lblTotalBottom);
		
		JLabel lblReturnValue = new JLabel("Rp. xxxx");
		lblReturnValue.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblReturnValue.setBounds(479, 514, 72, 23);
		contentPane.add(lblReturnValue);
		
		JLabel lblQty = new JLabel("Qty   :");
		lblQty.setFont(new Font("Segoe UI", Font.PLAIN, 34));
		lblQty.setBounds(10, 507, 88, 48);
		contentPane.add(lblQty);
		
		txtQty = new JTextField();
		txtQty.setFont(new Font("Segoe UI", Font.PLAIN, 34));
		txtQty.setText("1");
		txtQty.setBounds(114, 511, 86, 39);
		contentPane.add(txtQty);
		txtQty.setColumns(10);
		
		
		JButton btnAdd = new JButton("Add to Cart");
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		btnAdd.setBounds(240, 503, 135, 48);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		btnRemove.setBounds(698, 120, 83, 23);
		contentPane.add(btnRemove);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		btnCheckout.setBounds(649, 452, 131, 85);
		contentPane.add(btnCheckout);
		
		JLabel lblItem = new JLabel("Item :");
		lblItem.setFont(new Font("Segoe UI", Font.PLAIN, 34));
		lblItem.setBounds(8, 467, 94, 39);
		contentPane.add(lblItem);
		
		JLabel lblItemName = new JLabel("Name");
		lblItemName.setFont(new Font("Segoe UI", Font.PLAIN, 34));
		lblItemName.setBounds(114, 467, 116, 39);
		contentPane.add(lblItemName);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Cashier.class.getResource("/views/pika10025.png")));
		lblNewLabel.setBounds(37, 11, 131, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblNamaPerusahaanJeleque = new JLabel("NAMA PERUSAHAAN");
		lblNamaPerusahaanJeleque.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNamaPerusahaanJeleque.setHorizontalAlignment(SwingConstants.LEFT);
		lblNamaPerusahaanJeleque.setBounds(178, 11, 603, 39);
		contentPane.add(lblNamaPerusahaanJeleque);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(178, 48, 602, 2);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_1 = new JLabel("<html>\r\nJl. Alamat Perusahaan no. Nomor Rumah Perusahaan, Bantul, D.I.Y.<br/>\r\nPhone : +62 69696969\r\n</html>");
		lblNewLabel_1.setBounds(178, 52, 603, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblRp = new JLabel("Rp.");
		lblRp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblRp.setBounds(480, 486, 30, 23);
		contentPane.add(lblRp);
		
		
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

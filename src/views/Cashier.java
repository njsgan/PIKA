package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;

import assets.Item;
import assets.Company;
import assets.Purchase;
import assets.Transaction;
import assets.User;
import assets.UserCashier;
import dataConnector.DBConn;
import dataContainer.Container;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListenerProxy;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Cashier extends JFrame {

	private JPanel contentPane;
	private JTextField txtFind;
	private JTable itemList;
	private JTable purchasesList;
	private JFormattedTextField textFieldPay;
	private JFormattedTextField txtQty;
	private JLabel lblTotalBottom;
	private NumberFormat formatTotal = NumberFormat.getIntegerInstance(Locale.GERMAN);
	private NumberFormat format = NumberFormat.getIntegerInstance();
    private NumberFormatter formatter = new NumberFormatter(format);
    private Integer total = 0;
	
	private ArrayList<Purchase> purchases = new ArrayList<Purchase>();
	
	private JTextField fieldBox = new JTextField();
	private JPasswordField fieldBoxPswd = new JPasswordField();;
	
	private Company company = DBConn.readData();
	private JTextField txtMember;

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
	
	// Box buat ID Supervisor
	private JPanel getPanel()
	{
	    JPanel basePanel = new JPanel();

	    JPanel centerPanel = new JPanel();
	    centerPanel.setLayout(new GridLayout(3, 2, 5, 5));

	    JLabel labelBox = new JLabel("Supervisor ID : ");
	    JLabel labelBoxPswd = new JLabel("Password : ");

	    // TODO : Focus ke FieldBox 
	    centerPanel.add(labelBox);
	    centerPanel.add(fieldBox);
	    centerPanel.add(labelBoxPswd);
	    centerPanel.add(fieldBoxPswd);
	    

	    basePanel.add(centerPanel);


	    return basePanel;
	}
	
	private void AddItems() {
		Container.items.add(new Item("123", "Item 1", 50000, 50));
		Container.items.add(new Item("234", "Item 2", 50000, 50));
		Container.items.add(new Item("345", "Item 3", 50000, 50));
	}
	
	private void UpdateList() {
		String col[] = {"Code", "Name", "Price", "Stock"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0){
			public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		itemList.setModel(tableModel);
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
	
	private void AddPurchase(Item item, Integer qty) {
		purchases.add(new Purchase(item, qty));
	}
	
	private void SetTotal() {
		total = 0;
		for(Purchase purchase : purchases) {
			total += purchase.getItem().getPrice() * purchase.getQty();
		}
		lblTotalBottom.setText(formatTotal.format(total));
	}
	
	private void UpdatePurchaseList() {
		String colP[] = {"Name", "Price", "Qty"};
		DefaultTableModel purchaseModel = new DefaultTableModel(colP, 0) {
			public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		purchasesList.setModel(purchaseModel);
		for(Purchase purchase : purchases) {
			String name = purchase.getItem().getName();
			Integer price = purchase.getItem().getPrice();
			Integer qty = purchase.getQty();
			Object[] obj = {name, price, qty};
			purchaseModel.addRow(obj);
		}
	}
	
	private Item findItem(String code) {
		for(Item item : Container.items) {
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
	
	
	public Cashier(UserCashier cashier) {
//		AddItems();
		setResizable(false);
		setTitle("PiKA Point-of-Sales");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
//		addWindowListener(new java.awt.event.WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				// TODO Auto-generated method stub
//				DBConn.UpdateTrxDB();
//			}
//		});
		
		
		JLabel lblCashier = new JLabel("Cashier :");
		lblCashier.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblCashier.setBounds(566, 11, 72, 21);
		contentPane.add(lblCashier);
		
		JLabel lblName = new JLabel(cashier.getFname());
		lblName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblName.setBounds(565, 37, 178, 21);
		contentPane.add(lblName);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 179, 864, 10);
		contentPane.add(separator);
		
		txtFind = new JTextField();
		txtFind.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtFind.setBounds(10, 198, 296, 20);
		contentPane.add(txtFind);
		txtFind.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnFind.setBounds(316, 197, 64, 23);
		contentPane.add(btnFind);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 229, 367, 289);
		contentPane.add(scrollPane);

		//items list table here

		itemList = new JTable();
		UpdateList();
		scrollPane.setViewportView(itemList);
		//selection
		//single selection
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		itemList.setRowSelectionInterval(0, 0);
		//get selected
		
		
		JLabel lblListBelanja = new JLabel("Purchased Item(s)  :");
		lblListBelanja.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblListBelanja.setBounds(414, 199, 189, 14);
		contentPane.add(lblListBelanja);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(414, 229, 367, 289);
		contentPane.add(scrollPane_1);
		
		purchasesList = new JTable();
		
		scrollPane_1.setViewportView(purchasesList);
		
		JLabel lblTotal_1 = new JLabel("Total : Rp.");
		lblTotal_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTotal_1.setBounds(37, 82, 105, 20);
		contentPane.add(lblTotal_1);
		
		JLabel lblPay = new JLabel("Pay");
		lblPay.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPay.setBounds(414, 573, 46, 20);
		contentPane.add(lblPay);
		
		format.setGroupingUsed(false);
		formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    // If you want the value to be committed on each keystroke instead of focus lost
	    formatter.setCommitsOnValidEdit(true);
	    
		textFieldPay = new JFormattedTextField(formatter);
		textFieldPay.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldPay.setBounds(520, 577, 105, 20);
		contentPane.add(textFieldPay);
		textFieldPay.setColumns(10);
		
		
		// Listen to change on amount payment [KEMBALIAN]
		
//		PropertyChangeListener l = new PropertyChangeListener() {
//
//			@Override
//			public void propertyChange(PropertyChangeEvent evt) {
//				Integer pembayaran = Integer.parseInt(textField_1.getText());
//				Integer total = 0;
//				for(Purchase purchase : purchases) {
//					total += purchase.getItem().getPrice() * purchase.getQty();
//				}
//				// TODO Auto-generated method stub
//				Integer kembalian = pembayaran - total;
//			}
//	    };
//	    
//	    textField_1.addPropertyChangeListener("value", l);
		
		JLabel lblReturn = new JLabel("Return");
		lblReturn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblReturn.setBounds(414, 604, 64, 23);
		contentPane.add(lblReturn);
		
		lblTotalBottom = new JLabel("0");
		lblTotalBottom.setFont(new Font("Segoe UI", Font.BOLD, 60));
		lblTotalBottom.setBounds(36, 113, 519, 55);
		contentPane.add(lblTotalBottom);
		
		JLabel lblReturnValue = new JLabel("Rp. 0");
		lblReturnValue.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblReturnValue.setBounds(479, 604, 146, 23);
		contentPane.add(lblReturnValue);
		
		textFieldPay.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				String payment = textFieldPay.getText().toString();
				if(textFieldPay.getText()==null) payment = "0";
				Integer pay = Integer.parseInt(payment);
				if((pay-total) > 0) lblReturnValue.setText("Rp. "+formatTotal.format(pay-total));
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					textFieldPay.setValue(null);
					lblReturnValue.setText("-");
				}
			}
		});
		
		JLabel lblQty = new JLabel("Qty   :");
		lblQty.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblQty.setBounds(10, 582, 88, 48);
		contentPane.add(lblQty);
		
	    txtQty = new JFormattedTextField(formatter);
		txtQty.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		txtQty.setText("1");
		txtQty.setBounds(114, 588, 105, 39);
		contentPane.add(txtQty);
		txtQty.setColumns(10);
		
		
		JButton btnAdd = new JButton("Add to Cart");
		btnAdd.setForeground(new Color(102, 255, 255));
		btnAdd.setBackground(new Color(0, 0, 51));
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		btnAdd.setBounds(240, 544, 135, 84);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		btnRemove.setBounds(698, 197, 83, 23);
		contentPane.add(btnRemove);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setForeground(new Color(102, 255, 255));
		btnCheckout.setBackground(new Color(0, 0, 51));
		btnCheckout.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		btnCheckout.setBounds(650, 542, 131, 84);
		contentPane.add(btnCheckout);
		
		JLabel lblItemName = new JLabel("No item selected..");
		lblItemName.setFont(new Font("Segoe UI", Font.PLAIN, 27));
		lblItemName.setBounds(10, 544, 220, 39);
		contentPane.add(lblItemName);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Cashier.class.getResource("/views/pika10025.png")));
		lblNewLabel.setBounds(37, 11, 131, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblNamaPerusahaanJeleque = new JLabel(company.getName());
		lblNamaPerusahaanJeleque.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNamaPerusahaanJeleque.setHorizontalAlignment(SwingConstants.LEFT);
		lblNamaPerusahaanJeleque.setBounds(178, 0, 390, 39);
		contentPane.add(lblNamaPerusahaanJeleque);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(178, 37, 373, 2);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_1 = new JLabel("<html>\r\n"+ company.getAddress() +"<br/>\r\nPhone : "+ company.getPhone() +", Fax : "+company.getFax()+"\r\n</html>");
		lblNewLabel_1.setBounds(178, 41, 390, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblRp = new JLabel("Rp.");
		lblRp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblRp.setBounds(480, 576, 30, 23);
		contentPane.add(lblRp);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"CASH", "DEBIT", "CREDIT"}));
		comboBox.setBounds(414, 542, 211, 20);
		contentPane.add(comboBox);
		
		JLabel lblMemberId = new JLabel("Member ID :");
		lblMemberId.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblMemberId.setBounds(566, 69, 105, 21);
		contentPane.add(lblMemberId);
		
		txtMember = new JTextField();
		txtMember.setBounds(566, 97, 215, 30);
		contentPane.add(txtMember);
		txtMember.setColumns(10);
		
		JLabel lblMemberName = new JLabel("Non Member");
		lblMemberName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMemberName.setBounds(566, 138, 131, 14);
		contentPane.add(lblMemberName);
		
		JButton btnMember = new JButton("Apply");
		btnMember.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnMember.setBounds(709, 138, 72, 23);
		contentPane.add(btnMember);
		
		JLabel lblPoint = new JLabel("Point :");
		lblPoint.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPoint.setBounds(566, 154, 46, 14);
		contentPane.add(lblPoint);
		
		JLabel lblPointValue = new JLabel("0");
		lblPointValue.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPointValue.setBounds(625, 153, 46, 14);
		contentPane.add(lblPointValue);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				
				Object selected = comboBox.getSelectedItem();
				
				if(selected.equals("CASH")) {
					textFieldPay.setEditable(true);
				}
				else {
					textFieldPay.setEditable(false);
				}
				
			}
		});
		
		
		//find item when clicked
		itemList.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int rowIndex = itemList.getSelectedRow();
					Item selected = findItem((String)itemList.getValueAt(rowIndex, 0));
					lblItemName.setText(selected.getName());
					txtQty.requestFocus();
				}
				catch(Exception e1) {};
			}
		});
		
		//find item with search
		AbstractAction actionSearch = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	int idx = -1;
				for(int i = 0; i<itemList.getRowCount(); i++) {
					if(txtFind.getText().equals(itemList.getValueAt(i, 0)) || txtFind.getText().equals(itemList.getValueAt(i, 1))) {
						idx = i;
						txtFind.setText("");
						break;
					}
				}
				if(idx!=-1) {
					itemList.setRowSelectionInterval(idx, idx);
					Item selected = findItem((String)itemList.getValueAt(idx, 0));
					lblItemName.setText(selected.getName());
					txtQty.requestFocus();
		  		}
				else {
					txtFind.setText(null);
					lblItemName.setText("Select an Item");
					itemList.getSelectionModel().clearSelection();
				}
		    }
		};
		
		//AddQty With Enter
		AbstractAction actionATC = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	Integer qty = Integer.parseInt(txtQty.getText());
				int rowIndex = itemList.getSelectedRow();
				Item selected = findItem((String)itemList.getValueAt(rowIndex, 0));
				if(selected.getStock()>=qty) {
					selected.setStock(selected.getStock()-qty);
					AddPurchase(selected, qty);
					UpdateList();
				}
				else {
					JOptionPane.showMessageDialog(null, "Purchase Quantity must be lower than stock", "Insufficient Stock", JOptionPane.ERROR_MESSAGE);
				}
				txtQty.setText("1");
				UpdatePurchaseList();		
				SetTotal();
				txtFind.requestFocus();
		    }
		};
		
		btnFind.addActionListener(actionSearch);
		txtFind.addActionListener(actionSearch);
		
		
		btnAdd.addActionListener(actionATC);
		txtQty.addActionListener(actionATC);
		txtQty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					Integer qty = Integer.parseInt(txtQty.getText());
					int rowIndex = itemList.getSelectedRow();
					Item selected = findItem((String)itemList.getValueAt(rowIndex, 0));
					if(selected.getStock()>=qty) {
						selected.setStock(selected.getStock()-qty);
						AddPurchase(selected, qty);
						UpdateList();
					}
					else {
						JOptionPane.showMessageDialog(null, "Purchase Quantity must be lower than stock", "Insufficient Stock", JOptionPane.ERROR_MESSAGE);
					}
					txtQty.setText("1");
					UpdatePurchaseList();		
					SetTotal();
					txtFind.requestFocus();
				}
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				// TODO : Check DB ID Supervisor
				int rowIndex = purchasesList.getSelectedRow();
				String spvID = fieldBox.getText();
				String spvPass = fieldBoxPswd.getText(); // TODO : Password gamau kebaca
				if(rowIndex != -1){
					Integer boxSPV = JOptionPane.showConfirmDialog(null, getPanel(), "Call a Supervisor to Remove", JOptionPane.OK_CANCEL_OPTION);
					if(DBConn.isSPV(spvID,spvPass)){
						Purchase selected = findItemToDelete(purchasesList.getValueAt(rowIndex, 0).toString());
						// TODO : Revert back stock to its initial stock
						selected.getItem().setStock(selected.getItem().getStock()+selected.getQty());
						purchases.remove(selected);
						UpdateList();
						UpdatePurchaseList();
						SetTotal();
		    	    }else if(boxSPV == JOptionPane.OK_OPTION){
		    	    	System.out.println(spvPass);
		    	    	JOptionPane.showMessageDialog(null, "Not a supervisor", "Error!", JOptionPane.ERROR_MESSAGE);
		    	    }
				}else{
					JOptionPane.showMessageDialog(null, "No product selected", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!purchases.isEmpty()) {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
					LocalDateTime now = LocalDateTime.now();
					String trxID = "TRX"+cashier.getUsername().toUpperCase()+dtf.format(now).toString();
					Transaction trx = new Transaction(trxID, cashier);
					for(Purchase purchase : purchases) {
						trx.addPurchase(purchase);
					}
					Container.transactions.add(trx);
					purchases.clear();
					UpdatePurchaseList();
					SetTotal();
					UpdateList();
					DBConn.UpdateItemDB();
					DBConn.UpdateTrxDB();
				}
			}
		});
	}
}

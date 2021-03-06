package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import assets.Company;
import assets.Item;
import assets.Member;
import assets.Transaction;
import assets.TransactionHistory;
import assets.UserSupervisor;
import dataConnector.DBConn;
import dataContainer.Container;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Supervisor extends JFrame {
	
	private JPanel contentPane;
	private static JTable itemsList;
	private JTable transactionsList;
	private Company company = DBConn.readData();
	private static JTable membersList;

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
	public static void updateItemList() {
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
	
	public static void UpdateMembersList() {
		String col[] = {"UID", "Name", "Phone", "Address", "Point"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0){
			public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		membersList.setModel(tableModel);
		//adding items :)
		for(Member member : Container.memberList) {
			String UID = member.getUID();
			String name = member.getName();
			String phone = member.getPhone();
			String address = member.getAddress();
			Integer point = member.getPoint();
			Object[] obj = {UID, name, phone, address, point};
			tableModel.addRow(obj);
		}
	}
	public Supervisor(UserSupervisor supervisor) {
		setTitle("PiKA Point-of-Sales | Supervisor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGreeting = new JLabel("Welcome, "+supervisor.getFname());
		lblGreeting.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblGreeting.setBounds(30, 130, 359, 24);
		contentPane.add(lblGreeting);
		
		JLabel lblItemsList = new JLabel("Items List");
		lblItemsList.setBounds(30, 172, 83, 14);
		contentPane.add(lblItemsList);
		
		JLabel lblTransactionsList = new JLabel("Transactions List");
		lblTransactionsList.setBounds(423, 172, 163, 14);
		contentPane.add(lblTransactionsList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 197, 359, 411);
		contentPane.add(scrollPane);
		
		itemsList = new JTable();
		scrollPane.setViewportView(itemsList);
		itemsList.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int rowIndex = itemsList.getSelectedRow();
					Item selected = Container.items.get(rowIndex);
					ItemDetails detail = new ItemDetails(selected);
					detail.setVisible(true);
				}
				catch(Exception ee) {}
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(423, 197, 369, 411);
		contentPane.add(scrollPane_1);
		
		transactionsList = new JTable();
		scrollPane_1.setViewportView(transactionsList);
		
		//click on transaction
		transactionsList.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int rowIndex = transactionsList.getSelectedRow();
					TransactionHistory selected = Container.transactionHistory.get(rowIndex);
					TransactionDetails trxDetail = new TransactionDetails(selected);
					trxDetail.setVisible(true);
				} catch (Exception e2) {}
			}
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Supervisor.class.getResource("/views/pika10025.png")));
		label.setBounds(30, 46, 125, 60);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("<html>\r\n"+ company.getAddress() +"<br/>\r\nPhone : "+ company.getPhone() +", Fax : "+company.getFax()+"\r\n</html>");
		label_2.setBounds(181, 72, 505, 39);
		contentPane.add(label_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(175, 72, 602, 2);
		contentPane.add(separator);
		
		JButton btnNewItem = new JButton("New Item");
		btnNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddItem addItem = new AddItem();
				addItem.setVisible(true);
				if(addItem.isActive()==false) {
					updateItemList();
				}
			}
		});
		btnNewItem.setBounds(274, 165, 115, 29);
		contentPane.add(btnNewItem);
		
		JLabel lblMembersList = new JLabel("Members List");
		lblMembersList.setBounds(832, 169, 125, 20);
		contentPane.add(lblMembersList);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(842, 197, 421, 411);
		contentPane.add(scrollPane_2);
		
		membersList = new JTable();
		scrollPane_2.setViewportView(membersList);
		
		JLabel label_1 = new JLabel(company.getName());
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		label_1.setBounds(181, 32, 623, 39);
		contentPane.add(label_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1274, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Account");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSignOut = new JMenuItem("Sign Out");
		mnNewMenu.add(mntmSignOut);
			
		mntmSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Login loginScreen = new Login();
				//TODO : manggil login lagi
				loginScreen.frame.setVisible(true);
			}
		});
		
		membersList.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int rowIndex = membersList.getSelectedRow();
					Member selected = Container.memberList.get(rowIndex);
					MemberDetail memberDetail = new MemberDetail(selected);
					memberDetail.setVisible(true);
				} catch (Exception e3) {}
			}
		});
		
		
		
		setLocationRelativeTo(null);
		
		updateItemList();
		updateTransactionList();
		UpdateMembersList();
	}
}

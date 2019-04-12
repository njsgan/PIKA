package views;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import dataConnector.DBConn;

import java.awt.Window.Type;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import javax.swing.JProgressBar;
import java.awt.Font;

public class Splash {

	public JFrame frmPika;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Splash window = new Splash();
//					window.frmPika.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Splash() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPika = new JFrame();
		frmPika.setUndecorated(true);
		frmPika.setBackground(new Color(255, 255, 255, 0));
		frmPika.setResizable(false);
		frmPika.setTitle("PiKA");
		frmPika.setBounds(100, 100, 450, 300);
		frmPika.setLocationRelativeTo(null);
		frmPika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPika.getContentPane().setLayout(null);
		
		JLabel lblConn = new JLabel("");
		lblConn.setHorizontalAlignment(SwingConstants.CENTER);
		lblConn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblConn.setBounds(87, 220, 280, 14);
		frmPika.getContentPane().add(lblConn);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(53, 49, 344, 165);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Splash.class.getResource("pikasmall.png")));
		frmPika.getContentPane().add(lblNewLabel);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(0, 213, 450, 29);
		frmPika.getContentPane().add(progressBar);
		
		SwingWorker sw = new SwingWorker() {
	        @Override
	        protected Object doInBackground() throws Exception {
	            progressBar.setIndeterminate(true);
	            lblConn.setText("Connecting Database...");
	            DBConn.addItems();
	            return null;
	        }

	        @Override
	        public void done(){
	        	progressBar.setIndeterminate(false);
	        	lblConn.setText("Done Connecting...");
	            progressBar.setValue(100); // 100%
	        }
	    };
	     sw.execute();     
	}

}

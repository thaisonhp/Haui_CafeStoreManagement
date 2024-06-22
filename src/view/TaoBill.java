package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Bill;
import model.Customer;
import model.Product;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JTable;

public class TaoBill extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TaoBill frame = new TaoBill();
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
	public TaoBill(Bill i) {
		
		// thong tin bill 
		String id = i.getId() ; 
		Customer customer = i.getCustomer(); 
		String date = i.getDate() ; 
		Double total = i.getTotal() ; 
		String CreateBy  = i.getCreateBy() ;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 693);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Thông tin chi tiết hoá đơn");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		lblTitle.setBounds(93, 19, 357, 42);
		contentPane.add(lblTitle);
		
		JLabel lblNewL = new JLabel("Tên khách hàng");
		lblNewL.setBounds(23, 92, 121, 16);
		contentPane.add(lblNewL);
		
		JLabel lblNameValue = new JLabel("New label");
		lblNameValue.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNameValue.setBounds(156, 92, 294, 16);
		contentPane.add(lblNameValue);
		
		JLabel lblEmail = new JLabel("Email               ");
		lblEmail.setBounds(23, 127, 121, 16);
		contentPane.add(lblEmail);
		
		JLabel lblEmailValue = new JLabel("New label");
		lblEmailValue.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblEmailValue.setBounds(156, 127, 294, 16);
		contentPane.add(lblEmailValue);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại ");
		lblSinThoi.setBounds(23, 169, 121, 16);
		contentPane.add(lblSinThoi);
		
		JLabel lblPhoneNumberValue = new JLabel("New label");
		lblPhoneNumberValue.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPhoneNumberValue.setBounds(156, 169, 294, 16);
		contentPane.add(lblPhoneNumberValue);
		
		JLabel lblAddress = new JLabel("Địa Chỉ ");
		lblAddress.setBounds(23, 214, 121, 16);
		contentPane.add(lblAddress);
		
		JLabel lblAddressValue = new JLabel("New label");
		lblAddressValue.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblAddressValue.setBounds(156, 214, 294, 16);
		contentPane.add(lblAddressValue);
		
		table = new JTable();
		table.setBounds(23, 305, 427, 224);
		contentPane.add(table);
		
	}
//	public void updateProductTable(List<Product> products) {
//        DefaultTableModel dtm = (DefaultTableModel) this.tableDrink.getModel();
//        dtm.setRowCount(0);
//        products.forEach(product -> {
//            dtm.addRow(new Object[]{product.getId(), product.getName(), product.getCategory().getName(), product.getPrice()});
//        });
//    }

}

package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.ProductDAO;

import javax.swing.border.EmptyBorder;

import model.Category;
import model.Product;

public class KhoDoUong extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    KhoDoUong frame = new KhoDoUong();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public KhoDoUong() {
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 838, 441);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Thông tin chi tiết");
        lblNewLabel.setBounds(232, 16, 333, 39);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 24));
        contentPane.add(lblNewLabel);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Tên sản phẩm", "Loại sản phẩm", "Giá" }
        ));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(6, 92, 826, 215);
        contentPane.add(scrollPane);

        JButton btnShow = new JButton("Hiển thị ");
        btnShow.setBounds(6, 51, 117, 29);
        contentPane.add(btnShow);

        // Add ActionListener to the button
        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               List<Product> products = new ArrayList();
			try {
				ProductDAO manager = new ProductDAO();
				products = manager.genProductData();
				for(Product p : products) {
					System.out.println(p);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
               showProductDetails(products);
            }
        });
    }



    private void showProductDetails(List<Product> products) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data

        for (Product product : products) {
            model.addRow(new Object[] {
                product.getId(),
                product.getName(),
                product.getCategory().getName(),
                product.getPrice()
            });
        }
    }
    public void updateProductTable(List<Product> products) {
        DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
        dtm.setRowCount(0);
        products.forEach(product -> {
            dtm.addRow(new Object[] { product.getId(), product.getName(), product.getCategory(), product.getPrice()});
        });
    }


    // Method to handle add category action
    
}

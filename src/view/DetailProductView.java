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

public class DetailProductView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    DetailProductView frame = new DetailProductView(products);
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public DetailProductView(List<Product> products) {
    	String nameCategory = products.get(0).getCategory().getName();
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 838, 441);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Thông tin chi tiết của " + nameCategory);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 24));
        lblNewLabel.setBounds(144, 19, 542, 39);
        contentPane.add(lblNewLabel);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Tên sản phẩm", "Loại sản phẩm", "Giá" }
        ));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(6, 92, 826, 215);
        contentPane.add(scrollPane);
        showProductDetails(products);
        setVisible(true);
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

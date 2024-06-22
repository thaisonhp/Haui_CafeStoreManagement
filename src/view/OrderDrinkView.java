package view ;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.CategoryDAO;
import dao.ProductDAO;
import model.Bill;
import model.Category;
import model.Customer;
import model.Product;
import javax.swing.JToolBar;
import javax.swing.JSeparator;

public class OrderDrinkView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textBillId;
    private JTextField textName;
    private JTextField textPhone;
    private JTextField textEmail;
    private JTextField textAddress;
    private JTable tableTypeDrink;
    private JTable tableDrink;
    private JTable tableBill;
    private DefaultTableModel modelTypeDrink;
    private DefaultTableModel modelDrink;
    private JTextField textDate;
    private JTextField textCreateBy;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OrderDrinkView frame = new OrderDrinkView();
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
    public OrderDrinkView() {
        
        List<Product> listdrink = new ArrayList<>();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 999, 630);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblID = new JLabel("Bill ID : ");
        lblID.setBounds(22, 30, 61, 16);
        contentPane.add(lblID);

        textBillId = new JTextField();
        textBillId.setBounds(72, 25, 130, 26);
        contentPane.add(textBillId);
        textBillId.setColumns(10);

        JLabel lblInfoCus = new JLabel("Customer Information");
        lblInfoCus.setBounds(23, 63, 179, 16);
        contentPane.add(lblInfoCus);

        JLabel lblNewLabel = new JLabel("Full Name");
        lblNewLabel.setBounds(22, 91, 89, 16);
        contentPane.add(lblNewLabel);

        textName = new JTextField();
        textName.setBounds(120, 86, 179, 26);
        contentPane.add(textName);
        textName.setColumns(10);

        JLabel lblPhoneNumber = new JLabel("Phone number");
        lblPhoneNumber.setBounds(22, 119, 106, 16);
        contentPane.add(lblPhoneNumber);

        textPhone = new JTextField();
        textPhone.setColumns(10);
        textPhone.setBounds(120, 114, 179, 26);
        contentPane.add(textPhone);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(22, 147, 89, 16);
        contentPane.add(lblEmail);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(22, 175, 89, 16);
        contentPane.add(lblAddress);

        textEmail = new JTextField();
        textEmail.setColumns(10);
        textEmail.setBounds(120, 142, 179, 26);
        contentPane.add(textEmail);

        textAddress = new JTextField();
        textAddress.setColumns(10);
        textAddress.setBounds(120, 175, 179, 26);
        contentPane.add(textAddress);

        modelTypeDrink = new DefaultTableModel(new Object[]{"ID", "Name"}, 0);
        tableTypeDrink = new JTable(modelTypeDrink);
        tableTypeDrink.setBounds(385, 31, 603, 225);
        contentPane.add(tableTypeDrink);

        JScrollPane scrollPaneTypeDrink = new JScrollPane(tableTypeDrink);
        scrollPaneTypeDrink.setBounds(385, 31, 603, 225);
        contentPane.add(scrollPaneTypeDrink);

        JLabel lblNewLabel_1 = new JLabel("Danh Mục Các Loại Đồ Uống");
        lblNewLabel_1.setBounds(386, 6, 306, 16);
        contentPane.add(lblNewLabel_1);

        modelDrink = new DefaultTableModel(new Object[]{"ID", "Name", "Category", "Price"}, 0);
        tableDrink = new JTable(modelDrink);
        tableDrink.setBounds(385, 295, 603, 236);
        contentPane.add(tableDrink);

        JScrollPane scrollPaneDrink = new JScrollPane(tableDrink);
        scrollPaneDrink.setBounds(385, 295, 603, 236);
        contentPane.add(scrollPaneDrink);

        JLabel lblNewLabel_1_1 = new JLabel("Thông tin chi tiết : ");
        lblNewLabel_1_1.setBounds(385, 267, 306, 16);
        contentPane.add(lblNewLabel_1_1);

        tableBill = new JTable(new DefaultTableModel(new Object[]{"ID", "Name", "Price"}, 0));
        tableBill.setBounds(22, 295, 306, 236);
        contentPane.add(tableBill);

        JScrollPane scrollPane_2 = new JScrollPane(tableBill);
        scrollPane_2.setBounds(22, 295, 306, 236);
        contentPane.add(scrollPane_2);

        JLabel lblDrinkChoosed = new JLabel("Đồ đã chọn :");
        lblDrinkChoosed.setBounds(22, 263, 139, 16);
        contentPane.add(lblDrinkChoosed);
        
        
        JButton btnExit = new JButton("Thoát");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng cửa sổ DrinkManagementView
                HomeView homeView = new HomeView(); // Tạo một instance mới của HomeView
                homeView.setVisible(true); // Hiển thị HomeView
            }
        });
        btnExit.setBounds(871, 556, 117, 29);
        contentPane.add(btnExit);

        this.setVisible(true);

        JButton btnGenBill = new JButton("Xuất Bill");
        btnGenBill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // Tạo bill và hoàn thiện thông tin
                Bill i = createBill();
                
                // Truyền bill i sang trang tạo bill
                TaoBill taoBillPage = new TaoBill(i); // i là bill đã được điền thông tin từ trang order
                
                // Hiển thị thông báo thành công
                JOptionPane.showMessageDialog(null, "Đã tạo bill thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnGenBill.setBounds(22, 556, 117, 29);
        contentPane.add(btnGenBill);

        CategoryDAO db = new CategoryDAO();
        JButton btnShow = new JButton("Hiển thị");
        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Category> danhsachdouong = db.getAll();
                    updateCategoryTable(danhsachdouong);
                } catch (ClassNotFoundException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnShow.setBounds(871, 1, 117, 29);
        contentPane.add(btnShow);
        
        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(22, 203, 89, 16);
        contentPane.add(lblDate);
        
        textDate = new JTextField();
        textDate.setColumns(10);
        textDate.setBounds(120, 198, 179, 26);
        contentPane.add(textDate);
        
        JLabel lblCreateBy = new JLabel("Create By");
        lblCreateBy.setBounds(22, 231, 89, 16);
        contentPane.add(lblCreateBy);
        
        textCreateBy = new JTextField();
        textCreateBy.setColumns(10);
        textCreateBy.setBounds(120, 226, 179, 26);
        contentPane.add(textCreateBy);
        
        JLabel lblBillInfo = new JLabel("Bill Information");
        lblBillInfo.setBounds(22, 6, 277, 16);
        contentPane.add(lblBillInfo);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(354, 0, 0, 529);
        contentPane.add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(354, 30, 0, 501);
        contentPane.add(separator_1);

        tableTypeDrink.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tableTypeDrink.getSelectedRow() != -1) {
                    int selectedRow = tableTypeDrink.getSelectedRow();
                    int id = Integer.parseInt(tableTypeDrink.getValueAt(selectedRow, 0).toString());
                    ProductDAO dbKhoDoUong = new ProductDAO();
                    try {
                        List<Product> products = dbKhoDoUong.getAll();
                        List<Product> result = new ArrayList<>();
                        for (Product product : products) {
                            if (product.getCategory().getId() == id) {
                                result.add(product);
                            }
                        }
                        updateProductTable(result);
                    } catch (ClassNotFoundException | IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Event listener for tableDrink selection
        tableDrink.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tableDrink.getSelectedRow() != -1) {
                    int selectedRow = tableDrink.getSelectedRow();
                    int id = Integer.parseInt(tableDrink.getValueAt(selectedRow, 0).toString());
                    String name = tableDrink.getValueAt(selectedRow, 1).toString();
                    double price = Double.parseDouble(tableDrink.getValueAt(selectedRow, 3).toString());
                    Category category = new Category((int)(Math.random()*10 + 1),tableDrink.getValueAt(selectedRow, 2).toString());
                    // Create a Product object for the selected item
                    Product selectedProduct = new Product(id, name,category, price);

                    // Add the selected product to the bill table
                    addToBillTable(selectedProduct);
                }
            }
        });

        setVisible(true);
    }

    public void updateCategoryTable(List<Category> categories) {
        DefaultTableModel dtm = (DefaultTableModel) this.tableTypeDrink.getModel();
        dtm.setRowCount(0);
        categories.forEach(category -> {
            dtm.addRow(new Object[]{category.getId(), category.getName()});
        });
    }

    public void updateProductTable(List<Product> products) {
        DefaultTableModel dtm = (DefaultTableModel) this.tableDrink.getModel();
        dtm.setRowCount(0);
        products.forEach(product -> {
            dtm.addRow(new Object[]{product.getId(), product.getName(), product.getCategory().getName(), product.getPrice()});
        });
    }

    public void addToBillTable(Product product) {
        DefaultTableModel dtm = (DefaultTableModel) this.tableBill.getModel();
        dtm.addRow(new Object[]{product.getId(), product.getName(), product.getPrice()});
    }

    public Customer getCustomerInfo() {
        String name = textName.getText();
        String email = textEmail.getText();
        String phonenumber = textPhone.getText();
        String address = textAddress.getText();
        String id = textBillId.getText() + "-" + name.charAt((int) (Math.random() * name.length()));
        return new Customer(id, name, email, phonenumber, address);
    }
    public Bill createBill() {
    	DefaultTableModel model = (DefaultTableModel) tableBill.getModel();
    	int rowCount = model.getRowCount();
    	String id = textBillId.getText();
    	Customer customer = getCustomerInfo();
    	String date =  textDate.getText();
    	double total = 0 ;
		for (int row = 0; row < rowCount; row++) {

			Object value = model.getValueAt(row, 2);
			// Xử lý giá trị value ở đây
			total = total + Double.parseDouble( value.toString()) ;

			System.out.println(); // Xuống dòng sau khi duyệt hết các cột của dòng hiện tại
		}
		String createBY = textCreateBy.getText();
		
    	
		return new Bill(id,customer,date,total,createBY);
    	
    }
}

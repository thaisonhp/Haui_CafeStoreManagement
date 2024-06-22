package view ;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controller.DrinkController;
import controller.ProductController;
import dao.CategoryDAO;
import dao.ProductDAO;
import model.Category;
import model.Product;
import java.awt.Color;

public class DrinkManagementView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel contentPane_1;
    private JPanel contentPane_1_1;
    private CategoryDAO manager;
    private DrinkController drinkController;
    private JTable table;
    private JTextField textField_id;
    private JTextField textField_Name_2;
    private JTextField textField_price;
    private JTextField textField_size;
    private JTextField textField_brand;
    private JTextField textField_typescreen;
    private JComboBox<String> comboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DrinkManagementView frame = new DrinkManagementView();
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
    public DrinkManagementView() {
    	setTitle("Quản lý loại đồ uống");

        this.manager = new CategoryDAO();
        this.drinkController = new DrinkController(this.manager, this);
        ImageIcon icon = new ImageIcon("/Users/luongthaison/Desktop/drink4.jpeg"); // Đường dẫn đến file ảnh nền
        Image backgroundImage = icon.getImage();
        contentPane_1 = new JPanel();
        // Sử dụng BackgroundPanel thay cho JPanel
        contentPane_1_1 = new ChangeBackground(backgroundImage);
     // Thêm ActionListener cho nút hoặc sự kiện thoát
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 840, 617);

        
//        contentPane_1.setBackground(new Color(204, 153, 102));
        contentPane_1_1.setBorder(null);
        setContentPane(contentPane_1_1);
        contentPane_1_1.setLayout(null);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(18, 43, 816, 5);
        contentPane_1_1.add(separator_2);

        JLabel lblDanhSchTv = new JLabel("Danh sách loại đồ uống");
        lblDanhSchTv.setBackground(new Color(204, 153, 102));
        lblDanhSchTv.setBounds(6, 19, 284, 29);
        contentPane_1_1.add(lblDanhSchTv);

        String[] columnNames = { "ID", "Name"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(6, 51, 828, 249);
        contentPane_1_1.add(scrollPane);

        JSeparator separator_5 = new JSeparator();
        separator_5.setBounds(6, 396, 828, 12);
        contentPane_1_1.add(separator_5);

        JLabel lblNewLabel_2 = new JLabel("ID sản phẩm:");
        lblNewLabel_2.setBounds(18, 441, 92, 16);
        contentPane_1_1.add(lblNewLabel_2);

        textField_id = new JTextField();
        textField_id.setBounds(105, 436, 130, 26);
        contentPane_1_1.add(textField_id);
        textField_id.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Thông tin sản phẩm");
        lblNewLabel_3.setBounds(18, 413, 153, 16);
        contentPane_1_1.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Name");
        lblNewLabel_4.setBounds(18, 472, 61, 16);
        contentPane_1_1.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Size");
        lblNewLabel_5.setBounds(481, 441, 61, 16);
        contentPane_1_1.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Price");
        lblNewLabel_6.setBounds(280, 441, 61, 16);
        contentPane_1_1.add(lblNewLabel_6);

        textField_Name_2 = new JTextField();
        textField_Name_2.setBounds(105, 469, 130, 26);
        contentPane_1_1.add(textField_Name_2);
        textField_Name_2.setColumns(10);

        textField_price = new JTextField();
        textField_price.setBounds(311, 436, 130, 26);
        contentPane_1_1.add(textField_price);
        textField_price.setColumns(10);

        textField_size = new JTextField();
        textField_size.setBounds(523, 436, 126, 26);
        contentPane_1_1.add(textField_size);
        textField_size.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("Brand ");
        lblNewLabel_8.setBounds(481, 472, 61, 16);
        contentPane_1_1.add(lblNewLabel_8);

        JLabel lblNewLabel_9 = new JLabel("Type screen");
        lblNewLabel_9.setBounds(661, 441, 90, 16);
        contentPane_1_1.add(lblNewLabel_9);

        textField_brand = new JTextField();
        textField_brand.setBounds(523, 467, 130, 26);
        contentPane_1_1.add(textField_brand);
        textField_brand.setColumns(10);

        textField_typescreen = new JTextField();
        textField_typescreen.setBounds(742, 436, 92, 26);
        contentPane_1_1.add(textField_typescreen);
        textField_typescreen.setColumns(10);

        JButton btnThem = new JButton("Thêm");
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCategoryActionPerformed(e);
            }
        });
        btnThem.setBounds(6, 518, 117, 29);
        contentPane_1_1.add(btnThem);

        JButton btnSua = new JButton("Sửa");
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCategoryActionPerformed(e);
            }
        });
        btnSua.setBounds(173, 518, 117, 29);
        contentPane_1_1.add(btnSua);

        JButton btnXoa = new JButton("Xoá");
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCategoryActionPerformed(e);
            }
        });
        btnXoa.setBounds(334, 518, 117, 29);
        contentPane_1_1.add(btnXoa);

        JButton btnDetail = new JButton("Xem thông tin chi tiết");
        btnDetail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	detailCategoriesPerformed(e);
            }

			private void detailCategoriesPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
			        int selectedRow = table.getSelectedRow();
			        if (selectedRow == -1) {
			            System.out.println("No row selected.");
			            // Hiển thị thông báo lỗi hoặc cảnh báo nếu không có hàng nào được chọn
			            return;
			        }

			        //cột đầu tiên là ID của Category
			        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
			        String categoryName = table.getValueAt(selectedRow, 1).toString();
			        ProductDAO dbKhoDoUong = new ProductDAO() ;
			        System.out.println(categoryName);
			        List<Product> products = new ArrayList();
					try {
						products = dbKhoDoUong.getAll();//  lấy dữ liệu trong file 
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					List<Product> result = new ArrayList();
					for(Product p : products) {
						if(p.getName().contains(categoryName)) {
							result.add(p);
						}
					}
					// Hiển thị thông tin chi tiết của sản phẩm trong một cửa sổ mới
			        new DetailProductView(result) ;
//			        showProductDetails(new Product(0, "Example Product", category, 0.0));
			    } catch (NumberFormatException ex) {
			        ex.printStackTrace();
			        // Xử lý ngoại lệ nếu có lỗi chuyển đổi số
			    }
			}
        });
        
        btnDetail.setBounds(634, 312, 200, 29);
        contentPane_1_1.add(btnDetail);

        JButton btnHuy = new JButton("Huỷ");
        btnHuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelActionPerformed(e);
            }
        });
        btnHuy.setBounds(693, 518, 117, 29);
        contentPane_1_1.add(btnHuy);

        JButton btnLuu = new JButton("Lưu");
        btnLuu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveActionPerformed(e);
            }
        });
        btnLuu.setBounds(509, 518, 117, 29);
        contentPane_1_1.add(btnLuu);
      
        JButton btnshow = new JButton("Hiển thị");
        btnshow.setBackground(new Color(204, 0, 102));
        btnshow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayActionPerformed(e);
            	
            }
        });
        btnshow.setBounds(173, 19, 117, 29);
        contentPane_1_1.add(btnshow);

        String[] waytoSearch = { "Name", "Price", "Size", "Type screeen", "Brand" };
        comboBox = new JComboBox<>(waytoSearch);
        comboBox.setBounds(130, 313, 93, 27);
        contentPane_1_1.add(comboBox);

        JButton btnTimkiem_1 = new JButton("Tìm kiếm");
        btnTimkiem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchActionPerformed(e);
            }
        });
        btnTimkiem_1.setBounds(6, 312, 117, 29);
        contentPane_1_1.add(btnTimkiem_1);
        
        JButton btnExit = new JButton("Thoát");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng cửa sổ DrinkManagementView
                HomeView homeView = new HomeView(); // Tạo một instance mới của HomeView
                homeView.setVisible(true); // Hiển thị HomeView
            }
        });
        btnExit.setBounds(717, 19, 117, 29);
        contentPane_1_1.add(btnExit);

        this.setVisible(true);
    }

    // Method to update the category table
    public void updateCategoryTable(List<Category> categories) {
        DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
        dtm.setRowCount(0);
        categories.forEach(category -> {
            dtm.addRow(new Object[] { category.getId(), category.getName() });
        });
    }

   
    private void addCategoryActionPerformed(ActionEvent evt) {
        try {
            int id = Integer.parseInt(textField_id.getText());
            String name = textField_Name_2.getText();
            
            Category newCategory = new Category(id, name);
            // Khởi tạo đối tượng Category từ các giá trị vừa lấy được

            boolean added = drinkController.addCategory(newCategory);
            if (added) {
                // Nếu thêm thành công, cập nhật lại bảng danh sách
                updateCategoryTable(drinkController.getAllCategories());
            } else {
                // Nếu thêm không thành công, hiển thị thông báo lỗi
                System.out.println("Failed to add category. ID may already exist.");
                // Có thể thêm phần hiển thị cảnh báo hoặc thông báo lỗi GUI ở đây
            }
        } catch (NumberFormatException | ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu có lỗi chuyển đổi số hoặc khi gọi đến controller
        }
    }

    // Method to handle update category action
    private void updateCategoryActionPerformed(ActionEvent evt) {
        try {
            int id = Integer.parseInt(textField_id.getText());
            String name = textField_Name_2.getText();
            // Lấy các thông tin cần thiết khác từ các textField tương ứng

            Category updatedCategory = new Category(id, name);
            // Khởi tạo đối tượng Category mới với thông tin cập nhật

            boolean updated = drinkController.updateCategory(updatedCategory);
            if (updated) {
                // Nếu cập nhật thành công, cập nhật lại bảng danh sách
                updateCategoryTable(drinkController.getAllCategories());
            } else {
                // Nếu cập nhật không thành công, hiển thị thông báo lỗi
                System.out.println("Failed to update category. ID does not exist.");
                // Có thể thêm phần hiển thị cảnh báo hoặc thông báo lỗi GUI ở đây
            }
        } catch (NumberFormatException | ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu có lỗi chuyển đổi số hoặc khi gọi đến controller
        }
    }

    // Method to handle delete category action
    private void deleteCategoryActionPerformed(ActionEvent evt) {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                System.out.println("No row selected.");
                // Hiển thị thông báo lỗi hoặc cảnh báo nếu không có hàng nào được chọn
                return;
            }

            // Giả sử cột đầu tiên là ID của Category
            int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

            Category categoryToDelete = new Category(id, "");
            // Tạo đối tượng Category với chỉ ID để xóa

            boolean deleted = drinkController.deleteCategory(categoryToDelete);
            if (deleted) {
                // Nếu xóa thành công, cập nhật lại bảng danh sách
                updateCategoryTable(drinkController.getAllCategories());
            } else {
                // Nếu xóa không thành công, hiển thị thông báo lỗi
                System.out.println("Failed to delete category. ID does not exist.");
                // Có thể thêm phần hiển thị cảnh báo hoặc thông báo lỗi GUI ở đây
            }
        } catch (NumberFormatException | ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu có lỗi chuyển đổi số hoặc khi gọi đến controller
        }
    }

    // Method to handle search category action
    private void searchActionPerformed(ActionEvent evt) {
        try {
            // Hiển thị hộp thoại để người dùng nhập từ khóa tìm kiếm
            String searchKeyword = JOptionPane.showInputDialog(this, "Nhập từ khóa tìm kiếm:", "Tìm kiếm", JOptionPane.PLAIN_MESSAGE);
            
            // Nếu người dùng nhấn hủy hoặc không nhập gì, thì không thực hiện tìm kiếm
            if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
                return;
            }

            // Thực hiện tìm kiếm và cập nhật bảng danh sách
            updateCategoryTable(drinkController.searchCategories(searchKeyword));
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu có lỗi khi gọi đến controller
        }
    }


    // Method to handle display all categories action
    private void displayActionPerformed(ActionEvent evt) {
        try {
            drinkController.genCategory();
            updateCategoryTable(drinkController.getAllCategories());
            // Gọi controller để lấy tất cả các loại đồ uống và cập nhật bảng danh sách
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu có lỗi khi gọi đến controller
        }
    }

    // Method to handle cancel action
    private void cancelActionPerformed(ActionEvent evt) {
        // Xóa trắng các textField và chuẩn bị cho một thêm mới hoặc chỉnh sửa mới
        textField_id.setText("");
        textField_Name_2.setText("");
        textField_price.setText("");
        textField_size.setText("");
        textField_brand.setText("");
        textField_typescreen.setText("");
    }

    // Method to handle save action
    private void saveActionPerformed(ActionEvent evt) {
        // Có thể thêm logic lưu các thay đổi vào cơ sở dữ liệu nếu có
    }

   
   
}

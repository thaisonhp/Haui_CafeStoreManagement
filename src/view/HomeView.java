package view ;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import view.OrderDrinkView;

public class HomeView extends JFrame {
	private static HomeView instance; // Biến toàn cục

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView frame = new HomeView();
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
	public HomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 838, 441);
		contentPane = new JPanel();
		// Tải ảnh nền từ file
        ImageIcon icon = new ImageIcon("/Users/luongthaison/Desktop/cafe.jpeg"); // Đường dẫn đến file ảnh nền
        Image backgroundImage = icon.getImage();

        // Sử dụng BackgroundPanel thay cho JPanel
        contentPane = new ChangeBackground(backgroundImage);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnQLDU = new JButton("Quản lý đồ uống");
		btnQLDU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DrinkManagementView();
			}
		});
		btnQLDU.setBounds(65, 111, 159, 34);
		contentPane.add(btnQLDU);

		JButton btnQLNV = new JButton("Quản lý nhân viên");
		btnQLNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployesManagementView();
			}
		});
		btnQLNV.setBounds(65, 197, 159, 34);
		contentPane.add(btnQLNV);

		JButton btnOrderdouong = new JButton("Đặt đồ uống");
		btnOrderdouong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OrderDrinkView();
			}
		});
		btnOrderdouong.setBounds(65, 274, 159, 34);
		contentPane.add(btnOrderdouong);

		JButton btnThongKe = new JButton("Thống kê");
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StatisticView();
			}
		});
		btnThongKe.setBounds(466, 197, 159, 34);
		contentPane.add(btnThongKe);

		JButton btnBill = new JButton("Bill");
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				new BillView();
			}
		});
		btnBill.setBounds(466, 111, 159, 34);
		contentPane.add(btnBill);

		JLabel lblNewLabel = new JLabel("Quản lý quán cafe");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(278, 23, 203, 34);
		contentPane.add(lblNewLabel);

		JButton btnChangeInfo = new JButton("Đổi thông tin tài khoản");
		btnChangeInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword();
			}
		});
		btnChangeInfo.setBounds(466, 277, 159, 34);
		contentPane.add(btnChangeInfo);
		setVisible(true);
	}
}
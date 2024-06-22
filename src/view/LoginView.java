package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import common.BackgroundPanel;
import common.PlaceholderPassword;
import common.PlaceholderTextField;
import dao.UserDAO;
import model.User;
import util.FormUtils;
import util.HashPassword;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PlaceholderTextField textField;
	private PlaceholderPassword passwordField;
	private JLabel lblQuest;
	private JButton btnRegister_button;
	private JButton btnLogin_button;
	private JCheckBox showPasswordCheckBox;
	private UserDAO userDAO;

	/**
	 * Create the frame.
	 */
	public LoginView() {
		userDAO = new UserDAO(); // Initialize userDAO here

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 350);

		contentPane = new BackgroundPanel("/resources/66783.jpg");
		contentPane.setBackground(new Color(250, 250, 210));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("ĐĂNG NHẬP");
		lblLogin.setForeground(new Color(0, 0, 0));
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(66, 25, 171, 29);
		contentPane.add(lblLogin);

		textField = new PlaceholderTextField("Nhập email");
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(12, 67, 292, 41);
		contentPane.add(textField);

		passwordField = new PlaceholderPassword("Nhập mật khẩu");
		passwordField.setBounds(12, 131, 292, 41);
		contentPane.add(passwordField);

		showPasswordCheckBox = new JCheckBox("Hiện mật khẩu");
		showPasswordCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		showPasswordCheckBox.setBounds(12, 179, 123, 23);
		contentPane.add(showPasswordCheckBox);

		btnLogin_button = new JButton("Đăng nhập");
		btnLogin_button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin_button.setBounds(95, 231, 113, 29);
		contentPane.add(btnLogin_button);

		lblQuest = new JLabel("Bạn chưa có tài khoản?");
		lblQuest.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuest.setBounds(7, 271, 144, 29);
		contentPane.add(lblQuest);

		btnRegister_button = new JButton("Đăng ký ngay");
		btnRegister_button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister_button.setBounds(161, 271, 123, 29);
		contentPane.add(btnRegister_button);

		JButton btnForgotPassword = new JButton("Quên mật khẩu");
		btnForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnForgotPassword.setBounds(160, 175, 144, 29);
		contentPane.add(btnForgotPassword);

		// Add action listeners directly in the LoginView class
		btnLogin_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!FormUtils.ValidateForm(contentPane)) {
					JOptionPane.showMessageDialog(LoginView.this, "Vui lòng nhập đầy đủ thông tin", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				String email = textField.getText();
				String password = passwordField.getText();

				try {
					User user = userDAO.get(u -> u.getEmail().equals(email));

					if (user == null || !user.getPassword().equals(HashPassword.hashPassword(password))) {
						JOptionPane.showMessageDialog(LoginView.this, "Tên đăng nhập hoặc mật khẩu không chính xác.",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!user.isStatus()) {
						JOptionPane.showMessageDialog(LoginView.this,
								"Tài khoản của bạn không có quyền truy cập. Vui lòng liên hệ Admin.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					JOptionPane.showMessageDialog(LoginView.this, "Đăng nhập thành công");
					dispose();
					new HomeView(user).setVisible(true);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		btnRegister_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SignUpView().setVisible(true);
			}
		});

		showPasswordCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (showPasswordCheckBox.isSelected()) {
					passwordField.setEchoChar((char) 0); // Hiển thị mật khẩu
				} else {
					passwordField.setEchoChar('\u25cf'); // Ẩn mật khẩu lại
				}
			}
		});

		btnForgotPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ForgotPasswordView forgotPasswordFrame = new ForgotPasswordView();
				forgotPasswordFrame.setVisible(true);
			}
		});
	}
}
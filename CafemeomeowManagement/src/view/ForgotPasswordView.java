package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import common.PlaceholderTextField;
import dao.UserDAO;
import model.User;
import util.FormUtils;
import util.HashPassword;
import util.PasswordGenerator;
import util.SendMail;

public class ForgotPasswordView extends JFrame {

	private JPanel contentPane;
	private PlaceholderTextField emailField;
	private PlaceholderTextField OtpField;
	private SendMail sm;
	private UserDAO userDAO;

	/**
	 * Create the frame.
	 */
	public ForgotPasswordView() {

		sm = new SendMail();
		userDAO = new UserDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 277);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 250, 210));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewpass = new JLabel("Lấy lại mật khẩu");
		lblNewpass.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewpass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewpass.setBounds(84, 11, 156, 50);
		contentPane.add(lblNewpass);

		emailField = new PlaceholderTextField("Nhập email");
		emailField.setBackground(new Color(255, 255, 255));
		emailField.setBounds(10, 72, 223, 33);
		contentPane.add(emailField);

		JButton btnOTP = new JButton("Gửi OTP");
		btnOTP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText();

				if (email.isEmpty()) {
					JOptionPane.showMessageDialog(ForgotPasswordView.this, "Vui lòng điền email.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!sm.sendOtp(email)) {
					JOptionPane.showMessageDialog(ForgotPasswordView.this,
							"Có lỗi trong quá trình gửi mã OTP. Vui lòng thử lại", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				JOptionPane.showMessageDialog(ForgotPasswordView.this,
						"Mã OTP đã được gửi vào email. Vui lòng kiểm tra.");
			}
		});
		btnOTP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOTP.setBounds(244, 72, 90, 33);
		contentPane.add(btnOTP);

		OtpField = new PlaceholderTextField("Nhập OTP");
		OtpField.setBackground(Color.WHITE);
		OtpField.setBounds(10, 126, 136, 33);
		contentPane.add(OtpField);

		JButton btnResetPassword = new JButton("Cấp lại mật khẩu");
		btnResetPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText();
				String otp = OtpField.getText();

				if (!FormUtils.ValidateForm(contentPane)) {
					JOptionPane.showMessageDialog(ForgotPasswordView.this, "Vui lòng nhập đầy đủ thông tin", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					User user = userDAO.get(u -> u.getEmail().equals(email));
					if (user == null) {
						JOptionPane.showMessageDialog(ForgotPasswordView.this, "Email không tồn tại. Vui lòng thử lại.",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}

				if (!otp.equals(sm.getOtp())) {
					JOptionPane.showMessageDialog(ForgotPasswordView.this, "Vui lòng kiểm tra lại mã OTP", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				String newPassword = PasswordGenerator.generatePassword();
				String hashedPassword = HashPassword.hashPassword(newPassword);

				try {
					User user = userDAO.get(u -> u.getEmail().equals(email));
					user.setPassword(hashedPassword);
					if (!userDAO.update(user)) {
						JOptionPane.showMessageDialog(ForgotPasswordView.this,
								"Có lỗi trong quá trình cấp lại mật khẩu. Vui lòng thử lại", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}

				if (!sm.sendNewPassword(email, newPassword)) {
					JOptionPane.showMessageDialog(ForgotPasswordView.this,
							"Có lỗi trong quá trình gửi mật khẩu mới. Vui lòng thử lại", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				JOptionPane.showMessageDialog(ForgotPasswordView.this,
						"Mật khẩu mới đã được gửi về email. Vui lòng kiểm tra");

				FormUtils.resetForm(contentPane);
				dispose();
				new LoginView().setVisible(true); // Mở màn hình đăng nhập
			}
		});
		btnResetPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnResetPassword.setBounds(178, 126, 156, 33);
		contentPane.add(btnResetPassword);

		JButton btnCancel = new JButton("Hủy");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginView().setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(118, 182, 89, 33);
		contentPane.add(btnCancel);
	}
}

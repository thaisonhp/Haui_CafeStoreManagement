package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import common.PlaceholderPassword;
import common.PlaceholderTextField;
import dao.UserDAO;
import model.User;
import util.FormUtils;
import util.SendMail;

public class SignUpView extends JFrame {

	private JPanel contentPane;
	private PlaceholderTextField fullNameField;
	private PlaceholderTextField emailField;
	private PlaceholderTextField phoneField;
	private PlaceholderTextField addressField;
	private PlaceholderPassword passwordField;
	private PlaceholderPassword confirmPasswordField;
	private PlaceholderTextField otpField;
	private JButton btnRegister;
	private JButton btnCancel;
	private JCheckBox showPasswordCheckBox;
	private JButton btnsendOTP_Button;
	private SendMail sm;
	private UserDAO userDAO;

	/**
	 * Create the frame.
	 */
	public SignUpView() {

		sm = new SendMail();
		userDAO = new UserDAO();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 446);
		setTitle("Đăng Ký");

		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 250, 210));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRegister = new JLabel("TẠO TÀI KHOẢN");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblRegister.setBounds(82, 23, 160, 36);
		contentPane.add(lblRegister);

		fullNameField = new PlaceholderTextField("Họ và tên");
		fullNameField.setBounds(22, 70, 177, 20);
		contentPane.add(fullNameField);
		fullNameField.setColumns(10);

		emailField = new PlaceholderTextField("Email");
		emailField.setBounds(22, 111, 209, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);

		phoneField = new PlaceholderTextField("Số điện thoại");
		phoneField.setBounds(205, 70, 112, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);

		addressField = new PlaceholderTextField("Địa chỉ");
		addressField.setBounds(22, 152, 295, 20);
		contentPane.add(addressField);

		passwordField = new PlaceholderPassword("Nhập mật khẩu");
		passwordField.setBounds(22, 195, 295, 20);
		contentPane.add(passwordField);

		confirmPasswordField = new PlaceholderPassword("Nhập lại mật khẩu");
		confirmPasswordField.setBounds(22, 240, 295, 20);
		contentPane.add(confirmPasswordField);

		showPasswordCheckBox = new JCheckBox("Hiển thị mật khẩu");
		showPasswordCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (showPasswordCheckBox.isSelected()) {
					passwordField.setEchoChar((char) 0);
					confirmPasswordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('\u25cf');
					confirmPasswordField.setEchoChar('\u25cf');
				}
			}
		});
		showPasswordCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		showPasswordCheckBox.setBounds(26, 274, 150, 20);
		contentPane.add(showPasswordCheckBox);

		btnRegister = new JButton("Đăng ký");
		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!FormUtils.ValidateForm(contentPane)) {
					JOptionPane.showMessageDialog(SignUpView.this, "Vui lòng nhập đầy đủ thông tin", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				String fullname = fullNameField.getText();
				String email = emailField.getText();
				String phonenumber = phoneField.getText();
				String address = addressField.getText();
				String password = passwordField.getText();
				String confirmPassword = confirmPasswordField.getText();
				String otp = otpField.getText();

				if (!isValidPhoneNumber(phonenumber)) {
					JOptionPane.showMessageDialog(SignUpView.this, "Số điện thoại không hợp lệ", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(SignUpView.this, "Vui lòng kiểm tra lại mật khẩu", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!otp.equals(sm.getOtp())) {
					JOptionPane.showMessageDialog(SignUpView.this, "Vui lòng kiểm tra lại mã xác nhận", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				User user = new User(fullname, email, phonenumber, address, password, true);
				try {
					if (!userDAO.add(user)) {
						JOptionPane.showMessageDialog(SignUpView.this,
								"Đã tồn tại tài khoản với tên tài khoản hoặc email này. Vui lòng thử lại.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(SignUpView.this, "Đăng ký thành công");

				dispose();
				new LoginView().setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRegister.setBounds(97, 324, 130, 27);
		contentPane.add(btnRegister);

		btnCancel = new JButton("Đăng nhập");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginView().setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(177, 373, 112, 23);
		contentPane.add(btnCancel);

		JLabel lbllabel_quest = new JLabel("Bạn đã có tài khoản?");
		lbllabel_quest.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbllabel_quest.setHorizontalAlignment(SwingConstants.CENTER);
		lbllabel_quest.setBounds(34, 373, 132, 23);
		contentPane.add(lbllabel_quest);

		btnsendOTP_Button = new JButton("Gửi mã");
		btnsendOTP_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fullname = fullNameField.getText();
				String email = emailField.getText();

				if (email.isEmpty()) {
					JOptionPane.showMessageDialog(SignUpView.this, "Vui lòng điền email.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					if (userDAO.isUserExist(fullname, email)) {
						JOptionPane.showMessageDialog(SignUpView.this,
								"Đã tồn tại tài khoản email này. Vui lòng thử lại.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}

				if (!sm.sendOtp(emailField.getText())) {
					JOptionPane.showMessageDialog(SignUpView.this,
							"Có lỗi trong quá trình lấy mã xác nhận. Vui lòng thử lại", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				JOptionPane.showMessageDialog(SignUpView.this, "Mã xác nhận đã được gửi vào email. Vui lòng kiểm tra.");
			}
		});
		btnsendOTP_Button.setBounds(241, 110, 81, 23);
		contentPane.add(btnsendOTP_Button);

		otpField = new PlaceholderTextField("Nhập OTP");
		otpField.setHorizontalAlignment(SwingConstants.CENTER);
		otpField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		otpField.setBounds(222, 271, 95, 27);
		contentPane.add(otpField);
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		String phoneRegex = "^0\\d{9}$";
		Pattern pat = Pattern.compile(phoneRegex);
		return phoneNumber != null && pat.matcher(phoneNumber).matches();
	}
}
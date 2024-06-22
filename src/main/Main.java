package main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import view.*;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView Loginframe = new LoginView();
					Loginframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
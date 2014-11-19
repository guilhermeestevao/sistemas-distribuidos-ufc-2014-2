package caseRemoto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class FormCriacaoUsuario {

	private JFrame frame;
	private JTextField tfNome;
	private JPanel panel;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public FormCriacaoUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 450, 181);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 56, 69, 14);
		getFrame().getContentPane().add(lblNome);

		tfNome = new JTextField();
		tfNome.setBounds(89, 53, 287, 20);
		getFrame().getContentPane().add(tfNome);
		tfNome.setColumns(10);

		JLabel lblUsurio = new JLabel("USUÁRIO");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsurio.setBackground(Color.WHITE);
		lblUsurio.setBounds(0, 0, 434, 32);
		lblUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblUsurio);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 434, 32);
		getFrame().getContentPane().add(panel);

		btnNewButton = new JButton("SUBMETER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final String user = tfNome.getText().toString();

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Tabuleiro window = new Tabuleiro(user);
							window.getFrame().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBounds(99, 91, 225, 23);
		getFrame().getContentPane().add(btnNewButton);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}

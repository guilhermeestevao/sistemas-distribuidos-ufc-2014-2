package caseRemoto;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

public class Tabuleiro {

	private JFrame frame;
	private List<String> posicoes;
	private String posicoesBatalhaNaval[];
	private List<JButton> buttons;
	private List<JButton> buttons1;
	private Usuario usuario;
	private JLabel lblValorPontuacao;
	private int pecasAcertadas;
	private int pontuacao;
	private Logica logica;
	
	public Tabuleiro(Usuario usuario) {
		logica = new Logica();
		this.usuario = usuario;
		posicoesBatalhaNaval = logica.posicionarTabuleiro(usuario.getNavio());
		pontuacao = 0;
		pecasAcertadas = 0;
		initialize();
	}

	private void initialize() {

		buttons = new ArrayList<JButton>();
		buttons1 = new ArrayList<JButton>();

		setFrame(new JFrame());
		getFrame().setResizable(false);
		getFrame().setBounds(100, 100, 1000, 600);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel panel = new JPanel();
		panel.setBounds(10, 44, 450, 450);
		getFrame().getContentPane().add(panel);
		panel.setLayout(null);

		final JPanel panel_1 = new JPanel();
		panel_1.setBounds(534, 44, 450, 450);
		getFrame().getContentPane().add(panel_1);
		panel_1.setLayout(null);

		getFrame().getContentPane().setLayout(null);
		
		JLabel lblUser = new JLabel(usuario.getNome());
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUser.setBounds(39, 19, 335, 14);
		frame.getContentPane().add(lblUser);
		
		JLabel lblPontuao = new JLabel("Pontuação: ");
		lblPontuao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPontuao.setBounds(20, 513, 126, 14);
		frame.getContentPane().add(lblPontuao);
		
		lblValorPontuacao = new JLabel(String.valueOf(pontuacao));
		lblValorPontuacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValorPontuacao.setBounds(132, 513, 162, 14);
		frame.getContentPane().add(lblValorPontuacao);

		JButton button_0_0 = new JButton("");
		buttons.add(button_0_0);

		final JButton button_0_1 = new JButton("");
		buttons.add(button_0_1);

		final JButton button_0_2 = new JButton("");
		buttons.add(button_0_2);

		final JButton button_0_3 = new JButton("");
		buttons.add(button_0_3);
		
		final JButton button_0_4 = new JButton("");
		buttons.add(button_0_4);

		final JButton button_0_5 = new JButton("");
		buttons.add(button_0_5);

		final JButton button_0_6 = new JButton("");
		buttons.add(button_0_6);

		final JButton button_0_7 = new JButton("");
		buttons.add(button_0_7);

		final JButton button_0_8 = new JButton("");
		buttons.add(button_0_8);

		final JButton button_0_9 = new JButton("");
		buttons.add(button_0_9);

		final JButton button_1_0 = new JButton("");
		buttons.add(button_1_0);

		final JButton button_1_1 = new JButton("");
		buttons.add(button_1_1);

		final JButton button_1_2 = new JButton("");
		buttons.add(button_1_2);

		final JButton button_1_3 = new JButton("");
		buttons.add(button_1_3);

		final JButton button_1_4 = new JButton("");
		buttons.add(button_1_4);

		final JButton button_1_5 = new JButton("");
		buttons.add(button_1_5);

		final JButton button_1_6 = new JButton("");
		buttons.add(button_1_6);

		final JButton button_1_7 = new JButton("");
		buttons.add(button_1_7);

		final JButton button_1_8 = new JButton("");
		buttons.add(button_1_8);

		final JButton button_1_9 = new JButton("");
		buttons.add(button_1_9);

		final JButton button_2_0 = new JButton("");
		buttons.add(button_2_0);

		final JButton button_2_1 = new JButton("");
		buttons.add(button_2_1);

		final JButton button_2_2 = new JButton("");
		buttons.add(button_2_2);

		final JButton button_2_3 = new JButton("");
		buttons.add(button_2_3);

		final JButton button_2_4 = new JButton("");
		buttons.add(button_2_4);

		final JButton button_2_5 = new JButton("");
		buttons.add(button_2_5);

		final JButton button_2_6 = new JButton("");
		buttons.add(button_2_6);

		final JButton button_2_7 = new JButton("");
		buttons.add(button_2_7);

		final JButton button_2_8 = new JButton("");
		buttons.add(button_2_8);

		final JButton button_2_9 = new JButton("");
		buttons.add(button_2_9);

		final JButton button_3_0 = new JButton("");
		buttons.add(button_3_0);

		final JButton button_3_1 = new JButton("");
		buttons.add(button_3_1);

		final JButton button_3_2 = new JButton("");
		buttons.add(button_3_2);

		final JButton button_3_3 = new JButton("");
		buttons.add(button_3_3);

		final JButton button_3_4 = new JButton("");
		buttons.add(button_3_4);

		final JButton button_3_5 = new JButton("");
		buttons.add(button_3_5);

		final JButton button_3_6 = new JButton("");
		buttons.add(button_3_6);

		final JButton button_3_7 = new JButton("");
		buttons.add(button_3_7);

		final JButton button_3_8 = new JButton("");
		buttons.add(button_3_8);

		final JButton button_3_9 = new JButton("");
		buttons.add(button_3_9);

		final JButton button_4_0 = new JButton("");
		buttons.add(button_4_0);

		final JButton button_4_1 = new JButton("");
		buttons.add(button_4_1);

		final JButton button_4_2 = new JButton("");
		buttons.add(button_4_2);

		final JButton button_4_3 = new JButton("");
		buttons.add(button_4_3);

		final JButton button_4_4 = new JButton("");
		buttons.add(button_4_4);

		final JButton button_4_5 = new JButton("");
		buttons.add(button_4_5);

		final JButton button_4_6 = new JButton("");
		buttons.add(button_4_6);

		final JButton button_4_7 = new JButton("");
		buttons.add(button_4_7);

		final JButton button_4_8 = new JButton("");
		buttons.add(button_4_8);

		final JButton button_4_9 = new JButton("");
		buttons.add(button_4_9);

		final JButton button_5_0 = new JButton("");
		buttons.add(button_5_0);

		final JButton button_5_1 = new JButton("");
		buttons.add(button_5_1);

		final JButton button_5_2 = new JButton("");
		buttons.add(button_5_2);

		final JButton button_5_3 = new JButton("");
		buttons.add(button_5_3);

		final JButton button_5_4 = new JButton("");
		buttons.add(button_5_4);

		final JButton button_5_5 = new JButton("");
		buttons.add(button_5_5);

		final JButton button_5_6 = new JButton("");
		buttons.add(button_5_6);

		final JButton button_5_7 = new JButton("");
		buttons.add(button_5_7);

		final JButton button_5_8 = new JButton("");
		buttons.add(button_5_8);

		final JButton button_5_9 = new JButton("");
		buttons.add(button_5_9);

		final JButton button_6_0 = new JButton("");
		buttons.add(button_6_0);

		final JButton button_6_1 = new JButton("");
		buttons.add(button_6_1);

		final JButton button_6_2 = new JButton("");
		buttons.add(button_6_2);

		final JButton button_6_3 = new JButton("");
		buttons.add(button_6_3);

		final JButton button_6_4 = new JButton("");
		buttons.add(button_6_4);

		final JButton button_6_5 = new JButton("");
		buttons.add(button_6_5);

		final JButton button_6_6 = new JButton("");
		buttons.add(button_6_6);

		final JButton button_6_7 = new JButton("");
		buttons.add(button_6_7);

		final JButton button_6_8 = new JButton("");
		buttons.add(button_6_8);

		final JButton button_6_9 = new JButton("");
		buttons.add(button_6_9);

		final JButton button_7_0 = new JButton("");
		buttons.add(button_7_0);

		final JButton button_7_1 = new JButton("");
		buttons.add(button_7_1);

		final JButton button_7_2 = new JButton("");
		buttons.add(button_7_2);

		final JButton button_7_3 = new JButton("");
		buttons.add(button_7_3);

		final JButton button_7_4 = new JButton("");
		buttons.add(button_7_4);

		final JButton button_7_5 = new JButton("");
		buttons.add(button_7_5);

		final JButton button_7_6 = new JButton("");
		buttons.add(button_7_6);

		final JButton button_7_7 = new JButton("");
		buttons.add(button_7_7);

		final JButton button_7_8 = new JButton("");
		buttons.add(button_7_8);

		final JButton button_7_9 = new JButton("");
		buttons.add(button_7_9);

		final JButton button_8_0 = new JButton("");
		buttons.add(button_8_0);

		final JButton button_8_1 = new JButton("");
		buttons.add(button_8_1);

		final JButton button_8_2 = new JButton("");
		buttons.add(button_8_2);

		final JButton button_8_3 = new JButton("");
		buttons.add(button_8_3);

		final JButton button_8_4 = new JButton("");
		buttons.add(button_8_4);

		final JButton button_8_5 = new JButton("");
		buttons.add(button_8_5);

		final JButton button_8_6 = new JButton("");
		buttons.add(button_8_6);

		final JButton button_8_7 = new JButton("");
		buttons.add(button_8_7);

		final JButton button_8_8 = new JButton("");
		buttons.add(button_8_8);

		final JButton button_8_9 = new JButton("");
		buttons.add(button_8_9);

		final JButton button_9_0 = new JButton("");
		buttons.add(button_9_0);

		final JButton button_9_1 = new JButton("");
		buttons.add(button_9_1);

		final JButton button_9_2 = new JButton("");
		buttons.add(button_9_2);

		final JButton button_9_3 = new JButton("");
		buttons.add(button_9_3);

		final JButton button_9_4 = new JButton("");
		buttons.add(button_9_4);

		final JButton button_9_5 = new JButton("");
		buttons.add(button_9_5);

		final JButton button_9_6 = new JButton("");
		buttons.add(button_9_6);

		final JButton button_9_7 = new JButton("");
		buttons.add(button_9_7);

		final JButton button_9_8 = new JButton("");
		buttons.add(button_9_8);

		final JButton button_9_9 = new JButton("");
		buttons.add(button_9_9);

		JButton button_1_0_0 = new JButton("");
		buttons1.add(button_1_0_0);

		final JButton button_1_0_1 = new JButton("");
		buttons1.add(button_1_0_1);

		final JButton button_1_0_2 = new JButton("");
		buttons1.add(button_1_0_2);

		final JButton button_1_0_3 = new JButton("");
		buttons1.add(button_1_0_3);
		

		final JButton button_1_0_4 = new JButton("");
		buttons1.add(button_1_0_4);

		final JButton button_1_0_5 = new JButton("");
		buttons1.add(button_1_0_5);

		final JButton button_1_0_6 = new JButton("");
		buttons1.add(button_1_0_6);

		final JButton button_1_0_7 = new JButton("");
		buttons1.add(button_1_0_7);

		final JButton button_1_0_8 = new JButton("");
		buttons1.add(button_1_0_8);

		final JButton button_1_0_9 = new JButton("");
		buttons1.add(button_1_0_9);

		final JButton button_1_1_0 = new JButton("");
		buttons1.add(button_1_1_0);

		final JButton button_1_1_1 = new JButton("");
		buttons1.add(button_1_1_1);

		final JButton button_1_1_2 = new JButton("");
		buttons1.add(button_1_1_2);

		final JButton button_1_1_3 = new JButton("");
		buttons1.add(button_1_1_3);

		final JButton button_1_1_4 = new JButton("");
		buttons1.add(button_1_1_4);

		final JButton button_1_1_5 = new JButton("");
		buttons1.add(button_1_1_5);

		final JButton button_1_1_6 = new JButton("");
		buttons1.add(button_1_1_6);

		final JButton button_1_1_7 = new JButton("");
		buttons1.add(button_1_1_7);

		final JButton button_1_1_8 = new JButton("");
		buttons1.add(button_1_1_8);

		final JButton button_1_1_9 = new JButton("");
		buttons1.add(button_1_1_9);

		final JButton button_1_2_0 = new JButton("");
		buttons1.add(button_1_2_0);

		final JButton button_1_2_1 = new JButton("");
		buttons1.add(button_1_2_1);

		final JButton button_1_2_2 = new JButton("");
		buttons1.add(button_1_2_2);

		final JButton button_1_2_3 = new JButton("");
		buttons1.add(button_1_2_3);

		final JButton button_1_2_4 = new JButton("");
		buttons1.add(button_1_2_4);

		final JButton button_1_2_5 = new JButton("");
		buttons1.add(button_1_2_5);

		final JButton button_1_2_6 = new JButton("");
		buttons1.add(button_1_2_6);

		final JButton button_1_2_7 = new JButton("");
		buttons1.add(button_1_2_7);

		final JButton button_1_2_8 = new JButton("");
		buttons1.add(button_1_2_8);

		final JButton button_1_2_9 = new JButton("");
		buttons1.add(button_1_2_9);

		final JButton button_1_3_0 = new JButton("");
		buttons1.add(button_1_3_0);

		final JButton button_1_3_1 = new JButton("");
		buttons1.add(button_1_3_1);

		final JButton button_1_3_2 = new JButton("");
		buttons1.add(button_1_3_2);

		final JButton button_1_3_3 = new JButton("");
		buttons1.add(button_1_3_3);

		final JButton button_1_3_4 = new JButton("");
		buttons1.add(button_1_3_4);

		final JButton button_1_3_5 = new JButton("");
		buttons1.add(button_1_3_5);

		final JButton button_1_3_6 = new JButton("");
		buttons1.add(button_1_3_6);

		final JButton button_1_3_7 = new JButton("");
		buttons1.add(button_1_3_7);

		final JButton button_1_3_8 = new JButton("");
		buttons1.add(button_1_3_8);

		final JButton button_1_3_9 = new JButton("");
		buttons1.add(button_1_3_9);

		final JButton button_1_4_0 = new JButton("");
		buttons1.add(button_1_4_0);

		final JButton button_1_4_1 = new JButton("");
		buttons1.add(button_1_4_1);

		final JButton button_1_4_2 = new JButton("");
		buttons1.add(button_1_4_2);

		final JButton button_1_4_3 = new JButton("");
		buttons1.add(button_1_4_3);

		final JButton button_1_4_4 = new JButton("");
		buttons1.add(button_1_4_4);

		final JButton button_1_4_5 = new JButton("");
		buttons1.add(button_1_4_5);

		final JButton button_1_4_6 = new JButton("");
		buttons1.add(button_1_4_6);

		final JButton button_1_4_7 = new JButton("");
		buttons1.add(button_1_4_7);

		final JButton button_1_4_8 = new JButton("");
		buttons1.add(button_1_4_8);

		final JButton button_1_4_9 = new JButton("");
		buttons1.add(button_1_4_9);

		final JButton button_1_5_0 = new JButton("");
		buttons1.add(button_1_5_0);

		final JButton button_1_5_1 = new JButton("");
		buttons1.add(button_1_5_1);

		final JButton button_1_5_2 = new JButton("");
		buttons1.add(button_1_5_2);

		final JButton button_1_5_3 = new JButton("");
		buttons1.add(button_1_5_3);

		final JButton button_1_5_4 = new JButton("");
		buttons1.add(button_1_5_4);

		final JButton button_1_5_5 = new JButton("");
		buttons1.add(button_1_5_5);

		final JButton button_1_5_6 = new JButton("");
		buttons1.add(button_1_5_6);

		final JButton button_1_5_7 = new JButton("");
		buttons1.add(button_1_5_7);

		final JButton button_1_5_8 = new JButton("");
		buttons1.add(button_1_5_8);

		final JButton button_1_5_9 = new JButton("");
		buttons1.add(button_1_5_9);

		final JButton button_1_6_0 = new JButton("");
		buttons1.add(button_1_6_0);

		final JButton button_1_6_1 = new JButton("");
		buttons1.add(button_1_6_1);

		final JButton button_1_6_2 = new JButton("");
		buttons1.add(button_1_6_2);

		final JButton button_1_6_3 = new JButton("");
		buttons1.add(button_1_6_3);

		final JButton button_1_6_4 = new JButton("");
		buttons1.add(button_1_6_4);

		final JButton button_1_6_5 = new JButton("");
		buttons1.add(button_1_6_5);

		final JButton button_1_6_6 = new JButton("");
		buttons1.add(button_1_6_6);

		final JButton button_1_6_7 = new JButton("");
		buttons1.add(button_1_6_7);

		final JButton button_1_6_8 = new JButton("");
		buttons1.add(button_1_6_8);

		final JButton button_1_6_9 = new JButton("");
		buttons1.add(button_1_6_9);

		final JButton button_1_7_0 = new JButton("");
		buttons1.add(button_1_7_0);

		final JButton button_1_7_1 = new JButton("");
		buttons1.add(button_1_7_1);

		final JButton button_1_7_2 = new JButton("");
		buttons1.add(button_1_7_2);

		final JButton button_1_7_3 = new JButton("");
		buttons1.add(button_1_7_3);

		final JButton button_1_7_4 = new JButton("");
		buttons1.add(button_1_7_4);

		final JButton button_1_7_5 = new JButton("");
		buttons1.add(button_1_7_5);

		final JButton button_1_7_6 = new JButton("");
		buttons1.add(button_1_7_6);

		final JButton button_1_7_7 = new JButton("");
		buttons1.add(button_1_7_7);

		final JButton button_1_7_8 = new JButton("");
		buttons1.add(button_1_7_8);

		final JButton button_1_7_9 = new JButton("");
		buttons1.add(button_1_7_9);

		final JButton button_1_8_0 = new JButton("");
		buttons1.add(button_1_8_0);

		final JButton button_1_8_1 = new JButton("");
		buttons1.add(button_1_8_1);

		final JButton button_1_8_2 = new JButton("");
		buttons1.add(button_1_8_2);

		final JButton button_1_8_3 = new JButton("");
		buttons1.add(button_1_8_3);

		final JButton button_1_8_4 = new JButton("");
		buttons1.add(button_1_8_4);

		final JButton button_1_8_5 = new JButton("");
		buttons1.add(button_1_8_5);

		final JButton button_1_8_6 = new JButton("");
		buttons1.add(button_1_8_6);

		final JButton button_1_8_7 = new JButton("");
		buttons1.add(button_1_8_7);

		final JButton button_1_8_8 = new JButton("");
		buttons1.add(button_1_8_8);

		final JButton button_1_8_9 = new JButton("");
		buttons1.add(button_1_8_9);

		final JButton button_1_9_0 = new JButton("");
		buttons1.add(button_1_9_0);

		final JButton button_1_9_1 = new JButton("");
		buttons1.add(button_1_9_1);

		final JButton button_1_9_2 = new JButton("");
		buttons1.add(button_1_9_2);

		final JButton button_1_9_3 = new JButton("");
		buttons1.add(button_1_9_3);

		final JButton button_1_9_4 = new JButton("");
		buttons1.add(button_1_9_4);

		final JButton button_1_9_5 = new JButton("");
		buttons1.add(button_1_9_5);

		final JButton button_1_9_6 = new JButton("");
		buttons1.add(button_1_9_6);

		final JButton button_1_9_7 = new JButton("");
		buttons1.add(button_1_9_7);

		final JButton button_1_9_8 = new JButton("");
		buttons1.add(button_1_9_8);

		final JButton button_1_9_9 = new JButton("");
		buttons1.add(button_1_9_9);
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				buttons.get((i*10)+j).setBounds(10+(39*j), 10+(39*i), 40, 40);
				panel.add(buttons.get((i*10)+j));
				buttons1.get((i*10)+j).setBounds(10+(39*j), 10+(39*i), 40, 40);
				panel_1.add(buttons1.get((i*10)+j));
				buttons1.get((i*10)+j).setEnabled(false);
			}
		}

		for (int i = 0; i < buttons.size(); i++) {
			final int j = i;
			buttons.get(j).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verificarPosicao(j, buttons.get(j));
					verificarPosicaoAdversario(j, buttons1.get(j));
				}
			});
		}

	}

	private void verificarPosicao(int i, JButton button) {
		if (posicoesBatalhaNaval[i].equals("submarino")) {
			functionButton(button, new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\navio.png"));
		} else if (posicoesBatalhaNaval[i].equals("left_torpedeiro")) {
			functionButton(button, new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\left_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("right_torpedeiro")) {
			functionButton(button, new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\right_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("center_cruzador")) {
			functionButton(button, new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\center_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("left_cruzador")) {
			functionButton(button, new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\left_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("right_cruzador")) {
			functionButton(button, new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\right_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("center_couradoro")) {
			functionButton(button, new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\center_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("left_couradoro")) {
			functionButton(button, new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\left_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("right_couradoro")) {
			functionButton(button, new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\right_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("center_porta_avioes")) {
			functionButton(button, new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\center_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("left_porta_avioes")) {
			functionButton(button, new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\left_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("right_porta_avioes")) {
			functionButton(button, new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\right_nav.png"));
		} else {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\x.png"));
			button.setEnabled(false);
		}
	}
	
	private void verificarPosicaoAdversario(int i, JButton button) {
		if (posicoesBatalhaNaval[i].equals("submarino")) {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\navio.png"));
		} else if (posicoesBatalhaNaval[i].equals("left_torpedeiro")) {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\left_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("right_torpedeiro")) {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\right_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("center_cruzador")) {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\center_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("left_cruzador")) {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\left_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("right_cruzador")) {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\right_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("center_couradoro")) {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\center_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("left_couradoro")) {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\left_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("right_couradoro")) {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\right_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("center_porta_avioes")) {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\center_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("left_porta_avioes")) {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\left_nav.png"));
		} else if (posicoesBatalhaNaval[i].equals("right_porta_avioes")) {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\right_nav.png"));
		} else {
			button.setIcon(new ImageIcon(
					"C:\\Users\\Alex Oliveira\\Documents\\workspace\\TrabalhoSdRMI\\img\\x.png"));
		}
	}

	private void functionButton(JButton button, ImageIcon image) {
		button.setIcon(image);
		button.setEnabled(false);
		pontuacao += 1;
		lblValorPontuacao.setText(String.valueOf(pontuacao));
		pecasAcertadas += 1;
		if(pecasAcertadas == 30){
			String message = logica.msgGameOver(pontuacao, usuario.getNome());
			JOptionPane.showMessageDialog(null, message);
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}

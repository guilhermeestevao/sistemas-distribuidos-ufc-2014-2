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
	private JLabel lblNavios;
	private JPanel panel_1;
	private JLabel lblSubmarino;
	private JLabel lblPosioInicialSubmarino1;
	private JLabel lblPosioInicialSubmarino2;
	private JTextField tfSub1;
	private JTextField tfSub2;
	private JLabel lblTorpedeirotamanho;
	private JLabel lblPosioInicialTorpedeiro;
	private JTextField tfTorpedeiro1;
	private JTextField tfTorpedeiro2;
	private JLabel lblPosioInicialTorpedeiro_1;
	private JLabel lblCruzadortamanho;
	private JLabel lblPosioInicialCruzador;
	private JTextField tfCruzador1;
	private JTextField tfCruzador2;
	private JLabel lblPosioInicialCruzador_1;
	private JLabel lblCouradorotamanho;
	private JLabel lblPosioInicialCouradoro;
	private JTextField tfCouradoro1;
	private JTextField tfCouradoro2;
	private JLabel lblPosioInicialCouradoro_1;
	private JLabel lblPortaAviestamanho;
	private JLabel lblPosioInicialPorta;
	private JTextField tfPortaAvioes1;
	private JTextField tfPortaAvioes2;
	private JLabel lblPosioInicialPorta_1;
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
		getFrame().setBounds(100, 100, 450, 595);
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
		
		lblNavios = new JLabel("NAVIOS (POSIÇÃO HORIZONTAL - 0 a 99)");
		lblNavios.setHorizontalAlignment(SwingConstants.CENTER);
		lblNavios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNavios.setBackground(Color.WHITE);
		lblNavios.setBounds(0, 92, 434, 32);
		getFrame().getContentPane().add(lblNavios);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 92, 434, 32);
		getFrame().getContentPane().add(panel_1);
		
		lblSubmarino = new JLabel("SUBMARINO (TAMANHO: 1)");
		lblSubmarino.setBounds(0, 123, 434, 32);
		lblSubmarino.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblSubmarino);
		
		lblPosioInicialSubmarino1 = new JLabel("Posição inicial submarino 1");
		lblPosioInicialSubmarino1.setBounds(0, 157, 205, 14);
		lblPosioInicialSubmarino1.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblPosioInicialSubmarino1);
		
		lblPosioInicialSubmarino2 = new JLabel("Posição inicial submarino 2");
		lblPosioInicialSubmarino2.setBounds(202, 157, 232, 14);
		lblPosioInicialSubmarino2.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblPosioInicialSubmarino2);
		
		tfSub1 = new JTextField();
		tfSub1.setBounds(58, 176, 86, 20);
		getFrame().getContentPane().add(tfSub1);
		tfSub1.setColumns(10);
		
		tfSub2 = new JTextField();
		tfSub2.setColumns(10);
		tfSub2.setBounds(277, 176, 86, 20);
		getFrame().getContentPane().add(tfSub2);
		
		lblTorpedeirotamanho = new JLabel("TORPEDEIRO (TAMANHO: 2)");
		lblTorpedeirotamanho.setHorizontalAlignment(SwingConstants.CENTER);
		lblTorpedeirotamanho.setBounds(0, 198, 434, 32);
		getFrame().getContentPane().add(lblTorpedeirotamanho);
		
		lblPosioInicialTorpedeiro = new JLabel("Posição inicial torpedeiro 1");
		lblPosioInicialTorpedeiro.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosioInicialTorpedeiro.setBounds(0, 232, 205, 14);
		getFrame().getContentPane().add(lblPosioInicialTorpedeiro);
		
		tfTorpedeiro1 = new JTextField();
		tfTorpedeiro1.setColumns(10);
		tfTorpedeiro1.setBounds(58, 251, 86, 20);
		getFrame().getContentPane().add(tfTorpedeiro1);
		
		tfTorpedeiro2 = new JTextField();
		tfTorpedeiro2.setColumns(10);
		tfTorpedeiro2.setBounds(277, 251, 86, 20);
		getFrame().getContentPane().add(tfTorpedeiro2);
		
		lblPosioInicialTorpedeiro_1 = new JLabel("Posição inicial torpedeiro 2");
		lblPosioInicialTorpedeiro_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosioInicialTorpedeiro_1.setBounds(202, 232, 232, 14);
		getFrame().getContentPane().add(lblPosioInicialTorpedeiro_1);
		
		lblCruzadortamanho = new JLabel("CRUZADOR (TAMANHO: 3)");
		lblCruzadortamanho.setHorizontalAlignment(SwingConstants.CENTER);
		lblCruzadortamanho.setBounds(0, 273, 434, 32);
		getFrame().getContentPane().add(lblCruzadortamanho);
		
		lblPosioInicialCruzador = new JLabel("Posição inicial cruzador 1");
		lblPosioInicialCruzador.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosioInicialCruzador.setBounds(0, 307, 205, 14);
		getFrame().getContentPane().add(lblPosioInicialCruzador);
		
		tfCruzador1 = new JTextField();
		tfCruzador1.setColumns(10);
		tfCruzador1.setBounds(58, 326, 86, 20);
		getFrame().getContentPane().add(tfCruzador1);
		
		tfCruzador2 = new JTextField();
		tfCruzador2.setColumns(10);
		tfCruzador2.setBounds(277, 326, 86, 20);
		getFrame().getContentPane().add(tfCruzador2);
		
		lblPosioInicialCruzador_1 = new JLabel("Posição inicial cruzador 2");
		lblPosioInicialCruzador_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosioInicialCruzador_1.setBounds(202, 307, 232, 14);
		getFrame().getContentPane().add(lblPosioInicialCruzador_1);
		
		lblCouradorotamanho = new JLabel("COURADORO (TAMANHO: 4)");
		lblCouradorotamanho.setHorizontalAlignment(SwingConstants.CENTER);
		lblCouradorotamanho.setBounds(0, 346, 434, 32);
		getFrame().getContentPane().add(lblCouradorotamanho);
		
		lblPosioInicialCouradoro = new JLabel("Posição inicial Couradoro 1");
		lblPosioInicialCouradoro.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosioInicialCouradoro.setBounds(0, 380, 205, 14);
		getFrame().getContentPane().add(lblPosioInicialCouradoro);
		
		tfCouradoro1 = new JTextField();
		tfCouradoro1.setColumns(10);
		tfCouradoro1.setBounds(58, 399, 86, 20);
		getFrame().getContentPane().add(tfCouradoro1);
		
		tfCouradoro2 = new JTextField();
		tfCouradoro2.setColumns(10);
		tfCouradoro2.setBounds(277, 399, 86, 20);
		getFrame().getContentPane().add(tfCouradoro2);
		
		lblPosioInicialCouradoro_1 = new JLabel("Posição inicial Couradoro 2");
		lblPosioInicialCouradoro_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosioInicialCouradoro_1.setBounds(202, 380, 232, 14);
		getFrame().getContentPane().add(lblPosioInicialCouradoro_1);
		
		lblPortaAviestamanho = new JLabel("PORTA AVIÕES (TAMANHO: 5)");
		lblPortaAviestamanho.setHorizontalAlignment(SwingConstants.CENTER);
		lblPortaAviestamanho.setBounds(0, 421, 434, 32);
		getFrame().getContentPane().add(lblPortaAviestamanho);
		
		lblPosioInicialPorta = new JLabel("Posição inicial Porta Aviões 1");
		lblPosioInicialPorta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosioInicialPorta.setBounds(0, 455, 205, 14);
		getFrame().getContentPane().add(lblPosioInicialPorta);
		
		tfPortaAvioes1 = new JTextField();
		tfPortaAvioes1.setColumns(10);
		tfPortaAvioes1.setBounds(58, 474, 86, 20);
		getFrame().getContentPane().add(tfPortaAvioes1);
		
		tfPortaAvioes2 = new JTextField();
		tfPortaAvioes2.setColumns(10);
		tfPortaAvioes2.setBounds(277, 474, 86, 20);
		getFrame().getContentPane().add(tfPortaAvioes2);
		
		lblPosioInicialPorta_1 = new JLabel("Posição inicial Porta Aviões 2");
		lblPosioInicialPorta_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosioInicialPorta_1.setBounds(202, 455, 232, 14);
		getFrame().getContentPane().add(lblPosioInicialPorta_1);
		
		btnNewButton = new JButton("SUBMETER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Usuario user = new Usuario();
				user.setNome(tfNome.getText().toString());
				List<Navio> navios = new ArrayList<Navio>();
				Navio navio = new Navio();
				navio.setNome("submarino");
				navio.setNavio1(Integer.parseInt(tfSub1.getText().toString()));
				navio.setNavio2(Integer.parseInt(tfSub2.getText().toString()));
				navios.add(navio);
				Navio navio1 = new Navio();
				navio1.setNome("torpedeiro");
				navio1.setNavio1(Integer.parseInt(tfTorpedeiro1.getText().toString()));
				navio1.setNavio2(Integer.parseInt(tfTorpedeiro2.getText().toString()));
				navios.add(navio1);
				Navio navio2 = new Navio();
				navio2.setNome("cruzador");
				navio2.setNavio1(Integer.parseInt(tfCruzador1.getText().toString()));
				navio2.setNavio2(Integer.parseInt(tfCruzador2.getText().toString()));
				navios.add(navio2);
				Navio navio3 = new Navio();
				navio3.setNome("couradoro");
				navio3.setNavio1(Integer.parseInt(tfCouradoro1.getText().toString()));
				navio3.setNavio2(Integer.parseInt(tfCouradoro2.getText().toString()));
				navios.add(navio3);
				Navio navio4 = new Navio();
				navio4.setNome("porta_avioes");
				navio4.setNavio1(Integer.parseInt(tfPortaAvioes1.getText().toString()));
				navio4.setNavio2(Integer.parseInt(tfPortaAvioes2.getText().toString()));
				navios.add(navio4);
				user.setNavio(navios);
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
		btnNewButton.setBounds(89, 512, 225, 23);
		getFrame().getContentPane().add(btnNewButton);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}

package br.com.unisantos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JEditorPane;
import javax.swing.Box;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class Cadastro {

	private JFrame frame;
	private JTextField txt_nome1;
	private JTextField txt_nome2;
	private JTextField txt_nome3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro window = new Cadastro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 51));
		frame.setBounds(150, 100, 568, 312);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt_nome1 = new JTextField();
		txt_nome1.setBounds(178, 101, 96, 19);
		frame.getContentPane().add(txt_nome1);
		txt_nome1.setColumns(10);
		
		txt_nome2 = new JTextField();
		txt_nome2.setBounds(178, 141, 96, 19);
		frame.getContentPane().add(txt_nome2);
		txt_nome2.setColumns(10);
		
		txt_nome3 = new JTextField();
		txt_nome3.setBounds(178, 181, 96, 19);
		frame.getContentPane().add(txt_nome3);
		txt_nome3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Escreva tr\u00EAs nomes");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel.setBounds(123, 62, 156, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lbl_nome1 = new JLabel("Nome 1");
		lbl_nome1.setForeground(Color.WHITE);
		lbl_nome1.setBounds(123, 104, 45, 13);
		frame.getContentPane().add(lbl_nome1);
		
		JLabel lbl_nome2 = new JLabel("Nome 2");
		lbl_nome2.setForeground(Color.WHITE);
		lbl_nome2.setBounds(123, 144, 45, 13);
		frame.getContentPane().add(lbl_nome2);
		
		JLabel lbl_nome3 = new JLabel("Nome 3");
		lbl_nome3.setForeground(Color.WHITE);
		lbl_nome3.setBounds(123, 184, 45, 13);
		frame.getContentPane().add(lbl_nome3);
		
		JLabel lbl_sexoNome1 = new JLabel("0%");
		lbl_sexoNome1.setForeground(Color.WHITE);
		lbl_sexoNome1.setBounds(298, 104, 96, 13);
		frame.getContentPane().add(lbl_sexoNome1);
		
		JLabel lbl_sexoNome2 = new JLabel("0%");
		lbl_sexoNome2.setForeground(Color.WHITE);
		lbl_sexoNome2.setBounds(298, 144, 96, 13);
		frame.getContentPane().add(lbl_sexoNome2);
		
		JLabel lbl_sexoNome3 = new JLabel("0%");
		lbl_sexoNome3.setForeground(Color.WHITE);
		lbl_sexoNome3.setBounds(298, 184, 96, 13);
		frame.getContentPane().add(lbl_sexoNome3);
		
		JButton btn_predizer = new JButton("Predizer");
		btn_predizer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(	txt_nome1.getText()==null || txt_nome1.getText().trim().equals(""))
				{
					lbl_sexoNome1.setText("Digite algo");
				}
				
				if(	txt_nome2.getText()==null || txt_nome2.getText().trim().equals(""))
				{
					lbl_sexoNome2.setText("Digite algo");
				}
				
				if(	txt_nome3.getText()==null || txt_nome3.getText().trim().equals(""))
				{
					lbl_sexoNome3.setText("Digite algo");
				}
					
			}
		});
		btn_predizer.setBounds(113, 234, 330, 21);
		frame.getContentPane().add(btn_predizer);
		
		JPanel pn_results = new JPanel();
		pn_results.setBorder(new LineBorder(Color.WHITE, 3, true));
		pn_results.setBackground(new Color(0, 0, 51));
		pn_results.setBounds(284, 89, 159, 127);
		frame.getContentPane().add(pn_results);
		
		JPanel pn_nomes = new JPanel();
		pn_nomes.setBorder(new LineBorder(Color.WHITE, 3, true));
		pn_nomes.setBackground(new Color(0, 0, 51));
		pn_nomes.setBounds(113, 89, 169, 127);
		frame.getContentPane().add(pn_nomes);
		
		JLabel lblResultado = new JLabel("Resultado");
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setForeground(Color.WHITE);
		lblResultado.setFont(new Font("Verdana", Font.BOLD, 12));
		lblResultado.setBounds(282, 62, 156, 29);
		frame.getContentPane().add(lblResultado);
		
		JLabel lblIdentificadorDeGeneros = new JLabel("Identificador de G\u00EAneros");
		lblIdentificadorDeGeneros.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentificadorDeGeneros.setForeground(Color.WHITE);
		lblIdentificadorDeGeneros.setFont(new Font("Verdana", Font.BOLD, 20));
		lblIdentificadorDeGeneros.setBounds(10, 10, 534, 29);
		frame.getContentPane().add(lblIdentificadorDeGeneros);
	}
}

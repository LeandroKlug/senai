package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class ViewSobre extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSobre frame = new ViewSobre();
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
	public ViewSobre() {
		setTitle("Sobre Grupo 1 Senai");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 501, 384);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ViewSobre.class.getResource("/imagens/Nomes do Grupo 1.png")));
		lblNewLabel.setBounds(73, 26, 378, 292);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ViewSobre.class.getResource("/imagens/Fotos de 3.png")));
		lblNewLabel_1.setBounds(10, 75, 45, 194);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ViewSobre.class.getResource("/imagens/Foto da Aman.png")));
		lblNewLabel_2.setBounds(0, 11, 69, 56);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(ViewSobre.class.getResource("/imagens/Foto de R.jpg")));
		lblNewLabel_3.setBounds(0, 277, 63, 57);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Grupo 1");
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNewLabel_4.setBounds(75, 0, 376, 15);
		contentPane.add(lblNewLabel_4);
	}
}

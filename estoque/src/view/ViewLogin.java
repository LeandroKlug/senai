package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PessoaDAO;
import models.Pessoa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import util.Seguranca;
import util.Validador;

import java.awt.event.KeyEvent;

public class ViewLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JPasswordField textFieldSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogin frame = new ViewLogin();
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
	public ViewLogin() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewLogin.class.getResource("/imagens/heart.png")));
		setTitle(":: Login doa\u00E7\u00F5es SENAI");
		setBounds(100, 100, 567, 340);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN DOA\u00C7\u00D5ES");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel.setBounds(54, 13, 173, 38);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 55, 535, 2);
		contentPane.add(separator);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblEmail.setBounds(12, 70, 80, 28);
		contentPane.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblSenha.setBounds(12, 157, 80, 28);
		contentPane.add(lblSenha);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Arial Black", Font.BOLD, 13));
		textFieldEmail.setBounds(9, 105, 536, 36);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldSenha = new JPasswordField();
		textFieldSenha.setFont(new Font("Arial Black", Font.BOLD, 13));
		textFieldSenha.setColumns(10);
		textFieldSenha.setBounds(9, 190, 536, 36);
		contentPane.add(textFieldSenha);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 204));
		panel.setForeground(new Color(0, 153, 204));
		panel.setBounds(0, 238, 555, 66);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Desenvolvido por Equipe 1");
		lblNewLabel_2.setBounds(18, 19, 272, 28);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNewLabel_2.setForeground(Color.WHITE);
		panel.add(lblNewLabel_2);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setMnemonic(KeyEvent.VK_ENTER); // Alt + N
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean vEmail = Validador.email(textFieldEmail.getText());
				boolean vSenha = Validador.qtdeMinChar(textFieldSenha.getText(), 6);
				
				if(vEmail && vSenha) {
				
					// criptografando a senha
					String senhaCriptografada = Seguranca.geraSenhaCriptografada(
														textFieldSenha.getText());				
					// buscando no banco de dados
					PessoaDAO pLogin = new PessoaDAO();
					Pessoa pessoaLoginSucesso = pLogin.efetuaLogin(textFieldEmail.getText(), senhaCriptografada);
					
					if(pessoaLoginSucesso.getId() > 0) {
						
						JOptionPane.showMessageDialog(null,
								"Bem vindo "+pessoaLoginSucesso.getNome()+"!",
							    "Login efetuado com sucesso!",
							    JOptionPane.INFORMATION_MESSAGE);
						
								// Criando um objeto para abrir a tela ViewPrincipal
								ViewPrincipal telaPrincipal = new ViewPrincipal(pessoaLoginSucesso);
									
								// Exibindo a ViewPrincipal na tela
								telaPrincipal.setVisible(true);
								
								// Fechando a tela de ViewLogin / Liberando recursos
								dispose();
								
					}else{
						JOptionPane.showMessageDialog(null,
							"Dados incorretos, tente novamente!",
						    "Alerta do sistema!",JOptionPane.ERROR_MESSAGE);
					}
				}else {
					if(!vEmail) {
						JOptionPane.showMessageDialog(null,
							"Informe um e-mail válido",
						    "Alerta do sistema!",JOptionPane.INFORMATION_MESSAGE);
					}
					if(!vSenha) {
					JOptionPane.showMessageDialog(null,
						"Informe uma senha de pelo menos 6 caracteres",
					    "Alerta do sistema!",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnEntrar.setBounds(456, 15, 89, 36);
		panel.add(btnEntrar);
		btnEntrar.setFont(new Font("Arial Black", Font.BOLD, 15));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ViewLogin.class.getResource("/imagens/donation.png")));
		lblNewLabel_1.setBounds(9, 10, 48, 38);
		contentPane.add(lblNewLabel_1);
		setLocationRelativeTo(null);
	}
}
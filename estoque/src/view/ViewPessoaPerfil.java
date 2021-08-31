package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PessoaDAO;
import dao.PessoaPerfilDAO;
import models.Pessoa;
import models.PessoaPerfil;
import util.Seguranca;
import util.Validador;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPessoaPerfil extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCor;
	private JLabel lblNome;
	private JLabel lblCor;
	private JLabel lblCadastroDePerfils;
	private JButton btnGravar;
	private JButton btnLimpar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPessoaPerfil frame = new ViewPessoaPerfil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void limparFormulario() {
		
		textFieldNome.setText("");
		textFieldCor.setText("");
		System.out.println("Formulário limpo com sucesso!");
		
	}
	
	/**
	 * Create the frame.
	 */
	public ViewPessoaPerfil() {
		setTitle("Cadastro de Perfils");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewPessoaPerfil.class.getResource("/imagens/cadastrar.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 230);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCadastroDePerfils = new JLabel("Cadastro de Perfils");
		lblCadastroDePerfils.setForeground(Color.BLUE);
		lblCadastroDePerfils.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblCadastroDePerfils.setEnabled(true);
		lblCadastroDePerfils.setBounds(10, 11, 333, 41);
		contentPane.add(lblCadastroDePerfils);
		
		lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNome.setBounds(10, 63, 97, 28);
		contentPane.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Arial Black", Font.BOLD, 16));
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(107, 64, 317, 30);
		contentPane.add(textFieldNome);
		
		lblCor = new JLabel("Cor");
		lblCor.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblCor.setBounds(10, 102, 97, 28);
		contentPane.add(lblCor);
		
		textFieldCor = new JTextField();
		textFieldCor.setFont(new Font("Arial Black", Font.BOLD, 16));
		textFieldCor.setColumns(10);
		textFieldCor.setBounds(107, 102, 317, 30);
		contentPane.add(textFieldCor);
		
		btnGravar = new JButton("Gravar");
		btnGravar.setBackground(Color.GREEN);
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// variável para armazenar as mensagens de erro
				String msgErro = "";
				
				// preparando os dados para inserir no banco de dados
				// Criando um objeto para inserir/atualizar no banco de dados
				PessoaPerfil pesPerfil = new PessoaPerfil();
				
				// validações para o campo nome
				boolean vNome = Validador.qtdeMinMaxChar(textFieldNome.getText(), 5, 255);
				if(!vNome) { msgErro +="O nome precisa ter entre 5 e 255 caracteres!\n"; }
				
				// validações para o campo nome
				boolean vCor = Validador.qtdeMinMaxChar(textFieldCor.getText(), 3, 255);
				if(!vNome) { msgErro +="A cor precisa ter entre 3 e 255 caracteres!\n"; }
				
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//FALTA modo de edição E REGRAS DE VALIDAÇÃO DE EXCEÇÃO
				
				pesPerfil.setNomePerfil(textFieldNome.getText());
				pesPerfil.setCor(textFieldCor.getText());
				
				// gravar os dados no banco de dados
				PessoaPerfilDAO pessoaDAO = new PessoaPerfilDAO();
				pessoaDAO.insert(pesPerfil);
				
				if(pesPerfil.getId() > 0) {
					JOptionPane.showMessageDialog(null,
							"Perfil cadastrado com sucesso!",
						    "Mensagem do sistema!",
						    JOptionPane.INFORMATION_MESSAGE);
				}
				
				limparFormulario();
			}
		});
		btnGravar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnGravar.setBounds(254, 150, 80, 30);
		contentPane.add(btnGravar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});
		btnLimpar.setBackground(Color.ORANGE);
		btnLimpar.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnLimpar.setBounds(344, 150, 80, 30);
		contentPane.add(btnLimpar);
	}
}

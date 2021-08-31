package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.PessoaPerfilDAO;
import dao.StatusDAO;
import models.PessoaPerfil;
import models.StatusDoItem;
import util.Validador;

public class ViewStatus extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldStatus;
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
					ViewStatus frame = new ViewStatus();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void limparFormulario() {
		
		textFieldStatus.setText("");
		System.out.println("Formulário limpo com sucesso!");
		
	}
	
	/**
	 * Create the frame.
	 */
	public ViewStatus() {
		setTitle("Cadastro de Status");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewPessoaPerfil.class.getResource("/imagens/cadastrar.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 190);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCadastroDePerfils = new JLabel("Cadastro de Status");
		lblCadastroDePerfils.setForeground(Color.BLUE);
		lblCadastroDePerfils.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblCadastroDePerfils.setEnabled(true);
		lblCadastroDePerfils.setBounds(10, 11, 333, 41);
		contentPane.add(lblCadastroDePerfils);
		
		lblCor = new JLabel("Status");
		lblCor.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblCor.setBounds(10, 63, 97, 28);
		contentPane.add(lblCor);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setFont(new Font("Arial Black", Font.BOLD, 16));
		textFieldStatus.setColumns(10);
		textFieldStatus.setBounds(107, 63, 317, 30);
		contentPane.add(textFieldStatus);
		
		btnGravar = new JButton("Gravar");
		btnGravar.setBackground(Color.GREEN);
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// variável para armazenar as mensagens de erro
				String msgErro = "";
				
				// preparando os dados para inserir no banco de dados
				// Criando um objeto para inserir/atualizar no banco de dados
				StatusDoItem doacaoStatus = new StatusDoItem();
				
				// validações para o campo Status
				boolean vStatus = Validador.qtdeMinMaxChar(textFieldStatus.getText(), 5, 255);
				if(!vStatus) { msgErro +="O status precisa ter entre 5 e 255 caracteres!\n"; }
				
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//FALTA modo de edição E REGRAS DE VALIDAÇÃO DE EXCEÇÃO
				
				if (vStatus) {
				
				doacaoStatus.setStatus(textFieldStatus.getText());
				
				// gravar os dados no banco de dados
				StatusDAO statusDAO = new StatusDAO();
				statusDAO.insert(doacaoStatus);
				
				if(doacaoStatus.getId() > 0) {
					JOptionPane.showMessageDialog(null,
							"Novo Status cadastrado com sucesso!",
						    "Mensagem do sistema!",
						    JOptionPane.INFORMATION_MESSAGE);
				}
				
				limparFormulario();
				
				} else {
					
					if(msgErro != "") {
						JOptionPane.showMessageDialog(null,
								msgErro,
							    "Mensagem do sistema!",
							    JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
			}
		});
		btnGravar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnGravar.setBounds(254, 110, 80, 30);
		contentPane.add(btnGravar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});
		btnLimpar.setBackground(Color.ORANGE);
		btnLimpar.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnLimpar.setBounds(344, 110, 80, 30);
		contentPane.add(btnLimpar);
	}
}
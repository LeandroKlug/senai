package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.PessoaDAO;
import dao.PessoaPerfilDAO;
import models.Pessoa;
import models.PessoaPerfil;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import util.Seguranca;
import util.UtilMascara;
import util.UtilModeloComboBox;
import util.Validador;

import java.awt.Color;
import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewPessoaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxSexo;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxPerfil;
	private JFormattedTextField textFieldNascimento;
	private JFormattedTextField textFieldCPF;
	private JTextField textFieldEmail;
	private JFormattedTextField textFieldTelefone;
	private JFormattedTextField textFieldCelular;
	private JPasswordField passwordFieldSenha;
	private JTable tablePessoas;
	private JButton btnGravar;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JCheckBox checkBoxSenha;
	PessoaDAO pessoaDAO = new PessoaDAO();
	
	// recursos adicionais para facilitar a edi��o
	private Pessoa pessoaSelecionadoEdicao;
	private boolean modoEdicao = false;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPessoaCadastro frame = new ViewPessoaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//para refrescar o checkbox apos nova inser��o de um item no checkbox usando outro view
	public void setVisibleAlterado (boolean b) {
		comboBoxPerfil.setModel(modeloPessoaPerfilCombobox(0));
		tablePessoas.setModel(modeloDadosTabelaPessoa());
		this.setVisible(b);
	}
	
	// m�todo que consulta/retorna os tipos de perfis cadastrados no banco de dados
	private List<PessoaPerfil> listaPessoaPerfil(){
		PessoaPerfilDAO ppDAO = new PessoaPerfilDAO();
		List<PessoaPerfil> listaPP = ppDAO.selectAll(PessoaPerfil.class);
		return listaPP;
	}
	
	// m�todo para gerar os elementos que ser�o exibidos no combobox de tipos de perfis
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DefaultComboBoxModel modeloPessoaPerfilCombobox(long idTipo) {

		List<PessoaPerfil> listaPP = listaPessoaPerfil();
		DefaultComboBoxModel modeloComboboxPessoaPerfil = 
				new DefaultComboBoxModel();
		
		for(PessoaPerfil atual : listaPP) {
			modeloComboboxPessoaPerfil.addElement(new UtilModeloComboBox(atual.getNomePerfil(), atual.getId()));
			if (atual.getId() == idTipo) {
				modeloComboboxPessoaPerfil.setSelectedItem(new UtilModeloComboBox(atual.getNomePerfil(), atual.getId()));
			}
		}

		return modeloComboboxPessoaPerfil;
	}
	
	private DefaultTableModel modeloDadosTabelaPessoa() {
		
		DefaultTableModel modeloTabelaPessoa = 
			new DefaultTableModel(
					new Object[][] {}, 
					new String[] { "Id", "Nome", "Sexo", "Nascimento", "CPF", "E-mail", "Telefone", "Celular", "Tipo Perfil" } 
				) {
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] tiposColunas = new Class[] {
					long.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class<?> getColumnClass(int indiceColuna) {
					return tiposColunas[indiceColuna];
				}
		};
		
		List<Pessoa> listaDePessoas = pessoaDAO.selectAll(Pessoa.class);
		
		for(Pessoa pessoaAtual : listaDePessoas) {
			Object linhaAtualDaTabela[] = new Object[] {
				pessoaAtual.getId(),
				pessoaAtual.getNome(),
				pessoaAtual.getSexo(),
				pessoaAtual.getDataNasc(),
				pessoaAtual.getCpf(),
				pessoaAtual.getEmail(),
				pessoaAtual.getTelefone(),
				pessoaAtual.getCelular(),
				pessoaAtual.getTipoPerfil().getNomePerfil(),
			};
			modeloTabelaPessoa.addRow(linhaAtualDaTabela);
		}
		return modeloTabelaPessoa;
	}
	
	private void limparFormulario() {
		
		textFieldNome.setText("");
		textFieldNascimento.setText("");
		comboBoxSexo.setSelectedIndex(0);
		textFieldEmail.setText("");
		textFieldCPF.setText("");
		textFieldCelular.setText("");
		textFieldTelefone.setText("");
		passwordFieldSenha.setText("");
		btnExcluir.setEnabled(false);
		btnLimpar.setEnabled(true);
		checkBoxSenha.setEnabled(false);
		passwordFieldSenha.setEnabled(true);
		System.out.println("Formul�rio limpo com sucesso!");
		
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ViewPessoaCadastro() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setTitle(":: Sistema doa\u00E7\u00F5es SENAI - Cadastro de Pessoas");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewPessoaCadastro.class.getResource("/imagens/heart.png")));
		setBounds(100, 100, 926, 680);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Cadastro de Pessoas");
		lblTitulo.setForeground(Color.BLUE);
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblTitulo.setEnabled(true);
		lblTitulo.setBounds(10, 11, 333, 41);
		contentPane.add(lblTitulo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 57, 889, 3);
		contentPane.add(separator);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNome.setBounds(17, 77, 97, 28);
		contentPane.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Arial Black", Font.BOLD, 16));
		textFieldNome.setBounds(114, 78, 370, 30);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblSexo.setBounds(524, 71, 68, 28);
		contentPane.add(lblSexo);
		
		comboBoxSexo = new JComboBox();
		comboBoxSexo.setFont(new Font("Arial Black", Font.BOLD, 16));
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] {"Feminino", "Masculino"}));
		comboBoxSexo.setBounds(634, 73, 262, 30);
		contentPane.add(comboBoxSexo);
		
		JLabel lblDataNascimento = new JLabel("Nascimento");
		lblDataNascimento.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblDataNascimento.setBounds(524, 132, 169, 28);
		contentPane.add(lblDataNascimento);
		
		textFieldNascimento = new JFormattedTextField(UtilMascara.add("dat"));
		textFieldNascimento.setFont(new Font("Arial Black", Font.BOLD, 16));
		textFieldNascimento.setColumns(10);
		textFieldNascimento.setBounds(637, 131, 262, 30);
		contentPane.add(textFieldNascimento);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblCpf.setBounds(17, 132, 97, 28);
		contentPane.add(lblCpf);
		
		textFieldCPF = new JFormattedTextField(UtilMascara.add("cpf"));
		textFieldCPF.setFont(new Font("Arial Black", Font.BOLD, 16));
		textFieldCPF.setColumns(10);
		textFieldCPF.setBounds(114, 130, 370, 30);
		contentPane.add(textFieldCPF);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblEmail.setBounds(17, 186, 97, 28);
		contentPane.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Arial Black", Font.BOLD, 16));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(114, 184, 370, 30);
		contentPane.add(textFieldEmail);
		
		textFieldTelefone = new JFormattedTextField(UtilMascara.add("tel"));
		textFieldTelefone.setFont(new Font("Arial Black", Font.BOLD, 16));
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(637, 185, 262, 30);
		contentPane.add(textFieldTelefone);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblTelefone.setBounds(527, 187, 169, 28);
		contentPane.add(lblTelefone);
		
		textFieldCelular = new JFormattedTextField(UtilMascara.add("cel"));
		textFieldCelular.setFont(new Font("Arial Black", Font.BOLD, 16));
		textFieldCelular.setColumns(10);
		textFieldCelular.setBounds(637, 234, 262, 30);
		contentPane.add(textFieldCelular);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblCelular.setBounds(527, 236, 169, 28);
		contentPane.add(lblCelular);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblSenha.setBounds(17, 236, 97, 28);
		contentPane.add(lblSenha);
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setFont(new Font("Arial Black", Font.BOLD, 16));
		passwordFieldSenha.setColumns(10);
		passwordFieldSenha.setBounds(114, 234, 283, 30);
		contentPane.add(passwordFieldSenha);
		
		comboBoxPerfil = new JComboBox();
		comboBoxPerfil.setFont(new Font("Arial Black", Font.BOLD, 16));
		comboBoxPerfil.setBounds(114, 285, 370, 30);
		contentPane.add(comboBoxPerfil);
		comboBoxPerfil.setModel(modeloPessoaPerfilCombobox(0));
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblPerfil.setBounds(18, 286, 68, 28);
		contentPane.add(lblPerfil);
		
		btnGravar = new JButton("Gravar");
		btnGravar.setBackground(Color.GREEN);
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// vari�vel para armazenar as mensagens de erro
				String msgErro = "";
				
				// preparando os dados para inserir no banco de dados
				// Criando um objeto para inserir/atualizar no banco de dados
				Pessoa pessoaInserirAlterar = new Pessoa();
				
				/*****************************************************************************
				 * 						REGRAS DE VALIDA��O PADR�O
				 *****************************************************************************/
				
				// valida��es para o campo nome
				boolean vNome = Validador.qtdeMinMaxChar(textFieldNome.getText(), 5, 255);
				if(!vNome) { msgErro +="O nome precisa ter entre 5 e 255 caracteres!\n"; }
				
				// valida��es para o campo de data de nascimento
				boolean vData = Validador.data(textFieldNascimento.getText());
				if(!vData) { msgErro += "Data inv�lida!\n"; }
				
				// valida��es para telefone
				boolean vTelefone = Validador.telefone(textFieldTelefone.getText());
				if(!vTelefone) { msgErro += "Telefone em formato inv�lido !\n"; }
				
				// valida��es para telefone
				boolean vCelular = Validador.telefone(textFieldCelular.getText());
				if(!vCelular) { msgErro += "Celular em formato inv�lido !\n"; }
				
				// valida��es para o campo senha padr�o
				boolean vSenha = Validador.qtdeMinMaxChar(passwordFieldSenha.getText(), 6, 100);
				//if(!vSenha) { msgErro +="A senha precisa ter entre 6 e 100 caracteres!\n"; }
				
				// valida��es para o campo de cpf
				boolean vCpf = Validador.verificaAtributoEmUso("cpf", textFieldCPF.getText()) &&
							   Validador.validaCPF(textFieldCPF.getText());
				//if(!vCpf) { msgErro += "CPF em uso ou inv�lido !\n"; }
				
				// valida��es para o campo de e-mail
				boolean vEmail = Validador.verificaAtributoEmUso("email", textFieldEmail.getText()) &&
						Validador.email(textFieldEmail.getText());
				//if(!vEmail) { msgErro += "E-mail em uso ou inv�lido !\n"; }
				
				String senhaCriptograda = Seguranca.geraSenhaCriptografada(passwordFieldSenha.getText());
				pessoaInserirAlterar.setSenha(senhaCriptograda); // com SHA-1
				
				/*****************************************************************************
				 * 						REGRAS DE VALIDA��O PADR�O
				 *****************************************************************************/
				
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//FALTA modo de edi��o E REGRAS DE VALIDA��O DE EXCE��O - Raf: n�o mais
				
				/*****************************************************************************
				 * 						REGRAS DE VALIDA��O DE EXCE��O
				 * 			SOMENTE APLICADAS QUANDO O MODO DE EDI��O ESTIVER ATIVADO
				 *****************************************************************************/

				// modo de edi��o ativado
				if(modoEdicao == true) {
					
					pessoaInserirAlterar.setId(pessoaSelecionadoEdicao.getId());
					
					System.out.println("Gravando no modo de edi��o!");
					
					if(checkBoxSenha.isSelected()) {
						// valida��es para o campo senha padr�o
						vSenha = Validador.qtdeMinMaxChar(passwordFieldSenha.getText(), 6, 100);
						if(!vSenha) { msgErro +="A senha precisa ter entre 6 e 100 caracteres!\n"; }
						
					}else {
						vSenha = true;
						System.out.println("Checkbox desmarcado, n�o vamos alterar a senha!");
						pessoaInserirAlterar.setSenha(pessoaSelecionadoEdicao.getSenha()); // com SHA-1
					}
					
					// regras de exce��o para e-mail e CPF no modo de edi��o
					
					if(textFieldEmail.getText().equals(pessoaSelecionadoEdicao.getEmail())) {
						vEmail = true;
						System.out.println("Email de edi��o true "+pessoaSelecionadoEdicao.getEmail());
					}else {
						System.out.println("Email de edi��o false"+pessoaSelecionadoEdicao.getEmail());
					}
					
					if(textFieldCPF.getText().equals(pessoaSelecionadoEdicao.getCpf())) {
						vCpf = true;
						System.out.println("cpf � igual! true");
						System.out.println("Email de edi��o"+pessoaSelecionadoEdicao.getCpf());
					}else {
						System.out.println("cpf � diferente! false!");
					}
					System.out.println("Cpf carregadoo"+ pessoaSelecionadoEdicao.getCpf());
					System.out.println("Cpf formulario"+ textFieldCPF.getText());
					
					
				}else {
					
					// modo de inser��o (NOVO REGISTRO)
					
					
				}
				
				if(!vCpf) { msgErro += "CPF em uso ou inv�lido !\n"; }
				if(!vSenha) { msgErro +="A senha precisa ter entre 6 e 100 caracteres!\n"; }
				if(!vEmail) { msgErro += "E-mail em uso ou inv�lido !\n"; }
							
				
				/*****************************************************************************
				 * 						REGRAS DE VALIDA��O DE EXCE��O
				 * 			SOMENTE APLICADAS QUANDO O MODO DE EDI��O ESTIVER ATIVADO
				 *****************************************************************************/
				
				if (vNome &&  vData &&  vEmail && vCpf && vTelefone && vCelular && vSenha) {
				
				pessoaInserirAlterar.setNome(textFieldNome.getText());
				pessoaInserirAlterar.setDataNasc(textFieldNascimento.getText());
				pessoaInserirAlterar.setSexo(comboBoxSexo.getSelectedItem().toString());
				pessoaInserirAlterar.setEmail(textFieldEmail.getText());
				pessoaInserirAlterar.setCpf(textFieldCPF.getText());
				pessoaInserirAlterar.setCelular(textFieldCelular.getText());
				pessoaInserirAlterar.setTelefone(textFieldTelefone.getText());
				
				// preparando o tipo perfil para inser��o
				PessoaPerfilDAO ppDAO = new PessoaPerfilDAO();
				PessoaPerfil ppInserir = ppDAO.select(PessoaPerfil.class, 
								listaPessoaPerfil().get(comboBoxPerfil.getSelectedIndex()).getId());
		
				// preparando tipo de perfil
				pessoaInserirAlterar.setTipoPerfil(ppInserir);
				
				// gravar os dados no banco de dados
				// pessoaDAO.insert(pessoaInserirAlterar); 
				pessoaDAO.salvar(pessoaInserirAlterar); //PessoaDAO: update else inserir
				
				if(pessoaInserirAlterar.getId() > 0) {

					String acao = "cadastrada";
					if(modoEdicao == true) {
						acao = "alterada";
					}

					JOptionPane.showMessageDialog(null,
							"Pessoa "+acao+" com sucesso!",
							"Mensagem do sistema",
							JOptionPane.INFORMATION_MESSAGE);
				}
				limparFormulario();
				modoEdicao = false;
				tablePessoas.setModel(modeloDadosTabelaPessoa());

				}else {

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
		btnGravar.setBounds(637, 285, 80, 30);
		contentPane.add(btnGravar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});
		btnLimpar.setBackground(Color.ORANGE);
		btnLimpar.setEnabled(true);
		btnLimpar.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnLimpar.setBounds(727, 285, 80, 30);
		contentPane.add(btnLimpar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(Color.RED);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// indice da linha que foi selecionada
				int posicaoLinha = tablePessoas.getSelectedRow();
				
				// id desta pessoa que foi selecionada
				int idPessoa = Integer.valueOf(tablePessoas.getModel().getValueAt(posicaoLinha, 0).toString());
				
				// exclui o registro selecionado
				PessoaDAO pesExcluir = new PessoaDAO();
				pesExcluir.delete(Pessoa.class, idPessoa);
				
				// ajusta os bot�es para configura��o inicial
				//btnExcluir.setEnabled(false);
				//btnLimpar.setEnabled(true);
				
				// limpando o formul�rio
				limparFormulario();
				
				checkBoxSenha.setEnabled(false);
				checkBoxSenha.setSelected(false);
				passwordFieldSenha.setEnabled(true);
				
				// recarregar a tabela!
				tablePessoas.setModel(modeloDadosTabelaPessoa());
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnExcluir.setBounds(815, 285, 80, 30);
		contentPane.add(btnExcluir);
		
		JLabel lblListaDePessoas = new JLabel("Lista de Pessoas Cadastradas");
		lblListaDePessoas.setFont(new Font("Arial Black", Font.BOLD, 19));
		lblListaDePessoas.setEnabled(true);
		lblListaDePessoas.setBounds(10, 347, 333, 41);
		contentPane.add(lblListaDePessoas);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 340, 889, 3);
		contentPane.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 395, 889, 233);
		contentPane.add(scrollPane);
		
		tablePessoas = new JTable();
		tablePessoas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// estamos trabalhando no modo de edi��o
				modoEdicao = true;
				
				// indice da linha que foi selecionada
				int posicaoLinha = tablePessoas.getSelectedRow();
				
				// manipular exibi��o dos bot�es
				btnExcluir.setEnabled(true);
				btnLimpar.setEnabled(true);
				
				int idpessoa = Integer.valueOf(tablePessoas.getModel().getValueAt(posicaoLinha, 0).toString());
				
				PessoaDAO pesBuscar = new PessoaDAO();
				pessoaSelecionadoEdicao = pesBuscar.select(Pessoa.class, idpessoa);
				
				// limpeza do form por causa das m�scaras
				limparFormulario();
				
				// bloquear o campo de senha
				passwordFieldSenha.setEnabled(false);
				
				// ativar o checkbox
				checkBoxSenha.setSelected(false);
				checkBoxSenha.setEnabled(true);
				
				// manipular exibi��o dos bot�es
				btnExcluir.setEnabled(true);
				btnLimpar.setEnabled(false);
				
				textFieldNome.setText(pessoaSelecionadoEdicao.getNome());
				comboBoxSexo.setSelectedItem(pessoaSelecionadoEdicao.getSexo());
				textFieldNascimento.setText(pessoaSelecionadoEdicao.getDataNasc());
				textFieldCPF.setText(pessoaSelecionadoEdicao.getCpf());
				textFieldEmail.setText(pessoaSelecionadoEdicao.getEmail());
				textFieldTelefone.setText(pessoaSelecionadoEdicao.getTelefone());
				textFieldCelular.setText(pessoaSelecionadoEdicao.getCelular());
				//comboBoxPerfil.setSelectedItem(pessoaSelecionadoEdicao.getTipoPerfil());
				
				/* N�o desse jeito:
				textFieldNascimento.setText(tablePessoas.getModel().getValueAt(posicaoLinha, 3).toString());
				textFieldEmail.setText(tablePessoas.getModel().getValueAt(posicaoLinha, 4).toString());
				textFieldTelefone.setText(tablePessoas.getModel().getValueAt(posicaoLinha, 5).toString());
				textFieldCelular.setText(tablePessoas.getModel().getValueAt(posicaoLinha, 6).toString());
				comboBoxPerfil.setSelectedItem().toString().getValueAt(posicaoLinha, 7).toString());
				*/
				
				// definir qual o op��o de perfil deve ficar setada
				comboBoxPerfil.setModel(modeloPessoaPerfilCombobox(pessoaSelecionadoEdicao.getTipoPerfil().getId()));
			}
		});
		tablePessoas.setFont(new Font("Tahoma", Font.BOLD, 14));
		tablePessoas.setModel(modeloDadosTabelaPessoa());
		scrollPane.setViewportView(tablePessoas);
		setLocationRelativeTo(null);
		
		checkBoxSenha = new JCheckBox("Habilitar");
		checkBoxSenha.setBackground(Color.CYAN);
		checkBoxSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxSenha.isSelected()){
					passwordFieldSenha.setEnabled(true);
				}else {
					passwordFieldSenha.setEnabled(false);
				}
			}
		});
		checkBoxSenha.setEnabled(false);
		checkBoxSenha.setFont(new Font("Arial Black", Font.BOLD, 11));
		checkBoxSenha.setBounds(403, 239, 81, 23);
		contentPane.add(checkBoxSenha);
		setLocationRelativeTo(null);
	}	
}

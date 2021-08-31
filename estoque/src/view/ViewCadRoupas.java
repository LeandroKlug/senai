package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PessoaDAO;
import dao.PessoaPerfilDAO;
import dao.RoupasDAO;
import dao.StatusDAO;
import models.Pessoa;
import models.PessoaPerfil;
import models.Roupas;
import models.StatusDoItem;
import util.UtilMascara;
import util.UtilModeloComboBox;
import util.Validador;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class ViewCadRoupas extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDataHora;
	private JTextField textFieldTitulo;
	private JComboBox comboBoxTipo;
	private JComboBox comboBoxTamanho;
	private JComboBox comboBoxCategoria;
	private JComboBox comboBoxClassifiPublico;
	private JComboBox comboBoxEstadoDeConservacao;
	private JTextArea textAreaDescricao;
	private JScrollPane scrollPane;
	private JButton btnCadastrar;
	private JButton btnLimpar;
	RoupasDAO roupaDAO = new RoupasDAO();
	private JComboBox comboBoxStatus;
	private JComboBox comboBoxPessoa;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCadRoupas frame = new ViewCadRoupas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setVisibleAlterado (boolean b) {
		comboBoxStatus.setModel(modeloStatusCombobox(0));
		comboBoxPessoa.setModel(modeloComboBoxPessoa(0));
		this.setVisible(b);
	}
	
	private List<StatusDoItem> listaStatus(){
		StatusDAO statDAO = new StatusDAO();
		List<StatusDoItem> listaStat = statDAO.selectAll(StatusDoItem.class);
		return listaStat;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DefaultComboBoxModel modeloStatusCombobox(long idTipo) {

		List<StatusDoItem> listaStat = listaStatus();
		DefaultComboBoxModel modeloComboboxStatus = 
				new DefaultComboBoxModel();
		
		for(StatusDoItem atual : listaStat) {
			modeloComboboxStatus.addElement(
				new UtilModeloComboBox(atual.getStatus(), 
										atual.getId()));
		}

		return modeloComboboxStatus;
	}
	
	private List<Pessoa> listaPessoa(){
		PessoaDAO pesDAO = new PessoaDAO();
		List<Pessoa> listaPes = pesDAO.selectAll(Pessoa.class);
		return listaPes;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DefaultComboBoxModel modeloComboBoxPessoa(long idTipo) {

		List<Pessoa> listaPes = listaPessoa();
		DefaultComboBoxModel modeloComboboxPessoa = 
				new DefaultComboBoxModel();
		
		for(Pessoa atual : listaPes) {
			modeloComboboxPessoa.addElement(
				new UtilModeloComboBox(atual.getNome(), 
										atual.getId()));
		}

		return modeloComboboxPessoa;
	}
	
	private void limparFormulario() {
			
		textFieldTitulo.setText("");
		textFieldDataHora.setText("");
		comboBoxTipo.setSelectedIndex(0);
		comboBoxTamanho.setSelectedIndex(0);
		comboBoxCategoria.setSelectedIndex(0);
		comboBoxClassifiPublico.setSelectedIndex(0);
		comboBoxEstadoDeConservacao.setSelectedIndex(0);
		textAreaDescricao.setText("");
		comboBoxStatus.setSelectedIndex(0);
		comboBoxPessoa.setSelectedIndex(0);
		System.out.println("FormulÃ¡rio limpo com sucesso!");
			
	}
	
	public void teste() {
		ViewCadRoupas  cad= new ViewCadRoupas();
		cad.setVisible(false);
	}
	
	public ViewCadRoupas() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(ViewCadRoupas.class.getResource("/imagens/883315251552641715-20.png")));
		setTitle("Cadastro de Roupas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRoupas = new JLabel("Cadastro das Doa\u00E7\u00F5es de Roupas");
		lblRoupas.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblRoupas.setBounds(10, 11, 642, 31);
		contentPane.add(lblRoupas);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(10, 40, 664, 2);
		contentPane.add(separator);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTitulo.setBounds(10, 53, 200, 15);
		contentPane.add(lblTitulo);
		
		JLabel lblDataEHora = new JLabel("Data e Hora");
		lblDataEHora.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblDataEHora.setBounds(10, 79, 200, 15);
		contentPane.add(lblDataEHora);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTipo.setBounds(10, 103, 200, 15);
		contentPane.add(lblTipo);
		
		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTamanho.setBounds(10, 129, 200, 15);
		contentPane.add(lblTamanho);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblCategoria.setBounds(10, 155, 200, 15);
		contentPane.add(lblCategoria);
		
		JLabel lblClassificaoDoPublico = new JLabel("Classifica\u00E7\u00E3o do p\u00FAblico");
		lblClassificaoDoPublico.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblClassificaoDoPublico.setBounds(10, 179, 200, 15);
		contentPane.add(lblClassificaoDoPublico);
		
		JLabel lblEstadoDeConservao = new JLabel("Estado de conserva\u00E7\u00E3o");
		lblEstadoDeConservao.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblEstadoDeConservao.setBounds(10, 205, 200, 15);
		contentPane.add(lblEstadoDeConservao);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblDescrio.setBounds(10, 292, 200, 15);
		contentPane.add(lblDescrio);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(10, 329, 664, 2);
		contentPane.add(separator_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(215, 288, 459, 30);
		contentPane.add(scrollPane);
		
		textAreaDescricao = new JTextArea();
		scrollPane.setViewportView(textAreaDescricao);
		
		textFieldDataHora = new JFormattedTextField(UtilMascara.add("dat"));
		textFieldDataHora.setFont(new Font("Arial Black", Font.BOLD, 11));
		textFieldDataHora.setBounds(215, 78, 180, 20);
		contentPane.add(textFieldDataHora);
		textFieldDataHora.setColumns(10);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setFont(new Font("Arial Black", Font.BOLD, 11));
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(215, 53, 180, 20);
		contentPane.add(textFieldTitulo);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"Camisa", "Camiseta", "Blusa", "Jaqueta", "Blusa de frio", "Gravata", "Saia", "Vestido", "Cal\u00E7a", "Cal\u00E7a jeans", "Cal\u00E7\u00E3o", "Meias", "Pijama", "Camisola", "Roup\u00E3o"}));
		comboBoxTipo.setFont(new Font("Arial Black", Font.BOLD, 11));
		comboBoxTipo.setBounds(215, 101, 180, 20);
		contentPane.add(comboBoxTipo);
		
		comboBoxTamanho = new JComboBox();
		comboBoxTamanho.setModel(new DefaultComboBoxModel(new String[] {"PP", "M", "G", "GG", "XG", "XGG"}));
		comboBoxTamanho.setFont(new Font("Arial Black", Font.BOLD, 11));
		comboBoxTamanho.setBounds(215, 127, 180, 20);
		contentPane.add(comboBoxTamanho);
		
		comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setModel(new DefaultComboBoxModel(new String[] {"Infantil", "Adulto", "Adulto Slim", "Plus Size"}));
		comboBoxCategoria.setFont(new Font("Arial Black", Font.BOLD, 11));
		comboBoxCategoria.setBounds(215, 153, 180, 20);
		contentPane.add(comboBoxCategoria);
		
		comboBoxClassifiPublico = new JComboBox();
		comboBoxClassifiPublico.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino", "Unissex"}));
		comboBoxClassifiPublico.setFont(new Font("Arial Black", Font.BOLD, 11));
		comboBoxClassifiPublico.setBounds(215, 177, 180, 20);
		contentPane.add(comboBoxClassifiPublico);
		
		comboBoxEstadoDeConservacao = new JComboBox();
		comboBoxEstadoDeConservacao.setModel(new DefaultComboBoxModel(new String[] {"Novo", "Bom", "Regular", "Ruim"}));
		comboBoxEstadoDeConservacao.setFont(new Font("Arial Black", Font.BOLD, 11));
		comboBoxEstadoDeConservacao.setBounds(215, 203, 180, 20);
		contentPane.add(comboBoxEstadoDeConservacao);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// variável para armazenar as mensagens de erro
				String msgErro = "";
				
				// validações para o campo nome
				boolean vTitulo = Validador.qtdeMinMaxChar(textFieldTitulo.getText(), 5, 255);
				if(!vTitulo) { msgErro +="O titulo precisa ter entre 5 e 255 caracteres!\n"; }
				
				// validações para o campo de data de nascimento
				boolean vData = Validador.data(textFieldDataHora.getText());
				if(!vData) { msgErro += "Data inválida!\n"; }
				
				if (vTitulo &&  vData) {
				
				// preparando os dados para inserir no banco de dados
				Roupas roupa1 = new Roupas();
				// criar um objeto de DAO
				RoupasDAO roupaInserir = new RoupasDAO();
				
				roupa1.setTitulo(textFieldTitulo.getText());
				roupa1.setDataHoraCadastro(textFieldDataHora.getText());
				roupa1.setTipo(comboBoxTipo.getSelectedItem().toString());
				roupa1.setTamanho(comboBoxTamanho.getSelectedItem().toString());
				roupa1.setCategoria(comboBoxCategoria.getSelectedItem().toString());
				roupa1.setClassifiPublico(comboBoxClassifiPublico.getSelectedItem().toString());
				roupa1.setEstadoConvervacao(comboBoxEstadoDeConservacao.getSelectedItem().toString());
				roupa1.setdescricao(textAreaDescricao.getText());
				
				// preparando o tipo perfil para inserção
				StatusDAO statDAO = new StatusDAO();
				StatusDoItem statInserir = statDAO.select(StatusDoItem.class, 
								listaStatus().get(comboBoxStatus.getSelectedIndex()).getId());
		
				// preparando tipo de status
				roupa1.setstatus(statInserir);
				
				// preparando o tipo perfil para inserção
				PessoaDAO pesDAO = new PessoaDAO();
				Pessoa pesInserir = pesDAO.select(Pessoa.class, 
								listaPessoa().get(comboBoxPessoa.getSelectedIndex()).getId());
		
				// preparando tipo de pessoa
				roupa1.setpessoa(pesInserir);
				
				// inserir no banco de dados
				roupaInserir.insert(roupa1);
				
				if(roupa1.getId() > 0) {
					System.out.println("Roupa cadastrada com sucesso!");
				}
				
				if(roupa1.getId() > 0) {
					JOptionPane.showMessageDialog(null,
							"A roupa "+roupa1.getTitulo()+" foi cadastrado !",
						    null,
						    JOptionPane.INFORMATION_MESSAGE);	
				}else {
					JOptionPane.showMessageDialog(null,
						"Algo deu errado!",
					    "Alerta do sistema!",
					    JOptionPane.ERROR_MESSAGE);
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
		btnCadastrar.setBackground(Color.GREEN);
		btnCadastrar.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnCadastrar.setBounds(434, 340, 115, 30);
		contentPane.add(btnCadastrar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});
		btnLimpar.setBackground(Color.RED);
		btnLimpar.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnLimpar.setBounds(559, 340, 115, 30);
		contentPane.add(btnLimpar);
		
		JLabel lblBigImage = new JLabel("New label");
		lblBigImage.setIcon(new ImageIcon(ViewCadRoupas.class.getResource("/imagens/10063479181578983103-256.png")));
		lblBigImage.setBounds(405, 53, 269, 183);
		contentPane.add(lblBigImage);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblStatus.setBounds(10, 231, 200, 15);
		contentPane.add(lblStatus);
		
		comboBoxStatus = new JComboBox();
		comboBoxStatus.setFont(new Font("Arial Black", Font.BOLD, 11));
		comboBoxStatus.setBounds(215, 229, 180, 20);
		contentPane.add(comboBoxStatus);
		comboBoxStatus.setModel(modeloStatusCombobox(0));
		
		comboBoxPessoa = new JComboBox();
		comboBoxPessoa.setModel(modeloComboBoxPessoa(0));
		comboBoxPessoa.setFont(new Font("Arial Black", Font.BOLD, 11));
		comboBoxPessoa.setBounds(215, 257, 180, 20);
		contentPane.add(comboBoxPessoa);
		
		JLabel lblPessoa = new JLabel("Pessoa");
		lblPessoa.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblPessoa.setBounds(10, 257, 200, 15);
		contentPane.add(lblPessoa);
		
		setLocationRelativeTo(null);
	}
}
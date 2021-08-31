package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EstoqueDAO;
import dao.ItensDoadosDAO;
import dao.PessoaDAO;
import dao.RoupasDAO;
import dao.StatusDAO;
import models.Estoque;
import models.ItensDoados;
import models.Pessoa;
import models.Roupas;
import models.StatusDoItem;
import util.UtilModeloComboBox;
import util.Validador;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ViewDoacoes extends JFrame {

	// !!!!!!!!!!!!!!!!!!!!
	// Fazer DOAR ainda!!!!!!!!!!!!!!!!!!!!!!!!!

	private JPanel contentPane;
	private JTextField textFieldNomeDoItem;
	private JTextField textFieldQtdDoItem;
	private JButton btnDoar;
	private JButton btnLimpar;
	private JComboBox comboBoxStatus;
	private JComboBox comboBoxPessoa;
	private JLabel lblStatus;
	private JLabel lblPessoa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewDoacoes frame = new ViewDoacoes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//para refrescar o checkbox apos nova inserção de um item no checkbox usando outro view
	public void setVisibleAlterado (boolean b) {
		comboBoxStatus.setModel(modeloStatusCombobox(0));
		comboBoxPessoa.setModel(modeloComboBoxPessoa(0));
		this.setVisible(b);
	}
	
	// método que consulta/retorna os tipos de status cadastrados no banco de dados
	private List<StatusDoItem> listaStatus() {
		StatusDAO statDAO = new StatusDAO();
		List<StatusDoItem> listaStat = statDAO.selectAll(StatusDoItem.class);
		return listaStat;
	}

	// método para gerar os elementos que serão exibidos no combobox de tipos de
	// status
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DefaultComboBoxModel modeloStatusCombobox(long idTipo) {

		List<StatusDoItem> listaStat = listaStatus();
		DefaultComboBoxModel modeloComboboxStatus = new DefaultComboBoxModel();

		for (StatusDoItem atual : listaStat) {
			modeloComboboxStatus.addElement(new UtilModeloComboBox(atual.getStatus(), atual.getId()));
		}

		return modeloComboboxStatus;
	}

	// método que consulta/retorna os tipos de status cadastrados no banco de dados
	private List<Pessoa> listaPessoa() {
		PessoaDAO pesDAO = new PessoaDAO();
		List<Pessoa> listaPes = pesDAO.selectAll(Pessoa.class);
		return listaPes;
	}

	// método para gerar os elementos que serão exibidos no combobox de tipos de
	// status
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DefaultComboBoxModel modeloComboBoxPessoa(long idTipo) {

		List<Pessoa> listaPes = listaPessoa();
		DefaultComboBoxModel modeloComboboxPessoa = new DefaultComboBoxModel();

		for (Pessoa atual : listaPes) {
			modeloComboboxPessoa.addElement(new UtilModeloComboBox(atual.getNome(), atual.getId()));
		}

		return modeloComboboxPessoa;
	}

	private void limparFormulario() {

		textFieldNomeDoItem.setText("");
		textFieldQtdDoItem.setText("");
		comboBoxStatus.setSelectedIndex(0);
		comboBoxPessoa.setSelectedIndex(0);
		System.out.println("Formulário limpo com sucesso!");

	}

	/**
	 * Create the frame.
	 */
	public ViewDoacoes() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ViewDoacoes.class.getResource("/imagens/donationviewdoacoes.png")));
		setTitle("Doacoes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 434, 323);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeDoItem = new JLabel("Nome do Item");
		lblNomeDoItem.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNomeDoItem.setBounds(10, 81, 134, 28);
		contentPane.add(lblNomeDoItem);

		textFieldNomeDoItem = new JTextField();
		textFieldNomeDoItem.setFont(new Font("Arial Black", Font.BOLD, 16));
		textFieldNomeDoItem.setColumns(10);
		textFieldNomeDoItem.setBounds(154, 85, 252, 20);
		contentPane.add(textFieldNomeDoItem);

		JLabel lblQuantidadesDoItem = new JLabel("Quantidade");
		lblQuantidadesDoItem.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblQuantidadesDoItem.setBounds(10, 122, 134, 28);
		contentPane.add(lblQuantidadesDoItem);

		textFieldQtdDoItem = new JTextField();
		textFieldQtdDoItem.setFont(new Font("Arial Black", Font.BOLD, 16));
		textFieldQtdDoItem.setColumns(10);
		textFieldQtdDoItem.setBounds(154, 126, 252, 20);
		contentPane.add(textFieldQtdDoItem);

		btnDoar = new JButton("Doar");
		btnDoar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// variável para armazenar as mensagens de erro
				String msgErro = "";

				// Criando um objeto para inserir/atualizar no banco de dados
				//Doacoes doacoes1 = new Doacoes();
				ItensDoados itensDoados1 = new ItensDoados();
				Estoque estoque1 = new Estoque();

				// validações para o campo nome
				boolean vNome = Validador.qtdeMinMaxChar(textFieldNomeDoItem.getText(), 5, 255);
				if (!vNome) { msgErro += "O nome precisa ter entre 5 e 255 caracteres!\n"; }

				// validações para o campo quantidade
				boolean vQuantidade = Validador.numeros(textFieldQtdDoItem.getText());
				if (!vQuantidade) { msgErro += "So pode usar numeros positivos!\n"; }

				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				// FAZER DOAR 
				
				if (vNome && vQuantidade) {
				
					// criar um objeto de DAO
					//DoarItemDAO doacaoInserir = new DoarItemDAO();
					ItensDoadosDAO itensDoadosInserir = new ItensDoadosDAO();
					EstoqueDAO estoqueInserir = new EstoqueDAO();
					
					//doacoes1.setNome(textFieldNomeDoItem.getText());
					//doacoes1.setQuantidade(textFieldQtdDoItem.getText());
					itensDoados1.setNomeItem(textFieldNomeDoItem.getText());
					itensDoados1.setQuantidade(Integer.parseInt(textFieldQtdDoItem.getText()));
					estoque1.setNomeItem(textFieldNomeDoItem.getText());
					estoque1.setQuantidade(textFieldQtdDoItem.getText());
					
					// preparando o status para inserção
					StatusDAO statDAO = new StatusDAO();
					StatusDoItem statInserir = statDAO.select(StatusDoItem.class, 
									listaStatus().get(comboBoxStatus.getSelectedIndex()).getId());
			
					// preparando  status
					//doacoes1.setstatus(statInserir);
					itensDoados1.setstatus(statInserir);
					estoque1.setstatus(statInserir);
					
					// preparando o pessoa para inserção
					PessoaDAO pesDAO = new PessoaDAO();
					Pessoa pesInserir = pesDAO.select(Pessoa.class, 
									listaPessoa().get(comboBoxPessoa.getSelectedIndex()).getId());
			
					// preparando pessoa
					//doacoes1.setpessoa(pesInserir);
					itensDoados1.setpessoa(pesInserir);
					estoque1.setpessoa(pesInserir);
					
					// inserir no banco de dados
					//doacaoInserir.insert(doacoes1);
					itensDoadosInserir.insert(itensDoados1);
					estoqueInserir.insert(estoque1);
					
					if(itensDoados1.getId() > 0) {
						System.out.println("Doação cadastrada com sucesso!");
					}
					
					if(itensDoados1.getId() > 0) {
						JOptionPane.showMessageDialog(null,
								"Doação "+itensDoados1.getNomeItem()+" foi cadastrado !",
							    null,
							    JOptionPane.INFORMATION_MESSAGE);	
					} else {
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
		btnDoar.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnDoar.setBackground(Color.GREEN);
		btnDoar.setBounds(166, 240, 115, 30);
		contentPane.add(btnDoar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});
		btnLimpar.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnLimpar.setBackground(Color.RED);
		btnLimpar.setBounds(291, 240, 115, 30);
		contentPane.add(btnLimpar);

		JLabel lblCadastroDasDoaes = new JLabel("Cadastro das Doa\u00E7\u00F5es");
		lblCadastroDasDoaes.setForeground(Color.BLUE);
		lblCadastroDasDoaes.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblCadastroDasDoaes.setBounds(10, 11, 218, 31);
		contentPane.add(lblCadastroDasDoaes);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(10, 52, 218, 2);
		contentPane.add(separator);

		JLabel lbldonateDoar = new JLabel("");
		lbldonateDoar.setIcon(new ImageIcon(ViewDoacoes.class.getResource("/imagens/donateDoar.png")));
		lbldonateDoar.setBounds(116, 231, 40, 39);
		contentPane.add(lbldonateDoar);

		JLabel lbldonateB = new JLabel("");
		lbldonateB.setIcon(new ImageIcon(ViewDoacoes.class.getResource("/imagens/donateB.png")));
		lbldonateB.setBounds(238, 0, 69, 82);
		contentPane.add(lbldonateB);
		
		comboBoxStatus = new JComboBox();
		comboBoxStatus.setFont(new Font("Arial Black", Font.BOLD, 11));
		comboBoxStatus.setBounds(154, 162, 252, 20);
		contentPane.add(comboBoxStatus);
		comboBoxStatus.setModel(modeloStatusCombobox(0));
		
		comboBoxPessoa = new JComboBox();
		comboBoxPessoa.setModel(modeloComboBoxPessoa(0));
		comboBoxPessoa.setFont(new Font("Arial Black", Font.BOLD, 11));
		comboBoxPessoa.setBounds(154, 193, 252, 20);
		contentPane.add(comboBoxPessoa);
		
		lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblStatus.setBounds(10, 156, 134, 28);
		contentPane.add(lblStatus);
		
		lblPessoa = new JLabel("Pessoa");
		lblPessoa.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblPessoa.setBounds(10, 187, 134, 28);
		contentPane.add(lblPessoa);
		setLocationRelativeTo(null);
	}
}

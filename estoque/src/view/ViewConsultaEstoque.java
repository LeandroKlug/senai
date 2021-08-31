package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.EstoqueDAO;
import dao.ItensDoadosDAO;
import dao.PessoaDAO;
import dao.StatusDAO;
import models.Estoque;
import models.ItensDoados;
import models.Pessoa;
import models.Roupas;
import models.StatusDoItem;
import util.Validador;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;

public class ViewConsultaEstoque extends JFrame {

	private JPanel contentPane;
	private JTable tableListaEstoque;
	private JLabel lblListaEstoque;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JTextField textFieldQuantidade;
	private JTextField textFieldNome;
	private JLabel lblIcon;
	private JButton btnLimpar;
	private JButton btnFiltroId;
	private JTextField textFieldFiltroId;
	private JLabel lblNewLabel;
	private EstoqueDAO estoqueItemDAO = new EstoqueDAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewConsultaEstoque frame = new ViewConsultaEstoque();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// lista inicial sem nenhum filtro
	private List<Estoque> listaDeEstoque(){
		return estoqueItemDAO.selectAll(Estoque.class);
	}

	private List<Estoque> listaDeEstoque(String filtro, String parametro){
		
		List<Estoque> retorno;
		switch(filtro) {
			case "filtro-id": {
				retorno = estoqueItemDAO.selectAllComFiltro("id", parametro);
				break;
			}
			default:{
				retorno = estoqueItemDAO.selectAll(Estoque.class);
				break;
			}
		}
		return retorno;
	}	

	// para refrescar o checkbox apos nova inserção de um item no checkbox usando
	// outro view
	public void setVisibleAlterado(boolean b) {
		tableListaEstoque.setModel(modeloDadosTabelaEstoque(listaDeEstoque()));
		this.setVisible(b);
	}

	private DefaultTableModel modeloDadosTabelaEstoque(List<Estoque> listaEstoque) {

		DefaultTableModel listaTabela = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Nome", "Quantidade" /* , "Descrição do item", "Categoria do item", "Data" */ }) {
			private static final long serialVersionUID = 1L;
			Class[] tiposColunas = new Class[] { Integer.class, String.class,
					Integer.class /* , String.class, String.class, Integer.class, String.class */
			};

			public Class<?> getColumnClass(int indiceColuna) {
				return tiposColunas[indiceColuna];
			}
		};

		for (Estoque linha : listaEstoque) {

			Object linhaAtual[] = new Object[] { linha.getId(), linha.getNomeItem(),
					// linha.getDescricaoItem(),
					linha.getQuantidade(),
					// linha.getTipoItem(),
					// linha.getDataDoacao()
			};
			listaTabela.addRow(linhaAtual);
		}
		return listaTabela;
	}

	private void limparFormulario() {

		textFieldNome.setText("");
		textFieldQuantidade.setText("");
		System.out.println("Formulário limpo com sucesso!");

	}

	public ViewConsultaEstoque() {
		setFont(new Font("Arial Black", Font.BOLD, 12));
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ViewConsultaEstoque.class.getResource("/imagens/estoque.png")));
		setTitle("Estoque");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 664, 379);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 628, 206);
		contentPane.add(scrollPane);

		tableListaEstoque = new JTable();
		tableListaEstoque.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				int posicaoLinha = tableListaEstoque.getSelectedRow();
				int iditens = Integer.valueOf(tableListaEstoque.getModel().getValueAt(posicaoLinha, 0).toString());
				
				btnExcluir.setEnabled(true);
				
				String itemDoado = (String) tableListaEstoque.getValueAt(posicaoLinha, 1);
				int qtddItem = Integer.parseInt((String) tableListaEstoque.getValueAt(posicaoLinha, 2));			
				
				ViewInfoDoacaoEstoque viewItemDoacaoEstoque = new ViewInfoDoacaoEstoque();
				viewItemDoacaoEstoque.tipoItemDoado.setText(itemDoado);
				viewItemDoacaoEstoque.qtddItem.setText(Integer.toString(qtddItem));
				viewItemDoacaoEstoque.idItemDoado.setText(Integer.toString(iditens));
				viewItemDoacaoEstoque.setVisible(true);
			}
		});
		tableListaEstoque.setModel(modeloDadosTabelaEstoque(listaDeEstoque()));
		scrollPane.setViewportView(tableListaEstoque);

		lblListaEstoque = new JLabel("Estoque");
		lblListaEstoque.setForeground(Color.BLUE);
		lblListaEstoque.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblListaEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaEstoque.setBounds(10, 11, 628, 49);
		contentPane.add(lblListaEstoque);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int posicaoLinha = tableListaEstoque.getSelectedRow();
				int itemId = Integer.valueOf(tableListaEstoque.getModel().getValueAt(posicaoLinha, 0).toString());
				
				System.out.println("Id item: " + itemId);

				EstoqueDAO estoqueDAO = new EstoqueDAO();
				estoqueDAO.delete(Estoque.class, itemId);

				tableListaEstoque.setModel(modeloDadosTabelaEstoque(listaDeEstoque()));
			}
		});
		btnExcluir.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnExcluir.setBounds(523, 299, 115, 30);
		contentPane.add(btnExcluir);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableListaEstoque.setModel(modeloDadosTabelaEstoque(listaDeEstoque()));
			}
		});
		btnLimpar.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnLimpar.setBounds(341, 298, 80, 30);
		contentPane.add(btnLimpar);
		
		btnFiltroId = new JButton("Filtrar");
		btnFiltroId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Validador.isInteiro(textFieldFiltroId.getText())) {
					tableListaEstoque.setModel(modeloDadosTabelaEstoque(listaDeEstoque("filtro-id",textFieldFiltroId.getText())));
				}else {
					JOptionPane.showMessageDialog(null,
							"Informe um número inteiro no campo identificador!",
						    "Alerta do sistema!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnFiltroId.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnFiltroId.setBounds(257, 298, 80, 30);
		contentPane.add(btnFiltroId);
		
		textFieldFiltroId = new JTextField();
		textFieldFiltroId.setFont(new Font("Arial Black", Font.BOLD, 11));
		textFieldFiltroId.setColumns(10);
		textFieldFiltroId.setBounds(113, 299, 140, 30);
		contentPane.add(textFieldFiltroId);
		
		lblNewLabel = new JLabel("Buscar por ID");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 305, 121, 20);
		contentPane.add(lblNewLabel);

/*		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// variável para armazenar as mensagens de erro
				String msgErro = "";

				// Criando um objeto para inserir/atualizar no banco de dados
				Estoque estoque1 = new Estoque();
				ItensDoados itensDoados1 = new ItensDoados();

				// validações para o campo nome
				boolean vNome = Validador.qtdeMinMaxChar(textFieldNome.getText(), 5, 255);
				if (!vNome) { msgErro += "O nome precisa ter entre 5 e 255 caracteres!\n"; }

				// validações para o campo nome
				boolean vQuantidade = Validador.numeros(textFieldQuantidade.getText());
				if (!vQuantidade) { msgErro += "So pode usar numeros!\n"; }

				// !!!!!!!!!!!!!!!!!!!! Tirar Inserir daqui??????
				// Fazer Alterar!!!!!!!!!!!!!!!!!!! a nr 5 da entrega 3 tem que fazer (aqui? ou outro view/ViewConsultaEstoque)!!!!!!!!!!!!!
				
				if (vNome && vQuantidade) {
				
				// criar um objeto de DAO
				EstoqueDAO estoqueInserir = new EstoqueDAO();
				
				estoque1.setNomeItem(textFieldNome.getText());
				estoque1.setQuantidade(textFieldQuantidade.getText());
				
				// preparando o status para inserção
				StatusDAO statDAO = new StatusDAO();
				//StatusDoItem statInserir = statDAO.select(StatusDoItem.class, listaStatus().get(comboBoxStatus.getSelectedIndex()).getId());
		
				// preparando status
				//estoque1.setstatus(statInserir);
				
				// preparando o pessoa para inserção
				PessoaDAO pesDAO = new PessoaDAO();
				//Pessoa pesInserir = pesDAO.select(Pessoa.class, listaPessoa().get(comboBoxPessoa.getSelectedIndex()).getId());
		
				// preparando pessoa
				//estoque1.setpessoa(pesInserir);
				
				// inserir no banco de dados
				estoqueInserir.insert(estoque1);
				
				if(itensDoados1.getId() > 0) {
					System.out.println("Alteração cadastrada com sucesso!");
				}
				
				if(itensDoados1.getId() > 0) {
					JOptionPane.showMessageDialog(null,
							"Alteração "+estoque1.getNomeItem()+" foi cadastrado !",
						    null,
						    JOptionPane.INFORMATION_MESSAGE);	
				} else {
					JOptionPane.showMessageDialog(null,
						"Algo deu errado!",
					    "Alerta do sistema!",
					    JOptionPane.ERROR_MESSAGE);
				}
				
				limparFormulario();
				
				tableListaEstoque.setModel(listaEstoque());
				
				} else {
					
					if(msgErro != "") {
						JOptionPane.showMessageDialog(null,
								msgErro,
							    "Mensagem do sistema!",
							    JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				
				//ABRIR TELA PARA FAZER EDIÇÂO/ALTERAÇÂO
				
			}
		});
		btnAlterar.setBackground(Color.ORANGE);
		btnAlterar.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnAlterar.setBounds(523, 284, 115, 30);
		contentPane.add(btnAlterar);	*/

//		JLabel lblNome = new JLabel("Nome");
//		lblNome.setFont(new Font("Arial Black", Font.BOLD, 16));
//		lblNome.setBounds(179, 284, 59, 25);
//		contentPane.add(lblNome);

//		JLabel lblQuantidade = new JLabel("Quantidade");
//		lblQuantidade.setFont(new Font("Arial Black", Font.BOLD, 16));
//		lblQuantidade.setBounds(179, 331, 107, 25);
//		contentPane.add(lblQuantidade);

//		textFieldQuantidade = new JTextField();
//		textFieldQuantidade.setFont(new Font("Arial Black", Font.BOLD, 16));
//		textFieldQuantidade.setBounds(307, 331, 170, 25);
//		contentPane.add(textFieldQuantidade);
//		textFieldQuantidade.setColumns(10);

//		textFieldNome = new JTextField();
//		textFieldNome.setFont(new Font("Arial Black", Font.BOLD, 16));
//		textFieldNome.setColumns(10);
//		textFieldNome.setBounds(307, 284, 170, 25);
//		contentPane.add(textFieldNome);

/*		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(ViewConsultaEstoque.class.getResource("/imagens/estoque 128.png")));
		lblIcon.setBounds(10, 253, 138, 140);
		contentPane.add(lblIcon);
		setLocationRelativeTo(null);
*/
	}
}

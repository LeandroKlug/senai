package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;

public class ViewConsultaItensDoados extends JFrame {

	private JPanel contentPane;
	private JTable tableListaItensDoados;
	private JLabel lblListaItensDoados;
	private JButton btnExcluir;
	private JButton btnLimpar;
	private JButton btnFiltroId;
	private JTextField textFieldFiltroId;
	private JLabel lblNewLabel;
	private ItensDoadosDAO itensDoadosDAO = new ItensDoadosDAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewConsultaItensDoados frame = new ViewConsultaItensDoados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// lista inicial sem nenhum filtro
	private List<ItensDoados> listaDeItensDoados(){
		return itensDoadosDAO.selectAll(ItensDoados.class);
	}

	private List<ItensDoados> listaDeItensDoados(String filtro, String parametro){
		
		List<ItensDoados> retorno;
		switch(filtro) {
			case "filtro-id": {
				retorno = itensDoadosDAO.selectAllComFiltro("id", parametro);
				break;
			}
			default:{
				retorno = itensDoadosDAO.selectAll(ItensDoados.class);
				break;
			}
		}
		return retorno;
	}	
	
	// para refrescar o checkbox apos nova inserção de um item no checkbox usando
	// outro view
	public void setVisibleAlterado(boolean b) {
		tableListaItensDoados.setModel(modeloDadosTabelaItensDoados(listaDeItensDoados()));
		this.setVisible(b);
	}

	private DefaultTableModel modeloDadosTabelaItensDoados(List<ItensDoados> listaItensDoados) {

		DefaultTableModel listaTabela = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Nome", "Quantidade", "Pessoa Recebeu" /* , "Descrição do item", "Categoria do item", "Data" */ }) {
			private static final long serialVersionUID = 1L;
			Class[] tiposColunas = new Class[] { Integer.class, String.class,
					String.class, Pessoa.class /* , String.class, String.class, Integer.class, String.class */
			};

			public Class<?> getColumnClass(int indiceColuna) {
				return tiposColunas[indiceColuna];
			}
		};

		for (ItensDoados linha : listaItensDoados) {
			
			String pessoaDoado;
			
			if (linha.getstatus().getId() == 3) {
				PessoaDAO p = new PessoaDAO();
				pessoaDoado = p.select(Pessoa.class, linha.getpessoa().getId()).getNome();
			}
			else {
				pessoaDoado = "";
			}
			
			Object linhaAtual[] = new Object[] { linha.getId(), linha.getNomeItem(), linha.getQuantidade(), pessoaDoado};
			listaTabela.addRow(linhaAtual);
		}
		return listaTabela;
	}

	public ViewConsultaItensDoados() {
		setFont(new Font("Arial Black", Font.BOLD, 12));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ViewConsultaItensDoados.class.getResource("/imagens/donation.png")));
		setTitle("Itens Doados - Hist\u00F3ria ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 664, 430);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 628, 252);
		contentPane.add(scrollPane);

		tableListaItensDoados = new JTable();
		tableListaItensDoados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// indice da linha que foi selecionada
				int posicaoLinha = tableListaItensDoados.getSelectedRow();

				// manipular exibição dos botões
				btnExcluir.setEnabled(true);

				int iditens = Integer.valueOf(tableListaItensDoados.getModel().getValueAt(posicaoLinha, 0).toString());

				// precisa dessa parte?
				ItensDoadosDAO itensBuscar = new ItensDoadosDAO();
				ItensDoados itensSelecionado = itensBuscar.select(ItensDoados.class, iditens);
				
				String itemDoado = (String) tableListaItensDoados.getValueAt(posicaoLinha, 1);
				int qtddItem = (int) tableListaItensDoados.getValueAt(posicaoLinha, 2);
				
				ViewInfoDoacao viewItemDoacao = new ViewInfoDoacao();
				viewItemDoacao.tipoItemDoado.setText(itemDoado);
				viewItemDoacao.qtddItem.setText(Integer.toString(qtddItem));
				viewItemDoacao.idItemDoado.setText(Integer.toString(iditens));
				viewItemDoacao.setVisible(true);
			}
		});
		tableListaItensDoados.setModel(modeloDadosTabelaItensDoados(listaDeItensDoados()));
		scrollPane.setViewportView(tableListaItensDoados);

		lblListaItensDoados = new JLabel("Itens doados - Hist\u00F3ria ");
		lblListaItensDoados.setForeground(Color.BLUE);
		lblListaItensDoados.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblListaItensDoados.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaItensDoados.setBounds(10, 11, 628, 49);
		contentPane.add(lblListaItensDoados);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(Color.RED);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicaoLinha = tableListaItensDoados.getSelectedRow();
				int itemId = Integer.valueOf(tableListaItensDoados.getModel().getValueAt(posicaoLinha, 0).toString());

				System.out.println("Id item: " + itemId);

				ItensDoadosDAO doadosItemDAO = new ItensDoadosDAO();
				doadosItemDAO.delete(ItensDoados.class, itemId);

				tableListaItensDoados.setModel(modeloDadosTabelaItensDoados(listaDeItensDoados()));
			}
		});
		btnExcluir.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnExcluir.setBounds(523, 350, 115, 30);
		contentPane.add(btnExcluir);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableListaItensDoados.setModel(modeloDadosTabelaItensDoados(listaDeItensDoados()));
			}
		});
		btnLimpar.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnLimpar.setBounds(341, 349, 80, 30);
		contentPane.add(btnLimpar);
		
		btnFiltroId = new JButton("Filtrar");
		btnFiltroId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Validador.isInteiro(textFieldFiltroId.getText())) {
					tableListaItensDoados.setModel(modeloDadosTabelaItensDoados(listaDeItensDoados("filtro-id",textFieldFiltroId.getText())));
				}else {
					JOptionPane.showMessageDialog(null,
							"Informe um número inteiro no campo identificador!",
						    "Alerta do sistema!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnFiltroId.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnFiltroId.setBounds(257, 349, 80, 30);
		contentPane.add(btnFiltroId);
		
		textFieldFiltroId = new JTextField();
		textFieldFiltroId.setFont(new Font("Arial Black", Font.BOLD, 11));
		textFieldFiltroId.setColumns(10);
		textFieldFiltroId.setBounds(113, 350, 140, 30);
		contentPane.add(textFieldFiltroId);
		
		lblNewLabel = new JLabel("Buscar por ID");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 356, 121, 20);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);

	}
}

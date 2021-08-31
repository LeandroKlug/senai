package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ItensDoadosDAO;
import dao.PessoaDAO;
import dao.RoupasDAO;
import dao.StatusDAO;
import models.ItensDoados;
import models.Pessoa;
import models.Roupas;
import models.StatusDoItem;
import util.UtilModeloComboBox;
import util.Validador;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ViewConsultaRoupas extends JFrame {

	private JPanel contentPane;
	private JTable tableListaRoupas;
	private JLabel lblListaItensDoados;
	private JButton btnExcluir;
	private JTextField textFieldFiltroId;
	private RoupasDAO roupasDAO = new RoupasDAO();
	private JButton btnFiltroId;
	private JButton btnLimpar;
	private StatusDAO stDAO = new StatusDAO();
	private JComboBox comboBoxStatus;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewConsultaRoupas frame = new ViewConsultaRoupas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// lista inicial sem nenhum filtro
	private List<Roupas> listaDeRoupas(){
		return roupasDAO.selectAll(Roupas.class);
	}

	private List<Roupas> listaDeRoupas(String filtro, String parametro){
		
		List<Roupas> retorno;
		switch(filtro) {
			case "filtro-id": {
				retorno = roupasDAO.selectAllComFiltro("id", parametro);
				break;
			}
			case "filtro-status": {
				retorno = roupasDAO.selectAllComFiltro("status", parametro);
				break;
			}
			default:{
				retorno = roupasDAO.selectAll(Roupas.class);
				break;
			}
		}
		return retorno;
	}	

	public void setVisibleAlterado(boolean b) {
		tableListaRoupas.setModel(modeloDadosTabelaRoupas(listaDeRoupas()));
		this.setVisible(b);
	}

	private DefaultTableModel modeloDadosTabelaRoupas(List<Roupas> listaRoupas) {

		DefaultTableModel listaTabela = new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Pessoa",
				"Titulo", "Data Cadastro", "Status" /* , "Descrição do item", "Categoria do item", "Data" */ }) {
			private static final long serialVersionUID = 1L;
			Class[] tiposColunas = new Class[] { Integer.class, String.class, String.class,
					String.class, String.class, String.class /* , String.class, String.class, Integer.class, String.class */
			};

			public Class<?> getColumnClass(int indiceColuna) {
				return tiposColunas[indiceColuna];
			}
		};

		for (Roupas linha : listaRoupas) {
			
			String pessoaDoado;
			
			if (linha.getstatus().getId() == 3) {
				PessoaDAO p = new PessoaDAO();
				pessoaDoado = p.select(Pessoa.class, linha.getpessoa().getId()).getNome();
			}
			else {
				pessoaDoado = "";
			}

			Object linhaAtual[] = new Object[] { linha.getId(), linha.getpessoa().getNome(), linha.getTitulo(),
					linha.getDataHoraCadastro(), linha.getstatus().getStatus(), pessoaDoado
			};
			listaTabela.addRow(linhaAtual);
		}
		return listaTabela;
	}
	
	private DefaultComboBoxModel modeloStatusCombobox() {

		List<StatusDoItem> listaStatus = stDAO.selectAll(StatusDoItem.class);
		DefaultComboBoxModel modeloComboboxStatus = new DefaultComboBoxModel();
		for (StatusDoItem atual : listaStatus) {
			modeloComboboxStatus.addElement(new UtilModeloComboBox(atual.getStatus(), atual.getId()));
		}
		return modeloComboboxStatus;
	}
	
	public ViewConsultaRoupas() {
		setFont(new Font("Arial Black", Font.BOLD, 12));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ViewConsultaRoupas.class.getResource("/imagens/10063479181578983103-256.png")));
		setTitle("Roupas cadastradas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 664, 430);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
				lblListaItensDoados = new JLabel("Roupas cadastradas");
				lblListaItensDoados.setForeground(Color.BLUE);
				lblListaItensDoados.setFont(new Font("Arial Black", Font.BOLD, 20));
				lblListaItensDoados.setHorizontalAlignment(SwingConstants.CENTER);
				lblListaItensDoados.setBounds(10, 11, 628, 49);
				contentPane.add(lblListaItensDoados);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 628, 252);
		contentPane.add(scrollPane);

		tableListaRoupas = new JTable();
		tableListaRoupas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int posicaoLinha = tableListaRoupas.getSelectedRow();
				int idroupas = Integer.valueOf(tableListaRoupas.getModel().getValueAt(posicaoLinha, 0).toString());
				RoupasDAO roupasBuscar = new RoupasDAO();
				Roupas roupasSelecionado = roupasBuscar.select(Roupas.class, idroupas);
				
				btnExcluir.setEnabled(true);
			
				String tituloItem = (String) tableListaRoupas.getValueAt(posicaoLinha, 2);
				String dataDoacao = (String) tableListaRoupas.getValueAt(posicaoLinha, 3);
				
				ViewInfoGerenciaRoupa viewInfoGerenciaRoupa = new ViewInfoGerenciaRoupa();			
				viewInfoGerenciaRoupa.itemDoado.setText(tituloItem);
				viewInfoGerenciaRoupa.dataDoacao.setText(dataDoacao);
				viewInfoGerenciaRoupa.idItemDoado.setText(Integer.toString(idroupas));
				viewInfoGerenciaRoupa.setVisible(true);
			}
		});
		tableListaRoupas.setModel(modeloDadosTabelaRoupas(listaDeRoupas()));
		scrollPane.setViewportView(tableListaRoupas);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(Color.RED);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicaoLinha = tableListaRoupas.getSelectedRow();
				int roupaId = Integer.valueOf(tableListaRoupas.getModel().getValueAt(posicaoLinha, 0).toString());
				
				RoupasDAO roupaDAO = new RoupasDAO();
				roupaDAO.delete(Roupas.class, roupaId);
				tableListaRoupas.setModel(modeloDadosTabelaRoupas(listaDeRoupas()));
			}
		});
		btnExcluir.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnExcluir.setBounds(523, 350, 115, 30);
		contentPane.add(btnExcluir);
		
		JLabel lblFiltroID = new JLabel("Buscar por ID");
		lblFiltroID.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblFiltroID.setBounds(10, 356, 121, 20);
		contentPane.add(lblFiltroID);
		
		textFieldFiltroId = new JTextField();
		textFieldFiltroId.setFont(new Font("Arial Black", Font.BOLD, 11));
		textFieldFiltroId.setColumns(10);
		textFieldFiltroId.setBounds(113, 350, 140, 30);
		contentPane.add(textFieldFiltroId);
		
		btnFiltroId = new JButton("Filtrar");
		btnFiltroId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Validador.isInteiro(textFieldFiltroId.getText())) {
					tableListaRoupas.setModel(modeloDadosTabelaRoupas(listaDeRoupas("filtro-id",textFieldFiltroId.getText())));
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
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableListaRoupas.setModel(modeloDadosTabelaRoupas(listaDeRoupas()));
			}
		});
		btnLimpar.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnLimpar.setBounds(341, 349, 80, 30);
		contentPane.add(btnLimpar);
		
		
		comboBoxStatus = new JComboBox();
		comboBoxStatus.setModel(modeloStatusCombobox());
		comboBoxStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				
				###############################################
				estavamos usando a lista de roupas ao invés da lista de 
				status para pegar o id do status... por isso vinha 
				aquele 6 "misterioso"... rs 				
				############################################### 
				 
				long id = listaDeRoupas().get(comboBoxStatus.getSelectedIndex()).getId();
				tableListaRoupas.setModel(modeloDadosTabelaRoupas(listaDeRoupas("filtro-status",String.valueOf(id))));
				System.out.println("Registro selecionado: "+id);
				*/
				
				// posição do item no combobox
				int posicaoItem = comboBoxStatus.getSelectedIndex();
				
				// id do item que foi selecionado
				long idItem = stDAO.selectAll(StatusDoItem.class).get(posicaoItem).getId();

				// ativando filtro 
				tableListaRoupas.setModel(modeloDadosTabelaRoupas(listaDeRoupas("filtro-status",String.valueOf(idItem))));
			}
		});
		comboBoxStatus.setFont(new Font("Arial Black", Font.BOLD, 9));
		comboBoxStatus.setBounds(523, 38, 115, 22);
		contentPane.add(comboBoxStatus);
		
		JLabel lblFiltro = new JLabel("Filtrar por status");
		lblFiltro.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblFiltro.setBounds(523, 11, 115, 30);
		contentPane.add(lblFiltro);
		
		setLocationRelativeTo(null);
	}
}

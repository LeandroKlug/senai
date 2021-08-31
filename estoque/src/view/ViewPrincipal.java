package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PessoaDAO;
import models.Pessoa;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import java.awt.event.InputEvent;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class ViewPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Pessoa pessoaLogada;
	private JLabel lblDadosPessoaLogada;
	private JButton btnGerenciaHistoria;
	private JButton btnGerenciaEstoque;
	private JButton btnGerenciaRoupa;
	private JButton btnTelaCadRoupas;
	private JButton btnSair;
	private JButton btnCadastroDoaes;
	private JButton btnTelaCadPessoas;
	private JButton btnSobre;
	private JMenuBar menuBarPrincipal;
	private JMenu menuSenha;
	private JMenu menuCadastros;
	private JMenu menuSobre;
	private JMenu menuEstoqueHistoria;
	private JMenu menuArquivo;
	private JMenuItem mntmStatus;
	private JMenuItem mntmRoupasCAD;
	private JMenuItem mntmPessoaPerfil;
	private JMenuItem mntmDoacaoCadastro;
	private JMenuItem mntmPessoaCadastro;
	private JMenuItem mntmAlterarSenha;
	private JMenuItem mntmHistoria;
	private JMenuItem mntmEstoque;
	private JMenuItem mntmRoupasGerencia;
	private JMenuItem mntmSair;
	// abrir so uma tela:
	private ViewPessoaCadastro vwPessoaCadastro = null;
	private ViewDoacoes vwDoacoes = null; 
	private ViewSobre vwSobre = null; 
	private ViewAlterarSenha vwAlterarSenha = null;
	private ViewConsultaEstoque vwEstoque = null; 
	private ViewConsultaItensDoados vwHistoriaItensDoados = null;
	private ViewPessoaPerfil vwPessoaPerfil = null;
	private ViewCadRoupas vwCadRoupas = null;
	private ViewConsultaRoupas vwGerenciaRoupas = null;
	private ViewStatus vwStatus = null;
	private JLabel lblUsAdm;
	private JLabel lblUsOper;
	private JLabel lblUsUsu;
	private PessoaDAO pesDAO = new PessoaDAO();
	/**
	 * Launch the application.
	 */
	public Pessoa getPessoaLogada() {
		return pessoaLogada;
	}

	public void setPessoaLogada(Pessoa pessoaLogada) {
		this.pessoaLogada = pessoaLogada;
	}
	

	
	//abir so uma tela: [junto com a parte de cima]
	private void abrirTelaCadPessoas() {;
		if(vwPessoaCadastro == null) {
			vwPessoaCadastro = new ViewPessoaCadastro();
		}
		vwPessoaCadastro.setVisibleAlterado(true);
	}
	
	private void abrirTelaDoacoes() {
		if(vwDoacoes == null) {
			vwDoacoes = new ViewDoacoes();
		}
		vwDoacoes.setVisibleAlterado(true);
	}
	
	private void abrirTelaPessoaPerfil() {
		if(vwPessoaPerfil == null) {
			vwPessoaPerfil = new ViewPessoaPerfil();
		}
		vwPessoaPerfil.setVisible(true);
	}
	
	private void abrirTelaStatus() {
		if(vwStatus == null) {
			vwStatus = new ViewStatus();
		}
		vwStatus.setVisible(true);
	}
	
	private void abrirTelaAlterarSenha() {
		if(vwAlterarSenha == null) {
			vwAlterarSenha = new ViewAlterarSenha(pessoaLogada);
		}
		vwAlterarSenha.setVisible(true);
	}
	
	private void abrirTelaEstoque() {
		if(vwEstoque == null) {
			vwEstoque = new ViewConsultaEstoque();
		}
		vwEstoque.setVisibleAlterado(true);
	}
	
	private void abrirTelaHistoria() { //ItensDoados
		if(vwHistoriaItensDoados == null) {
			vwHistoriaItensDoados = new ViewConsultaItensDoados();
		}
		vwHistoriaItensDoados.setVisibleAlterado(true);
	}
	
	private void abrirTelaSobre() {
		if(vwSobre == null) {
			vwSobre = new ViewSobre();
		}
		vwSobre.setVisible(true);
	}
	
	//TEMPORARIO
	//abrirTelaCadRoupas
	private void abrirTelaCadRoupas() {
		if(vwCadRoupas == null) {
			vwCadRoupas = new ViewCadRoupas();
		}
		vwCadRoupas.setVisibleAlterado(true);
	}
	
	//abrirTelaGerenciaRoupas
	private void abrirTelaGerenciaRoupas() {
		if(vwGerenciaRoupas == null) {
			vwGerenciaRoupas = new ViewConsultaRoupas();
		}
		vwGerenciaRoupas.setVisibleAlterado(true);
	}
	
	private void controleSaidaJanela() {
		
		Object[] opcoes = {"Sim", "Não"};
		int resultado = JOptionPane.showOptionDialog(null,
						    "Deseja realmente sair??",
						    "Aviso",
						    JOptionPane.YES_NO_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,     //do not use a custom Icon
						    opcoes,  //the titles of buttons
						    opcoes[0]);
		
		if(resultado == 0) {
			System.exit(0);
		}
	}

	/**
	 * Create the frame.
	 * @param pessoaLoginSucesso 
	 */
	@SuppressWarnings("deprecation")
	public ViewPrincipal(Pessoa pessoaLoginSucesso) {
		
		// definindo na tela a informação de quem está logado
		setPessoaLogada(pessoaLoginSucesso);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewPrincipal.class.getResource("/imagens/heart.png")));
		
		// comportamento padrÃ£o de fechamento do frame
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// reescrever o controle de saÃ­da da tela
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				controleSaidaJanela();
			}
		});		
		
		setTitle(": :Doa\u00E7\u00F5es SENAI - Principal ::");
		setBounds(100, 100, 960, 395);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel rodape = new JPanel();
		rodape.setBackground(SystemColor.controlHighlight);
		rodape.setBounds(0, 328, 944, 30);
		contentPane.add(rodape);
		rodape.setLayout(null);
		
		lblDadosPessoaLogada = new JLabel();
		lblDadosPessoaLogada.setBounds(0, 0, 944, 30);
		rodape.add(lblDadosPessoaLogada);
		lblDadosPessoaLogada.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblDadosPessoaLogada.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// definindo os dados de exibição do rodapé
		lblDadosPessoaLogada.setText(getPessoaLogada().getNome()
									  +" - Perfil: "
									  +getPessoaLogada().getTipoPerfil().getNomePerfil());
		
		btnSobre = new JButton("Sobre");
		btnSobre.setBounds(0, 0, 79, 30);
		rodape.add(btnSobre);
		btnSobre.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnSobre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abrirTelaSobre();
			}
		});
		
		menuBarPrincipal = new JMenuBar();
		menuBarPrincipal.setBounds(0, 0, 1023, 22);
		contentPane.add(menuBarPrincipal);
		
		menuArquivo = new JMenu("Arquivo");
		menuArquivo.setFont(new Font("Arial Black", Font.BOLD, 12));
		menuArquivo.setSelectedIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/heart.png")));
		menuBarPrincipal.add(menuArquivo);
		
		mntmSair = new JMenuItem("Sair");
		mntmSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleSaidaJanela();
			}
		});
		menuArquivo.add(mntmSair);
		
		menuCadastros = new JMenu("Cadastros");
		menuCadastros.setFont(new Font("Arial Black", Font.BOLD, 12));
		menuBarPrincipal.add(menuCadastros);
		
		menuSobre = new JMenu("Sobre");
		menuSobre.setFont(new Font("Arial Black", Font.BOLD, 12));
		menuSobre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abrirTelaSobre();
			}
		});
		
		menuEstoqueHistoria = new JMenu("Gerenciamento");
		menuEstoqueHistoria.setFont(new Font("Arial Black", Font.BOLD, 12));
		menuBarPrincipal.add(menuEstoqueHistoria);
		
		mntmRoupasGerencia = new JMenuItem("Estoque Roupas");
		mntmRoupasGerencia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		menuEstoqueHistoria.add(mntmRoupasGerencia);
		mntmRoupasGerencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaGerenciaRoupas();
			}
		});
		
		mntmEstoque = new JMenuItem("Estoque Doa\u00E7\u00F5es");
		mntmEstoque.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		menuEstoqueHistoria.add(mntmEstoque);
		mntmEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaEstoque();
			}
		});
		
		mntmHistoria = new JMenuItem("Historia Doa\u00E7\u00F5es");
		mntmHistoria.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		menuEstoqueHistoria.add(mntmHistoria);
		mntmHistoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaHistoria();
			}
		});
		
		menuSenha = new JMenu("Senha");
		menuSenha.setFont(new Font("Arial Black", Font.BOLD, 12));
		menuBarPrincipal.add(menuSenha);
		
		mntmAlterarSenha = new JMenuItem("Alterar Senha");
		mntmAlterarSenha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmAlterarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaAlterarSenha();
			}
		});
		menuSenha.add(mntmAlterarSenha);
		menuBarPrincipal.add(menuSobre);
		
		/*
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuSobre.add(mntmSobre); */
		
		mntmPessoaCadastro = new JMenuItem("Pessoas");
		mntmPessoaCadastro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mntmPessoaCadastro.setMnemonic(KeyEvent.VK_P);
		mntmPessoaCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaCadPessoas();
			}
		});
		
		menuCadastros.add(mntmPessoaCadastro);
		
		mntmDoacaoCadastro = new JMenuItem("Doacoes");
		mntmDoacaoCadastro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mntmDoacaoCadastro.setMnemonic(KeyEvent.VK_P);
		mntmDoacaoCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaDoacoes();
			}
		});
		
		mntmPessoaPerfil = new JMenuItem("Pessoa Perfil");
		mntmPessoaPerfil.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		menuCadastros.add(mntmPessoaPerfil);
		mntmPessoaPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaPessoaPerfil();
			}
		});
		
		mntmRoupasCAD = new JMenuItem("Roupas");
		mntmRoupasCAD.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		menuCadastros.add(mntmRoupasCAD);
		mntmRoupasCAD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaCadRoupas();
			}
		});
		
		menuCadastros.add(mntmDoacaoCadastro);
		
		mntmStatus = new JMenuItem("Status");
		mntmStatus.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuCadastros.add(mntmStatus);
		mntmStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaStatus();
			}
		});
		
		btnTelaCadPessoas = new JButton("Cadastro Pessoas");
		btnTelaCadPessoas.setForeground(Color.BLACK);
		btnTelaCadPessoas.setBackground(UIManager.getColor("List.dropLineColor"));
		btnTelaCadPessoas.setMnemonic(KeyEvent.VK_P);
		btnTelaCadPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaCadPessoas();
			}
		});
		btnTelaCadPessoas.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnTelaCadPessoas.setBounds(10, 33, 300, 67);
		contentPane.add(btnTelaCadPessoas);
		
		btnCadastroDoaes = new JButton("Cadastro Doa\u00E7\u00F5es");
		btnCadastroDoaes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaDoacoes();
			}
		});
		btnCadastroDoaes.setBackground(UIManager.getColor("List.dropLineColor"));
		btnCadastroDoaes.setForeground(Color.BLACK);
/*		btnCadastroDoaes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abrirTelaDoacoes();
			}
		});
*/		btnCadastroDoaes.setMnemonic(KeyEvent.VK_D);
		btnCadastroDoaes.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnCadastroDoaes.setBounds(336, 33, 300, 67);
		contentPane.add(btnCadastroDoaes);	
		
		btnSair = new JButton("Sair");
		btnSair.setBackground(UIManager.getColor("List.dropLineColor"));
		btnSair.setFont(new Font("Arial Black", Font.BOLD, 24));
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controleSaidaJanela();
			}
		});
		btnSair.setBounds(453, 277, 183, 40);
		contentPane.add(btnSair);
		
		btnTelaCadRoupas = new JButton("Cadastro Roupa");
		btnTelaCadRoupas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaCadRoupas();
			}
		});
		btnTelaCadRoupas.setMnemonic(KeyEvent.VK_P);
		btnTelaCadRoupas.setForeground(Color.BLACK);
		btnTelaCadRoupas.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnTelaCadRoupas.setBackground(UIManager.getColor("List.dropLineColor"));
		btnTelaCadRoupas.setBounds(10, 111, 300, 67);
		contentPane.add(btnTelaCadRoupas);
		
		btnGerenciaRoupa = new JButton("Gerencia Roupa");
		btnGerenciaRoupa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaGerenciaRoupas();
			}
		});
		btnGerenciaRoupa.setMnemonic(KeyEvent.VK_P);
		btnGerenciaRoupa.setForeground(Color.BLACK);
		btnGerenciaRoupa.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnGerenciaRoupa.setBackground(UIManager.getColor("List.dropLineColor"));
		btnGerenciaRoupa.setBounds(10, 189, 300, 67);
		contentPane.add(btnGerenciaRoupa);
		
		btnGerenciaEstoque = new JButton("Gerencia Estoque");
		btnGerenciaEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaEstoque();
			}
		});
		btnGerenciaEstoque.setMnemonic(KeyEvent.VK_P);
		btnGerenciaEstoque.setForeground(Color.BLACK);
		btnGerenciaEstoque.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnGerenciaEstoque.setBackground(UIManager.getColor("List.dropLineColor"));
		btnGerenciaEstoque.setBounds(336, 111, 300, 67);
		contentPane.add(btnGerenciaEstoque);
		
		btnGerenciaHistoria = new JButton("Gerencia Historia");
		btnGerenciaHistoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaHistoria();
			}
		});
		btnGerenciaHistoria.setMnemonic(KeyEvent.VK_P);
		btnGerenciaHistoria.setForeground(Color.BLACK);
		btnGerenciaHistoria.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnGerenciaHistoria.setBackground(UIManager.getColor("List.dropLineColor"));
		btnGerenciaHistoria.setBounds(336, 189, 300, 67);
		contentPane.add(btnGerenciaHistoria);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.BLUE);
		panel.setBounds(643, 33, 291, 284);
		contentPane.add(panel);
		
		JLabel lblUsuriosCadastrados = new JLabel("Usu\u00E1rios cadastrados");
		lblUsuriosCadastrados.setForeground(Color.CYAN);
		lblUsuriosCadastrados.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblUsuriosCadastrados.setBounds(10, 11, 277, 54);
		panel.add(lblUsuriosCadastrados);
		
		lblUsAdm = new JLabel("Administradores: "+pesDAO.getQtdeUsuariosTipoPerfil(1));
		lblUsAdm.setForeground(Color.CYAN);
		lblUsAdm.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblUsAdm.setBounds(10, 76, 277, 33);
		panel.add(lblUsAdm);
		
		lblUsOper = new JLabel("Operadores: "+pesDAO.getQtdeUsuariosTipoPerfil(3));
		lblUsOper.setForeground(Color.CYAN);
		lblUsOper.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblUsOper.setBounds(10, 118, 277, 33);
		panel.add(lblUsOper);
		
		lblUsUsu = new JLabel("Usu\u00E1rios: "+pesDAO.getQtdeUsuariosTipoPerfil(2));
		lblUsUsu.setForeground(Color.CYAN);
		lblUsUsu.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblUsUsu.setBounds(10, 162, 277, 33);
		panel.add(lblUsUsu);
		
		// Centralizando o JFrame na tela 
		setLocationRelativeTo(null);
		
		restringeMenus();
	}
	
	private void restringeMenus() {
	
	long idPerfil = getPessoaLogada().getTipoPerfil().getId();
	String nomePerfil = getPessoaLogada().getTipoPerfil().getNomePerfil();
	
		//Administrador tem todas permissões
		
		//Usuario não pode cadastrar Pessoa, PessoaPerfil, Status e não pode ver historia ||
		if (idPerfil == 2) {
			mntmPessoaCadastro.setEnabled(false);
			mntmStatus.setEnabled(false);
			mntmPessoaPerfil.setEnabled(false);
			mntmHistoria.setEnabled(false);
			
			btnGerenciaHistoria.setEnabled(false);
			btnTelaCadPessoas.setEnabled(false);
		}
		
		//Operador não pode cadastrar PessoaPerfil nem Status
		if (idPerfil == 3) {
			mntmStatus.setEnabled(false);
			mntmPessoaPerfil.setEnabled(false);
		}
		
		//Outros Perfils: Mesmo do Usuario
		if (idPerfil != 1 & idPerfil != 2 & idPerfil != 3) {
			mntmPessoaCadastro.setEnabled(false);
			mntmStatus.setEnabled(false);
			mntmPessoaPerfil.setEnabled(false);
			mntmHistoria.setEnabled(false);
			
			btnGerenciaHistoria.setEnabled(false);
			btnTelaCadPessoas.setEnabled(false);
		}
	
		/*
		private JButton btnGerenciaHistoria;
		private JButton btnGerenciaEstoque;
		private JButton btnGerenciaRoupa;
		private JButton btnTelaCadRoupas;
		private JButton btnCadastroDoaes;
		private JButton btnTelaCadPessoas;
		private JMenuItem mntmStatus;
		private JMenuItem mntmRoupasCAD;
		private JMenuItem mntmPessoaPerfil;
		private JMenuItem mntmDoacaoCadastro;
		private JMenuItem mntmPessoaCadastro;
		private JMenuItem mntmHistoria;
		private JMenuItem mntmEstoque;
		private JMenuItem mntmRoupasGerencia;
		*/
	}
}
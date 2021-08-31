package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EstoqueDAO;
import dao.ItensDoadosDAO;
import dao.RoupasDAO;
import models.Estoque;
import models.ItensDoados;
import models.Roupas;
import util.UtilMascara;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class ViewInfoGerenciaRoupa extends JFrame {

	private JPanel contentPane;
	public JTextField itemDoado;
	public JTextField dataDoacao;
	public JTextField idItemDoado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewInfoGerenciaRoupa frame = new ViewInfoGerenciaRoupa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewInfoGerenciaRoupa() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewInfoDoacao.class.getResource("/imagens/estoque.png")));
		setTitle("Alterar Roupas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 351, 232);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item doado");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 314, 27);
		contentPane.add(lblNewLabel);
		
		itemDoado = new JTextField();
		itemDoado.setFont(new Font("Arial Black", Font.BOLD, 11));
		itemDoado.setBounds(10, 49, 314, 27);
		contentPane.add(itemDoado);
		itemDoado.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Data");
		lblQuantidade.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblQuantidade.setBounds(10, 101, 63, 27);
		contentPane.add(lblQuantidade);
		
		dataDoacao = new JFormattedTextField(UtilMascara.add("dat"));
		dataDoacao.setFont(new Font("Arial Black", Font.BOLD, 11));
		dataDoacao.setColumns(10);
		dataDoacao.setBounds(70, 101, 126, 27);
		contentPane.add(dataDoacao);
		
		JButton editar = new JButton("Editar");
		editar.setBackground(Color.ORANGE);
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				
				RoupasDAO roupasDAO = new RoupasDAO();
				Roupas atualizaRoupa = roupasDAO.select(Roupas.class, Integer.parseInt(idItemDoado.getText()));
				
				atualizaRoupa.setTitulo(itemDoado.getText());
				atualizaRoupa.setDataHoraCadastro(dataDoacao.getText());
				//atualizaRoupa.setstatus(null);
				roupasDAO.update(atualizaRoupa);			

				JOptionPane.showInternalMessageDialog(null, "Item atualizado com sucesso!");
			
			}
		});
		editar.setFont(new Font("Arial Black", Font.BOLD, 18));
		editar.setBounds(225, 156, 99, 27);
		contentPane.add(editar);
		
		idItemDoado = new JTextField();
		idItemDoado.setFont(new Font("Arial Black", Font.BOLD, 11));
		idItemDoado.setBounds(10, 161, 86, 20);
		contentPane.add(idItemDoado);
		idItemDoado.setColumns(10);
		idItemDoado.setVisible(false);
		
		setLocationRelativeTo(null);
	}
}

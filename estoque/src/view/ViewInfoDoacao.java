package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EstoqueDAO;
import dao.ItensDoadosDAO;
import models.Estoque;
import models.ItensDoados;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class ViewInfoDoacao extends JFrame {

	private JPanel contentPane;
	public JTextField tipoItemDoado;
	public JTextField qtddItem;
	public JTextField idItemDoado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewInfoDoacao frame = new ViewInfoDoacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewInfoDoacao() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewInfoDoacao.class.getResource("/imagens/estoque.png")));
		setTitle("Alterar Historia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 351, 232);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tipo de item doado");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 314, 27);
		contentPane.add(lblNewLabel);
		
		tipoItemDoado = new JTextField();
		tipoItemDoado.setFont(new Font("Arial Black", Font.BOLD, 11));
		tipoItemDoado.setBounds(10, 49, 314, 27);
		contentPane.add(tipoItemDoado);
		tipoItemDoado.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblQuantidade.setBounds(10, 101, 124, 27);
		contentPane.add(lblQuantidade);
		
		qtddItem = new JTextField();
		qtddItem.setFont(new Font("Arial Black", Font.BOLD, 11));
		qtddItem.setColumns(10);
		qtddItem.setBounds(134, 101, 190, 27);
		contentPane.add(qtddItem);
		
		JButton editar = new JButton("Editar");
		editar.setBackground(Color.ORANGE);
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItensDoadosDAO itensAtualizaDAO = new ItensDoadosDAO();
								
				ItensDoados atualizaItem = itensAtualizaDAO.select(ItensDoados.class, Integer.parseInt(idItemDoado.getText()));
				atualizaItem.setNomeItem(tipoItemDoado.getText());
				atualizaItem.setQuantidade(Integer.parseInt(qtddItem.getText()));
				//atualizaItem.setstatus(null);
							
				itensAtualizaDAO.update(atualizaItem);
				
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

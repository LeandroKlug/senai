package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PessoaDAO;
import models.Pessoa;
import util.Seguranca;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ViewAlterarSenha extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField pfSenhaAtual;
	private JPasswordField pfNovaSenha;
	private JPasswordField pfNovaSenhaConf;

	public ViewAlterarSenha(Pessoa pessoaLogadaAlterar) {
		setTitle("Alterar Senha");
		
		Pessoa pAlterar = pessoaLogadaAlterar;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 328);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Senha atual");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 184, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNovaSenha = new JLabel("Nova senha");
		lblNovaSenha.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNovaSenha.setBounds(10, 80, 137, 25);
		contentPane.add(lblNovaSenha);
		
		JLabel lblConfirmaoSenha = new JLabel("Confirma\u00E7\u00E3o senha");
		lblConfirmaoSenha.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblConfirmaoSenha.setBounds(10, 160, 222, 25);
		contentPane.add(lblConfirmaoSenha);
		
		pfSenhaAtual = new JPasswordField();
		pfSenhaAtual.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pfSenhaAtual.setBounds(10, 38, 414, 40);
		contentPane.add(pfSenhaAtual);
		pfSenhaAtual.setColumns(10);
		
		pfNovaSenha = new JPasswordField();
		pfNovaSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pfNovaSenha.setColumns(10);
		pfNovaSenha.setBounds(10, 109, 414, 40);
		contentPane.add(pfNovaSenha);
		
		pfNovaSenhaConf = new JPasswordField();
		pfNovaSenhaConf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pfNovaSenhaConf.setColumns(10);
		pfNovaSenhaConf.setBounds(10, 188, 414, 40);
		contentPane.add(pfNovaSenhaConf);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(Color.GREEN);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String senhaAtual = pAlterar.getSenha().toString();
				String senhaDigitada = Seguranca.geraSenhaCriptografada(pfSenhaAtual.getText()).toString();
				
				boolean senhaAtualOk = senhaAtual.equals(senhaDigitada);
				
				// 1. Verificar se a senha digitada é a do usuário
				if(!senhaAtualOk) {
					JOptionPane.showMessageDialog(null, "Senha atual não confere", "Alerta do sistema", JOptionPane.INFORMATION_MESSAGE);
				}
				
				boolean senhasIguaisOk = (pfNovaSenha.getText().equals(pfNovaSenhaConf.getText())) 
										 && 
										 (pfNovaSenha.getText().length() >= 6);
				
				// 2. Comparar se senha e confirmação são iguais
				if(!senhasIguaisOk) {
					JOptionPane.showMessageDialog(null, "As senhas digitadas são diferentes ou não tem pelo menos 6 caracteres", "Alerta do sistema", JOptionPane.INFORMATION_MESSAGE);
				}
				
				// 3. Atualizar a senha
				if(senhaAtualOk && senhasIguaisOk) {
					
					// Pessoa com a senha atualizada
					pAlterar.setSenha(Seguranca.geraSenhaCriptografada(pfNovaSenha.getText()));
					
					// atualizar no banco de dados
					PessoaDAO pAtualizaSenha = new PessoaDAO();
					pAtualizaSenha.update(pAlterar);
					
					if(senhaAtual.equals(senhaDigitada)) {
						JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!", "Alerta do sistema", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				}
			}
		});
		btnSalvar.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnSalvar.setBounds(157, 239, 129, 40);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.ORANGE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnCancelar.setBounds(295, 239, 129, 40);
		contentPane.add(btnCancelar);
	}
}

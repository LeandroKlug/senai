package dao;

import javax.persistence.Query;

import models.Pessoa;

public class PessoaDAO extends DAOModel<Pessoa> {
	
	public boolean salvar(Pessoa p1) {
			
		if(p1.getId() > 0) {
			// já está no banco de dados
			this.update(p1);
		}else {
			this.insert(p1);
		}
		return true;
	}	
	
	// consulta uma pessoa no banco de dados com base no e-mail e senha
	public Pessoa efetuaLogin(String emailF, String senhaF) {
	        
        Pessoa p = new Pessoa();
	    try {
	        p = (Pessoa) conexaoAtual()
	            .createQuery("SELECT p FROM Pessoa p where p.email = ?1 AND p.senha = ?2")
	            .setParameter(1, emailF)
	            .setParameter(2, senhaF)
	            .getSingleResult();
	    }catch(Exception e) {
	        System.out.println(e.getMessage());
	    }
	    return p;
	}
	
	// Pesquisar nas pessoas se existe alguma utilizando o atributo com o valor informado
	// Retornar false caso está em uso e true, caso esteja disponível
	
	public boolean verificaAtributoEmUsoPessoa(String atributo, String valor) {
        
        Pessoa p = new Pessoa();
	    try {
	        p = (Pessoa) conexaoAtual()
	            .createQuery("SELECT p FROM Pessoa p where p."+atributo+" = ?1")
	            .setParameter(1, valor)
	            .getSingleResult();
	    }catch(Exception e) {
	        System.out.println(e.getMessage());
	    }
	    if(p.getId() > 0){
	    	return false; // está em uso
	    }else {
	    	return true; // está disponível
	    }
	}

	public String getQtdeUsuariosTipoPerfil(long tpPerfil) {

		Query consulta = conexaoAtual()
				.createQuery("SELECT count(p) FROM Pessoa p where p.tipoPerfil = "+tpPerfil);

		return consulta.getSingleResult().toString(); // qtde
	}
}
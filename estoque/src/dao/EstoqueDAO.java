package dao;

import java.util.List;

import javax.persistence.Query;

import models.Estoque;
import models.Roupas;

public class EstoqueDAO  extends DAOModel<Estoque> {

	public boolean alterar(Estoque estoque1) {
		
		if(estoque1.getId() > 0) {
			// já está no banco de dados
			this.update(estoque1);
		}
		return true;
	}	
	
	public String getQtdeEstoquePorStatus(long status) {
		
		Query consulta = conexaoAtual()
			.createQuery("SELECT count(r) FROM Estoque r where r.status = "+status);
		
		return consulta.getSingleResult().toString(); // qtde
	}

	@SuppressWarnings("unchecked")
	public List<Estoque> selectAllComFiltro(String campo, String parametro){
		return (List<Estoque>) conexaoAtual()
			   .createQuery("SELECT p from Estoque p where p."+campo+"="+parametro)
			   .getResultList();
	}
}

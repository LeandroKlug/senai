package dao;

import java.util.List;

import javax.persistence.Query;

import models.ItensDoados;
import models.Pessoa;
import models.Roupas;

public class ItensDoadosDAO extends DAOModel<ItensDoados> {

	public boolean salvar(ItensDoados d1) {
		
		if(d1.getId() > 0) {
			// já está no banco de dados
			this.update(d1);
		}else {
			this.insert(d1);
		}
		return true;
	}
	
	public String getQtdeItensDoadosPorStatus(long status) {
		
		Query consulta = conexaoAtual()
			.createQuery("SELECT count(r) FROM ItensDoados r where r.status = "+status);
		
		return consulta.getSingleResult().toString(); // qtde
	}

	@SuppressWarnings("unchecked")
	public List<ItensDoados> selectAllComFiltro(String campo, String parametro){
		return (List<ItensDoados>) conexaoAtual()
			   .createQuery("SELECT p from ItensDoados p where p."+campo+"="+parametro)
			   .getResultList();
	}
}

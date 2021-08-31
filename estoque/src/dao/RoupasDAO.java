package dao;

import java.util.List;
import javax.persistence.Query;
import models.Roupas;

public class RoupasDAO extends DAOModel<Roupas> {

	public String getQtdeRoupasPorStatus(long status) {
		
		Query consulta = conexaoAtual()
				//Roupas e um model, escrever jeito correto
			.createQuery("SELECT count(r) FROM Roupas r where r.status = "+status);
		
		return consulta.getSingleResult().toString(); // qtde
	}

	@SuppressWarnings("unchecked")
	public List<Roupas> selectAllComFiltro(String campo, String parametro){
		return (List<Roupas>) conexaoAtual()
			   .createQuery("SELECT p from Roupas p where p."+campo+"="+parametro)
			   .getResultList();
	}
}
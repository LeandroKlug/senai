package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// classe faz a conexão com o banco de dados
// posso obter a conexão que está aberta e utilizar conforme a necessidade

public abstract class UtilConexaoJPA {
	
	private static EntityManagerFactory entitymanager;
	
	public static EntityManagerFactory abreConexaoComBanco() {
		
		if(entitymanager == null) {
			entitymanager = Persistence.createEntityManagerFactory("doacoesPU");
		}
		return entitymanager;
	}
}
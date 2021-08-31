package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class DAOModel<E> implements DAO<E> {
	
	// retornar a conex�o com o banco
	public EntityManager conexaoAtual() {
		EntityManagerFactory emf = UtilConexaoJPA.abreConexaoComBanco(); // conex�o	
		return emf.createEntityManager();
	}

	// M�todo GEN�RICO para inserir um objeto do tipo E 
	public E insert(E objeto) {
		EntityManager em = conexaoAtual();
		em.getTransaction().begin();
		em.persist(objeto); // aluno, pessoa, qualquer entidade!
		em.getTransaction().commit();
		em.close();		
		return objeto;
	}
	
	// M�todo GEN�RICO para fazer consultas pelo c�digo
	public E select (Class<E> c, long codigo) {
		E objeto = conexaoAtual().find(c, codigo);
		return objeto;
	}
	
	public E update(E objeto) {
		EntityManager em = conexaoAtual();
		em.getTransaction().begin();
		objeto = em.merge(objeto);
		em.getTransaction().commit();
		em.close();	
		return objeto;
	}
	
	public 	E delete (Class<E> c, long codigo) {
		// busco o objeto que quero excluir
		EntityManager em = conexaoAtual();
		E objeto = em.find(c, codigo);
		em.getTransaction().begin();
		em.remove(objeto);
		em.getTransaction().commit();
		em.close();
		return objeto;
	}
	
	@SuppressWarnings("unchecked")
	public List<E> selectAll(Class<E> c){
		return (List<E>) conexaoAtual()
			   .createQuery("SELECT t from "+c.getSimpleName() + " t")
			   .getResultList();
	}
}

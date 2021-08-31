package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class DAOModel<E> implements DAO<E> {
	
	// retornar a conexão com o banco
	public EntityManager conexaoAtual() {
		EntityManagerFactory emf = UtilConexaoJPA.abreConexaoComBanco(); // conexão	
		return emf.createEntityManager();
	}

	// Método GENÉRICO para inserir um objeto do tipo E 
	public E insert(E objeto) {
		EntityManager em = conexaoAtual();
		em.getTransaction().begin();
		em.persist(objeto); // aluno, pessoa, qualquer entidade!
		em.getTransaction().commit();
		em.close();		
		return objeto;
	}
	
	// Método GENÉRICO para fazer consultas pelo código
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

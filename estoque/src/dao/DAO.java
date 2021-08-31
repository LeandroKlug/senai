package dao;

import java.util.List; // 1 objeto-> 10 registros -> 10 objetos

public interface DAO<E> {
	
	E insert (E objeto) throws Exception;
	
	E select (Class<E> c, long codigo) throws Exception;
	
	E update (E objeto) throws Exception;
	
	E delete (Class<E> c, long codigo) throws Exception;

	List<E> selectAll(Class<E> c) throws Exception;
	
}
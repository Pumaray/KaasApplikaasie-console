package rsvier.dao;

public interface PersistenceDAO<K,E> {
	
	public E save(E e);
	
	public boolean remove(E e);
	
	public E findById(K id);
	
	public E merge(E e );

}

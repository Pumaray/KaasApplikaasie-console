package rsvier.dao;

public interface PersistenceDAO<E> {
	
	public E save(E e);
	
	public boolean remove(E e);
	
	public E findById(long id);
	
	public E merge(E e );

}

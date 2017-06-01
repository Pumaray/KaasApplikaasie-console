package rsvier.view;

public interface Screen<E> {

	public void show();

	public void setBean(E bean);

	public E getBean();

}

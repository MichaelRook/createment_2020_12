package nl.prolector.cursus.java.io.streams.opdrachten.bank;

public interface Memento <T> {
	
	public T getState();
	public void setState(T aState);
}

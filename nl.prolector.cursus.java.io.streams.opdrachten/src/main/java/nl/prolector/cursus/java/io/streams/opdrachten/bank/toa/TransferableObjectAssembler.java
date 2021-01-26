package nl.prolector.cursus.java.io.streams.opdrachten.bank.toa;

public interface TransferableObjectAssembler <A ,B > {

	B convert(A a);
}

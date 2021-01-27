package nl.prolector.cursus.java.io.streams.opdrachten.bank.toa;

import java.util.function.Function;

public interface TransferableObjectAssembler <A ,B > extends Function<A,B>{

	B convert(A a);
	
	@Override
	default B apply(A a) {
		return convert(a);
	}
}

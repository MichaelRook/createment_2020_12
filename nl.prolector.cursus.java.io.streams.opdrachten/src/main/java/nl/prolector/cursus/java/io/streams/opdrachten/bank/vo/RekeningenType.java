package nl.prolector.cursus.java.io.streams.opdrachten.bank.vo;

public enum RekeningenType {
	
	Spaar,Courant;
	
	
	public static RekeningenType fromType(Class <?> aType) {
		RekeningenType type;
		
		if(aType == CourantRekeningMemento.class) {
			type = Courant;
		} else if (aType == SpaarRekeningMemento.class) {
			type = Spaar;
		} else {
			throw new AssertionError("Dumbass");
		}
		
		return type;
	};

}

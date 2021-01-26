package nl.prolector.cursus.java.io.streams.opdrachten.bank.vo;

import java.io.Serializable;

public final class Pinpas  implements Serializable, Comparable<Pinpas>{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final BankRekeningMemento aMemento;
	private final String bankNaam;

	
	
	
	public Pinpas(BankRekeningMemento aMemento, String bankNaam) {
		super();
		this.aMemento = aMemento;
		this.bankNaam = bankNaam;
	}




	@Override
	public int compareTo(Pinpas o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}

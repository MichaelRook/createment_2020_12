package nl.prolector.cursus.java.io.streams.opdrachten.bank.vo;

import java.io.Serializable;

public abstract class BankRekeningMemento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String houder;
	private final double saldo;
	private final int rekeningnummer;
	
	
	/**
	 * Creates a Memento object which can be used to set the state of a {@link AbstractBankRekeningEntity} object 
	 * 
	 * @param houder
	 * @param saldo
	 * @param rekeningnummer
	 */
	protected BankRekeningMemento(String houder, double saldo, int rekeningnummer) {
		this.houder = houder;
		this.saldo = saldo;
		this.rekeningnummer = rekeningnummer;
	}



	public String getHouder() {
		return houder;
	}



	public double getSaldo() {
		return saldo;
	}



	public int getRekeningnummer() {
		return rekeningnummer;
	}



	@Override
	public String toString() {
		return String.format("BankRekeningMemento [houder=%s, saldo=%s, rekeningnummer=%s]", houder, saldo,
				rekeningnummer);
	}
	
	
}

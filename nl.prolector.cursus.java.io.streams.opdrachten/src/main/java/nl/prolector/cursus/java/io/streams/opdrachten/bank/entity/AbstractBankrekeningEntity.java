package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.Serializable;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.BankRekeningMemento;

public abstract class AbstractBankrekeningEntity<S extends BankRekeningMemento> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String houder;
	private double saldo;
	private int rekeningnummer;
	// private static int nieuwRekeningnummer = 1000;
	private final static int PRIME = 59;

	/**
	 * Constructor used for creating empty bankrekeningen of type spaar or courant. An empty bankrekening
	 * Object can subsequently be filled using a {@link nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.BankRekeningMemento} instance
	 * using the method {@link #setState(BankRekeningMemento)}.
	 */
	public AbstractBankrekeningEntity() {
	}
	
	

	public AbstractBankrekeningEntity(String houder, double saldo, int rekeningnummer) {
		this.houder = houder;
		this.saldo = saldo;
		this.rekeningnummer = rekeningnummer;

	}

	/**
	 * Retrieve houder name from bankRekening Object
	 * @return a String
	 */
	
	public String getHouder() {
		return this.houder;
	}

	/**
	 * Retrieve saldo value from bankRekening Object
	 * @return a double
	 */
	
	public double getSaldo() {
		return this.saldo;
	}

	public int getRekeningnummer() {
		return this.rekeningnummer;
	}

	public void stort(double bedrag) {
		assert saldo > 0.0 : "Saldo is negatief";
		if (bedrag < 0.0) {
			throw new IllegalArgumentException("Bedrag mag niet negatief zijn");
		}
		this.saldo += bedrag;
	}

	
	//TODO: zorg voor fout exceptie beter. 
	public void neemOp(double bedrag) throws Exception {
		assert saldo > 0.0 : "Saldo is negatief";
		if (bedrag < 0.0) {
			throw new IllegalArgumentException("Bedrag mag niet negatief zijn");
		}
		if (this.saldo - bedrag >= 0.0) {
			this.saldo -= bedrag;
		} else {
			throw new Exception();
		}
	}

	
	public void print() {
		System.out.printf("----------------------------------------%n");
		System.out.printf("Naam  : %s%n", this.houder);
		System.out.printf("Saldo : %.2f%n", this.saldo);
		System.out.printf("Nummer: %d%n", this.rekeningnummer);
	}

	

	void setState(S inputState) {
		this.houder = inputState.getHouder();
		this.rekeningnummer = inputState.getRekeningnummer();
		this.saldo = inputState.getSaldo();
	}

	@Override
	public String toString() {
		return String.format("Bankrekening [houder=%s, saldo=%s, rekeningnummer=%s]", houder, saldo, rekeningnummer);
	}

	abstract S getState();
	
	public int hashCode() {
		int value = AbstractBankrekeningEntity.PRIME;
		value *= this.getRekeningnummer();
		value *= this.getHouder().hashCode();
		return value;
	}

}

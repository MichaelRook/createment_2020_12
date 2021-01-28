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
	 * Constructor used for creating empty Bankrekeningen of type spaar or courant. An empty Bankrekening
	 * object can subsequently be filled using a {@link BankRekeningMemento} instance
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
	
	/**
	 * Retrieve BankRekening nummer from  this BankRekening
	 * @return an Integer
	 */

	public int getRekeningnummer() {
		return this.rekeningnummer;
	}

	/**
	 * Allows for depositing a non-negative bedrag into this Bankrekening
	 * @param bedrag
	 * @throws IllegalArgumentException If provided bedrag is negative
	 */
	
	public void stort(double bedrag) {
		assert saldo > 0.0 : "Saldo is negatief";
		if (bedrag < 0.0) {
			throw new IllegalArgumentException("Bedrag mag niet negatief zijn");
		}
		this.saldo += bedrag;
	}

	
	/**
	 * Allows for withdrawing a non-negative bedrag from this Bankrekening
	 * @param bedrag
	 * @throws Exception If remaining bedrag after withdrawl would be below 0.0.
	 * 
	 */ 
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

	
	/**
	 * Prints the naam, saldo and nummer of this Bankrekening to the console;
	 * 
	 */
	
	public void print() {
		System.out.printf("----------------------------------------%n");
		System.out.printf("Naam  : %s%n", this.houder);
		System.out.printf("Saldo : %.2f%n", this.saldo);
		System.out.printf("Nummer: %d%n", this.rekeningnummer);
	}

	
	/**
	 * Uses a {@link BankRekeningMemento} in order to
	 * set the states (naam houder, rekeningnummer & saldo ) of this bankrekening object.
	 * @param inputState
	 */
	void setState(S inputState) {
		this.houder = inputState.getHouder();
		this.rekeningnummer = inputState.getRekeningnummer();
		this.saldo = inputState.getSaldo();
	}

	@Override
	public String toString() {
		return String.format("Bankrekening [houder=%s, saldo=%s, rekeningnummer=%s]", houder, saldo, rekeningnummer);
	}

	/**
	 * Retrieve the state of the current bankrekening (naam houder, rekeningnummer & saldo ) using a {@linkplain nl.prolector.crusus.java.io.streams.opdrachten.bank.vo.BankRekeningMemento}
	 * @return a {@link BankRekeningMemento}
	 */
	abstract S getState();
	
	/**
	 * creates hashCode based on prime value, nummer and houder. 
	 */
	public int hashCode() {
		int value = AbstractBankrekeningEntity.PRIME;
		value *= this.getRekeningnummer();
		value *= this.getHouder().hashCode();
		return value;
	}
	
	/**
	 * checks if two AbstractBankrekeningEntity instances are equal. 
	 * returns true if super.equals is false, if obj is a bankrekening instance, and if all fields are equal.'
	 * fields: houder, rekeningnummer, hashcode.
	 */
	@Override
	public  boolean equals(Object obj) {
		boolean equals = super.equals(obj);
		if (!equals && obj instanceof AbstractBankrekeningEntity) {
			final AbstractBankrekeningEntity<?> that = (AbstractBankrekeningEntity<?>) obj;
			equals = this.getHouder().equals(that.getHouder()) 
					&& this.getRekeningnummer() == that.getRekeningnummer()
					&& this.hashCode() == that.hashCode();
		}
		return equals;

	}

}

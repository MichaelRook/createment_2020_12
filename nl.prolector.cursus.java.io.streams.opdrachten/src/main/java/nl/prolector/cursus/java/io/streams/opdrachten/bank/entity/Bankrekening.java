package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.PrintWriter;
import java.io.Serializable;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.BankRekeningMemento;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.OnvoldoendeSaldoException;

public abstract class Bankrekening<S extends BankRekeningMemento> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String houder;
	private double saldo;
	private int rekeningnummer;
	// private static int nieuwRekeningnummer = 1000;

	public Bankrekening() {
	}

	public Bankrekening(String houder, double saldo, int rekeningnummer) {
		this.houder = houder;
		this.saldo = saldo;
		this.rekeningnummer = rekeningnummer;

	}

	public String getHouder() {
		return this.houder;
	}

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

	public void neemOp(double bedrag) throws OnvoldoendeSaldoException {
		assert saldo > 0.0 : "Saldo is negatief";
		if (bedrag < 0.0) {
			throw new IllegalArgumentException("Bedrag mag niet negatief zijn");
		}
		if (this.saldo - bedrag >= 0.0) {
			this.saldo -= bedrag;
		} else {
			throw new OnvoldoendeSaldoException(this);
		}
	}

	public void print() {
		System.out.printf("----------------------------------------%n");
		System.out.printf("Naam  : %s%n", this.houder);
		System.out.printf("Saldo : %.2f%n", this.saldo);
		System.out.printf("Nummer: %d%n", this.rekeningnummer);
	}

	public void save(PrintWriter out) {

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

}

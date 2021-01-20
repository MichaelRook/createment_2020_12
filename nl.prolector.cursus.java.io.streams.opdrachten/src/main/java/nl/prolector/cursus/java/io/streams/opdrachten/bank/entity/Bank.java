package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeMap;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.BankrekeningBestaatNietException;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.OnvoldoendeSaldoException;

public class Bank implements Serializable, Iterable<Bankrekening<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeMap<Integer, Bankrekening<?>> rekeningen;
	private final String naam;
	public final static int EERSTE_REKENINGNR = 1000;
	private transient int laatsteRekeningnr = Bank.EERSTE_REKENINGNR;

	private int getNewRekeningnr() {
		this.laatsteRekeningnr++;
		return laatsteRekeningnr;
	};

	void setLaatsteRekeningNr(int laatsteNr) {
		this.laatsteRekeningnr = laatsteNr;
	}

	public Bank(String aNaam) {
		if (aNaam == null) {
			throw new IllegalArgumentException("Invalid name given");
		}
		this.rekeningen = new TreeMap<Integer, Bankrekening<?>>();
		this.naam = aNaam;
	}

	public int openSpaarRekening(String houder, double saldo) {
		checkSaldo(saldo);
		int newRekeningNr = this.getNewRekeningnr();
		Spaarrekening myNewRekening = new Spaarrekening(houder, saldo, newRekeningNr);
		this.voegRekeningToe(myNewRekening);
		return newRekeningNr;
	}

	public int openRekeningCourant(String houder, double saldo) {
		checkSaldo(saldo);
		int newRekeningNr = this.getNewRekeningnr();
		RekeningCourant myNewRekening = new RekeningCourant(houder, saldo, newRekeningNr);
		this.voegRekeningToe(myNewRekening);
		return newRekeningNr;
	}

	private void checkSaldo(double saldo) {
		if (saldo < 0.0) {
			throw new IllegalArgumentException("Saldo mag niet negatief zijn");
		}
	}

	public int getRekeningNr(String houder) {
		for (Bankrekening<?> b : this.rekeningen.values()) {
			if (houder.equals(b.getHouder())) {
				return b.getRekeningnummer();
			}
		}
		return -1;
	}

	public double getSaldo(int rekeningNr) throws BankrekeningBestaatNietException {
		return this.getRekening(rekeningNr).getSaldo();
	}

	public void neemOp(int rekeningNr, double bedrag)
			throws OnvoldoendeSaldoException, BankrekeningBestaatNietException {
		this.getRekening(rekeningNr).neemOp(bedrag);
	}

	public void stort(int rekeningNr, double bedrag) throws BankrekeningBestaatNietException {
		this.getRekening(rekeningNr).stort(bedrag);
	}

	public void opheffenRekening(int rekeningNr) {
		this.rekeningen.remove(rekeningNr);
	}

	public void overschrijving(int vanRekeningNr, int naarRekeningNr, double bedrag)
			throws OnvoldoendeSaldoException, BankrekeningBestaatNietException {
		this.neemOp(vanRekeningNr, bedrag);
		this.stort(naarRekeningNr, bedrag);
	}

	public void print() {
		for (Bankrekening<?> b : this.rekeningen.values()) {
			b.print();
		}
	}

	// Let Op: private Alleen voor gebruik binnen de class Bank
	void voegRekeningToe(Bankrekening<?> rekening) {
		this.rekeningen.put(rekening.getRekeningnummer(), rekening);
	}

	// Let Op: private Alleen voor gebruik binnen de class Bank
	private Bankrekening<?> getRekening(int rekeningNr) throws BankrekeningBestaatNietException {
		if (!this.rekeningen.containsKey(rekeningNr)) {
			throw new BankrekeningBestaatNietException(rekeningNr);
		}
		return this.rekeningen.get(rekeningNr);
	}

	public String getNaam() {
		return this.naam;
	}


	private void readObject(java.io.ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
		inputStream.defaultReadObject();
		
		if(this.rekeningen.isEmpty()) {
			this.laatsteRekeningnr = Bank.EERSTE_REKENINGNR;
		}else {
			this.laatsteRekeningnr = this.rekeningen.lastKey();
		}
		
		
	}

	private void writeObject(java.io.ObjectOutputStream outputStream) throws IOException {
		outputStream.defaultWriteObject();
	}

	@Override
	public Iterator<Bankrekening<?>> iterator() {
		return this.rekeningen.values().iterator();
	}
}

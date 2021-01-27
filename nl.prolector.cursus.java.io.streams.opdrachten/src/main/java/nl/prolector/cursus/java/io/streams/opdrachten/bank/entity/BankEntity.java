package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class BankEntity implements Serializable, Iterable<AbstractBankrekeningEntity<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeMap<Integer, AbstractBankrekeningEntity<?>> rekeningen;
	private final String naam;
	public final static int EERSTE_REKENINGNR = 1000;
	private transient int laatsteRekeningnr = BankEntity.EERSTE_REKENINGNR;

	private int getNewRekeningnr() {
		this.laatsteRekeningnr++;
		return laatsteRekeningnr;
	};

	void setLaatsteRekeningNr(int laatsteNr) {
		this.laatsteRekeningnr = laatsteNr;
	}

	private static final String BANK_NAAM_PATTERN_REGEX = "[A-Z][a-zA-Z\\s-]{0,69}";
	private static final Pattern BANK_NAAM_PATTERN = Pattern.compile(BankEntity.BANK_NAAM_PATTERN_REGEX);

	public BankEntity(String aNaam) {
		if (aNaam == null) {
			throw new IllegalArgumentException("Invalid name given");
		}
		if(!BankEntity.BANK_NAAM_PATTERN.matcher(aNaam).matches()) {
			String msg = String.format("The banknaam '%s' does not match the regex %s",aNaam,BankEntity.BANK_NAAM_PATTERN_REGEX);
					
			throw new IllegalArgumentException(msg);
		}
		this.rekeningen = new TreeMap<Integer, AbstractBankrekeningEntity<?>>();
		this.naam = aNaam;
	}

	public int openSpaarRekening(String houder, double saldo) {
		checkSaldo(saldo);
		int newRekeningNr = this.getNewRekeningnr();
		SpaarrekeningEntity myNewRekening = new SpaarrekeningEntity(houder, saldo, newRekeningNr);
		this.voegRekeningToe(myNewRekening);
		return newRekeningNr;
	}

	public int openRekeningCourant(String houder, double saldo) {
		checkSaldo(saldo);
		int newRekeningNr = this.getNewRekeningnr();
		RekeningCourantEntity myNewRekening = new RekeningCourantEntity(houder, saldo, newRekeningNr);
		this.voegRekeningToe(myNewRekening);
		return newRekeningNr;
	}

	private void checkSaldo(double saldo) {
		if (saldo < 0.0) {
			throw new IllegalArgumentException("Saldo mag niet negatief zijn");
		}
	}

	public int getRekeningNr(String houder) {
		for (AbstractBankrekeningEntity<?> b : this.rekeningen.values()) {
			if (houder.equals(b.getHouder())) {
				return b.getRekeningnummer();
			}
		}
		return -1;
	}

	public double getSaldo(int rekeningNr) throws Exception {
		return this.getRekening(rekeningNr).getSaldo();
	}

	public void neemOp(int rekeningNr, double bedrag) throws Exception {
		this.getRekening(rekeningNr).neemOp(bedrag);
	}

	public void stort(int rekeningNr, double bedrag) throws Exception {
		this.getRekening(rekeningNr).stort(bedrag);
	}

	public void opheffenRekening(int rekeningNr) {
		this.rekeningen.remove(rekeningNr);
	}

	public void overschrijving(int vanRekeningNr, int naarRekeningNr, double bedrag) throws Exception {
		this.neemOp(vanRekeningNr, bedrag);
		this.stort(naarRekeningNr, bedrag);
	}

	public void print() {
		for (AbstractBankrekeningEntity<?> b : this.rekeningen.values()) {
			b.print();
		}
	}

	// Let Op: private Alleen voor gebruik binnen de class Bank
	void voegRekeningToe(AbstractBankrekeningEntity<?> rekening) {
		this.rekeningen.put(rekening.getRekeningnummer(), rekening);
	}

	// Let Op: private Alleen voor gebruik binnen de class Bank
	private AbstractBankrekeningEntity<?> getRekening(int rekeningNr) throws Exception {
		if (!this.rekeningen.containsKey(rekeningNr)) {
			throw new Exception();
		}
		return this.rekeningen.get(rekeningNr);
	}

	public String getNaam() {
		return this.naam;
	}

	private void readObject(java.io.ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
		inputStream.defaultReadObject();

		if (this.rekeningen.isEmpty()) {
			this.laatsteRekeningnr = BankEntity.EERSTE_REKENINGNR;
		} else {
			this.laatsteRekeningnr = this.rekeningen.lastKey();
		}

	}

	private void writeObject(java.io.ObjectOutputStream outputStream) throws IOException {
		outputStream.defaultWriteObject();
	}

	@Override
	public Iterator<AbstractBankrekeningEntity<?>> iterator() {
		return this.rekeningen.values().iterator();
	}
	
	@Override
	public int hashCode() {
		return this.getNaam().hashCode();
	}
}

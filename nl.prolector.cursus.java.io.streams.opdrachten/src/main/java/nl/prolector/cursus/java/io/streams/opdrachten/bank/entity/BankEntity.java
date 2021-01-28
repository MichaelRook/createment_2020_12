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

	
	/**
	 * Creates an instance of a bankEntity.
	 * @param aNaam Must start with a captial letter, may not contain numbers and can have a maximum length of 70 characters.
	 */
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

	/**
	 * Adds a spaarRekeningEntity with the provided houder name and saldo to this bankEntity. Can be retrieved
	 * from the bank using the {@link #getRekening(int)} message.
	 * @param houder
	 * @param saldo must be a non-negative double
	 * @return The rekening nummer that belongs to the created spaarRekeningEntity
	 */
	public int openSpaarRekening(String houder, double saldo) {
		checkSaldo(saldo);
		int newRekeningNr = this.getNewRekeningnr();
		SpaarrekeningEntity myNewRekening = new SpaarrekeningEntity(houder, saldo, newRekeningNr);
		this.voegRekeningToe(myNewRekening);
		return newRekeningNr;
	}
	
	/**
	 * Adds a rekeningCourantEntity with the provided houder name and saldo to this bankEntity. Can be retrieved
	 * from the bank using the {@link #getRekening(int)} message.
	 * @param houder
	 * @param saldo must be a non-negative double
	 * @return The rekening nummer that belongs to the created rekeningCourantEntity
	 */

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

	/**
	 * Will provide the rekening nummer of the the provided rekening if the houder
	 * has a rekening with this bank. 
	 * @param houder a string
	 * @return A Rekening nummer if houder has a rekening, else -1
	 */
	public int getRekeningNr(String houder) {
		for (AbstractBankrekeningEntity<?> b : this.rekeningen.values()) {
			if (houder.equals(b.getHouder())) {
				return b.getRekeningnummer();
			}
		}
		return -1;
	}

	
	/**
	 * Retrieves the amount of saldo left on the BankRekeningEntity with the corresponding rekeningNumber
	 * @param rekeningNr
	 * @return saldo left on rekening
	 * @throws Exception Raises exception when there the provided rekeningnumber has no corresponding rekening in this bank.
	 */
	public double getSaldo(int rekeningNr) throws Exception {
		return this.getRekening(rekeningNr).getSaldo();
	}
	
	/**
	 * Allows for withdrawing a non-negative bedrag from the Bank rekening belonging to the provided rekening nummer.
	 * @param rekeningNr
	 * @throws Exception If remaining bedrag after withdrawl would be below 0.0.
	 * @throws Exception If there is no Bankrekening with the provided bankrekening nummer within this bank.
	 * 
	 */ 

	public void neemOp(int rekeningNr, double bedrag) throws Exception {
		this.getRekening(rekeningNr).neemOp(bedrag);
	}

	

	/**
	 * Allows for depositing a non-negative bedrag into this Bankrekening
	 * @param bedrag
	 * @throws IllegalArgumentException If provided bedrag is negative
	 * @throws Exception If there is no Bankrekening with the provided bankrekening nummer within this bank.
	 */
	
	public void stort(int rekeningNr, double bedrag) throws Exception {
		this.getRekening(rekeningNr).stort(bedrag);
	}

	/**
	 * Removes the rekening with the the provided rekening nummer from this bank. 
	 * @param rekeningNr
	 * @param bedrag
	 */
	
	public void opheffenRekening(int rekeningNr) {
		this.rekeningen.remove(rekeningNr);
	}

	/**
	 * Transfers a bedrag from a rekening to another rekening within this bank. 
	 * @param vanRekeningNr
	 * @param naarRekeningNr
	 * @param bedrag
	 * @exception If bedrag is negative
	 * @exception If bedrag to be withdrawn from the rekening with rekeningnummer vanRekeningNr is more than the saldo of this rekening
	 * 
	 */
	
	public void overschrijving(int vanRekeningNr, int naarRekeningNr, double bedrag) throws Exception {
		this.neemOp(vanRekeningNr, bedrag);
		this.stort(naarRekeningNr, bedrag);
	}

	/**
	 * prints the rekeningen of the current bank to the console, invokes the function {@link AbstractBankrekeningEntity#print()}
	 * 
	 */
	
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

	
	//TODO remove this function.
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
}

package nl.prolector.cursus.java.io.streams.opdrachten.bank;

public abstract class BankRekeningMemento {

	private final String houder;
	private final double saldo;
	private final int rekeningnummer;
	
	
	
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

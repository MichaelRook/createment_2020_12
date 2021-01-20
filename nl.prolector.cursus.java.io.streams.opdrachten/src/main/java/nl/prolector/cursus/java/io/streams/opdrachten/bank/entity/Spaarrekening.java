package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.OnvoldoendeSaldoException;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.SpaarRekeningMemento;

public class Spaarrekening extends Bankrekening<SpaarRekeningMemento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final double BOETE_RENTE = 10.0;
	private static final double CREDIT_RENTE = 5.0;

	
	public Spaarrekening(String houder, double saldo, int rekeningnummer) {
		super(houder, saldo, rekeningnummer);
	}

	/**
	 * Voor Intern gebruik!!!
	 * 
	 * 
	 */

	Spaarrekening() {

	}

	@Override
	public void stort(double bedrag) {
		super.stort(bedrag + CREDIT_RENTE * bedrag / 100.0);
	}

	@Override
	public void neemOp(double bedrag) throws OnvoldoendeSaldoException {
		try {
			super.neemOp(bedrag + BOETE_RENTE * bedrag / 100.0);
		} catch (OnvoldoendeSaldoException e) {
			throw new OnvoldoendeSaldoException(this, e);
		}
	}

	@Override
	public void print() {
		super.print();
		System.out.printf("Boeterente is %.1f%%%n", BOETE_RENTE);
		System.out.printf("Creditrente is %.1f%%%n", CREDIT_RENTE);
	}

	SpaarRekeningMemento getState() {
		SpaarRekeningMemento aSpaarMement = new SpaarRekeningMemento(this.getHouder(), this.getSaldo(),
				this.getRekeningnummer());
		return aSpaarMement;
	}

	@Override
	void setState(SpaarRekeningMemento inputState) {
		super.setState(inputState);

	}

}

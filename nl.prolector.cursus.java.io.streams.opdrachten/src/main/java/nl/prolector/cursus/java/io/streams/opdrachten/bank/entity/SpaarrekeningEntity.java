package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.SpaarRekeningMemento;

public class SpaarrekeningEntity extends AbstractBankrekeningEntity<SpaarRekeningMemento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final double BOETE_RENTE = 10.0;
	protected static final double CREDIT_RENTE = 5.0;

	
	public SpaarrekeningEntity(String houder, double saldo, int rekeningnummer) {
		super(houder, saldo, rekeningnummer);
	}

	/**
	 * Voor Intern gebruik!!!
	 * 
	 * 
	 * 
	 */

	SpaarrekeningEntity() {

	}

	@Override
	public void stort(double bedrag) {
		super.stort(bedrag + CREDIT_RENTE * bedrag / 100.0);
	}
	

	@Override
	public void neemOp(double bedrag) throws Exception {
		try {
			super.neemOp(bedrag + BOETE_RENTE * bedrag / 100.0);
		} catch (Exception e) {
			throw new Exception();
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
